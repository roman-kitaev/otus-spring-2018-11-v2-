package ru.otus.HW081.shell;


import org.springframework.data.repository.CrudRepository;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.HW081.domain.Author;
import ru.otus.HW081.domain.Book;
import ru.otus.HW081.domain.Genre;
import ru.otus.HW081.domain.HasDescription;
import ru.otus.HW081.repostory.AuthorRepository;
import ru.otus.HW081.repostory.BookRepository;
import ru.otus.HW081.repostory.GenreRepository;

import java.util.*;


@ShellComponent
public class BDShellCommands {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private Invoker invoker;

    public BDShellCommands(AuthorRepository authorRepository,
                           BookRepository bookRepository,
                           GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        invoker = new Invoker();
    }

    @ShellMethod(value = "Show all books",
            key = "show all")
    public void showAll() {
        System.out.println("All books the base has:");
        int i = 1;
        for(Book book : bookRepository.findAll()) {
            System.out.println(i++ + ")" + book.getEntityDescription());
        }
    }

    @ShellMethod(value = "Show books selecting by genre(s) or/and author(s)",
            key = "show by parameters")
    public void showBooksByGenreAndAuthor() {
        List<String> authorList = askAdditionalInfo(authorRepository);

        List<String> genreList = askAdditionalInfo(genreRepository);

        List<Book> bookList = bookRepository.findBooksByAuthorsAndGenres(authorList,
                genreList);

        System.out.println("Your criterias:");
        System.out.println("Authors : " + authorList);
        System.out.println("Genres : " + genreList);

        System.out.println("Books you have chosen:");
        int i = 1;
        for(Book book : bookList) {
            System.out.println(i++ + ")" + book.getEntityDescription());
        }
    }

    @ShellMethod(value = "Add an author.",
            key = "add author")
    public String addAuthor(String argument) {
        return invoker.executeCommand(new AddAuthor(argument));
    }

    @ShellMethod(value = "Add a genre.",
            key = "add genre")
    public String addGenre(String argument) {
        return invoker.executeCommand(new AddGenre(argument));
    }

    @ShellMethod(value = "Add a book.",
            key = "add book")
    public String addBook(String argument) {
        return invoker.executeCommand(new AddBook(argument));
    }

    @ShellMethod(value = "Add a comment to a book.",
            key = "add comment")
    public String addComment() {
        Book book = askBook();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your comment: ");
        String comment = scanner.next();

        bookRepository.addComment(book, comment);
        //or
        /*book.addComment(comment);
        bookRepository.save(book);*/
        return "The comment for '" + book.getTitle() + "' was successfully added";
    }

    @ShellMethod(value = "Show comments for a book.",
            key = "show comments")
    public void showComments() {
        Book book = askBook();

        System.out.println("Comments for book " + book.getTitle() + " :");
        for(String comment : book.getComments()) {
            System.out.println(comment + "\n");
        }
    }

    @ShellMethod("Delete a book by id")
    public String delete() {
        Book book = askBook();
        String title = book.getTitle();
        bookRepository.delete(book);

        return "The book '" + title + "' has been deleted";
    }

    private <T extends HasDescription, U> List<String> askAdditionalInfo(CrudRepository<T, U> repository) {
        Scanner scan = new Scanner(System.in);
        List<T> entityList = (List<T>)repository.findAll();
        List<String> chosenList = new ArrayList<>();
        System.out.println("Please choose a number(s) of entities(s) from the list below: ");
        System.out.println("In case of multiple choice please separate entities by space, e.g.: 3 5 6 10");

        System.out.println("0. NONE");
        for(int i = 0; i < entityList.size(); i++) {
            System.out.println((i + 1) + ". " + entityList.get(i).getEntityDescription());
        }
        boolean correctInput = true;
        do {
            correctInput = true;
            try {
                String inputString = scan.nextLine();
                if(inputString.equals("0")) return chosenList;
                Arrays.stream(inputString.split(" ")).
                        map(Integer::parseInt).
                        map(x -> entityList.get(x - 1)).
                        map(x -> x.getEntityDescription()).
                        forEach(chosenList::add);

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Wrong input! Try again!");
                chosenList = new ArrayList<>();
                correctInput = false;
            }
        } while(!correctInput);
        return chosenList;
    }

    private Book askBook() {
        Map<Integer, Book> bookMap = new HashMap<>();
        int i = 1;
        for(Book book : bookRepository.findAll()) {
            bookMap.put(i++, book);
        }

        int bookId;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose a number of book from the list below:");

        bookMap.forEach((index, book) -> {
            System.out.println(index + ")" + book.getEntityDescription());
        });

        do {
            System.out.println("Please enter valid id:");
            bookId = scan.nextInt();
        }while (!bookMap.containsKey(bookId));

        return bookMap.get(bookId);
    }

    private class AddAuthor implements Command {
        private String argument;

        public AddAuthor(String argument) {
            this.argument = argument;
        }

        @Override
        public String execute() {
            authorRepository.save(new Author(argument));
            return "The author " + argument + " was added to the base!";
        }
    }

    private class AddGenre implements Command {
        private String argument;

        public AddGenre(String argument) {
            this.argument = argument;
        }

        @Override
        public String execute() {
            genreRepository.save(new Genre(argument));
            return "The genre " + argument + " was added to the base!";
        }
    }

    private class AddBook implements Command {
        private String argument;

        public AddBook(String argument) {
            this.argument = argument;
        }

        @Override
        public String execute() {
            Book book = new Book(argument);

            List<String> authorList = askAdditionalInfo(authorRepository);
            for(String author : authorList) {
                book.addAuthor(new Author(author));
            }

            List<String> genreList = askAdditionalInfo(genreRepository);
            for(String genre : genreList) {
                book.addGenre(new Genre(genre));
            }

            bookRepository.save(book);
            return "The book " + argument + " was added to the base!";
        }
    }

    private class Invoker {
        public String executeCommand(Command command) {
            return command.execute();
        }
    }

    private interface Command {
        String execute();
    }
}



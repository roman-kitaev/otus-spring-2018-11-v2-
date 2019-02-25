package ru.otus.HW061.shell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.HW061.domain.*;
import ru.otus.HW061.repository.*;

import java.util.*;


@ShellComponent
public class BDShellCommands {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GenreRepository genreRepository;

    Invoker invoker = new Invoker();

    @ShellMethod("Show all books")
    public void show() {
        System.out.println("All books the base has:");
        for(Book book : bookRepository.getAll()) {
            System.out.println(book.getId() + " . " + book.getEntityDescription());
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
        int bookId = askBookId();
        Book book = bookRepository.getById(bookId);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your comment: ");
        String text = scanner.next();
        Comment comment = new Comment(text);

        comment.setBook(book);
        commentRepository.insert(comment);

        return "The comment for " + book.getTitle() + " was successfully added";
    }

    @ShellMethod(value = "Show comments for a book.",
            key = "show comments")
    public void showComments() {
        int bookId = askBookId();
        Book book = bookRepository.getById(bookId);

        System.out.println("Comments for book " + book.getTitle() + " :");
        for(Comment comment : book.getComments()) {
            System.out.println(comment.getComment() + "\n");
        }
    }

    @ShellMethod("Delete a book by id")
    public String delete() {
        int bookId = askBookId();

        String bookTitle = bookRepository.getById(bookId).getTitle();

        bookRepository.delete(bookId);

        return "The book " + bookTitle + " was deleted";
    }

    private <T extends HasDescription> List<T> ask(MyRepository<T> repository) {
        Scanner scan = new Scanner(System.in);
        List<T> entityList = repository.getAll();
        List<T> chosenList = new ArrayList<>();
        System.out.println("Please choose a number(s) of entities(s) from the list below: ");
        System.out.println("In case of multiple choice please separate entities by space, e.g.: 3 5 6 10");
        for(int i = 0; i < entityList.size(); i++) {
            System.out.println((i + 1) + ". " + entityList.get(i).getEntityDescription());
        }
        boolean correctInput = true;
        do {
            correctInput = true;
            try {
                String inputString = scan.nextLine();
                Arrays.stream(inputString.split(" ")).
                        map(Integer::parseInt).
                        map(x -> entityList.get(x - 1)).
                        forEach(chosenList::add);

            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Wrong input! Try again!");
                chosenList = new ArrayList<>();
                correctInput = false;
            }
        } while(!correctInput);
        return chosenList;
    }

    private int askBookId() {
        Set<Integer> bookIdSet = new HashSet<>();
        Scanner scan = new Scanner(System.in);
        int bookId;

        System.out.println("Please choose a number of book from the list below:");

        for(Book book : bookRepository.getAll()) {
            System.out.println(book.getId() + ". " + book.getEntityDescription());
            bookIdSet.add(book.getId());
        }

        do {
            System.out.println("Please enter valid id:");
            bookId = scan.nextInt();
        }while (!bookIdSet.contains(bookId));

        return bookId;
    }

    private interface Command {
        String execute();
    }

    private class AddAuthor implements Command {
        private String argument;

        public AddAuthor(String argument) {
            this.argument = argument;
        }

        @Override
        public String execute() {
            authorRepository.insert(new Author(argument));
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
            genreRepository.insert(new Genre(argument));
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

            List<Author> authorList = ask(authorRepository);
            for(Author author : authorList) {
                book.addAuthor(author);
            }

            List<Genre> genreList = ask(genreRepository);
            for(Genre genre : genreList) {
                book.addGenre(genre);
            }

            bookRepository.insert(book);
            return "The book " + argument + " was added to the base!";
        }
    }

    private class Invoker {
        public String executeCommand(Command command) {
            return command.execute();
        }
    }
}



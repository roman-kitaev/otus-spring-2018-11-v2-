package ru.otus.HW051.shell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.HW051.dao.AuthorDao;
import ru.otus.HW051.dao.BookDao;
import ru.otus.HW051.dao.GenreDao;
import ru.otus.HW051.domain.Author;
import ru.otus.HW051.domain.Book;
import ru.otus.HW051.domain.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


@ShellComponent
public class BDShellCommands {
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private BookDao bookDao;

    Invoker invoker = new Invoker();

    @ShellMethod("Show all books")
    public void show() {
        System.out.println("All books the base has:");
        for(Book book : bookDao.getAll()) {
            System.out.println(book.getTitle() + " by " + book.getAuthor().getName() +
                    ", it is a " + book.getGenre().getGenre());
        }
    }

    @ShellMethod(value = "Add a book.",
            key = "add book")
    public String addBook(String argument) {
        return invoker.executeCommand(new AddBook(argument));
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

    @ShellMethod("Delete a book by id")
    public String delete() {
        Set<Integer> bookIdSet = new HashSet<>();
        Scanner scan = new Scanner(System.in);
        int bookId;

        System.out.println("Please choose a number of book you want to delete from the list below:");

        for(Book book : bookDao.getAll()) {
            System.out.println(book.getId() + ". " + book.getTitle() + " by " + book.getAuthor().getName() +
                    ", it is a " + book.getGenre().getGenre());
            bookIdSet.add(book.getId());
        }

        do {
            System.out.println("Please enter valid id:");
            bookId = scan.nextInt();
        }while (!bookIdSet.contains(bookId));

        String bookTitle = bookDao.getById(bookId).getTitle();

        bookDao.delete(bookId);

        return "The book " + bookTitle + " was deleted";
    }

    private int askAuthorId() {
        Scanner scan = new Scanner(System.in);
        int authorId;
        Set<Integer> authorIdSet = new HashSet<>();

        System.out.println("Please choose a number of author from the list below: ");
        for(Author author : authorDao.getAll()) {
            System.out.println(author.getId() + ". " + author.getName());
            authorIdSet.add(author.getId());
        }
        do {
            System.out.println("Please enter valid id:");
            authorId = scan.nextInt();
        }while (!authorIdSet.contains(authorId));

        return authorId;
    }

    private String askGenreTitle() {
        Scanner scan = new Scanner(System.in);
        int genreId;
        List<Genre> genreList = genreDao.getAll();

        System.out.println("Please choose a number of genre from the list below: ");
        for(int i = 0; i < genreList.size(); i++) {
            System.out.println((i + 1) + ". " + genreList.get(i).getGenre());
        }

        do {
            System.out.println("Please enter valid id:");
            genreId = scan.nextInt();
        }while ((genreId < 1) || (genreId > genreList.size()));

        return genreList.get(genreId - 1).getGenre();
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
            authorDao.insert(new Author(argument));
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
            genreDao.insert(new Genre(argument));
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
            int authorId = askAuthorId();
            String genreTitle = askGenreTitle();

            Book book = new Book(argument, authorDao.getById(authorId),
                    genreDao.getByGenre(genreTitle));

            bookDao.insert(book);
            return "The book " + argument + " was added to the base!";
        }
    }

    private class Invoker {
        public String executeCommand(Command command) {
            return command.execute();
        }
    }
}



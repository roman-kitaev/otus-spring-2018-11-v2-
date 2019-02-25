package ru.otus.HW051;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.HW051.dao.AuthorDao;
import ru.otus.HW051.dao.BookDao;
import ru.otus.HW051.dao.GenreDao;
import ru.otus.HW051.domain.Author;
import ru.otus.HW051.domain.Book;
import ru.otus.HW051.domain.Genre;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Hw051ApplicationTests {

	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private GenreDao genreDao;
	@Autowired
	private BookDao bookDao;

	Author author1 = new Author(1,"Pushkin");
	Author author2 = new Author(2,"Tolstoy");
	Genre genre1 = new Genre("novel");
	Genre genre2 = new Genre("poem");

	Random rand = new Random(47);

	@Before
	public void createAndFillTheTables() {
		bookDao.dropTable();
		genreDao.initiateTable();
		authorDao.initiateTable();
		bookDao.initiateTable();

		authorDao.insert(author1);
		authorDao.insert(author2);

		genreDao.insert(genre1);
		genreDao.insert(genre2);
	}

	@Test
	public void addBook() {
		String title = "Book1";
		Book bookToAdd = new Book(title, author1, genre1);
		bookDao.insert(bookToAdd);
		Book bookToGet = bookDao.getByTitle(title);

		Assert.assertEquals(bookToAdd, bookToGet);
	}

	@Test
	public void addBooks() {
		List<Book> listToAdd = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			listToAdd.add(new Book(("Book" + (i + 1)),
					(i % 2 == 0 ? author1 : author2),
					(rand.nextBoolean() ? genre1 : genre2)));
		}
		bookDao.insertBooks(listToAdd);

		List<Book> listToGet = bookDao.getAll();

		Set<Book> setToGet = new HashSet<>(listToGet);
		Set<Book> setToAdd = new HashSet<>(listToAdd);

		Assert.assertEquals(setToAdd, setToGet);
	}

	@Test
	public void countTest() {
		int beforeInsert = bookDao.count();
		int count = 3;

		for(int i = 0; i < 3; i++) {
			bookDao.insert(new Book(("Book" + (i + 1)),
					(i % 2 == 0 ? author1 : author2),
					(rand.nextBoolean() ? genre1 : genre2)));
		}

		int afterInsert = bookDao.count();
		Assert.assertEquals(count, afterInsert - beforeInsert);
	}

	@Test
	public void deleteTest() {
		bookDao.insert(new Book("Peace And War", author2, genre1));
		bookDao.insert(new Book("Ruslan and Ludmila", author1, genre2));

		List<Book> bookList = bookDao.getAll();
		List<Integer> bookIdList = new ArrayList<>();
		for(Book book : bookList) {
			bookIdList.add(book.getId());
		}

		int idToDelete = bookIdList.get(
				rand.nextInt(bookIdList.size()));

		int beforeDelete = bookDao.count();
		bookDao.delete(idToDelete);
		int afterDelete = bookDao.count();

		Assert.assertEquals(1, beforeDelete - afterDelete);
	}
}


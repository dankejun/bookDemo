package javabook.test;

import javabook.dao.BookDao;
import javabook.dao.impl.BookDaoImpl;
import javabook.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dankejun
 * @create 2020/11/26 22:52
 */
public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"我的第一本算法书" , "国哥" , new BigDecimal(2999), 9999 , 9 ,
                "static/img" +
                "/default" +
                ".jpg"));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "我的第一本算法书", "国哥", new BigDecimal(3999), 9999, 9, "static" +
                "/img/default.jpg"));

    }

    @Test
    public void queryBookById() {
        Book book = new Book();
        book = bookDao.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> list = new ArrayList<>();
        list = bookDao.queryBooks();
        for (Book book : list) {
            System.out.println(book);
        }
    }
}
package javabook.test;

import javabook.pojo.Book;
import javabook.service.BookService;
import javabook.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author dankejun
 * @create 2020/11/26 23:20
 */
public class BookServiceTest {

    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"我的第一本算法书" , "国哥" , new BigDecimal(2999), 9999 , 9 ,
                "static/img" +
                        "/default" +
                        ".jpg"));
    }


    @Test
    public void deleteBookById() {
        bookService.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"我的第一本算法书" , "hhh" , new BigDecimal(100), 9999 , 9 ,
                "static/img" +
                        "/default" +
                        ".jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookService.queryBooks());
    }
}
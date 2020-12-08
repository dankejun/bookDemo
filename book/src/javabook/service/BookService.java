package javabook.service;

import javabook.pojo.Book;
import javabook.pojo.Page;

import java.util.List;

/**
 * @author dankejun
 * @create 2020/11/26 23:14
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);

}

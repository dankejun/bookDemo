package javabook.dao.impl;

import javabook.dao.BookDao;
import javabook.pojo.Book;

import java.util.List;

/**
 * @author dankejun
 * @create 2020/11/26 22:39
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_books(`name`,`author`,`price`,`sales`,`stock`,`img_path`)" +
                "values(?,?,?,?,?,?) ";
        return update(sql, book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),
                book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_books where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_books set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?," +
                "`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),
                book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` " +
                "imgPath from t_books where id=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` " +
                "imgPath from t_books";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForTotalCount() {
        String sql = "select count(1) from t_books";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` " +
                "imgPath from t_books limit ?,?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryForTotalCountByPrice(int min, int max) {
        String sql = "select count(1) from t_books where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` " +
                "imgPath from t_books where price between ? and ? limit ?,?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}

package javabook.web;

import com.google.gson.Gson;
import javabook.pojo.Book;
import javabook.pojo.Cart;
import javabook.pojo.CartItem;
import javabook.service.BookService;
import javabook.service.impl.BookServiceImpl;
import javabook.utlis.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dankejun
 * @create 2020/12/2 23:14
 */
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        int id = WebUtils.parssInt(req.getParameter("id"), 0);
        int count = WebUtils.parssInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        int id = WebUtils.parssInt(req.getParameter("id"),0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Integer id = WebUtils.parssInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        resp.sendRedirect(req.getHeader("Referer"));
        req.getSession().setAttribute("lastItem", cartItem.getName());
    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        Integer id = WebUtils.parssInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastItem", cartItem.getName());
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("totalCount", cart.getTotalCount());
        resMap.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resMap);
        resp.getWriter().write(json);
    }
}

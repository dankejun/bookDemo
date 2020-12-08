package javabook.web;

import javabook.pojo.Book;
import javabook.pojo.Page;
import javabook.service.BookService;
import javabook.service.impl.BookServiceImpl;
import javabook.utlis.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dankejun
 * @create 2020/11/29 18:55
 */
public class CientBookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        int pageNo = WebUtils.parssInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parssInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        int pageNo = WebUtils.parssInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parssInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parssInt(req.getParameter("min"), 0);
        int max = WebUtils.parssInt(req.getParameter("max"), Integer.MAX_VALUE);
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}

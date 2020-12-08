package javabook.web;

import com.google.gson.Gson;
import javabook.pojo.User;
import javabook.service.UserService;
import javabook.service.impl.UserServiceImpl;
import javabook.utlis.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author dankejun
 * @create 2020/11/16 21:07
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resMap);
        resp.getWriter().write(json);
    }

    /**
     * 处理登录的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

        protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //调用业务逻辑方法
        User user = userService.login(new User(null, username, password, null));
        if (user == null) {
            //用户不存在，登录失败
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //保存用户登录后的信息到session
            req.getSession().setAttribute("user",user);
            //登录成功,跳转到成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理注册的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        User user = WebUtils.copyParmToBean(req.getParameterMap(), new User());
        //检查验证码
        if (token != null && token.equalsIgnoreCase(code)) {
            //检查用户名是否可用
            if (userService.existsUsername(username)) {//不可用
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("password", password);
                req.setAttribute("email", email);
                System.out.println("用户名[" + username + "]已存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {//可用
                //将用户信息保存到数据库
                req.getSession().setAttribute("user", user);
                userService.registUser(new User(null,username, password, email));
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);

            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("email", email);

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }

}

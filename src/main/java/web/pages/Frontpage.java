package web.pages;

import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/frontpage")
public class Frontpage extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            render("Start", "/WEB-INF/webpages/frontpage.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var s = req.getSession();
        s.setAttribute("username", "");
        s.setAttribute("employer","no");
        s.setAttribute("carport",null);
        resp.sendRedirect(req.getContextPath() + "/frontpage");
    }


}


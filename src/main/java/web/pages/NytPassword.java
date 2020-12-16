package web.pages;

import api.Utils;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/nytpassword")
public class NytPassword extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // req.setAttribute("items", api.findAllItems());
            render("Start", "/WEB-INF/webpages/nytpassword.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var s = req.getSession();

        String name = (String) s.getAttribute("username");
        String gammeltpassword = req.getParameter("gammeltpassword");
        String nytpassword = req.getParameter("nytpassword");

        if (gammeltpassword.equals(nytpassword)) {
            resp.sendError(500, "Det nye password skal være forskelligt fra det gamle!");
             return;
        }

        api.updateSellerPassword(name, gammeltpassword, nytpassword);
        resp.sendRedirect(req.getContextPath() + "/ordrer");
    }
}
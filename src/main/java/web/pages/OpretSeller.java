package web.pages;

import domain.items.Customer;
import domain.items.CustomerNotFound;
import domain.items.DBException;
import domain.items.Seller;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/opretSeller")
public class OpretSeller extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            render("Start", "/WEB-INF/webpages/opretSeller.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("Opret") != null) {
            String name = req.getParameter("name");
            String userName = req.getParameter("userName");
            System.out.println(name + "   " + userName);
            byte[] salt = Customer.generateSalt();
            byte[] secret = Customer.calculateSecret(salt, "1234");
            Seller seller = new Seller(userName, name, salt, secret);
            try {
                api.commitSeller(seller);
                var s = req.getSession();
                s.setAttribute("username", userName);
                s.setAttribute("employer", "yes");
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/frontpage");
        }
    }
}
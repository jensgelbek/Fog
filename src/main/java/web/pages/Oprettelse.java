package web.pages;

import api.Utils;
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

@WebServlet("/oprettelse")
public class Oprettelse extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // req.setAttribute("items", api.findAllItems());
            render("Start", "/WEB-INF/webpages/oprettelse.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("Opret") != null) {
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            String email = req.getParameter("email");
            int tlfNummer;
            try {
                tlfNummer = Integer.parseInt(req.getParameter("tlfNummer"));
            } catch (Exception e) {
                tlfNummer = 0;
            }
            String password = req.getParameter("password");
            String passwordrepeat = req.getParameter("passwordrepeat");
            byte[] salt = Customer.generateSalt();
            byte[] secret = Customer.calculateSecret(salt, password);
            Customer customer = new Customer(name, address, email, tlfNummer, false, salt, secret);
            try {
                api.commitCustomer(customer);
                var s = req.getSession();
                s.setAttribute("username", email);
                s.setAttribute("employer", "no");
            } catch (DBException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(req.getContextPath() + "/frontpage");

            if (req.getParameter("Log ud") != null) {
                req.setAttribute("username", email == null);

            }


        }

        if (req.getParameter("Loginemail") != null) {
            String userName = req.getParameter("Loginemail");
            if (userName.contains("@")) {
                String email = req.getParameter("Loginemail");
                String password = req.getParameter("Loginpassword");
                Customer customer = null;
                try {
                    customer = api.findCustomer(email);
                } catch (DBException e) {
                    e.printStackTrace();
                } catch (CustomerNotFound customerNotFound) {
                    customerNotFound.printStackTrace();
                }
                if (customer != null) {
                    boolean correctpassword = customer.checkPassword(password);
                    if (correctpassword) {
                        var s = req.getSession();
                        s.setAttribute("username", customer.getEmail());
                        s.setAttribute("employer", "no");
                    }
                    resp.sendRedirect(req.getContextPath() + "/frontpage");

                }
            } else {
                String password = req.getParameter("Loginpassword");
                Seller seller = null;
                try {
                    seller = api.findSeller(userName);
                } catch (DBException e) {
                    e.printStackTrace();
                }
                if (seller != null) {
                    boolean correctpassword = Utils.checkPassword(password, seller.getSecret(), seller.getSalt());
                    String nextPage= "/frontpage";
                    if (correctpassword) {
                        var s = req.getSession();
                        s.setAttribute("username", seller.getUsername());
                        s.setAttribute("employer", "yes");
                        if(password.equals("1234")){
                            nextPage="/nytpassword";
                        }
                    }
                    resp.sendRedirect(req.getContextPath() + nextPage);


                }

            }
        }
    }
}
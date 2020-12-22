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
        String nextPage="/frontpage";
        if(req.getParameter("Log ud")!=null) {
           s.setAttribute("username", "");
           s.setAttribute("employer", "no");
           s.setAttribute("carport", null);
           s.setAttribute("price", null);
       }

        //LOGIN
        if (req.getParameter("Loginemail") != null) {
            System.out.println("grebet");
            String userName = req.getParameter("Loginemail");
            Customer customer = null;
            Seller seller=null;

            //USER LOGIN
            if (userName.contains("@")) {
                String email = req.getParameter("Loginemail");
                String password = req.getParameter("Loginpassword");
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
                        s.setAttribute("username", customer.getEmail());
                        s.setAttribute("employer", "no");
                    }else{
                        customer=null;
                    }
                    nextPage= "/frontpage";
                }
            } else
            //SELLER LOGIN
            {
                String password = req.getParameter("Loginpassword");
                try {
                    seller = api.findSeller(userName);
                } catch (DBException e) {
                    e.printStackTrace();
                }
                if (seller != null) {
                    boolean correctpassword = Utils.checkPassword(password, seller.getSecret(), seller.getSalt());

                    if (correctpassword) {
                        s.setAttribute("username", seller.getUsername());
                        s.setAttribute("employer", "yes");
                        if(password.equals("1234")){
                            nextPage="/nytpassword";
                        }}else{
                        seller=null;
                    }

                }
            }
            // System.out.println(seller+" "+customer);
            if(seller==null&&customer==null){
                System.out.println("fejl");
                resp.sendError(401, "password og brugernavn passer ikke sammen");
            }
        }

        resp.sendRedirect(req.getContextPath() + nextPage);
    }


}


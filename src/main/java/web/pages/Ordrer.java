package web.pages;

import domain.items.*;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;

@WebServlet("/ordrer")
public class Ordrer extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = null;
        try {
            orders = api.findAllOrders();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<String> navne = new ArrayList<>();
        List<Integer> carportBredder = new ArrayList<>();
        List<Integer> carportLaengder = new ArrayList<>();
        List<Integer> skurBredder = new ArrayList<>();
        List<Integer> skurLaengder = new ArrayList<>();


        for (Order o : orders) {
            try {
                navne.add(api.findCustomer(o.getKundeEmail()).getName());
            } catch (DBException e) {
                e.printStackTrace();
            } catch (CustomerNotFound customerNotFound) {
                customerNotFound.printStackTrace();
            }


            try {

                Carport c = api.findCarport(o.getCarportId());
                carportBredder.add(c.getWidth());
                carportLaengder.add(c.getLenght());
                skurBredder.add(c.getShedWidth());
                skurLaengder.add(c.getShedLength());


            } catch (DBException e) {
                e.printStackTrace();
            }


        }
        req.setAttribute("carportbredder", carportBredder);
        req.setAttribute("carportlaengder", carportLaengder);
        req.setAttribute("skurbredder", skurBredder);
        req.setAttribute("skurlaengder", skurLaengder);
        req.setAttribute("Orders", orders);
        req.setAttribute("navne", navne);
        try {
            render("Start", "/WEB-INF/webpages/ordrer.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("vis") != null) {
            int orderToShow = Integer.parseInt(req.getParameter("vis"));
            System.out.println(orderToShow);

            resp.sendRedirect(req.getContextPath() + "/ordre?ordre=" + orderToShow);

        }
    }

}

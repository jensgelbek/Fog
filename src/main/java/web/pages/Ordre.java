package web.pages;

import domain.items.*;
import domain.materials.Stykliste;
import web.BaseServlet;
import web.svg.SvgCarport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ordre")
public class Ordre extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ordreId = Integer.parseInt(req.getParameter("ordre"));
        try {
            Order order = api.findOrder(ordreId);
            Carport carport = api.findCarport(order.getCarportId());
            Stykliste stykliste = api.findStykliste(ordreId);
            Customer customer= api.findCustomer(order.getKundeEmail());
            req.setAttribute("order", order);
            req.setAttribute("carport", carport);
            req.setAttribute("stykliste", stykliste);
            req.setAttribute("customer",customer);
            req.setAttribute("svg", SvgCarport.carport(carport.getWidth(), carport.getLenght(), carport.getShedWidth(), carport.getShedLength()).toString());
        } catch (DBException | CustomerNotFound e) {
            e.printStackTrace();
        }

        try {
            render("Ordre", "/WEB-INF/webpages/ordre.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}

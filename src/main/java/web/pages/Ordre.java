package web.pages;

import domain.items.*;
import domain.materials.Stykliste;
import infrastructure.Lists;
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
            Lists list = new Lists();
            req.setAttribute("carportMeasure", list.carportMeasure());
            req.setAttribute("tag", list.tag());
            req.setAttribute("shedW", list.shedwidth());
            req.setAttribute("shedL", list.shedlength());

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
        if (req.getParameter("edit") != null) {
            int orderId= Integer.parseInt(req.getParameter("edit"));
            Carport carport= null;
            try {
                carport = api.findCarport(api.findOrder(orderId).getCarportId());
            } catch (DBException e) {
                e.printStackTrace();
            }

           carport.setLenght(Integer.parseInt(req.getParameter("length")));
           carport.setShedWidth(Integer.parseInt(req.getParameter("width")));
           carport.setShedLength(Integer.parseInt(req.getParameter("shedlength")));
           carport.setShedWidth(Integer.parseInt(req.getParameter("shedwidth")));
            System.out.println(carport);
           api.updateCarport(carport);
            resp.sendRedirect(req.getContextPath() + "/ordre?ordre="+orderId);
        }
    }
}

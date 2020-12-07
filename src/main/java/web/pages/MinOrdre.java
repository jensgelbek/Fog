package web.pages;

import domain.items.Carport;
import domain.items.DBException;
import domain.items.Order;
import web.BaseServlet;
import web.svg.SvgCarport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/minOrdre")
public class MinOrdre extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ordreId=Integer.parseInt(req.getParameter("ordre"));
        try {
            Order order=api.findOrder(ordreId);
            Carport carport= api.findCarport(order.getCarportId());
            req.setAttribute("order",order);
            req.setAttribute("carport",carport);
            req.setAttribute("svg", SvgCarport.carport().toString());

        } catch (DBException e) {
            e.printStackTrace();
        }

        try {
            render("Ordre", "/WEB-INF/webpages/minOrdre.jsp", req, resp);
        } catch (ServletException | IOException  e){
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int orderToShow=0;
        if (req.getParameter("bestil")!=null) {
            orderToShow=Integer.parseInt(req.getParameter("bestil"));
            System.out.println("orderid="+orderToShow);
            api.updateOrderstatus(orderToShow,"bestil");
        }
        if (req.getParameter("afslå")!=null) {
            orderToShow=Integer.parseInt(req.getParameter("afslå"));
            System.out.println("orderid= afslå"+orderToShow);
            api.updateOrderstatus(orderToShow,"afslå");
        }
        if (req.getParameter("kontakt")!=null) {
            orderToShow=Integer.parseInt(req.getParameter("kontakt"));
            System.out.println("orderid= kontakt"+orderToShow);

            api.updateOrderstatus(orderToShow,"kontakt");
        }
        resp.sendRedirect(req.getContextPath() + "/minOrdre?ordre="+orderToShow);
    }

}

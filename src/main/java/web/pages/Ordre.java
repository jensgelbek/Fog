package web.pages;

import api.Utils;
import domain.items.*;
import domain.materials.Material;
import domain.materials.StykListeLinje;
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
        var s = req.getSession();
        int ordreId = Integer.parseInt(req.getParameter("ordre"));
        s.setAttribute("ordreId",ordreId);
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
            req.setAttribute("volumenliste", stykliste.volumenListe);
            req.setAttribute("unitliste",stykliste.unitListe);
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
        var s = req.getSession();
        int ordreId= (int) s.getAttribute("ordreId");
        if (req.getParameter("edit") != null) {
            int orderId= Integer.parseInt(req.getParameter("edit"));
            Carport carport= null;
            try {
                carport = api.findCarport(api.findOrder(orderId).getCarportId());
            } catch (DBException e) {
                e.printStackTrace();
            }

           carport.setLenght(Integer.parseInt(req.getParameter("length")));
            System.out.println(req.getParameter("length")+req.getParameter("width")+req.getParameter("shedlength")+req.getParameter("shedwidth"));
           carport.setWidth(Integer.parseInt(req.getParameter("width")));
            System.out.println(Integer.parseInt(req.getParameter("width")));
           carport.setShedLength(Integer.parseInt(req.getParameter("shedlength")));
           carport.setShedWidth(Integer.parseInt(req.getParameter("shedwidth")));
            System.out.println("ny carport"+ carport);
            api.updateCarport(carport);
            try {
                api.deletStykliste(orderId);
                Stykliste stykliste= api.calculateStykliste(carport);
                int newPrice= Utils.calculatePrice(stykliste);
                api.updateOrderPrice(orderId,newPrice);
                api.commitStykliste(stykliste,orderId);
            } catch (DBException e) {
                e.printStackTrace();
            }

            resp.sendRedirect(req.getContextPath() + "/ordre?ordre="+orderId);
        }
        if (req.getParameter("editunit") != null) {
            int styklistelinjeid= Integer.parseInt(req.getParameter("editunit"));

            int antal= Integer.parseInt(req.getParameter("antalunit"));

            api.updateStykListeLinjeAntal(styklistelinjeid, antal);
            resp.sendRedirect(req.getContextPath() + "/ordre?ordre="+ordreId);
        }
        if (req.getParameter("editvolumen") != null) {
            System.out.println("editvolumen");
            int styklistelinjeid= Integer.parseInt(req.getParameter("editvolumen"));
            //opdater antal
            int antal= Integer.parseInt(req.getParameter("antal"));
            api.updateStykListeLinjeAntal(styklistelinjeid, antal);
           /* //opdater materiale (med ny længde)
            StykListeLinje stykListeLinje=api.findStykListeLinje(styklistelinjeid);
            int length= Integer.parseInt(req.getParameter("length"));
            System.out.println(styklistelinjeid+" "+length+" "+stykListeLinje.getMateriale().getName());
            Material material=api.findVolumeMaterialNameLenght(stykListeLinje.getMateriale().getName(),length);
            System.out.println(material);
            api.updateStykListeLinjeMaterialeId(styklistelinjeid,material.getId());*/

            resp.sendRedirect(req.getContextPath() + "/ordre?ordre="+ordreId);
        }
    }

}

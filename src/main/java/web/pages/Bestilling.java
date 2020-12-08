package web.pages;


import domain.items.*;

import infrastructure.DBCarportRepository;
import infrastructure.Lists;
import web.BaseServlet;
import web.svg.SvgCarport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet("/bestilling")
public class Bestilling extends BaseServlet {

    public static List<Carport> getCarport(HttpServletRequest req) {

        var s = req.getSession();
        List<Carport> carportList = (List<Carport>) s.getAttribute("carportList");
        if (carportList == null) {
            carportList = new ArrayList<Carport>();
            s.setAttribute("carportList", carportList);
        }
        System.out.println(carportList);
        return carportList;
    }



    public static class CarportDTO {
        public int width = 0;
        public int length = 0;

        public static CarportDTO fromSession(HttpSession ses) {
            CarportDTO carport = (CarportDTO) ses.getAttribute("carport");
            if (carport == null) {
                carport = new CarportDTO();
                ses.setAttribute("carport", carport);
            }
            return carport;
        }

        public int getLength() {
            return length;
        }

        public int getWidth() {
            return width;
        }

        public String getDrawing() {
            return SvgCarport.carport(width, length).toString();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Lists list = new Lists();
            list.carportMeasure();
            list.tag();
            list.shed();

            req.setAttribute("carportMeasure", list.carportMeasure());
            req.setAttribute("tag", list.tag());
            req.setAttribute("shed", list.shed());
            CarportDTO.fromSession(req.getSession());
            // req.setAttribute("tag2", req.getSession().getAttribute("tag2"));

            req.setAttribute("tag2", req.getSession().getAttribute("tag2"));


            render("Bestilling", "/WEB-INF/webpages/bestilling.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var carportdto = CarportDTO.fromSession(req.getSession());
        carportdto.width = Integer.parseInt(req.getParameter("width"));
        carportdto.length = Integer.parseInt(req.getParameter("length"));
        //String tag = req.getParameter("tag");

        /*
        if (req.getParameter("target") != null)
            if (req.getParameter("target").equals("bestilling")) {


                int bredde = Integer.parseInt(req.getParameter("bredde"));
                int langde = Integer.parseInt(req.getParameter("laengde"));
                String tag = req.getParameter("tag");
                boolean rejsning = false;
                int shedW = 0;
                int shedL = 0;


                var s = req.getSession();
                s.setAttribute("bredde", bredde);
                s.setAttribute("langde", langde);
                s.setAttribute("tag2", tag);




                // New Carport
                Carport carport = new Carport(bredde, langde, rejsning, tag,  shedW, shedL);
                try {
                    int carportId =api.commitCarport(carport);
                    carport.setCarportID(carportId);
                    Order order=new Order(LocalDate.now(),null,null, (String) s.getAttribute("username"),1,carport.getCarportID(),75000,"tilbud");
                    api.commitOrder(order);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

         */

                resp.sendRedirect(req.getContextPath() + "/bestilling");
            }
    }


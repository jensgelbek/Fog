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
        public int width = 270;
        public int length = 270;

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

/*
            Integer width = (Integer) req.getSession().getAttribute("bredde");
            if (width == null) {
                width = 400;
                req.getSession().setAttribute("bredde", width);
            }
            Integer length = (Integer) req.getSession().getAttribute("langde");
            if (length == null) {
                length = 400;
                req.getSession().setAttribute("langde", length);
            }
            req.setAttribute("svg", SvgCarport.carport(width, length));

            req.setAttribute("bredde", req.getSession().getAttribute("bredde"));
            req.setAttribute("langde", req.getSession().getAttribute("langde")); */
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

        resp.sendRedirect(req.getContextPath() + "/bestilling");


        /* if (req.getParameter("target") != null) {

            if (req.getParameter("target").equals("bestilling")) {
                boolean rejsning = false;
                int shedW = 0;
                int shedL = 0;

                try {
                    Carport carport = new Carport(carportdto.width, carportdto.length, rejsning, tag, shedW, shedL);
                    api.commitCarport(carport);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                resp.sendRedirect(req.getContextPath() + "/bestilling/1");
            } else {

            }
        }*/
    }
}

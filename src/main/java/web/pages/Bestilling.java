package web.pages;


import api.Calc;

import domain.items.Carport;
import domain.items.DBException;
import domain.materials.StykListeLinje;
import domain.materials.Stykliste;
import infrastructure.Lists;
import org.w3c.dom.ls.LSOutput;
import web.BaseServlet;

import web.svg.CKL.Svg;
import web.svg.StykListeLinje;

import web.svg.SvgCarport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
        public int shedWidth = 0;
        public int shedLength = 0;

        public static CarportDTO fromSession(HttpSession ses) {
            CarportDTO carport = (CarportDTO) ses.getAttribute("carport");
            if (carport == null) {
                carport = new CarportDTO();
                ses.setAttribute("carport", carport);
            }
            System.out.println(carport.shedWidth);
            System.out.println(carport.shedLength);
            return carport;

            }


        public int getLength() {
            return length;
        }

        public int getWidth() {
            return width;
        }

        public int getShedWidth() { return shedWidth;}
        public int getShedLength() {return shedLength;}

        public String getDrawing() {
            return SvgCarport.carport(width, length, shedWidth, shedLength).toString();
        }


        public StykListeLinje sternWidthCalc() {
            return SvgCarport.sternWidthCalc(width, length);
        }

        public StykListeLinje sternLengthCalc() { return SvgCarport.sternLengthCalc(width, length); }

        public StykListeLinje spaerCalc() {
            return SvgCarport.spaerCalc(width, length);
        }

        public StykListeLinje shedWidthCalc() {return SvgCarport.shedWidthCalc(width);}

        public StykListeLinje shedLengthCalc() {return SvgCarport.shedLengthCalc(length);}

        }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Lists list = new Lists();
            list.carportMeasure();
            list.tag();
            list.shedwidth();
            list.shedlength();

            req.setAttribute("carportMeasure", list.carportMeasure());
            req.setAttribute("tag", list.tag());
            req.setAttribute("shedW", list.shedwidth());
            req.setAttribute("shedL", list.shedlength());
            CarportDTO.fromSession(req.getSession());

            req.setAttribute("sternWidthCalc", CarportDTO.fromSession(req.getSession()).sternWidthCalc());

            req.setAttribute("sternLengthCalc", CarportDTO.fromSession(req.getSession()).sternLengthCalc());
            req.setAttribute("spaerCalc", CarportDTO.fromSession(req.getSession()).spaerCalc());

            req.setAttribute("tag2", req.getSession().getAttribute("tag2"));


            render("Bestilling", "/WEB-INF/webpages/bestilling.jsp", req, resp);
        } catch (ServletException | IOException | DBException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var carportdto = CarportDTO.fromSession(req.getSession());
        carportdto.width = Integer.parseInt(req.getParameter("width"));
        carportdto.length = Integer.parseInt(req.getParameter("length"));

        carportdto.shedLength = Integer.parseInt(req.getParameter("shedWidth"));
        carportdto.shedWidth= Integer.parseInt(req.getParameter("shedLength"));

        //String tag = req.getParameter("tag");

        try {
            Stykliste stykliste = Calc.generereStykliste(carportdto.width, carportdto.length);
        } catch (DBException e) {
            e.printStackTrace();
        }


        resp.sendRedirect(req.getContextPath() + "/bestilling");
    }
}


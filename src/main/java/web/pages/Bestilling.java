package web.pages;


import api.Calc;

import com.mysql.cj.Session;
import domain.items.Carport;
import domain.items.DBException;
import domain.items.Order;
import domain.materials.StykListeLinje;
import domain.materials.Stykliste;
import infrastructure.DBStyklisteLinjeRepository;
import infrastructure.Database;
import infrastructure.Lists;
import org.w3c.dom.ls.LSOutput;
import web.BaseServlet;

import web.svg.CKL.Svg;
//import web.svg.StykListeLinje;

import web.svg.SvgCarport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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

        public int getShedWidth() {
            return shedWidth;
        }

        public int getShedLength() {
            return shedLength;
        }

        public String getDrawing() {
            return SvgCarport.carport(width, length, shedWidth, shedLength).toString();
        }

        /*
        public Stykliste stykliste() throws DBException {
            System.out.println("Hej");
            api.commitStykliste(Calc.generereStykliste(width, length),1);
            return Calc.generereStykliste(width, length);
        }

         */

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

            //req.setAttribute("sternLengthCalc", CarportDTO.fromSession(req.getSession()).sternLengthCalc());
            // req.setAttribute("spaerCalc", CarportDTO.fromSession(req.getSession()).spaerCalc());

            // req.setAttribute("tag2", req.getSession().getAttribute("tag2"));


            render("Bestilling", "/WEB-INF/webpages/bestilling.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nextpage = "/bestilling";
        if (req.getParameter("target").equals("bestilling")) {
            var carportdto = CarportDTO.fromSession(req.getSession());
            carportdto.width = Integer.parseInt(req.getParameter("width"));
            carportdto.length = Integer.parseInt(req.getParameter("length"));
            try {
                carportdto.shedLength = Integer.parseInt(req.getParameter("shedWidth"));
            } catch (Exception e) {
                carportdto.shedLength = 0;
            }
            try {
                carportdto.shedWidth = Integer.parseInt(req.getParameter("shedLength"));
            } catch (Exception e) {
                carportdto.shedWidth = 0;
            }
            //String tag = req.getParameter("tag");

            try {
                Stykliste stykliste = Calc.generereStykliste(carportdto.width, carportdto.length);
            } catch (DBException e) {
                e.printStackTrace();
            }
            // resp.sendRedirect(req.getContextPath() + "/bestilling");


        }
        if (req.getParameter("target").equals("tilbud")) {

            var s = req.getSession();


            if ((String) s.getAttribute("username") != null) {
                var carportdto = CarportDTO.fromSession(req.getSession());
                int width = Integer.parseInt(req.getParameter("width")) * 10;
                int length = Integer.parseInt(req.getParameter("length")) * 10;
                String tag = req.getParameter("tag");
                int shedLength = 0;
                int shedWidth = 0;
                try {
                    shedLength = Integer.parseInt(req.getParameter("shedWidth")) * 10;
                } catch (Exception e) {

                }
                try {
                    shedWidth = Integer.parseInt(req.getParameter("shedLength")) * 10;
                } catch (Exception e) {

                }

                Carport carport = new Carport(width, length, false, tag, shedWidth, shedLength);
                int orderid = 0;
                try {
                    int carportId = api.commitCarport(carport);
                    carport.setCarportID(carportId);

                    Order order = new Order(LocalDate.now(), null, null, (String) s.getAttribute("username"), carport.getCarportID(), 0, "tilbud");
                    orderid = api.commitOrder(order);
                    System.out.println(orderid);


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                //resp.sendRedirect(req.getContextPath() + "/minOrdre?ordre=" + orderid);
                nextpage = "/minOrdre?ordre=" + orderid;


                carport = new Carport(width, length, false, "trapez", shedWidth, shedLength);
                orderid = 0;
                try {
                    int carportId = api.commitCarport(carport);
                    carport.setCarportID(carportId);
                  //  var s = req.getSession();


                    Order order = new Order(LocalDate.now(), null, null, (String) s.getAttribute("username"), carport.getCarportID(), 0, "tilbud");
                    orderid = api.commitOrder(order);
                    System.out.println(orderid);


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                nextpage= "/minOrdre?ordre=" + orderid;


            }
        }
        resp.sendRedirect(req.getContextPath() + nextpage);
    }
}





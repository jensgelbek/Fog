package web.pages;

import api.Calc;
import domain.items.DBException;
import domain.materials.StykListeLinje;
import domain.materials.Stykliste;
import web.BaseServlet;
import web.svg.SvgCarport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bom")
public class Bom extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setAttribute("sternWidthCalc", Bestilling.CarportDTO.fromSession(req.getSession()).sternWidthCalc());

        } catch (DBException e) {
            e.printStackTrace();
        }

        render("BOM", "/WEB-INF/webpages/bom.jsp", req, resp);
    }
}


        /*
        try {
            Bestilling.CarportDTO.fromSession(req.getSession());
            req.setAttribute("sternWidthCalc", Calc.generereStykliste(req.getSession().getAttribute()));

        } catch (DBException e) {
            e.printStackTrace();
        }
        render("BOM", "/WEB-INF/webpages/bom.jsp", req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }




    }
}

         */


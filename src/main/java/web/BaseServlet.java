package web;

import api.Fog;

import infrastructure.DBCarportRepository;
import infrastructure.DBOrderRepository;
import infrastructure.Database;

import infrastructure.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class BaseServlet extends HttpServlet {
    protected static final Fog api;
    List<String > pagesForAll=List.of("/WEB-INF/webpages/bestilling.jsp","/WEB-INF/webpages/bestillingRejsning.jsp","/WEB-INF/webpages/frontpage.jsp","/WEB-INF/webpages/index.jsp",
            "/WEB-INF/webpages/kontakt.jsp","/WEB-INF/webpages/minOrdre.jsp","/WEB-INF/webpages/minside.jsp","/WEB-INF/webpages/oprettelse.jsp","/WEB-INF/errorpages/error.jsp");
    static {
        api = createApplication();
    }

    private static Fog createApplication() {
        Database db = new Database();

        return new Fog(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db), new DBVolumeMateialRepository(db), new DBUnitMaterialRepository(db), new DBStyklisteRepository(db),new DBStyklisteRepository(db),new DBMaterialRepository(db) );

    }


    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var s = req.getSession();
        String Employee=(String) s.getAttribute("employer");
        boolean isEmployee=false;
        if(Employee!=null){isEmployee=s.getAttribute("employer").equals("yes");};
        if(isEmployee||pagesForAll.contains(content)) {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            req.setAttribute("title", Fog.getTitle() + " - " + title);
            req.setAttribute("content", content);
            req.setAttribute("year", LocalDateTime.now().getYear());
            req.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(req, resp);

        }
        else {
            resp.sendError(401,"Du må ikke tilgå denne side!");
        }
    }


    protected void log(HttpServletRequest req, String str) {
        System.err.print("(" + LocalDateTime.now() + ")" + this.getClass().getCanonicalName() + " - " + req.getRequestURI() + " - " + str);
    }
}
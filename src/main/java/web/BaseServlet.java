package web;

import api.Webapp;

import domain.items.DBException;
import domain.items.Order;
import domain.items.Seller;
import domain.items.SellerRepository;
import infrastructure.DBCarportRepository;
import infrastructure.DBOrderRepository;
import infrastructure.Database;

import infrastructure.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class BaseServlet extends HttpServlet {
    protected static final Webapp api;

    static {
        api = createApplication();
    }

    private static Webapp createApplication() {
        Database db = new Database();

        return new Webapp(new DBOrderRepository(db), new DBCustomerRepository(db), new DBCarportRepository(db), new DBSellerRepository(db),new DBVolumeMateialRepository(db));

    }


    protected void render(String title, String content, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("title", Webapp.getTitle() + " - " + title);
        req.setAttribute("content", content);
        req.setAttribute("year", LocalDateTime.now().getYear());
        req.getRequestDispatcher("/WEB-INF/includes/base.jsp").forward(req, resp);
    }


    protected void log(HttpServletRequest req, String str) {
        System.err.print("(" + LocalDateTime.now() + ")" + this.getClass().getCanonicalName() + " - " + req.getRequestURI() + " - " + str);
    }
}
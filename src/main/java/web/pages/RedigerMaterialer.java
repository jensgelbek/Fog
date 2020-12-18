package web.pages;

import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redigermaterialer")
public class RedigerMaterialer extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("materialer", api.findAllMaterailTypes());
            render("Start", "/WEB-INF/webpages/redigermaterialer.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("navn");
        int pris=Integer.parseInt(req.getParameter("pris"));
        // System.out.println(name+pris);
        api.updateMaterialPrice(name,pris);
        resp.sendRedirect(req.getContextPath() + "/redigermaterialer");

    }


}
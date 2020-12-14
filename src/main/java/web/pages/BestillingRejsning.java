package web.pages;

import infrastructure.Lists;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bestillingRejsning")
public class BestillingRejsning extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Lists list = new Lists();
            list.carportMeasure();
            list.tagRejs();
            list.tagHaldning();
            list.shedlength();
            list.shedwidth();

            req.setAttribute("carportMeasure", list.carportMeasure());
            req.setAttribute("tagRejs", list.tagRejs());
            req.setAttribute("tagHald", list.tagHaldning());
            req.setAttribute("shedwidth", list.shedwidth());
            req.setAttribute("shedlength", list.shedlength());

            render("Bestilling", "/WEB-INF/webpages/bestillingRejsning.jsp", req, resp);
        } catch (ServletException | IOException e) {
            log(e.getMessage());
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

package dev.potat.servlets.lab16;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ListServlet", value = "/")
public class ListServlet extends HttpServlet {
    private ListStore store;

    public void init() {
        store = ListStore.getInstance("db.yaml");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + store.toString() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
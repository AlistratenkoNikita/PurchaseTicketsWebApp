/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.com.alistratenko.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.alistratenko.database.WorkWithDatabase;

/**
 *
 * @author Alistratenko Nikita
 */
@WebServlet(name="useTickets", urlPatterns={"/useTickets"})
public class useTickets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("Authorized").equals("1")) {
            response.sendRedirect("profile");
        } else {
            session.setAttribute("userAuthorizationError", 2);
            response.sendRedirect("welcome");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            WorkWithDatabase.useTickets(request.getSession().getAttribute("login").toString(),
                    request.getParameter("metroBox"),
                    request.getParameter("busBox"),
                    request.getParameter("tramBox"),
                    request.getParameter("trolleyBox"));
        } catch (SQLException ex) {
            Logger.getLogger(useTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("profile");
    }
}

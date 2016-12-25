/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.alistratenko.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "editUserInfo", urlPatterns = {"/editUserInfo"})
public class editUserInfo extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String res = null;
        try {
            res = WorkWithDatabase.editUserInfo(request.getSession().getAttribute("login").toString(),
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("phone"),
                    request.getParameter("city"));
        } catch (SQLException ex) {
            Logger.getLogger(editUserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getSession().setAttribute("userEdit", res);
        System.out.println("-------------------------------");
        System.out.println(request.getParameter("name"));    
        System.out.println("-------------------------------");
        response.sendRedirect("profile");
    }
}

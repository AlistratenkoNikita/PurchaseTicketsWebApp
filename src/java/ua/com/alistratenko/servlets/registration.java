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
import org.apache.commons.codec.digest.DigestUtils;
import ua.com.alistratenko.database.WorkWithDatabase;

/**
 *
 * @author Alistratenko Nikita
 */
@WebServlet(name="registration", urlPatterns={"/registration"})
public class registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("Authorized").equals("1")) {
            response.sendRedirect("profile");
        } else {
            request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean registerUser = WorkWithDatabase.registerUser(request.getParameter("login"),
                    DigestUtils.md5Hex(request.getParameter("password")),
                    request.getParameter("email"),
                    request.getParameter("name"),
                    request.getParameter("phone"),
                    request.getParameter("city"));
            if (registerUser){
                request.getSession().setAttribute("userAuthorizationError", 4);
                response.sendRedirect("welcome");
            }
            else{
                request.getSession().setAttribute("userAuthorizationError", 3);
                request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

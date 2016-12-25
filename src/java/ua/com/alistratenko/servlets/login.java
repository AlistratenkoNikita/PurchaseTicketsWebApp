/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.alistratenko.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import ua.com.alistratenko.database.ConnectionFactory;
import ua.com.alistratenko.database.WorkWithDatabase;
import ua.com.alistratenko.fillers.UserFillerInfo;

/**
 *
 * @author Alistratenko Nikita
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("Authorized").equals("1")) {
            response.sendRedirect("profile");
        } else {
            request.getSession().setAttribute("userAuthorizationError", 1);
            response.sendRedirect("welcome");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter("loginField");
        String password = DigestUtils.md5Hex(request.getParameter("passwordField"));
        String res = null;
        try {
            res = WorkWithDatabase.login(login, password);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (res.equals("1")) {
            session.setAttribute("Authorized", "1");
            session.setAttribute("login", login);
            response.sendRedirect("profile");
        } else if (res.equals("0")){
            session.setAttribute("userAuthorizationError", 2);
            response.sendRedirect("welcome");
        } else {
            session.setAttribute("userAuthorizationError", 3);
            response.sendRedirect("welcome");
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.com.alistratenko.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.com.alistratenko.database.WorkWithDatabase;
import ua.com.alistratenko.fillers.UserFillerInfo;

/**
 *
 * @author Alistratenko Nikita
 */
@WebServlet(name="profile", urlPatterns={"/profile"})
public class profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("Authorized").equals("1")) {
            try {
                ArrayList<String> userInfoList = WorkWithDatabase.getUserInfo(session.getAttribute("login").toString());
                UserFillerInfo.fillUserInfo(session, userInfoList.get(1), userInfoList.get(2), userInfoList.get(3), userInfoList.get(4));
                ArrayList<String> creditCardInfoList = WorkWithDatabase.getUserCCInfo(session.getAttribute("login").toString());
                UserFillerInfo.fillUserCCInfo(session, creditCardInfoList.get(0), creditCardInfoList.get(1), creditCardInfoList.get(2), creditCardInfoList.get(3), creditCardInfoList.get(4));
                UserFillerInfo.fillBoughtTickets(session, WorkWithDatabase.getBoughtTickets(session.getAttribute("login").toString()));
                UserFillerInfo.fillUsedTickets(session, WorkWithDatabase.getUsedTickets(session.getAttribute("login").toString()));
                request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(profile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.getSession().setAttribute("userAuthorizationError", 1);
            response.sendRedirect("welcome");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}

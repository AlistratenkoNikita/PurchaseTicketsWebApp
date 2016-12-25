/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.alistratenko.fillers;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import ua.com.alistratenko.entities.Ticket;

/**
 *
 * @author Alistratenko Nikita
 */
public class UserFillerInfo {

    public static void fillUserInfo(HttpSession session, String name, String email, String phone, String city) {
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);

        session.setAttribute("city", city.trim());

    }

    public static void fillUserCCInfo(HttpSession session, String number, String month, String year, String cvv, String owner) {
        session.setAttribute("number", number);
        session.setAttribute("month", month);
        session.setAttribute("year", year);
        session.setAttribute("cvv", cvv);
        session.setAttribute("owner", owner);
    }

    public static void fillBoughtTickets(HttpSession session, String[] list) {
        session.setAttribute("metroAmount", list[0]);
        session.setAttribute("busAmount", list[1]);
        session.setAttribute("tramAmount", list[2]);
        session.setAttribute("trolleyAmount", list[3]);
    }

    public static void fillUsedTickets(HttpSession session, ArrayList<Ticket> list) {
        if (list != null) {
            session.setAttribute("list", list);
        }
    }
}

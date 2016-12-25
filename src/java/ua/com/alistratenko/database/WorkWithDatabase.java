/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.alistratenko.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.com.alistratenko.entities.Ticket;
import ua.com.alistratenko.servlets.login;

/**
 *
 * @author Alistratenko Nikita
 */
public class WorkWithDatabase {

    public static String login(String login, String password) 
            throws SQLException {
        String result = null;
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            CallableStatement ct = ConnectionFactory.
                    connection.prepareCall("call loginuser(?,?,?)");
            ct.setString(1, login);
            ct.setString(2, password);
            ct.registerOutParameter(3, Types.CHAR);
            ct.execute();
            ConnectionFactory.connection.commit();
            result = ct.getString(3).trim();
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return result;
    }

    /**
     *
     * @param login
     * @return 0 - login, 1 - name, 2 - email, 3 - phone, 4 - city
     */
    public static ArrayList<String> getUserInfo(String login) 
            throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            Statement s = ConnectionFactory.connection.createStatement();
            ResultSet rs = s.executeQuery("select * from "
                    + "US where user_login='" + login + "'");
            rs.next();
            list.add(rs.getString(1).trim());
            list.add(rs.getString(2).trim());
            list.add(rs.getString(3).trim());
            if (rs.getString(4) != null) {
                list.add(rs.getString(4));
            } else {
                list.add("");
            }
            if (rs.getString(5) != null) {
                list.add(rs.getString(5));
            } else {
                list.add("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return list;
    }

    /**
     *
     * @param login
     * @return 0 - number, 1 - month, 2 - year, 3 - cvv, 4 - owner
     */
    public static ArrayList<String> getUserCCInfo(String login) 
            throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            Statement s = ConnectionFactory.connection.createStatement();
            ResultSet rs = s.executeQuery("select creditcard_number, "
                    + "cc_valid_to_month, cc_valid_to_year, cvv, "
                    + "cc_owner from USCR where user_login='"
                    + login + "' and status='active'");
            if (rs.next()) {
                list.add(rs.getString(1).trim());
                list.add(rs.getString(2).trim());
                list.add(rs.getString(3).trim());
                list.add(rs.getString(4).trim());
                list.add(rs.getString(5).trim());
            } else {
                list.add("");
                list.add("");
                list.add("");
                list.add("");
                list.add("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return list;
    }

    public static boolean registerUser
        (String login, String password, String email, String name, String phone, String city)
            throws SQLException {
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            String result = null;
            CallableStatement ct = ConnectionFactory.connection.
                    prepareCall("call registeruser(?,?,?,?,?,?,?)");
            ct.setString(1, login.trim());
            ct.setString(2, name.trim());
            ct.setString(3, password.trim());
            ct.setString(4, email.trim());
            ct.setString(5, phone.trim());
            ct.setString(6, city.trim());
            ct.registerOutParameter(7, Types.CHAR);
            ct.execute();
            result = ct.getString(7).trim();
            if (result.equals("1")) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return false;
    }

    public static void buyTickets
        (String login, String bus, String trolley, String metro, String tram) 
                throws SQLException {
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            if (bus != null) {
                if (Integer.valueOf(bus) > 0) {
                    CallableStatement cs = ConnectionFactory.connection.
                            prepareCall("call buyTickets(?,?,?,?)");
                    cs.setString(1, login);
                    cs.setInt(2, Integer.valueOf(bus));
                    cs.setString(3, "bus");
                    cs.setString(4, "3");
                    cs.execute();
                }
            }
            if (trolley != null) {
                if (Integer.valueOf(trolley) > 0) {
                    CallableStatement cs = ConnectionFactory.connection.
                            prepareCall("call buyTickets(?,?,?,?)");
                    cs.setString(1, login);
                    cs.setInt(2, Integer.valueOf(trolley));
                    cs.setString(3, "trolley");
                    cs.setString(4, "3");
                    cs.execute();
                }
            }
            if (metro != null) {
                if (Integer.valueOf(metro) > 0) {
                    CallableStatement cs = ConnectionFactory.connection.
                            prepareCall("call buyTickets(?,?,?,?)");
                    cs.setString(1, login);
                    cs.setInt(2, Integer.valueOf(metro));
                    cs.setString(3, "metro");
                    cs.setString(4, "4");
                    cs.execute();
                }
            }
            if (tram != null) {
                if (Integer.valueOf(tram) > 0) {
                    CallableStatement cs = ConnectionFactory.connection.
                            prepareCall("call buyTickets(?,?,?,?)");
                    cs.setString(1, login);
                    cs.setInt(2, Integer.valueOf(tram));
                    cs.setString(3, "tram");
                    cs.setString(4, "3");
                    cs.execute();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
    }

    public static String[] getBoughtTickets(String login) throws SQLException {
        String[] list = {"0", "0", "0", "0"};
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            Statement s = ConnectionFactory.connection.createStatement();
            ResultSet rs = s.executeQuery("select count(ticket_id) "
                    + "from ustick where user_login='" 
                    + login + "' and ticket_used_time is null "
                            + "and ticket_transport='metro'");
            if (rs.next()) {
                list[0] = rs.getString(1);
            }
            ResultSet rs1 = s.executeQuery("select count(ticket_id) "
                    + "from ustick where user_login='" + 
                    login + "' and ticket_used_time is null "
                            + "and ticket_transport='bus'");
            if (rs1.next()) {
                list[1] = rs1.getString(1);
            }
            ResultSet rs2 = s.executeQuery("select count(ticket_id) "
                    + "from ustick where user_login='" + 
                    login + "' and ticket_used_time is null "
                            + "and ticket_transport='tram'");
            if (rs2.next()) {
                list[2] = rs2.getString(1);
            }
            ResultSet rs3 = s.executeQuery("select count(ticket_id) "
                    + "from ustick where user_login='" + 
                    login + "' and ticket_used_time is null "
                            + "and ticket_transport='trolley'");
            if (rs3.next()) {
                list[3] = rs3.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return list;
    }

    public static ArrayList<Ticket> getUsedTickets(String login) 
            throws SQLException {
        ArrayList<Ticket> list = new ArrayList<>();
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            Statement s = ConnectionFactory.connection.createStatement();
            ResultSet rs = s.executeQuery("select ticket_id, "
                    + "ticket_transport, TO_CHAR(ticket_used_time, 'DD/MM/YY HH24:MI') "
                    + "from ustick where user_login='" + 
                    login + "' and ticket_used_time is not null "
                            + "order by ticket_used_time desc");
            while (rs.next()) {
                list.add(new Ticket(rs.getString(1).trim(), 
                        rs.getString(2).trim(), 
                        rs.getString(3).trim()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return list;
    }

    public static void useTickets
        (String login, String metro, String bus, String tram, String trolley) 
                throws SQLException {
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            if (metro != null && metro.equals("on")) {
                CallableStatement cs = ConnectionFactory.connection.
                        prepareCall("call activateTicket(?,?)");
                cs.setString(1, login);
                cs.setString(2, "metro");
                cs.execute();
            }
            if (bus != null && bus.equals("on")) {
                CallableStatement cs = ConnectionFactory.connection.
                        prepareCall("call activateTicket(?,?)");
                cs.setString(1, login);
                cs.setString(2, "bus");
                cs.execute();
            }
            if (tram != null && tram.equals("on")) {
                CallableStatement cs = ConnectionFactory.connection.
                        prepareCall("call activateTicket(?,?)");
                cs.setString(1, login);
                cs.setString(2, "tram");
                cs.execute();
            }
            if (trolley != null && trolley.equals("on")) {
                CallableStatement cs = ConnectionFactory.connection.
                        prepareCall("call activateTicket(?,?)");
                cs.setString(1, login);
                cs.setString(2, "trolley");
                cs.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }

    }

    public static String editUserInfo
        (String login, String name, String email, String phone, String city) 
                throws SQLException {
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            String res = null;
            CallableStatement cs = ConnectionFactory.connection.
                    prepareCall("call edituserinfo(?,?,?,?,?,?)");
            cs.setString(1, login);
            cs.setString(2, name);
            cs.setString(3, email);
            cs.setString(4, phone);
            cs.setString(5, city);
            cs.registerOutParameter(6, Types.CHAR);
            cs.execute();
            return cs.getString(6).trim();

        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return "";
    }

    public static boolean editCCInfo
        (String login, String owner, String number, String month, String year, String cvv) 
                throws SQLException {
        ConnectionFactory.connection.setAutoCommit(false);
        try {
            String res = null;
            CallableStatement cs = ConnectionFactory.connection.
                    prepareCall("call edituserccinfo(?,?,?,?,?,?,?)");
            cs.setString(1, login);
            cs.setString(2, owner);
            cs.setString(3, number);
            cs.setString(4, month);
            cs.setString(5, year);
            cs.setString(6, cvv);
            cs.registerOutParameter(7, Types.CHAR);
            cs.execute();
            res = cs.getString(7).trim();
            if (res.equals("0")) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkWithDatabase.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.connection.setAutoCommit(true);
        }
        return false;
    }
}

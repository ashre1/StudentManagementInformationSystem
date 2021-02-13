package com.nccs.sis.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;

public class LoginDAO {

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Statement stmt = null;

    public LoginDAO() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();

        } catch (Exception e) {
            System.out.println("Database.getConnection() Error -->" + e.getMessage());
            e.printStackTrace();
        }
    }

    public String sendName(String name) {
        String query = "SELECT Name From login_table WHERE UserName = '" + name + "'";
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                String actualName = rs.getString(1);
                return actualName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
      public String sendId(String name) {
        String query = "SELECT image_id From user_image WHERE UserName = '" + name + "'";
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                String id = rs.getString(1);
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    public boolean checkLogin(String name, String password, String userType) {

        String query = "SELECT * FROM login_table WHERE UserName = ? AND Password = ? AND UserType = ? ";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, userType);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPass(String userName, String password) {

        try {
            String query = "SELECT UserName FROM login_table WHERE UserName='" + userName + "' AND Password = '" + password + "'";
            System.out.println("M in checkPass");
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void update(String id, String pass) {
        try {
            String query = "UPDATE login_table SET Password = '" + pass + "' WHERE UserName = '" + id + "'";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    Calendar cal = Calendar.getInstance();

    public void log(String userName) {

        String date = (dateFormat.format(cal.getTime()));
        String time = (timeFormat.format(cal.getTime()));
        try {

            PreparedStatement pre = con.prepareStatement("INSERT INTO userlog(UserName,date,intime,outTime) VALUES(?,?,?,?)");
            PreparedStatement pre1 = con.prepareStatement("INSERT INTO check_user(UserName,Time) VALUES(?,?)");

            pre.setString(1, userName);
            pre.setString(2, date);
            pre.setString(3, time);
            pre.setString(4, "User login");
            pre.executeUpdate();
            pre.close();
            System.out.println("in");

            pre1.setString(1, userName);
            pre1.setString(2, time);
            pre1.executeUpdate();
            pre1.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean user(String userName) {
        try {
            PreparedStatement pre = con.prepareStatement("SELECT UserName FROM check_user WHERE UserName = ?");
            pre.setString(1, userName);
            rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String logTime;

    public void logout(String userName) {
        String time = (timeFormat.format(cal.getTime()));
        try {
            if (user(userName)) {
                PreparedStatement pre = con.prepareStatement("SELECT Time FROM check_user WHERE UserName = ?");
                pre.setString(1, userName);
                rs = pre.executeQuery();
                if (rs.next()) {
                    logTime = rs.getString("Time");
                }
                pre.close();

                PreparedStatement pre1 = con.prepareStatement("UPDATE userlog SET outTime=? WHERE UserName = ? AND intime=?");
                pre1.setString(1, time);
                pre1.setString(2, userName);
                pre1.setString(3, logTime);
                pre1.executeUpdate();
                pre1.close();

                PreparedStatement pre2 = con.prepareStatement("DELETE FROM check_user WHERE UserName=?");
                pre2.setString(1, userName);
                pre2.executeUpdate();
                pre2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void logout2(String userName) {
        String time = (timeFormat.format(cal.getTime()));
        int id = 0;
        try {
             PreparedStatement pre = con.prepareStatement("SELECT max(logId) FROM userlog WHERE UserName=?");
             pre.setString(1, userName);
              rs = pre.executeQuery();
                while (rs.next()) {
                    id = rs.getInt(1);
                }
             
            PreparedStatement pre1 = con.prepareStatement("UPDATE userlog SET outTime=? WHERE UserName = ? AND logId=?");
            pre1.setString(1, time);
            pre1.setString(2, userName);
            pre1.setInt(3, id);
            pre1.executeUpdate();
            pre1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

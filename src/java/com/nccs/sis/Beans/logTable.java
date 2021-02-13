package com.nccs.sis.Beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class logTable {

    private String userName;
    private String date;
    private String loginTime;
    private String logoutTime;
    private String imageID;
    private String imageName;

    logTable table = null;

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public logTable() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public logTable getTable() {
        return table;
    }

    public void setTable(logTable table) {
        this.table = table;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<logTable> getLogTable() {
        List<logTable> imageInfo = new ArrayList<logTable>();
        try {
            
            String strSql = "SELECT l.UserName,l.date,l.intime,l.outTime,i.image_id,i.image_name FROM userlog as l JOIN user_image as i on i.username=l.username ORDER BY l.logId DESC LIMIT 30";
            //  "select l.username,l.password,l.name,l.usertype,i.image_id,i.image_name from login_table as l Join user_image as i on i.username = l.username order by image_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                logTable tbl = new logTable();
                tbl.setUserName(rs.getString(1));
                tbl.setDate(rs.getString(2));
                tbl.setLoginTime(rs.getString(3));
                tbl.setLogoutTime(rs.getString(4));
                tbl.setImageID(rs.getString(5));
                tbl.setImageName(rs.getString(6));
                imageInfo.add(tbl);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

}

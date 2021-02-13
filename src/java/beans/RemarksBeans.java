/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "remarksBean")
public class RemarksBeans {

    private String regNo;
    private Date date;
    private String topic;
    private String details;
    private String actionTaken;
    public String fname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

//   public void insert(){
//       new RemarksDao().Insert(topic, fname, date, topic, details, actionTaken);
//   }
    Connection con;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rst;

    public RemarksBeans() {
        try {
            con = new Database().getConnection();
            stmt = con.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;

    }

    public void fullName() {
        try {
            String query = "Select firstname,middlename,lastname from student_info_table where student_id='" + regNo + "'";
            rst = stmt.executeQuery(query);
            while (rst.next()) {
                fname = rst.getString("firstname") + " " + rst.getString("middlename") + " " + rst.getString("lastname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RemarksBeans.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insert() {
          RequestContext content = RequestContext.getCurrentInstance();
            FacesMessage message = null;
        try {
            pstmt = con.prepareStatement("insert into rewardpunishmenttable(student_id,name,date,topic ,details,actionTaken) values(?,?,?,?,?,?)");
            pstmt.setString(1, regNo);
            pstmt.setString(2, fname);
            pstmt.setDate(3, convertUtilToSql(date));
            pstmt.setString(4, topic);
            pstmt.setString(5, details);
            pstmt.setString(6, actionTaken);
            int i = pstmt.executeUpdate();
            System.out.println(i);
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully inserted", "");
             setRegNo(null);
             setFname(null);
             setDate(null);
             setTopic(null);
             setDetails(null);
             setActionTaken(null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

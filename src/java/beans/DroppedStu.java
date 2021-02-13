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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "droppedBean")
@SessionScoped
public class DroppedStu {

    private String regno;
    private String sem;
    Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pstmt = null;
    private Date dateAd;
    private String reason;

    public DroppedStu() {
        try {
            con = new Database().getConnection();
            st = con.createStatement();
            rs = null;
        } catch (SQLException ex) {
            Logger.getLogger(DroppedStu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getDateAd() {
        return dateAd;
    }

    public void setDateAd(Date dateAd) {
        this.dateAd = dateAd;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public boolean Check() {
        try {
            String query = "SELECT Student_Id from current_std_table where student_id='" + regno + "'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void semester() {
        FacesContext fc = FacesContext.getCurrentInstance();
        System.out.println("semester");
        if (Check()) {
            System.out.println("check in");
            try {
                System.out.println(regno);
                String myQuery = "select current_semester from current_std_table where student_id='" + regno + "'";

                rs = st.executeQuery(myQuery);
                if (rs.next()) {
                    sem = rs.getString("current_semester");
                    fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Student Id ok", ""));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Student Id", ""));
            setRegno(null);
        }
    }


    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;

    }

    public void insert() {
       FacesContext fc = FacesContext.getCurrentInstance();
        try {
            System.out.println(sem);
            String myQuery1 = "delete from current_std_table where student_id='" + regno + "'";
            System.out.println("ccc");
            st.executeUpdate(myQuery1);

            java.sql.Date date = convertUtilToSql(dateAd);
            pstmt = con.prepareStatement("INSERT INTO drop_std_table(student_id,date,drop_semester,reason) VALUES (?,?,?,?)");
            pstmt.setString(1, regno);
            pstmt.setDate(2, date);
            pstmt.setString(3, sem);
            pstmt.setString(4, reason);
            pstmt.executeUpdate();
            fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Dropped", ""));
             setDateAd(null);
             setReason(null);
             setRegno(null);
             setSem(null);

        } catch (SQLException ex) {
            Logger.getLogger(DroppedStu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DroppedStu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

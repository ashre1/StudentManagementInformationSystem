/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dell
 */
@ManagedBean(name = "addBean")
@SessionScoped
public class AddSubject {

    public AddSubject() {
        reset();
    }
    ResultSet rs;

    private String subId;
    private String subName;
    private String sem;
    private String prog;
    private String[] sem1 = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth"};

    public void reset() {
        setSubId(null);
        setSubName(null);
        setSem(null);
        setProg(null);
    }

    public String[] getSem1() {
        return sem1;
    }

    public void setSem1(String[] sem1) {
        this.sem1 = sem1;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<SelectItem> getCategoryName() {
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {
            Connection con = new Database().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            String myQuery = "select faculty_name from faculty_table";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("faculty_name")));
            }
            con.close();
            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return program;
    }

    public void subjectName(){
        
        System.out.println("helloooooooo");
        try {
            Connection con = new Database().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            String myQuery = "select subject_name from subject_table where subject_id='"+subId+"'";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                
                setSubName(rs.getString("subject_name"));
                
            }
            con.close();
            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
    }

    int idm;

    public int facID(String name) {

        try {
            System.out.println(name);
            Connection con = new Database().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            String myQuery = "select faculty_id from faculty_table where faculty_name='" + name + "'";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                idm = rs.getInt("faculty_id");
                System.out.println(idm);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return idm;
    }

    public void delete(){
        try {
            RequestContext content = RequestContext.getCurrentInstance();
            FacesMessage message = null;
            String Sql="delete from subjecttaught where subject_id='"+subId+"'";
            Connection con = new Database().getConnection();
            Statement stmt=con.createStatement();
            int a=stmt.executeUpdate(Sql);
            Sql="delete from subject_table where subject_id='"+subId+"'";
            if(stmt.executeUpdate(Sql)==1){
                 message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted", "");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "");
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AddSubject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void upload() {
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {

            Connection con = new Database().getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO subject_table VALUES(?,?)");
            pstmt.setString(1, subId);
            pstmt.setString(2, subName);
            if (pstmt.executeUpdate() == 1) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, " Successfully Inserted", "");
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "");
            }
            pstmt = null;
            pstmt = con.prepareStatement("INSERT INTO subjecttaught(subject_id,semester,faculty_id)VALUES(?,?,?)");
            pstmt.setString(1, subId);
            pstmt.setString(2, sem);
            pstmt.setInt(3, facID(prog));
            pstmt.executeUpdate();
            con.close();
            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(AddSubject.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            reset();
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}

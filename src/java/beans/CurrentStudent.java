/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
@ManagedBean(name = "studBean")
@SessionScoped
public class CurrentStudent {

    public CurrentStudent() {

        try {
            con = new Database().getConnection();
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CurrentStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        reset();
    }

    private int year;
    private String prog;
    private String batch;
    private String csem;
    private String sem;
    private String[] semester = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth"};
    private String[] odd = {"Odd", "Even"};
    private String even;
    private String radio;
    private String regno;
    private String ID;
    private String fname;
    private String lname;
    private String mname;
    private String curSem;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getCurSem() {
        return curSem;
    }

    public void setCurSem(String curSem) {
        this.curSem = curSem;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getEven() {
        return even;
    }

    public void setEven(String even) {
        this.even = even;
    }

    public String getProg() {
        return prog;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCsem() {
        return csem;
    }

    public void setCsem(String csem) {
        this.csem = csem;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String[] getSemester() {
        return semester;
    }

    public void setSemester(String[] semester) {
        this.semester = semester;
    }

    public String[] getOdd() {
        return odd;
    }

    public void setOdd(String[] odd) {
        this.odd = odd;
    }

    int idm;
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public void reset() {
        setProg(null);
        setBatch(null);
        setSem(null);
        setEven(null);
    }

    public int facID(String name) {

        try {
            System.out.println(name);

            rs = null;
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
    String f;

    public String facID(int name) {

        try {
            System.out.println(name);

            rs = null;
            String myQuery = "select faculty_name from faculty_table where faculty_id='" + name + "'";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                f = rs.getString("faculty_name");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return f;
    }

    public List<SelectItem> getBatchFrom() {
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {

            rs = null;
            String myQuery = "select distinct batch from current_std_table where faculty_id='" + facID(prog) + "' ORDER BY batch";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("batch")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return program;
    }
    String program;

    public List<SelectItem> getCategoryName() {
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {

            rs = null;
            String myQuery = "select faculty_name from faculty_table";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("faculty_name")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return program;
    }

    public String getSemester1() {

        try {

            rs = null;
            String myQuery = "select distinct current_semester from current_std_table where batch ='" + batch + "'and faculty_id='" + facID(prog) + "'";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                program = rs.getString("current_semester");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return program;
    }

    public void update() {
        System.out.println("mmmmmmm");
        System.out.println(sem);
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {

            String query = "UPDATE current_std_table SET Current_Semester=?,current_year=?,oddeven=? where batch=? and faculty_id=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, sem);
            pstmt.setInt(2, year);
            pstmt.setString(3, even);
            pstmt.setString(4, batch);
            pstmt.setInt(5, facID(prog));
            if (pstmt.executeUpdate() >= 1) {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Table Updated", "");
                setBatch(null);
                setProg(null);
                setSem(null);
                setEven(null);
            } else {
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    
    public List<CurrentStudent> getAllImage() {
        List<CurrentStudent> imageInfo = new ArrayList<CurrentStudent>();
        try {
            String strSql = "select s.student_id,s.firstname,s.middlename,s.lastname,c.batch,c.current_year,c.faculty_id,c.current_semester from student_info_table as s inner join current_std_table as c on s.student_id=c.student_id where c.batch='" + batch + "' and c.faculty_id='" + facID(prog) + "'order by student_id ";
            //System.err.println("*select all***" + strSql);
            rs = st.executeQuery(strSql);
            while (rs.next()) {
                System.out.println(rs.getString("student_id"));
                String fname;
                CurrentStudent tbl = new CurrentStudent();
                tbl.setID(rs.getString("student_id"));
                tbl.setFname(rs.getString("firstname"));
                fname = rs.getString("firstname");
                tbl.setMname(rs.getString("middlename"));
                fname = fname + " " + rs.getString("middlename");
                tbl.setLname(rs.getString("lastname"));
                fname = fname + " " + rs.getString("lastname");
                tbl.setBatch(rs.getString("batch"));
                tbl.setYear(rs.getInt("current_year"));
                tbl.setProg(facID(rs.getInt("faculty_id")));
               // tbl.setCurSem(rs.getString("current_semester"));
                tbl.setFullName(fname);
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.Database;
import java.io.Serializable;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "viewAttnd")
@ViewScoped
public class ViewBean implements Serializable {

    private Date date;
    private String date1;
    private String remarks;
    private String firstName;
    private String middleName;
    private String lastName;
    private String student_id;
    private String fullName;

    private String batch;
    private String faculty;
    private String sec;
    private String fac_id;

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    private List<ViewBean> viewList;

    public ViewBean() {

        con = new Database().getConnection();
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ViewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String toDate(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String date_to_string = dateformat.format(date);
        return date_to_string;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getfacID(String name) {

        try {

            String myQuery = "select faculty_id from faculty_table where faculty_name='" + name + "'";

            rs = stmt.executeQuery(myQuery);

            while (rs.next()) {
                fac_id = rs.getString("faculty_id");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return fac_id;
    }

    /*public void check(){
     con = new DbConnection().getConnection();
     System.out.println("inside connection");
        
     System.out.println("SADASDASDASD");
     String qry= "Select Date from Test Where faculty_id = '1' AND section ='a' AND batch = '1'";
        
     try {
     stmt = con.createStatement();
     rs = stmt.executeQuery(qry);
     System.out.println("SADASDASDASD");

     while(rs.next()){
     System.out.println(rs.getString(1));
     }
     } catch (SQLException ex) {
     Logger.getLogger(ViewBean.class.getName()).log(Level.SEVERE, null, ex);
     }
     //  return "Attendance";
     }*/
    public List<SelectItem> getFacultyInfo() {
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {
            String myQuery = "select faculty_name from faculty_table";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("faculty_name")));
            }
//            con.close();
//            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return program;
    }

    public List<SelectItem> getBatchInfo() {

        List<SelectItem> batchList = new ArrayList<SelectItem>();
        try {

            String myQuery = "SELECT DISTINCT batch from current_std_table WHERE faculty_id='" + getfacID(faculty) + "'";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                batchList.add(new SelectItem(rs.getString("Batch")));
            }
//            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return batchList;

    }

    public String getSemesterInfo() {

        String sem = null;
        try {
            String myQuery = "select current_semester from current_std_table WHERE faculty_id='" + getfacID(faculty) + "' AND Batch='" + batch + "'";
            rs = stmt.executeQuery(myQuery);

            while (rs.next()) {
                sem = rs.getString("Current_Semester");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sem;
    }

    public List<SelectItem> getSectionInfo() {
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {
            String myQuery = "select distinct section from current_std_table WHERE faculty_id='" + getfacID(faculty) + "' AND Batch='" + batch + "'";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("section")));
            }
//            con.close();
//            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return program;
    }

    public List<ViewBean> getRemarksInfo() {

        viewList = new ArrayList<ViewBean>();

        try {
            String query = " SELECT A.Student_Id, S.FirstName,S.MiddleName, S.LastName, A.Date, A.Abs_Pre FROM attendance A "
                    + "            INNER JOIN student_info_table s "
                    + "            ON A.Student_Id = S.Student_Id"
                    + "            WHERE A.Faculty_Id = '" + getfacID(faculty) + "'"
                    + "            AND A.Section = '" + sec + "'"
                    + "            AND S.Batch ='" + batch + "'"
                    + "            AND A.DATE = '" + convertUtilToSql(date) + "' ORDER BY A.Student_Id";

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                ViewBean obj = new ViewBean();
                obj.setFirstName(rs.getString("FirstName"));
                obj.setMiddleName(rs.getString("MiddleName"));
                obj.setLastName(rs.getString("LastName"));
                obj.setDate1(rs.getString("Date"));
                obj.setStudent_id(rs.getString("Student_Id"));
                obj.setRemarks(rs.getString("Abs_Pre"));

                viewList.add(obj);

            }

        } catch (Exception e) {
        }

        return viewList;
    }

    public List<ViewBean> getFilterByAbsentOnly() {

        viewList = new ArrayList<ViewBean>();

        try {
            String query = " SELECT A.Student_Id, S.FirstName,S.MiddleName, S.LastName, A.Date, A.Abs_Pre FROM  attendance A "
                    + "            INNER JOIN student_info_table s "
                    + "            ON A.Student_Id = S.Student_Id"
                    + "            WHERE A.Faculty_Id = '" + getfacID(faculty) + "'"
                    + "            AND A.Section = '" + sec + "'"
                    + "            AND S.Batch ='" + batch + "'"
                    + "            AND A.DATE = '" + convertUtilToSql(date) + "' "
                    + "            AND A.Abs_Pre = 'Absent' "
                    + "            ORDER BY A.Student_Id";

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                ViewBean obj = new ViewBean();
                obj.setFirstName(rs.getString("FirstName"));
                obj.setMiddleName(rs.getString("MiddleName"));
                obj.setLastName(rs.getString("LastName"));
                obj.setDate1(rs.getString("Date"));
                obj.setStudent_id(rs.getString("Student_Id"));
                obj.setRemarks(rs.getString("Abs_Pre"));

                viewList.add(obj);

            }

        } catch (Exception e) {
        }

        return viewList;
    }

    public List<ViewBean> getFilterByBatch() {

        viewList = new ArrayList<ViewBean>();

        try {
            String query = " SELECT A.Student_Id, S.FirstName,S.MiddleName, S.LastName, A.Date, A.Abs_Pre FROM attendance A "
                    + "            INNER JOIN student_info_table s "
                    + "            ON A.Student_Id = S.Student_Id"
                    + "            WHERE A.Faculty_Id = '" + getfacID(faculty) + "'"
                    + "            AND S.Batch ='" + batch + "'"
                    + "            AND A.DATE = '" + convertUtilToSql(date) + "' "
                    //                    + "            AND A.Abs_Pre = 'Absent' "
                    + "            ORDER BY A.Student_Id";

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                ViewBean obj = new ViewBean();
                obj.setFirstName(rs.getString("FirstName"));
                obj.setMiddleName(rs.getString("MiddleName"));
                obj.setLastName(rs.getString("LastName"));
                obj.setDate1(rs.getString("Date"));
                obj.setStudent_id(rs.getString("Student_Id"));
                obj.setRemarks(rs.getString("Abs_Pre"));

                viewList.add(obj);

            }

        } catch (Exception e) {
        }

        return viewList;
    }

    public List<ViewBean> getFilterByBatchAbsOnly() {

        viewList = new ArrayList<ViewBean>();

        try {
            String query = " SELECT A.Student_Id, S.FirstName,S.MiddleName, S.LastName, A.Date, A.Abs_Pre FROM attendance A "
                    + "            INNER JOIN student_info_table s "
                    + "            ON A.Student_Id = S.Student_Id"
                    + "            WHERE A.Faculty_Id = '" + getfacID(faculty) + "'"
                    + "            AND S.Batch ='" + batch + "'"
                    + "            AND A.DATE = '" + convertUtilToSql(date) + "' "
                    + "            AND A.Abs_Pre = 'Absent' "
                    + "            ORDER BY A.Student_Id";

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                ViewBean obj = new ViewBean();
                obj.setFirstName(rs.getString("FirstName"));
                obj.setMiddleName(rs.getString("MiddleName"));
                obj.setLastName(rs.getString("LastName"));
                obj.setDate1(rs.getString("Date"));
                obj.setStudent_id(rs.getString("Student_Id"));
                obj.setRemarks(rs.getString("Abs_Pre"));

                viewList.add(obj);

            }

        } catch (Exception e) {
        }

        return viewList;
    }

    private String reason;
    ViewBean bean2;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ViewBean getBean2() {
        return bean2;
    }

    public void setBean2(ViewBean bean2) {
        this.bean2 = bean2;
    }

    public void update() {
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            String query = "Update attendance set remarks='" + reason + " ' where date='" + convertUtilToSql(date) + "' and student_id='" + bean2.student_id + "' and Abs_Pre='Absent'";
            int a = stmt.executeUpdate(query);
            if (a >= 1) {
                System.out.println("mmmamamamamamamamamam");
                 message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfull added reason", "");
            } else {
                System.out.println("mmamamammerror");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewBean.class.getName()).log(Level.SEVERE, null, ex);
        }
         FacesContext.getCurrentInstance().addMessage(null, message);

    }
    
      public void onDateSelect(SelectEvent e) {
        System.out.println("helloooooooooooooo......................s");
        FacesContext fc = FacesContext.getCurrentInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date1 = new Date();
        
       if(dateFormat.format(date).compareTo(dateFormat.format(date1))>0){
            fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Date!!!", ""));
            setDate(null);
        }
        
        
    }

}

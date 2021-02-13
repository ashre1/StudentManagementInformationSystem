/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "check")
@ViewScoped
public class Individual implements Serializable {

    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;

    private String gender;
    private String student_id;
    private String batch;
    private String firstName;
    private String middleName;
    private String lastName;
    private String year;
    private String plus2;
    private String perAdd;
    private String tempAdd;
    private String fatherName;
    private String fatheroccupation;
    private String fatherPhn;
    private String pluspercent;
    private String TUroll;
    private String guaname;
    private String goccupation;
    private String gphone;
    private String DOB;
    private String cmatScore;
    private String sem;
    private String sec;
    private String phone_no;
    private String mobileNo;
    private String abs_pre;
    private String remarks;
    private String fac_id;
    private String facultyName;
    private String date;
    private String punishDate;
    private String punishDetail;
    private String punishtopic;
    private String action;

    public String getPunishDate() {
        return punishDate;
    }

    public void setPunishDate(String punishDate) {
        this.punishDate = punishDate;
    }

    public String getPunishDetail() {
        return punishDetail;
    }

    public void setPunishDetail(String punishDetail) {
        this.punishDetail = punishDetail;
    }

    public String getPunishtopic() {
        return punishtopic;
    }

    public void setPunishtopic(String punishtopic) {
        this.punishtopic = punishtopic;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Individual() {

        try {
            con = new Database().getConnection();
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Individual.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlus2() {
        return plus2;
    }

    public void setPlus2(String plus2) {
        this.plus2 = plus2;
    }

    public String getPerAdd() {
        return perAdd;
    }

    public void setPerAdd(String perAdd) {
        this.perAdd = perAdd;
    }

    public String getTempAdd() {
        return tempAdd;
    }

    public void setTempAdd(String tempAdd) {
        this.tempAdd = tempAdd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatheroccupation() {
        return fatheroccupation;
    }

    public void setFatheroccupation(String fatheroccupation) {
        this.fatheroccupation = fatheroccupation;
    }

    public String getFatherPhn() {
        return fatherPhn;
    }

    public void setFatherPhn(String fatherPhn) {
        this.fatherPhn = fatherPhn;
    }

    public String getPluspercent() {
        return pluspercent;
    }

    public void setPluspercent(String pluspercent) {
        this.pluspercent = pluspercent;
    }

    public String getTUroll() {
        return TUroll;
    }

    public void setTUroll(String TUroll) {
        this.TUroll = TUroll;
    }

    public String getGuaname() {
        return guaname;
    }

    public void setGuaname(String guaname) {
        this.guaname = guaname;
    }

    public String getGoccupation() {
        return goccupation;
    }

    public void setGoccupation(String goccupation) {
        this.goccupation = goccupation;
    }

    public String getGphone() {
        return gphone;
    }

    public void setGphone(String gphone) {
        this.gphone = gphone;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCmatScore() {
        return cmatScore;
    }

    public void setCmatScore(String cmatScore) {
        this.cmatScore = cmatScore;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStudent_id() {
        System.out.println("STUDENT ID : " + student_id);
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAbs_pre() {
        return abs_pre;
    }

    public void setAbs_pre(String abs_pre) {
        this.abs_pre = abs_pre;
    }

    public String getRemarks() {

        if (remarks == null) {
            this.remarks = "No remarks..";
        }
        return remarks;

    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFac_id() {
        return fac_id;
    }

    public void setFac_id(String fac_id) {
        this.fac_id = fac_id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String ok = "false";

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public boolean checkID() {
        try {
            String query = "SELECT student_id from student_info_table where student_id = '" + student_id + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void information() {

        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        if (checkID()) {
            ok = "true";
            System.out.println("====================================================================================================");
            try {
                String query = "SELECT firstname,middlename,lastname,gender,DOB_english,mobileno,batch,cmat_score,year,faculty_id from student_info_table where student_id='" + student_id + "'";

                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    setFirstName(rs.getString(1));
                    setMiddleName(rs.getString(2));
                    setLastName(rs.getString(3));
                    setGender(rs.getString(4));
                    setDOB(rs.getString(5));
                    setMobileNo(rs.getString(6));
                    setBatch(rs.getString(7));
                    setCmatScore(rs.getString(8));
                    setYear(rs.getString(9));
                    setFac_id(rs.getString(10));
                }

                String query1 = "SELECT fullname,occupation,mobileno from family_details_table where student_id ='" + student_id + "' AND  relation like 'father' ";
                rs = stmt.executeQuery(query1);
                while (rs.next()) {
                    setFatherName(rs.getString(1));
                    setFatheroccupation(rs.getString(2));
                    setFatherPhn(rs.getString(3));
                }

                String query2 = "SELECT fullname,occupation,mobileno from family_details_table where student_id ='" + student_id + "' AND  relation like 'guardian' ";
                rs = stmt.executeQuery(query2);
                while (rs.next()) {
                    setGuaname(rs.getString(1));
                    setGoccupation(rs.getString(2));
                    setGphone(rs.getString(3));
                }

                String query3 = "SELECT faculty_name from faculty_table where faculty_id = '" + fac_id + "'";
                rs = stmt.executeQuery(query3);
                while (rs.next()) {
                    setFacultyName(rs.getString(1));
                }

                String query4 = "Select wardno,street,district from address_table where student_id = '" + student_id + "' AND addresstype like 'permanent' ";
                rs = stmt.executeQuery(query4);
                while (rs.next()) {
                    setPerAdd(rs.getString(2) + "," + rs.getString(1) + "," + rs.getString(3));
                }

                String query5 = "Select wardno,street,district from address_table where student_id = '" + student_id + "' AND addresstype like 'temprorary' ";
                rs = stmt.executeQuery(query5);
                while (rs.next()) {
                    setTempAdd(rs.getString(2) + "," + rs.getString(1) + "," + rs.getString(3));
                }

                String query6 = "Select institution,percentage from educational_detail_table where student_id = '" + student_id + "' AND board like 'HSEB'";
                rs = stmt.executeQuery(query6);
                while (rs.next()) {

                    setPlus2(rs.getString(1));
                    setPluspercent(rs.getString(2));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Registration No", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ok = "false";
        }

    }

    public List<Individual> getAttndRecord() {

        List<Individual> list = new ArrayList<>();

        String query = "SELECT Abs_Pre, Remarks, Date From Attendance where Student_id Like '" + student_id + "'";

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Individual rec = new Individual();

                rec.abs_pre = rs.getString(1);
                rec.remarks = rs.getString(2);
                rec.date = rs.getString(3);

                list.add(rec);
            }
        } catch (Exception E) {

            E.printStackTrace();
        }

        return list;
    }

    public List<Individual> getPunishmentRecord() {

        List<Individual> list1 = new ArrayList<>();

        String query = "SELECT Date,Topic,Details,ActionTaken From rewardpunishmenttable where Student_id = '" + student_id + "'";

        try {

            rs = stmt.executeQuery(query);

            while (rs.next()) {

                Individual rec = new Individual();

                rec.punishDate = rs.getString(1);
                rec.punishtopic = rs.getString(2);
                rec.punishDetail = rs.getString(3);
                rec.action = rs.getString(4);

                list1.add(rec);
            }
        } catch (Exception E) {

            E.printStackTrace();
        }

        return list1;

    }

}

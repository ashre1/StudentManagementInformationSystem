package beans;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tableBean")
@SessionScoped

public class TableBean {

    private String ID;
    private String Name;
    private String LastName;
    private String middlename;
    private String batch;
    private String year;
    private String gender;
    private String nationality;
    private String religion;
    private String email;
    private String mobno;
    private String citizenshipNO;
    private Date dateAd;

    public Date getDateAd() {
        return dateAd;
    }

    public void setDateAd(Date dateAd) {
        this.dateAd = dateAd;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getCitizenshipNO() {
        return citizenshipNO;
    }

    public void setCitizenshipNO(String citizenshipNO) {
        this.citizenshipNO = citizenshipNO;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getBatch() {
        return batch;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    

   
    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;

    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    public List<TableBean> getAllImage() {
        List<TableBean> imageInfo = new ArrayList<TableBean>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TableBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "");

            stmt = con.createStatement();
            String strSql = "select * from student_info_table order by student_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBean tbl = new TableBean();
                tbl.setID(rs.getString("student_id"));
                tbl.setName(rs.getString("firstname"));
                tbl.setMiddlename(rs.getString("middlename"));
                tbl.setLastName(rs.getString("lastname"));
                tbl.setBatch(rs.getString("batch"));
                tbl.setYear(rs.getString("year"));
                tbl.setGender(rs.getString("gender"));
                tbl.setReligion(rs.getString("religion"));
                tbl.setNationality(rs.getString("nationality"));
                tbl.setCitizenshipNO(rs.getString("citizenshipno"));
                tbl.setEmail(rs.getString("email"));
                tbl.setMobno(rs.getString("mobileno"));
                tbl.setDateAd((java.util.Date)rs.getDate("dob_english"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
}

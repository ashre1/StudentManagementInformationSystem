/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "bean")
@ViewScoped
public class AttendanceBackingBean implements Serializable {

    private String faculty;
    private String batch;
    private String semester;
    private String facId;

    private List<SelectItem> selectedStudents;
    private List<SelectItem> studentList;

    private String selectedFaculty;
    private String selectedSemester;
    private String selectedSection;
    private String selectedBatch;
    private String section;

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    Statement stmt = null;

    private String firstName;
    private String middleName;
    private String lastName;
    private String std_id;
    private String fullName;
    private Date date;

    private String checkDate;
    private String checkStudentId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    AttendanceBackingBean a = null;

    private List<String> listPresent = new ArrayList<>();
    private List<String> listAbsent = new ArrayList<>();
    private List<String> std_idList = new ArrayList<>();

    public AttendanceBackingBean() {
        try {
            con = new Database().getConnection();
            stmt = con.createStatement();
            rs = null;

        } catch (Exception e) {
        }
    }

    public List<String> getStd_idList() {

        try {
            String myQuery = "SELECT s.student_id,s.firstname,s.middlename,s.lastname FROM student_info_table AS S INNER JOIN Current_std_table AS C ON S.Student_id = C.student_id WHERE c.faculty_id='" + getfacID(faculty) + "' AND C.Batch ='" + batch + "'and C.section ='" + section + "'";
//            String myQuery = "SELECT firstname,middlename,lastname from student_info_table";
            System.out.println("ID taking succesful");

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                String std_id = rs.getString("Student_id");

                std_idList.add(std_id);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        for (String str : std_idList) {

            System.out.println("Std_id from std_idList =  " + str);

        }
        return std_idList;
    }

    public void setStd_idList(List<String> std_idList) {
        this.std_idList = std_idList;
    }

    public AttendanceBackingBean getA() {
        return a;
    }

    public void setA(AttendanceBackingBean a) {
        this.a = a;
    }

    public void add(String s) {

        System.out.println("S" + s);

        if (value) {
            listAbsent.add(s);
            System.out.println("ABSent: " + s);
        } else {
            for (String a : listAbsent) {
                if (a.equalsIgnoreCase(s)) {
                    System.out.println("Remove" + a);
                    listAbsent.remove(a);
                }
            }
        }
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;

    }

    public void fun() {
        FacesContext fc = FacesContext.getCurrentInstance();

        getStd_idList();
        Collection<String> idList = new HashSet<String>(std_idList);
        Collection<String> absentList = new HashSet<String>(listAbsent);

        Collection<String> absent = new HashSet<String>(listAbsent);
        Collection<String> present = new HashSet<String>();

        present.addAll(std_idList);
        absent.addAll(listAbsent);

        absent.retainAll(idList);
        present.removeAll(absent);

        System.out.printf("All list:%s%nAbsent:%s%nAbsent:%s%nPresent:%s%n", idList, absentList, absent, present);
        for (String id : present) {
            try {

//                String sql = "SELECT Date,Student_id from Attendance Where Date ='" +convertUtilToSql(date)+ "'AND Student_Id ='" +id+"'";
                System.out.println("try!!");
                ps = con.prepareStatement("INSERT INTO attendance(Student_Id,Faculty_Id,Section,Date,Abs_Pre,Batch) VALUES (?,?,?,?,?,?) ");

                System.out.println("date :" + date);

                ps.setString(1, id);
                ps.setString(2, facId);
                ps.setString(3, section);
                ps.setDate(4, convertUtilToSql(date));
                ps.setString(5, "Present");
                ps.setString(6, batch);

                ps.executeUpdate();

            } catch (Exception e) {

                e.getMessage();
            }

        }
        for (String abs : listAbsent) {

            try {
                ps = con.prepareStatement("INSERT INTO attendance(Student_Id,Faculty_Id,Section,Date,Abs_Pre,Batch)  VALUES (?,?,?,?,?,?)");

                ps.setString(1, abs);
                ps.setString(2, facId);
                ps.setString(3, section);
                ps.setDate(4, convertUtilToSql(date));
                ps.setString(5, "Absent");
                ps.setString(6, batch);

                ps.executeUpdate();

            } catch (Exception e) {
                e.getMessage();
            }
        }

        fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successful!!!", ""));
        setFaculty(null);
        setBatch(null);
        setSection(null);
        setDate(null);
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

    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getFullName() {
        fullName = firstName + middleName + lastName;
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSelectedBatch() {
        return selectedBatch;
    }

    public void setSelectedBatch(String selectedBatch) {
        this.selectedBatch = selectedBatch;
    }

    public String getSelectedFaculty() {
        return selectedFaculty;
    }

    public void setSelectedFaculty(String selectedFaculty) {
        this.selectedFaculty = selectedFaculty;
    }

    public String getSelectedSemester() {
        return selectedSemester;
    }

    public void setSelectedSemester(String selectedSemester) {
        this.selectedSemester = selectedSemester;
    }

    public String getSelectedSection() {
        return selectedSection;
    }

    public void setSelectedSection(String selectedSection) {
        this.selectedSection = selectedSection;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckStudentId() {
        return checkStudentId;
    }

    public void setCheckStudentId(String checkStudentId) {
        this.checkStudentId = checkStudentId;
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

    public List<SelectItem> getStudentInfo() {
        List<SelectItem> studentList = new ArrayList<>();

        try {
            String myQuery = "SELECT s.student_id,s.firstname,s.middlename,s.lastname FROM student_info_table AS S INNER JOIN Current_std_table AS C ON S.Student_id = C.student_id WHERE c.faculty_id='" + getfacID(faculty) + "' AND C.Batch ='" + batch + "'and C.section ='" + section + "'";
//            String myQuery = "SELECT firstname,middlename,lastname from student_info_table";
            System.out.println("INJ DONE");

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                String fullName = rs.getString("FirstName") + " " + rs.getString("MiddleName") + " " + rs.getString("LastName");
                String std_id = rs.getString("Student_id");

                studentList.add(new SelectItem(fullName));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return studentList;
    }

    public List<SelectItem> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<SelectItem> studentList) {
        this.studentList = studentList;
    }

    public void setSelectedStudents(List<SelectItem> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public List<SelectItem> getSelectedStudents() {

        System.out.println("DISPLAY:" + selectedStudents);
        return selectedStudents;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;

    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void reset() {
        setFaculty(null);
        setBatch(null);
        setSemester(null);
    }

    public String getSemesterInfo() {

        String sem = null;
        try {
            String myQuery = "select current_semester from current_std_table WHERE faculty_id='" + getfacID(faculty) + "' AND Batch='" + batch + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(myQuery);

            while (rs.next()) {
                sem = rs.getString("Current_Semester");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sem;
    }

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

    public String getfacID(String name) {

        try {
            System.out.println(name);
            String myQuery = "select faculty_id from faculty_table where faculty_name='" + name + "'";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                facId = rs.getString("faculty_id");
                System.out.println(facId);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return facId;
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

    public List<AttendanceBackingBean> getStudentNews() {
        List<AttendanceBackingBean> list = new ArrayList<>();

        try {
            String myQuery = "SELECT s.student_id,s.firstname,s.middlename,s.lastname FROM student_info_table AS S INNER JOIN Current_std_table AS C ON S.Student_id = C.student_id WHERE c.faculty_id='" + getfacID(faculty) + "' AND C.Batch ='" + batch + "'and C.section ='" + section + "'";
//            String myQuery = "SELECT firstname,middlename,lastname from student_info_table";
            System.out.println("INJ DONE");

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {

                AttendanceBackingBean obj = new AttendanceBackingBean();

                obj.setStd_id(rs.getString("Student_id"));
                obj.setFirstName(rs.getString("FirstName"));
                obj.setMiddleName(rs.getString("MiddleName"));
                obj.setLastName(rs.getString("LastName"));

                list.add(obj);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

    private boolean value;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    /**
     * *********************************************************************************************
     *
     *
     * VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
     * IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
     * EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
     * WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWw
     *
     *
     *
     */
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Student Attendance ", " Insertion Successful ");

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    /**
     * ************************************* DATE CHECK
     * *******************************************
     */
    /*    public void checkDate() {

     try {
     String query = "SELECT Date, Section ,  Student_id FROM attendance";
     rs = stmt.executeQuery(query);

     while (rs.next()) {

     if (rs.getDate("Date") == date && rs.getString("Student_id") == ) {

     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Student Attendance ", " Already inserted in selected date..");

     RequestContext.getCurrentInstance().showMessageInDialog(message);
                    
     this.date = null;
     this.batch = null;
     this.faculty = null;
     this.semester = null;
     this.section = null;

     }
     else{
                
     fun();
     }

     }

     } catch (Exception e) {

     e.getMessage();
     }
     }
     */
    public void onDateSelect(SelectEvent e) {
        System.out.println("helloooooooooooooo......................s");
        FacesContext fc = FacesContext.getCurrentInstance();
        if (check()) {
            fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Attendance already done!!!", ""));
            setDate(null);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date1 = new Date();
        
       if(dateFormat.format(date).compareTo(dateFormat.format(date1))>0){
            fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid Date!!!", ""));
            setDate(null);
        }
        
        
    }

    public boolean check() {
        System.out.println("hello");

        if (faculty.equalsIgnoreCase("BSCCSIT")) {

            String query = "SELECT Date FROM Attendance where batch ='" + batch + "' AND faculty_id = 3 AND DATE ='" + convertUtilToSql(date) + "'";
            try {
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (faculty.equalsIgnoreCase("BIM")) {
            String query = "SELECT Date FROM Attendance where batch ='" + batch + "' AND faculty_id = 1 AND DATE ='" + convertUtilToSql(date) + "'";
            try {
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    System.out.println("BIM KO VITRA");
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (faculty.equalsIgnoreCase("BHM")) {
            String query = "SELECT Date FROM Attendance where batch ='" + batch + "'  AND DATE ='" + convertUtilToSql(date) + "'";
            try {
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

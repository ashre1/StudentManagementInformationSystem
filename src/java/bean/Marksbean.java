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
import java.util.Arrays;
import java.util.Date;
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
@ManagedBean(name = "marksBean")
@SessionScoped
public class Marksbean {

    public Marksbean() {

        subject = new String[20];
        ExamId = new String[20];
        dateAd = new Date[20];
        subMarks = new String[20];
    }

    ResultSet rs = null;
    private String prog;
    private String batch;
    private String csem;
    private String examType;
    private String[] subject;
    int count;
    private Date[] dateAd;
    private String id;
    private String fname;
    private String mname;
    private String lname;
    private String fullName;
    private String[] subMarks;
    private Marksbean marks;
    
    public void done(){
        setProg(null);
        setBatch(null);
        setExamType(null);
    }

    public Marksbean getMarks() {
        return marks;
    }

    public void setMarks(Marksbean marks) {
        this.marks = marks;
    }

    public String[] getSubMarks() {
        return subMarks;
    }

    public void setSubMarks(String[] subMarks) {
        this.subMarks = subMarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date[] getDateAd() {
        return dateAd;
    }

    public void setDateAd(Date[] dateAd) {
        this.dateAd = dateAd;
    }

    private String[] ExamId;

    public String[] getExamId() {
        return ExamId;
    }

    public void setExamId(String[] ExamId) {
        this.ExamId = ExamId;
    }

    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    int idm;

    public int facID1(String name) {
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            System.out.println(name);

            rs = null;
            String myQuery = "select faculty_id from faculty_table where faculty_name='" + name + "'";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                idm = rs.getInt("faculty_id");
                System.out.println(idm);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return idm;
    }

    public List<SelectItem> getCategoryName() {
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {

            rs = null;
            String myQuery = "select faculty_name from faculty_table";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("faculty_name")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return program;
    }

    public List<SelectItem> getBatchFrom() {
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {

            rs = null;
            String myQuery = "select distinct batch from current_std_table where faculty_id='" + facID1(prog) + "' ORDER BY batch";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("batch")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return program;
    }

    public List<SelectItem> getExamTypeList() {
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {

            rs = null;
            String myQuery = "select * from exam_type_detail";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                program.add(new SelectItem(rs.getString("ExamType")));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return program;
    }

    public void semester() {
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            rs = null;
            String myQuery = "select distinct current_semester from current_std_table where batch ='" + batch + "'and faculty_id='" + facID1(prog) + "'";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                String program = rs.getString("current_semester");
                csem = program;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        subjectName();

    }

    public void subjectName() {
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            System.out.println("semester subject=" + csem);
            System.out.println("semester=" + facID1(prog));
            String query = "select s.subject_name from subject_table as s inner join subjecttaught as t on s.subject_id=t.subject_id where semester='" + csem + "' and faculty_id='" + facID1(prog) + "'";
            rs = stmt.executeQuery(query);
            count = 0;
            System.out.println("before while subject");
            while (rs.next()) {
                System.out.println("while subject");

                subject[count] = rs.getString("subject_name");
                count++;
            }
            System.out.println("after while subject");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("count=" + count);
        }
    }

    public String subjectId(String name) {
        String id = "0";
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            String query = "select subject_id from subject_table where subject_name='" + name + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getString("subject_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }
    }

    public int marksTypeId(String name) {
        int id = 0;
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            String query = "select examtype_id from exam_type_detail where examtype='" + name + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("examtype_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }
    }

    public void addDate() {
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;

        String examId = "0";
        if (examType.equalsIgnoreCase("Class test")) {
            examId = "CT";
        } else if (examType.equalsIgnoreCase("midterm")) {
            examId = "MT";
        } else if (examType.equalsIgnoreCase("pre-board")) {
            examId = "PB";
        } else if (examType.equalsIgnoreCase("board")) {
            examId = "B";
        }

        if (count == 1) {
            for (int i = 0; i < 1; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into exam_detail_table (Exam_Id, Faculty_Id, Batch, Semester, Subject_Id,ExamTypeID) Values  (?,?,?,?,?,?)";
                try {
                    ExamId[i] = examId + facID1(prog) + subjectId(subject[i]) + batch;
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setInt(2, facID1(prog));
                    pstmt.setString(3, batch);
                    pstmt.setString(4, csem);
                    pstmt.setString(5, subjectId(subject[i]));

                    pstmt.setInt(6, marksTypeId(examType));
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                } catch (SQLException ex) {
                    System.out.println("a");
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {

                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (count == 3) {
            for (int i = 0; i < 3; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into exam_detail_table (Exam_Id, Faculty_Id, Batch, Semester, Subject_Id,ExamTypeID) Values  (?,?,?,?,?,?)";
                try {
                    ExamId[i] = examId + facID1(prog) + subjectId(subject[i]) + batch;
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setInt(2, facID1(prog));
                    pstmt.setString(3, batch);
                    pstmt.setString(4, csem);
                    pstmt.setString(5, subjectId(subject[i]));

                    pstmt.setInt(6, marksTypeId(examType));
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                } catch (SQLException ex) {
                    System.out.println("a");
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (count == 4) {
            for (int i = 0; i < 4; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into exam_detail_table (Exam_Id, Faculty_Id, Batch, Semester, Subject_Id,ExamTypeID) Values  (?,?,?,?,?,?)";
                try {
                    ExamId[i] = examId + facID1(prog) + subjectId(subject[i]) + batch;
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setInt(2, facID1(prog));
                    pstmt.setString(3, batch);
                    pstmt.setString(4, csem);
                    pstmt.setString(5, subjectId(subject[i]));

                    pstmt.setInt(6, marksTypeId(examType));
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                } catch (SQLException ex) {
                    System.out.println("a");
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (count == 5) {
            for (int i = 0; i < 5; i++) {
                System.out.println("cd");
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                System.out.println("cd2");
                String query = "insert into exam_detail_table (Exam_Id, Faculty_Id, Batch, Semester, Subject_Id,ExamTypeID) Values  (?,?,?,?,?,?)";
                try {
                    ExamId[i] = examId + facID1(prog) + subjectId(subject[i]) + batch;
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setInt(2, facID1(prog));
                    pstmt.setString(3, batch);
                    pstmt.setString(4, csem);
                    pstmt.setString(5, subjectId(subject[i]));

                    pstmt.setInt(6, marksTypeId(examType));
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                } catch (SQLException ex) {
                    System.out.println("a1");
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (count == 6) {
            for (int i = 0; i < 6; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into exam_detail_table (Exam_Id, Faculty_Id, Batch, Semester, Subject_Id,ExamTypeID) Values  (?,?,?,?,?,?)";
                try {
                    ExamId[i] = examId + facID1(prog) + subjectId(subject[i]) + batch;
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setInt(2, facID1(prog));
                    pstmt.setString(3, batch);
                    pstmt.setString(4, csem);
                    pstmt.setString(5, subjectId(subject[i]));

                    pstmt.setInt(6, marksTypeId(examType));
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                } catch (SQLException ex) {

                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (count == 7) {
            for (int i = 0; i < 7; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into exam_detail_table (Exam_Id, Faculty_Id, Batch, Semester, Subject_Id,ExamTypeID) Values  (?,?,?,?,?,?)";
                try {
                    ExamId[i] = examId + facID1(prog) + subjectId(subject[i]) + batch;
                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setInt(2, facID1(prog));
                    pstmt.setString(3, batch);
                    pstmt.setString(4, csem);
                    pstmt.setString(5, subjectId(subject[i]));

                    pstmt.setInt(6, marksTypeId(examType));
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                } catch (SQLException ex) {
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }

    }

    public List<Marksbean> getStudentName() {
        List<Marksbean> student = new ArrayList<Marksbean>();
        Connection con = new Database().getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String query = "Select s.student_id,s.firstname,s.middlename,s.lastname from student_info_table as s inner join current_std_table as t on s.student_id=t.student_id where t.batch='" + batch + "' and t.faculty_id='" + facID1(prog) + "'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String fname;
                Marksbean mbean = new Marksbean();
                mbean.setId(rs.getString("student_id"));
                fname = rs.getString("firstname");
                mbean.setMname(rs.getString("middlename"));
                fname = fname + " " + rs.getString("middlename");
                mbean.setLname(rs.getString("lastname"));
                fname = fname + " " + rs.getString("lastname");
                mbean.setFullName(fname);
                student.add(mbean);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return student;
        }
    }

    public void addMarks() {
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;

        if (count == 1) {
            for (int i = 0; i < 1; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into obtained_marks_table (Exam_Id, Student_id,marks) Values  (?,?,?)";
                try {

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setString(2, marks.id);
                    pstmt.setString(3, subMarks[i]);
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                    if (i == 0) {
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added marks", "");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (count == 3) {
            for (int i = 0; i < 3; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into obtained_marks_table (Exam_Id, Student_id,marks) Values  (?,?,?)";
                try {

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setString(2, marks.id);
                    pstmt.setString(3, subMarks[i]);
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                   
                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added marks", "");

                } catch (SQLException ex) {
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                setMarks(null);
            }
        } else if (count == 4) {
            for (int i = 0; i < 4; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into obtained_marks_table (Exam_Id, Student_id,marks) Values  (?,?,?)";
                try {

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setString(2, marks.id);
                    pstmt.setString(3, subMarks[i]);
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                    if (i == 3) {
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added marks", "");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (count == 5) {
            for (int i = 0; i < 5; i++) {
                System.out.println("cd");
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                System.out.println("cd2");
                String query = "insert into obtained_marks_table (Exam_Id, Student_id,marks) Values  (?,?,?)";
                try {

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setString(2, marks.id);
                    pstmt.setString(3, subMarks[i]);
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                    if (i == 4) {
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added marks", "");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (count == 6) {
            for (int i = 0; i < 6; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into obtained_marks_table (Exam_Id, Student_id,marks) Values  (?,?,?)";
                try {

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setString(2, marks.id);
                    pstmt.setString(3, subMarks[i]);
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                    if (i == 5) {
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added marks", "");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (count == 7) {
            for (int i = 0; i < 7; i++) {
                Connection con = new Database().getConnection();
                PreparedStatement pstmt = null;
                String query = "insert into obtained_marks_table (Exam_Id, Student_id,marks) Values  (?,?,?)";
                try {

                    pstmt = con.prepareStatement(query);
                    pstmt.setString(1, ExamId[i]);
                    pstmt.setString(2, marks.id);
                    pstmt.setString(3, subMarks[i]);
                    int a = pstmt.executeUpdate();
                    System.out.println(a);
                    if (i == 6) {
                        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added marks", "");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        con.close();

                    } catch (SQLException ex) {
                        Logger.getLogger(Marksbean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
        Arrays.fill(subMarks, null);
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

}

package beans;

import dao.Database;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "marks")
@ViewScoped
public class MarksBean implements Serializable {

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    private int idm;
    private String firstname, lastname, std_id, date;
    private Float marks;
    private String subject, subid;
    private String batch;
    private String selectedBatch;
    private String program;
    private String selectedProgram;
    private String semester;
    private String selectedSemester;
    private String examType;

    public List<MarksBean> markSheet = new ArrayList<>();
    public List<MarksBean> sheet = new ArrayList<>();

    public MarksBean() {
        con = new Database().getConnection();
    }

    public List<MarksBean> getSheet() {
        List<MarksBean> subList = new ArrayList<MarksBean>();
        try {

            Statement stmt = con.createStatement();
            String query = "Select distinct Subject_Id from obtained_marks_table";
            ResultSet rs = stmt.executeQuery(query);
            boolean found = false;
            while (rs.next()) {
                MarksBean marksBean = new MarksBean();
                marksBean.setSubject(rs.getString("Subject_Id"));
                subList.add(marksBean);
                found = true;
            }
            rs.close();
            if (found) {
                return subList;
            } else {
                return null; // no entires found
            }
        } catch (Exception e) {
            System.out.println("Error In getSheet() -->" + e.getMessage());
            return (null);
        }
    }

    public void setSheet(List<MarksBean> sheet) {
        this.sheet = sheet;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStd_id() {
        return std_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Float getMarks() {
        return marks;
    }

    public void setMarks(Float marks) {
        this.marks = marks;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSelectedSemester() {
        return selectedSemester;
    }

    public void setSelectedSemester(String selectedSemester) {
        this.selectedSemester = selectedSemester;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSelectedProgram() {
        return selectedProgram;
    }

    public void setSelectedProgram(String selectedProgram) {
        this.selectedProgram = selectedProgram;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSelectedBatch() {
        return selectedBatch;
    }

    public void setSelectedBatch(String selectedBatch) {
        this.selectedBatch = selectedBatch;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public List<SelectItem> getProgramInfo() {
        List<SelectItem> programList = new ArrayList<SelectItem>();
        try {

            st = con.createStatement();
            String query = "SELECT Faculty_Name FROM faculty_table";

            ResultSet resultSet = st.executeQuery(query);
            while (resultSet.next()) {
                programList.add(new SelectItem(resultSet.getString("Faculty_Name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return programList;
    }

    public int facID(String name) {
        System.out.println("Name:" + name);
        try {
            System.out.println("inside fact id");
            System.out.println(name);
            st = con.createStatement();
            String query = "SELECT faculty_id from faculty_table where faculty_name='" + name + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                idm = rs.getInt("faculty_id");
                System.out.println("faculty id" +idm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("faculty id " + idm);
        return idm;
    }
    

    public List<SelectItem> getBatchInfo() {
        List<SelectItem> batchList = new ArrayList<SelectItem>();
        try {
            //con = new Database().getConnection();
            st = con.createStatement();
            System.out.println("Program:::" + program);
            String query = "SELECT DISTINCT Batch FROM current_std_table WHERE faculty_id='" + facID(program) + "' ORDER BY batch";

            rs = st.executeQuery(query);
            while (rs.next()) {
                batchList.add(new SelectItem(rs.getString("Batch")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return batchList;
    }

    public List<SelectItem> getSemesterInfo() {
        List<SelectItem> subList = new ArrayList<SelectItem>();
        try {
            //con = new Database().getConnection();
            System.out.println("conn");
            st = con.createStatement();
            System.out.println("st");
            String query = "SELECT DISTINCT Current_Semester from current_std_table WHERE Faculty_Id='" + facID(program) + "' AND Batch='" + batch + "'";
            System.out.println("query");
            rs = st.executeQuery(query);
            System.out.println("rs");
            while (rs.next()) {
                subList.add(new SelectItem(rs.getString("Current_Semester")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subList;
    }

    public List<SelectItem> getSubjectInfo() {
        List<SelectItem> subjectList = new ArrayList<SelectItem>();
        try {
            con = new Database().getConnection();
            st = con.createStatement();
            String query = "SELECT s.Subject_Name FROM subject_table s "
                    + "INNER JOIN subjecttaught st "
                    + "ON s.Subject_ID = st.Subject_Id "
                    + "WHERE st.semester='" + semester + "'";

            rs = st.executeQuery(query);
            while (rs.next()) {
                subjectList.add(new SelectItem(rs.getString("Subject_Name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    public String subID(String sub) {
        try {
            System.out.println(sub);
            st = con.createStatement();
            String query = "SELECT Subject_Id from subject_table where Subject_Name='" + sub + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                subid = rs.getString("Subject_Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subid;
    }

    public List<MarksBean> getMarkSheet() {
        System.out.println("========================");
        List<MarksBean> marksInfo = new ArrayList<>();
        try {
            System.out.println("   ____________________________");
            Statement stmt = con.createStatement();
            String query = "SELECT s.Student_Id,s.FirstName,s.LastName,o.Marks\n"
                    + "FROM student_info_table s\n"
                    + "INNER JOIN obtained_marks_table o\n"
                    + "ON s.Student_Id=o.Student_Id\n"
                    + "INNER JOIN exam_detail_table e\n"
                    + "ON o.Exam_Id=e.Exam_Id\n"
                    + "INNER JOIN exam_type_detail et\n"
                    + "ON et.ExamType_Id=e.ExamTypeID\n"
                    + "WHERE et.ExamType='" + examType + "'AND e.Subject_Id='" + subID(subject) + "' AND e.Batch='" + batch + "'";

            rs = stmt.executeQuery(query);
            boolean found = false;
            while (rs.next()) {
                MarksBean marksBean = new MarksBean();
                marksBean.setStd_id(rs.getString(1));
                marksBean.setFirstname(rs.getString("FirstName"));
                marksBean.setLastname(rs.getString(3));
                marksBean.setMarks(rs.getFloat(4));
                marksInfo.add(marksBean);
                found = true;
            }
            rs.close();
            if (found) {
                return marksInfo;
            } else {
                return null; // no entires found
            }
        } catch (Exception e) {
            System.out.println("Error In getmarks() -->" + e.getMessage());
            return (null);
        }

    }

    public List<SelectItem> getExamTypes() {
        List<SelectItem> exType = new ArrayList<SelectItem>();
        try {
            con = new Database().getConnection();
            st = con.createStatement();
            String query = "Select ExamType from exam_type_detail";

            rs = st.executeQuery(query);
            while (rs.next()) {
                exType.add(new SelectItem(rs.getString("ExamType")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return exType;
    }
}

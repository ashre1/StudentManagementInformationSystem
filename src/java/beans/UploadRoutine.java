/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Dell
 */
import dao.Database;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "uploadroutine")
@SessionScoped
public class UploadRoutine implements Serializable {

    public UploadRoutine() {

        try {
            con = new Database().getConnection();
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UploadRoutine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int id;
    private String program;
    private String Sem;
    private String[] semester = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth"};
    private String section;
    private String Sec[] = {"A", "B"};

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String[] getSec() {
        return Sec;
    }

    public void setSec(String[] Sec) {
        this.Sec = Sec;
    }

    private static final long serialVersionUID = 1L;
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSem() {
        return Sem;
    }

    public void setSem(String Sem) {
        this.Sem = Sem;
    }

    public String[] getSemester() {
        return semester;
    }

    public void setSemester(String[] semester) {
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public List<SelectItem> getCategoryName() {
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
        }

        return program;
    }

    public void upload() {

        try {
            System.out.println("sssss");
            String query = "select * from routine where program='" + program + "' and semester='" +Sem + "' and section='" +section + "'";

            rs = stmt.executeQuery(query);

            if (!rs.next()) {
                if (file != null) {
                    try {
                        System.out.println(file.getFileName());
                        InputStream fin2 = file.getInputstream();
                        Connection con = new Database().getConnection();
                        PreparedStatement pre = con.prepareStatement("insert into routine (program,semester,section,image) values(?,?,?,?)");

                        pre.setString(1, program);
                        pre.setString(2, Sem);
                        pre.setString(3, section);
                        pre.setBinaryStream(4, fin2, file.getSize());
                        pre.executeUpdate();
                        System.out.println("Inserting Successfully!");
                        pre.close();
                        FacesMessage msg = new FacesMessage("Succesful uploaded " + program +" " + Sem + " "+section + " "+"routine");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        setProgram(null);   
                        setSem(null);
                        setSection(null);

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                    }
                } else {
                    FacesMessage msg = new FacesMessage("Please select image!!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } else {
                if (file != null) {
                    try {
                        InputStream fin2 = file.getInputstream();
                        Connection con = new Database().getConnection();
                        PreparedStatement pre = con.prepareStatement("update routine set image=? where program='" + program + "' and semester='" + Sem + "' and section='" + section + "'");
                        pre.setBinaryStream(1, fin2, file.getSize());
                        pre.executeUpdate();
                        System.out.println("Inserting Successfully!");
                        pre.close();
                        FacesMessage msg = new FacesMessage("Succesful uploaded " + program + " " +Sem +  " "+section + " "+"routine");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UploadRoutine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List< UploadRoutine> getAllImage1() {
        List< UploadRoutine> imageInfo = new ArrayList< UploadRoutine>();
        Connection con = new Database().getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select routine_id,program,semester,section from routine where program='" + program + "' and semester='" + Sem + "' and section='" + section + "'";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                UploadRoutine tbl = new UploadRoutine();
                tbl.setId(rs.getInt("routine_id"));
                tbl.setProgram(rs.getString("program"));
                tbl.setSem(rs.getString("semester"));
                tbl.setSection(rs.getString("section"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

}

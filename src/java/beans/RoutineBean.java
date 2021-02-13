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
import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "routineBean")
@SessionScoped

public class RoutineBean {

    private String program;
    private String semester;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    int id;
    String Sec;

    public String getSec() {
        return Sec;
    }

    public void setSec(String Sec) {
        this.Sec = Sec;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<RoutineBean> getAllImage() {
        List<RoutineBean> imageInfo = new ArrayList<RoutineBean>();
        Connection con = new Database().getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select routine_id,program,semester,section from routine order by routine_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                RoutineBean tbl = new RoutineBean();
                tbl.setId(rs.getInt("routine_id"));
                tbl.setProgram(rs.getString("program"));
                tbl.setSemester(rs.getString("semester"));
                tbl.setSec(rs.getString("section"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

}

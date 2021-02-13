package beans;

import dao.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "NoticeView")
@ViewScoped

public class ShowingNotice {

    private String ename;
    private Date eventdate;
    private Date date1;

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getEventdate() {
        return eventdate;
    }

    public void setEventdate(Date eventdate) {
        this.eventdate = eventdate;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;

    }

//   String yourDate = dateFormat.format(date);
//   private Date date3=convertUtilToSql(yourDate);
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void click() {
        if (ename.isEmpty()) {
            FacesMessage msg = new FacesMessage("Please enter in field");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            String query = "INSERT INTO events(event_name,event_date)VALUES(?,?)";

            try {

                con = new Database().getConnection();
                ps = con.prepareStatement(query);
                ps.setString(1, ename);
                ps.setDate(2, convertUtilToSql(eventdate));
                ps.executeUpdate();
                //  setDate1(null);
                setEname(null);
                setEventdate(null);

                FacesMessage msg = new FacesMessage("Sucessfully Added Notices..");
                FacesContext.getCurrentInstance().addMessage(null, msg);
//    RequestContext requestContext = RequestContext.getCurrentInstance();
//         
//        requestContext.update("form:display");
//        requestContext.execute("PF('dlg').show()");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShowingNotice() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date d = new Date();

        date = dateFormat.format(d);

    }

    private String date;

    public void onDateSelect(SelectEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        date = format.format(event.getObject());
        System.out.println(date);

    }

    public List<ShowingNotice> notice = new ArrayList<>();

    public List<ShowingNotice> getNotice() {
        List<ShowingNotice> ntoc = new ArrayList<>();
        try {
            int i = 1;
            con = new Database().getConnection();
            Statement stmt = con.createStatement();
            String query = "SELECT event_name from events WHERE event_date='" + date + "'";
            rs = stmt.executeQuery(query);
            boolean found = false;

            while (rs.next()) {
                ShowingNotice sc = new ShowingNotice();
                sc.setName(i + ". " + rs.getString(1));
                //  sc.setEventDesc(rs.getString(2));
                ntoc.add(sc);
                i++;
                found = true;
            }
            rs.close();
            if (found) {
                found = false;
                i = 1;
                return ntoc;

            }

        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}

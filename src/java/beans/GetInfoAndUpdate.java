package beans;

import dao.Database;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "getStudentData")
@SessionScoped

public class GetInfoAndUpdate {

    public GetInfoAndUpdate() {
        reset();
    }

    public void reset() {

        setID(null);
        setBatch(null);
        setProgram(null);

    }

    private String radioStatus = null;
    private String ID;
    private String a = "0";
    private UploadedFile file;
    InputStream fin2 = null;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
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
    private GetInfoAndUpdate table;
    private String program;
    private String house_no;
    private String vdc;
    private String street;
    private String wardno;
    private String district;
    private String phone_no;

    private String t_house_no;
    private String t_street;

    private String t_vdc;
    private String t_wardno;
    private String t_district;
    private String t_phone_no;
    private int facId;
    private String plus2Division;
    private String plus2Percentage;
    private String plus2MajorSubject;
    private String plus2Others;
    private String cmatSymbol;
    private String cmatMarks;
    private String f_name;
    private String f_occupation;
    private String f_address;
    private String f_phone_no;
    private String g_name;
    private String g_occupation;
    private String g_address;
    private String g_phone_no;
    private String f_mobilno;
    private String g_mobilno;
    private String slcBoard;
    private String slcSchool;
    private String slcPassedYear;
    private String slcPassedYearnep;
    private String slcDivision;
    private String slcPercentage;
    private String slcMajorSubject;
    private String slcSymbol;
    private String plus2Symbol;
    private String plus2Board;
    private String plus2School;
    private String plus2PassedYear;
    private String plus2PassedYearnep;
    private String slcGrade;
    private int slcTranscript;
    private int slcCertificate;
    private int plus2Transcript;
    private int plus2Certificate;
    private int citizenship;
    private int photo;
    private String section;
    private String facName;
    private String Sem;
    private String contact;
    private String T_Contact;

    public String getT_Contact() {
        return T_Contact;
    }

    public void setT_Contact(String T_Contact) {
        this.T_Contact = T_Contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSem() {
        return Sem;
    }

    public void setSem(String Sem) {
        this.Sem = Sem;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public int getFacId() {
        return facId;
    }

    public void setFacId(int facId) {
        this.facId = facId;
    }

    public String getPlus2Division() {
        return plus2Division;
    }

    public void setPlus2Division(String plus2Division) {
        this.plus2Division = plus2Division;
    }

    public String getPlus2Percentage() {
        return plus2Percentage;
    }

    public void setPlus2Percentage(String plus2Percentage) {
        this.plus2Percentage = plus2Percentage;
    }

    public String getPlus2MajorSubject() {
        return plus2MajorSubject;
    }

    public void setPlus2MajorSubject(String plus2MajorSubject) {
        this.plus2MajorSubject = plus2MajorSubject;
    }

    public String getPlus2Others() {
        return plus2Others;
    }

    public void setPlus2Others(String plus2Others) {
        this.plus2Others = plus2Others;
    }

    public String getCmatSymbol() {
        return cmatSymbol;
    }

    public void setCmatSymbol(String cmatSymbol) {
        this.cmatSymbol = cmatSymbol;
    }

    public String getCmatMarks() {
        return cmatMarks;
    }

    public void setCmatMarks(String cmatMarks) {
        this.cmatMarks = cmatMarks;
    }

    public String getSlcDivision() {
        return slcDivision;
    }

    public void setSlcDivision(String slcDivision) {
        this.slcDivision = slcDivision;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getVdc() {
        return vdc;
    }

    public void setVdc(String vdc) {
        this.vdc = vdc;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWardno() {
        return wardno;
    }

    public void setWardno(String wardno) {
        this.wardno = wardno;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getT_house_no() {
        return t_house_no;
    }

    public void setT_house_no(String t_house_no) {
        this.t_house_no = t_house_no;
    }

    public String getT_street() {
        return t_street;
    }

    public void setT_street(String t_street) {
        this.t_street = t_street;
    }

    public String getT_vdc() {
        return t_vdc;
    }

    public void setT_vdc(String t_vdc) {
        this.t_vdc = t_vdc;
    }

    public String getT_wardno() {
        return t_wardno;
    }

    public void setT_wardno(String t_wardno) {
        this.t_wardno = t_wardno;
    }

    public String getT_district() {
        return t_district;
    }

    public void setT_district(String t_district) {
        this.t_district = t_district;
    }

    public String getT_phone_no() {
        return t_phone_no;
    }

    public void setT_phone_no(String t_phone_no) {
        this.t_phone_no = t_phone_no;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_occupation() {
        return f_occupation;
    }

    public void setF_occupation(String f_occupation) {
        this.f_occupation = f_occupation;
    }

    public String getF_address() {
        return f_address;
    }

    public void setF_address(String f_address) {
        this.f_address = f_address;
    }

    public String getF_phone_no() {
        return f_phone_no;
    }

    public void setF_phone_no(String f_phone_no) {
        this.f_phone_no = f_phone_no;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    public String getG_occupation() {
        return g_occupation;
    }

    public void setG_occupation(String g_occupation) {
        this.g_occupation = g_occupation;
    }

    public String getG_address() {
        return g_address;
    }

    public void setG_address(String g_address) {
        this.g_address = g_address;
    }

    public String getG_phone_no() {
        return g_phone_no;
    }

    public void setG_phone_no(String g_phone_no) {
        this.g_phone_no = g_phone_no;
    }

    public String getF_mobilno() {
        return f_mobilno;
    }

    public void setF_mobilno(String f_mobilno) {
        this.f_mobilno = f_mobilno;
    }

    public String getG_mobilno() {
        return g_mobilno;
    }

    public void setG_mobilno(String g_mobilno) {
        this.g_mobilno = g_mobilno;
    }

    public String getSlcBoard() {
        return slcBoard;
    }

    public void setSlcBoard(String slcBoard) {
        this.slcBoard = slcBoard;
    }

    public String getSlcSchool() {
        return slcSchool;
    }

    public void setSlcSchool(String slcSchool) {
        this.slcSchool = slcSchool;
    }

    public String getSlcPassedYear() {
        return slcPassedYear;
    }

    public void setSlcPassedYear(String slcPassedYear) {
        this.slcPassedYear = slcPassedYear;
    }

    public String getSlcPassedYearnep() {
        return slcPassedYearnep;
    }

    public void setSlcPassedYearnep(String slcPassedYearnep) {
        this.slcPassedYearnep = slcPassedYearnep;
    }

    public String getSlcPercentage() {
        return slcPercentage;
    }

    public void setSlcPercentage(String slcPercentage) {
        this.slcPercentage = slcPercentage;
    }

    public String getSlcMajorSubject() {
        return slcMajorSubject;
    }

    public void setSlcMajorSubject(String slcMajorSubject) {
        this.slcMajorSubject = slcMajorSubject;
    }

    public String getSlcSymbol() {
        return slcSymbol;
    }

    public void setSlcSymbol(String slcSymbol) {
        this.slcSymbol = slcSymbol;
    }

    public String getPlus2Symbol() {
        return plus2Symbol;
    }

    public void setPlus2Symbol(String plus2Symbol) {
        this.plus2Symbol = plus2Symbol;
    }

    public String getPlus2Board() {
        return plus2Board;
    }

    public void setPlus2Board(String plus2Board) {
        this.plus2Board = plus2Board;
    }

    public String getPlus2School() {
        return plus2School;
    }

    public void setPlus2School(String plus2School) {
        this.plus2School = plus2School;
    }

    public String getPlus2PassedYear() {
        return plus2PassedYear;
    }

    public void setPlus2PassedYear(String plus2PassedYear) {
        this.plus2PassedYear = plus2PassedYear;
    }

    public String getPlus2PassedYearnep() {
        return plus2PassedYearnep;
    }

    public void setPlus2PassedYearnep(String plus2PassedYearnep) {
        this.plus2PassedYearnep = plus2PassedYearnep;
    }

    public String getSlcGrade() {
        return slcGrade;
    }

    public void setSlcGrade(String slcGrade) {
        this.slcGrade = slcGrade;
    }

    public int getSlcTranscript() {
        return slcTranscript;
    }

    public void setSlcTranscript(int slcTranscript) {
        this.slcTranscript = slcTranscript;
    }

    public int getSlcCertificate() {
        return slcCertificate;
    }

    public void setSlcCertificate(int slcCertificate) {
        this.slcCertificate = slcCertificate;
    }

    public int getPlus2Transcript() {
        return plus2Transcript;
    }

    public void setPlus2Transcript(int plus2Transcript) {
        this.plus2Transcript = plus2Transcript;
    }

    public int getPlus2Certificate() {
        return plus2Certificate;
    }

    public void setPlus2Certificate(int plus2Certificate) {
        this.plus2Certificate = plus2Certificate;
    }

    public int getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(int citizenship) {
        this.citizenship = citizenship;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public String getRadioStatus() {

        return this.radioStatus;
    }

    public void setRadioStatus(String radioStatus) {

        this.radioStatus = radioStatus;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public GetInfoAndUpdate getTable() {
        return table;
    }

    public void setTable(GetInfoAndUpdate table) {
        this.table = table;
    }

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

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private String imageLength;

    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement pre1;
    ResultSet rs;

    public List<SelectItem> getCategoryName() {
        List<SelectItem> program = new ArrayList<SelectItem>();
        try {
            Connection con = new Database().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
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

    public void Focus() {
        RequestContext.getCurrentInstance().reset("form:program,batch");
        setProgram(null);
        setBatch(null);
    }

    public List<GetInfoAndUpdate> getAllImage() {
        List<GetInfoAndUpdate> imageInfo = new ArrayList<GetInfoAndUpdate>();
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");

            stmt = con.createStatement();
            String strSql = "select * from student_info_table order by student_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                String fname;
                GetInfoAndUpdate tbl = new GetInfoAndUpdate();
                tbl.setID(rs.getString("student_id"));
                tbl.setName(rs.getString("firstname"));
                fname = rs.getString("firstname");
                tbl.setMiddlename(rs.getString("middlename"));
                fname = fname + " " + rs.getString("middlename");
                tbl.setLastName(rs.getString("lastname"));
                fname = fname + " " + rs.getString("lastname");
                tbl.setBatch(rs.getString("batch"));
                tbl.setYear(rs.getString("year"));
                tbl.setGender(rs.getString("gender"));
                tbl.setReligion(rs.getString("religion"));
                tbl.setNationality(rs.getString("nationality"));
                tbl.setCitizenshipNO(rs.getString("citizenshipno"));
                tbl.setEmail(rs.getString("email"));
                tbl.setMobno(rs.getString("mobileno"));
                tbl.setDateAd((java.util.Date) rs.getDate("dob_english"));

                tbl.setFullName(fname);
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        } finally {
            reset();

        }
        return imageInfo;
    }
    int idm;

    public int facID1(String name) {

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
        }

        return idm;
    }

    String fname;

    public String facID1(int name) {

        try {
            System.out.println(name);

            rs = null;
            String myQuery = "select faculty_name from faculty_table where faculty_id='" + name + "'";

            rs = stmt.executeQuery(myQuery);
            while (rs.next()) {
                fname = rs.getString("faculty_name");
                System.out.println(idm);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return fname;
    }

    public List<GetInfoAndUpdate> getAllImage1() {
        List<GetInfoAndUpdate> imageInfo = new ArrayList<GetInfoAndUpdate>();
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");

            stmt = con.createStatement();
            if (ID != null) {
                a = ID;
            }
            int length = a.length();
            System.out.println(length);
            String strSql;
            if (length > 1 && radioStatus.equalsIgnoreCase("regno")) {
                strSql = "select * from student_info_table where student_id='" + ID + "' ";
                //System.err.println("*select all***" + strSql);

            } else {
                strSql = "select * from student_info_table where batch='" + batch + "' and faculty_id='" + facID1(program) + "' ";
            }
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                String fname;
                GetInfoAndUpdate tbl = new GetInfoAndUpdate();
                tbl.setID(rs.getString("student_id"));
                tbl.setName(rs.getString("firstname"));
                fname = rs.getString("firstname");
                tbl.setMiddlename(rs.getString("middlename"));
                fname = fname + " " + rs.getString("middlename");
                tbl.setLastName(rs.getString("lastname"));
                fname = fname + " " + rs.getString("lastname");
                tbl.setBatch(rs.getString("batch"));
                tbl.setYear(rs.getString("year"));
                tbl.setMobno(rs.getString("mobileno"));
                tbl.setFullName(fname);
                imageInfo.add(tbl);
//                    setBatch(null);
//                    setProgram(null);

            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //int size = radioStatus.length();
            if (radioStatus != null) {
                clear();
            }
        }
        return imageInfo;

    }

    public String information() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();
            String strSql;
            strSql = "select * from student_info_table where student_id='" + table.ID + "'";
            //System.err.println("*select all***" + strSql);
            setID(table.ID);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {

                setID(rs.getString("student_id"));
                setName(rs.getString("firstname"));
                setMiddlename(rs.getString("middlename"));
                setLastName(rs.getString("lastname"));
                setGender(rs.getString("gender"));
                setNationality(rs.getString("Nationality"));
                setReligion(rs.getString("Religion"));
                setCitizenshipNO(rs.getString("CitizenshipNo"));
                setDateAd(rs.getDate("DOB_english"));
                setEmail(rs.getString("email"));
                setCmatMarks(rs.getString("CMAT_Score"));
                setCmatSymbol(rs.getString("CMAT_Roll"));
                setBatch(rs.getString("batch"));
                setYear(rs.getString("year"));
                setMobno(rs.getString("mobileno"));
                setFacId(rs.getInt("faculty_id"));
                setFacName(facID1(rs.getInt("faculty_id")));
//                    setBatch(null);
//                    setProgram(null);
            }
            rs = null;
            strSql = null;
            strSql = "Select current_semester,section from current_std_table where student_id='" + table.ID + "'";
            rs = stmt.executeQuery(strSql);
            if (rs != null) {
                while (rs.next()) {
                    setSem(rs.getString("current_semester"));
                    setSection(rs.getString("section"));
                }
            } else {
                setSem("Not Available");
                setSection("Not Available");
            }

            rs = null;
            strSql = null;
            strSql = "Select * from address_table where student_id='" + table.ID + "'";
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                if ("permanent".equalsIgnoreCase(rs.getString("AddressType"))) {
                    setHouse_no(rs.getString("HouseNo"));
                    setVdc(rs.getString("VDCMunicipality"));
                    setWardno(rs.getString("WardNo"));
                    setDistrict(rs.getString("District"));
                    setStreet(rs.getString("street"));
                    setContact(rs.getString("Contacts"));
                } else {
                    setT_house_no(rs.getString("HouseNo"));
                    setT_vdc(rs.getString("VDCMunicipality"));
                    setT_wardno(rs.getString("WardNo"));
                    setT_district(rs.getString("District"));
                    setT_street(rs.getString("street"));
                    setT_Contact(rs.getString("Contacts"));
                }
            }

            rs = null;
            strSql = null;
            strSql = "Select * from family_details_table where student_id='" + table.ID + "'";
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                if ("father".equalsIgnoreCase(rs.getString("Relation"))) {
                    setF_name(rs.getString("FullName"));
                    setF_occupation(rs.getString("Occupation"));
                    setF_address(rs.getString("Address"));
                    setF_mobilno(rs.getString("MobileNo"));
                    setF_phone_no(rs.getString("Contacts"));
                } else {
                    setG_name(rs.getString("FullName"));
                    setG_occupation(rs.getString("Occupation"));
                    setG_address(rs.getString("Address"));
                    setG_mobilno(rs.getString("MobileNo"));
                    setG_phone_no(rs.getString("Contacts"));
                }
            }

            rs = null;
            strSql = null;
            strSql = "Select * from educational_detail_table where student_id='" + table.ID + "'";
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                if ("SLC".equalsIgnoreCase(rs.getString("level"))) {
                    setSlcBoard(rs.getString("Board"));
                    setSlcSchool(rs.getString("Institution"));
                    setSlcPassedYear(rs.getString("Passed_Year_Eng"));
                    setSlcPassedYearnep(rs.getString("Passed_Year_Nep"));
                    setSlcDivision(rs.getString("division"));
                    setSlcSymbol(rs.getString("SymbolNo"));
                    setSlcPercentage(rs.getString("Percentage"));
                    setSlcGrade(rs.getString("Grade"));
                    setSlcMajorSubject(rs.getString("MajorSubject"));

                } else {
                    setPlus2Board(rs.getString("Board"));
                    setPlus2Others(rs.getString("Level"));
                    setPlus2School(rs.getString("Institution"));
                    setPlus2PassedYear(rs.getString("Passed_Year_Eng"));
                    setPlus2PassedYearnep(rs.getString("Passed_Year_Nep"));
                    setPlus2Division(rs.getString("division"));
                    setPlus2Symbol(rs.getString("SymbolNo"));
                    setPlus2Percentage(rs.getString("Percentage"));
                    setPlus2MajorSubject(rs.getString("MajorSubject"));
                }
            }

            rs = null;
            strSql = null;
            strSql = "Select * from document_table where student_id='" + table.ID + "'";
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {

                setSlcTranscript(rs.getInt("SLC_Transcript"));
                setSlcCertificate(rs.getInt("SLC_Certificate"));
                setPlus2Transcript(rs.getInt("plus2_Transcript"));
                setPlus2Certificate(rs.getInt("plus2_Certificate"));
                setCitizenship(rs.getInt("CitizenShipPhoto"));
                setPhoto(rs.getInt("Photo"));

            }

        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //int size = radioStatus.length();
            if (radioStatus != null) {
                clear();
            }
        }
        System.out.println("in");
        return "ViewDetailAndUpdate";

    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;

    }

    public void infoUpdate() {
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            pre1 = con.prepareStatement("update student_info_table set FirstName=?,MiddleName=?,LastName=?,gender=?,nationality=?,religion=?,citizenshipno=?,dob_english=?,mobileno=?,email=?,Batch=?,Year=?,faculty_id=? where student_id=?");
            java.sql.Date date = convertUtilToSql(dateAd);

            pre1.setString(1, Name);
            pre1.setString(2, middlename);
            pre1.setString(3, LastName);
            pre1.setString(4, gender);
            pre1.setString(5, nationality);
            pre1.setString(6, religion);
            pre1.setString(7, citizenshipNO);
            pre1.setDate(8, date);
            pre1.setString(9, mobno);
            pre1.setString(10, email);
            pre1.setString(11, batch);
            pre1.setString(12, year);
            pre1.setInt(13, facID1(program));
            pre1.setString(14, table.ID);
            pre1.executeUpdate();
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated", "");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoUpdate2() {
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            pre1 = con.prepareStatement("update address_table set houseno=?,vdcmunicipality=?,wardno=?,district=?,street=?,contacts=? where student_id=? and AddressType=?");

            pre1.setString(1, house_no);
            pre1.setString(2, vdc);
            pre1.setString(3, wardno);
            pre1.setString(4, district);
            pre1.setString(5, street);
            pre1.setString(6, contact);
            pre1.setString(7, table.ID);
            pre1.setString(8, "Permanent");
            pre1.executeUpdate();

            pre1 = con.prepareStatement("update address_table set houseno=?,vdcmunicipality=?,wardno=?,district=?,street=?,contacts=? where student_id=? and AddressType=?");

            pre1.setString(1, t_house_no);
            pre1.setString(2, t_vdc);
            pre1.setString(3, t_wardno);
            pre1.setString(4, t_district);
            pre1.setString(5, t_street);
            pre1.setString(6, T_Contact);
            pre1.setString(7, table.ID);
            pre1.setString(8, "Temprorary");
            pre1.executeUpdate();

            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoUpdate3() {
        System.out.println("=========================22222222222222================");
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            pre1 = con.prepareStatement("Update educational_detail_table set level=?,board=?,institution=?,symbolno=?,percentage=?,division=?,grade=?,majorsubject=?,passed_year_eng=?,passed_year_nep=? where student_id=? and level not like 'SLC'");

            pre1.setString(1, plus2Others);
            pre1.setString(2, plus2Board);
            pre1.setString(3, plus2School);
            pre1.setString(4, plus2Symbol);
            pre1.setString(5, plus2Percentage);
            pre1.setString(6, plus2Division);
            pre1.setString(7, "----");
            pre1.setString(8, plus2MajorSubject);
            pre1.setString(9, plus2PassedYear);
            pre1.setString(10, plus2PassedYearnep);
            pre1.setString(11, table.ID);
            pre1.executeUpdate();

            pre1 = con.prepareStatement("Update educational_detail_table set level=?,board=?,institution=?,symbolno=?,percentage=?,division=?,grade=?,majorsubject=?,passed_year_eng=?,passed_year_nep=? where student_id=? and level =?");

            pre1.setString(1, "SLC");
            pre1.setString(2, slcBoard);
            pre1.setString(3, slcSchool);
            pre1.setString(4, slcSymbol);
            pre1.setString(5, slcPercentage);
            pre1.setString(6, slcDivision);
            pre1.setString(7, slcGrade);
            pre1.setString(8, slcMajorSubject);
            pre1.setString(9, slcPassedYear);
            pre1.setString(10, slcPassedYearnep);
            pre1.setString(11, table.ID);
            pre1.setString(12, "SLC");
            pre1.executeUpdate();

            pre1 = con.prepareStatement("update student_info_table set cmat_roll=?,cmat_score=? where student_id=?");
            pre1.setString(1, cmatSymbol);
            pre1.setString(2, cmatMarks);
            pre1.setString(3, table.ID);
            pre1.executeUpdate();
            
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated", "");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        
    }
    
    public void hello(){
         System.out.println("=====================");
        System.out.println("update4");
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated", "");
         FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoUpdate4() {
        System.out.println("=====================");
        System.out.println("update4");
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            pre1 = con.prepareStatement("update family_details_table set fullname=?,occupation=?,address=?,mobileno=?,contacts=? where student_id=? and relation=?");

            pre1.setString(1, f_name);
            pre1.setString(2, f_occupation);
            pre1.setString(3, f_address);
            pre1.setString(4, f_mobilno);
            pre1.setString(5, f_phone_no);
            pre1.setString(6, table.ID);
            pre1.setString(7, "Father");
            pre1.executeUpdate();
          
            pre1 = con.prepareStatement("update family_details_table set fullname=?,occupation=?,address=?,mobileno=?,contacts=? where student_id=? and relation=?");

            pre1.setString(1, g_name);
            pre1.setString(2, g_occupation);
            pre1.setString(3, g_address);
            pre1.setString(4, g_mobilno);
            pre1.setString(5, g_phone_no);
            pre1.setString(6, table.ID);
            pre1.setString(7, "Guardian");
            pre1.executeUpdate();

         message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void infoUpdate5() {
        System.out.println("=========================");
        System.out.println("update 5");
        RequestContext content = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            pre1 = con.prepareStatement("update document_table set slc_transcript=?,slc_certificate=?,plus2_transcript=?,plus2_certificate=?,citizenshipphoto=?,photo=? where student_id=?");

            pre1.setInt(1, slcTranscript);
            pre1.setInt(2, slcCertificate);
            pre1.setInt(3, plus2Transcript);
            pre1.setInt(4, plus2Transcript);
            pre1.setInt(5, citizenship);
            pre1.setInt(6, photo);
            pre1.setString(7, table.ID);
            pre1.executeUpdate();
            System.out.println("=============================");
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetInfoAndUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    BufferedImage resizeImageJpg;
    InputStream is;

    public void changeImage() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");
        System.out.println(file.getSize());
        if (file.getSize() != 0) {
            if (file.getSize() > 5000000) {
                FacesMessage msg = new FacesMessage("Image size too large!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {

                try {
                    BufferedImage originalImage = ImageIO.read(file.getInputstream());
                    int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
                    System.out.println(type);
                    resizeImageJpg = resizeImage(originalImage, type);//call to resize the image

                    // BufferedImage to ByteArrayInputStream
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    ImageIO.write(resizeImageJpg, "jpg", os);
                    is = new ByteArrayInputStream(os.toByteArray());

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
                    Statement stmt = con.createStatement();
                    //         PreparedStatement pre = con.prepareStatement("INSERT INTO upload_image (image_name,image) VALUES(?,?)");

                    pre1 = con.prepareStatement("update studentphoto set photo=? where studentId=?");

                    pre1.setBlob(1, is);
                    pre1.setString(2, id);
                    pre1.executeUpdate();
                    System.out.println("Inserting Successfully!");
                    System.out.println("Update Successfully!");
                    pre1.close();
                    FacesMessage msg = new FacesMessage("Succesful uploaded!!!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    this.setFile(null);

                } catch (Exception e) {
                    System.out.println("Exception-File Upload." + e.getMessage());
                }
            }
        } else {
            FacesMessage msg = new FacesMessage("Please select image!!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(1000, 1000, type);//set width and height of image
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1000, 1000, null);
        g.dispose();

        return resizedImage;
    }

    public void clear() {
        if (radioStatus.equalsIgnoreCase("batch")) {
            setID(null);
        } else {
            setProgram(null);
            setBatch(null);
        }
    }

}

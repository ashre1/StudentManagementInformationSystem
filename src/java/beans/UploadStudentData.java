package beans;

import dao.Database;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "uploadStdData")
@SessionScoped
public class UploadStudentData implements Serializable {

    private static final long serialVersionUID = 1L;
    int year1 = Calendar.getInstance().get(Calendar.YEAR);
    private UploadedFile file;
    private String name;
    private String year;
    private String batch;
    private String gender;
    public String nationality;
    public String citizenshipNo;
    public String religion;
    public String mobileNO;
    public String email;
    private Date dateAd;
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
    private String program;
    private int facId;
    private String id;
    private String lastName;
    private String middleName;
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
    private Float slcPercentage;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
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

    public String getSlcGrade() {
        return slcGrade;
    }

    public void setSlcGrade(String slcGrade) {
        this.slcGrade = slcGrade;
    }

    public String getSlcPassedYearnep() {
        return slcPassedYearnep;
    }

    public void setSlcPassedYearnep(String slcPassedYearnep) {
        this.slcPassedYearnep = slcPassedYearnep;
    }

    public String getPlus2PassedYearnep() {
        return plus2PassedYearnep;
    }

    public void setPlus2PassedYearnep(String plus2PassedYearnep) {
        this.plus2PassedYearnep = plus2PassedYearnep;
    }
    private String plus2Division;
    private Float plus2Percentage;
    private String plus2MajorSubject;
    private String plus2Others;
    private String cmatSymbol;
    private String cmatMarks;

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

    public String getSlcDivision() {
        return slcDivision;
    }

    public void setSlcDivision(String slcDivision) {
        this.slcDivision = slcDivision;
    }

    public Float getSlcPercentage() {
        return slcPercentage;
    }

    public void setSlcPercentage(Float slcPercentage) {
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

    public String getPlus2Division() {
        return plus2Division;
    }

    public void setPlus2Division(String plus2Division) {
        this.plus2Division = plus2Division;
    }

    public Float getPlus2Percentage() {
        return plus2Percentage;
    }

    public void setPlus2Percentage(Float plus2Percentage) {
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
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

    public Date getDateAd() {
        return dateAd;
    }

    public void setDateAd(Date dateAd) {
        this.dateAd = dateAd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCitizenshipNo() {
        return citizenshipNo;
    }

    public void setCitizenshipNo(String citizenshipNo) {
        this.citizenshipNo = citizenshipNo;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMobileNO() {
        return mobileNO;
    }

    public void setMobileNO(String mobileNO) {
        this.mobileNO = mobileNO;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

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

    public void ok() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            Connection con = new Database().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            String query = "SELECT Student_Id from student_info_table where Student_Id='" + id + "'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                fc.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration No. already exist.", ""));
                setId(null);
                num();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    int idm;

    public int facID(String name) {

        try {
            System.out.println(name);
            Connection con = new Database().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            String myQuery = "select faculty_id from faculty_table where faculty_name='" + name + "'";

            rs = st.executeQuery(myQuery);
            while (rs.next()) {
                idm = rs.getInt("faculty_id");
                System.out.println(idm);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return idm;
    }

    private int activeIndex;

    public void goToTab(int index) {
        this.activeIndex = index;
    }

    PreparedStatement pre = null;
    PreparedStatement pre1 = null;
    PreparedStatement pre3 = null;
    PreparedStatement pre4 = null;
    PreparedStatement pre5 = null;
    PreparedStatement pre6 = null;
    PreparedStatement pre7 = null;
    PreparedStatement pre8 = null;
    PreparedStatement pre9 = null;
    PreparedStatement pre10 = null;
    PreparedStatement pre11 = null;

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

        java.sql.Date sDate = new java.sql.Date(uDate.getTime());

        return sDate;

    }

    BufferedImage resizeImageJpg;
    InputStream is;

    private String status = "NO";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void upload() {
        System.out.println(file.getSize());
        if (!image()) {
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
                        System.out.println(file.getFileName());
                        Connection con = new Database().getConnection();

                        pre = con.prepareStatement("insert into studentphoto (studentId,photo) values(?,?)");
                        pre.setString(1, id);
                        pre.setBlob(2, is);
                        pre.executeUpdate();
                        System.out.println("Inserting Successfully!");
                        setStatus("YES");

                        FacesMessage msg = new FacesMessage("Image Uploaded!!");
                        FacesContext.getCurrentInstance().addMessage(null, msg);

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                    } finally {
                        try {
                            pre.close();
                            is.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
            } else {
                FacesMessage msg = new FacesMessage("Please select image!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }else{
            FacesMessage msg = new FacesMessage("Student image already exits!!!");
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

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();
    }

    public void num() {
        id = "NCCS" + program;
    }

    public void clear() {
        setName(null);
        setYear(null);
        setBatch(null);
        setGender(null);
        setNationality(null);
        setCitizenshipNo(null);
        setReligion(null);
        setMobileNO(null);
        setEmail(null);
        setDateAd(null);
        setHouse_no(null);
        setVdc(null);
        setStreet(null);
        setWardno(null);
        setDistrict(null);
        setPhone_no(null);

        setT_house_no(null);
        setT_district(null);
        setT_phone_no(null);
        setT_street(null);
        setT_vdc(null);
        setT_wardno(null);

        setCmatMarks(null);
        setCmatSymbol(null);

        setProgram(null);
        setId(null);
        setLastName(null);
        setMiddleName(null);

        setF_address(null);
        setF_mobilno(null);
        setF_name(null);
        setF_occupation(null);
        setF_phone_no(null);

        setG_address(null);
        setG_mobilno(null);
        setG_name(null);
        setG_occupation(null);
        setG_phone_no(null);

        setSlcBoard(null);
        setSlcDivision(null);
        setSlcGrade(null);
        setSlcMajorSubject(null);
        setSlcPassedYear(null);
        setSlcPassedYearnep(null);
        setSlcPercentage(Float.valueOf(0));
        setSlcSchool(null);
        setSlcSymbol(null);

        setPlus2Board(null);
        setPlus2Division(null);
        setPlus2MajorSubject(null);
        setPlus2Others(null);
        setPlus2Symbol(null);
        setPlus2Percentage(Float.valueOf(0));
        setPlus2School(null);
        setPlus2PassedYear(null);
        setPlus2PassedYearnep(null);

        setStatus("NO");
        setSlcTranscript(0);
        setSlcCertificate(0);

        setPlus2Transcript(0);
        setPlus2Certificate(0);

        setCitizenship(0);
        setPhoto(0);
        setSection(null);

    }

    public void save() {
        try {
            facId = facID(program);
            Connection con = new Database().getConnection();
            pre1 = con.prepareStatement("insert into student_info_table (student_id,FirstName,MiddleName,LastName,gender,nationality,religion,citizenshipno,dob_english,mobileno,email,Batch,cmat_roll,cmat_score,Year,faculty_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            java.sql.Date date = convertUtilToSql(dateAd);
            pre1.setString(1, id);
            pre1.setString(2, name);
            pre1.setString(3, middleName);
            pre1.setString(4, lastName);
            pre1.setString(5, gender);
            pre1.setString(6, nationality);
            pre1.setString(7, religion);
            pre1.setString(8, citizenshipNo);
            pre1.setDate(9, date);
            pre1.setString(10, mobileNO);
            pre1.setString(11, email);
            pre1.setString(12, batch);
            pre1.setString(13, cmatSymbol);
            pre1.setString(14, cmatMarks);
            pre1.setString(15, year);
            pre1.setInt(16, facId);
            pre1.executeUpdate();
            pre1.close();

            pre3 = con.prepareStatement("insert into address_table (student_id,houseno,vdcmunicipality,wardno,district,street,contacts,addresstype) values(?,?,?,?,?,?,?,?)");
            pre3.setString(1, id);
            pre3.setString(2, house_no);
            pre3.setString(3, vdc);
            pre3.setString(4, wardno);
            pre3.setString(5, district);
            pre3.setString(6, street);
            pre3.setString(7, phone_no);
            pre3.setString(8, "Permanent");
            pre3.executeUpdate();
            pre3.close();

            pre4 = con.prepareStatement("insert into address_table (student_id,houseno,vdcmunicipality,wardno,district,street,contacts,addresstype) values(?,?,?,?,?,?,?,?)");
            pre4.setString(1, id);
            pre4.setString(2, t_house_no);
            pre4.setString(3, t_vdc);
            pre4.setString(4, t_wardno);
            pre4.setString(5, t_district);
            pre4.setString(6, t_street);
            pre4.setString(7, t_phone_no);
            pre4.setString(8, "Temprorary");
            pre4.executeUpdate();
            pre4.close();

            pre5 = con.prepareStatement("insert into family_details_table (student_id,fullname,occupation,address,mobileno,contacts,relation) values(?,?,?,?,?,?,?)");
            pre5.setString(1, id);
            pre5.setString(2, f_name);
            pre5.setString(3, f_occupation);
            pre5.setString(4, f_address);
            pre5.setString(5, f_mobilno);
            pre5.setString(6, f_phone_no);
            pre5.setString(7, "Father");
            pre5.executeUpdate();
            pre5.close();

            pre6 = con.prepareStatement("insert into family_details_table (student_id,fullname,occupation,address,mobileno,contacts,relation) values(?,?,?,?,?,?,?)");
            pre6.setString(1, id);
            pre6.setString(2, g_name);
            pre6.setString(3, g_occupation);
            pre6.setString(4, g_address);
            pre6.setString(5, g_mobilno);
            pre6.setString(6, g_phone_no);
            pre6.setString(7, "Guardian");
            pre6.executeUpdate();
            pre6.close();

            pre7 = con.prepareStatement("insert into educational_detail_table (student_id,level,board,institution,symbolno,percentage,division,grade,majorsubject,passed_year_eng,passed_year_nep) values(?,?,?,?,?,?,?,?,?,?,?)");
            pre7.setString(1, id);
            pre7.setString(2, plus2Others);
            pre7.setString(3, plus2Board);
            pre7.setString(4, plus2School);
            pre7.setString(5, plus2Symbol);
            pre7.setFloat(6, plus2Percentage);
            pre7.setString(7, plus2Division);
            pre7.setString(8, "----");
            pre7.setString(9, plus2MajorSubject);
            pre7.setString(10, plus2PassedYear);
            pre7.setString(11, plus2PassedYearnep);
            pre7.executeUpdate();
            pre7.close();

            pre8 = con.prepareStatement("insert into educational_detail_table (student_id,level,board,institution,symbolno,percentage,division,grade,majorsubject,passed_year_eng,passed_year_nep) values(?,?,?,?,?,?,?,?,?,?,?)");
            pre8.setString(1, id);
            pre8.setString(2, "SLC");
            pre8.setString(3, slcBoard);
            pre8.setString(4, slcSchool);
            pre8.setString(5, slcSymbol);
            pre8.setFloat(6, slcPercentage);
            pre8.setString(7, slcDivision);
            pre8.setString(8, slcGrade);
            pre8.setString(9, slcMajorSubject);
            pre8.setString(10, slcPassedYear);
            pre8.setString(11, slcPassedYearnep);
            pre8.executeUpdate();
            pre8.close();

            System.out.println("aaaaa");
            pre9 = con.prepareStatement("insert into document_table (student_id,slc_transcript,slc_certificate,plus2_transcript,plus2_certificate,citizenshipphoto,photo) values(?,?,?,?,?,?,?)");
            pre9.setString(1, id);
            pre9.setInt(2, slcTranscript);
            pre9.setInt(3, slcCertificate);
            pre9.setInt(4, plus2Transcript);
            pre9.setInt(5, plus2Transcript);
            pre9.setInt(6, citizenship);
            pre9.setInt(7, photo);
            pre9.executeUpdate();
            pre9.close();

            System.out.println("bbbbb");
            pre10 = con.prepareStatement("insert into current_std_table (student_id,faculty_id,current_semester,current_year,oddeven,batch,section) values(?,?,?,?,?,?,?)");
            pre10.setString(1, id);
            pre10.setInt(2, facId);
            pre10.setString(3, "First");
            pre10.setInt(4, year1);
            pre10.setString(5, "Odd");
            pre10.setString(6, batch);
            pre10.setString(7, section);
            pre10.executeUpdate();
            pre10.close();

            if (!image()) {
                pre11 = con.prepareStatement("insert into studentphoto (studentId,photo) values(?,?)");
                pre11.setString(1, id);
                pre11.setBlob(2, selectImage());
                pre11.executeUpdate();

            }

            clear();

            FacesMessage msg = new FacesMessage("Student Information Added");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean image() {
        String query = "SELECT studentId from studentphoto WHERE studentId = '" + id + "'";

        try {
            Connection con = new Database().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    public Blob selectImage() {
        String query = "SELECT image FROM image";
        try {
            Connection con = new Database().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return (rs.getBlob(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

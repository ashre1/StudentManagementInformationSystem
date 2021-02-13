package com.nccs.sis.Beans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped

public class userBean {

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    private userBean table;
    private String imageID;
    private String imageName;
    private String userName;
    private String password;
    private String name;
    private String userType;
    private String imageLength;

    public userBean() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public userBean getTable() {
        return table;
    }

    public void setTable(userBean table) {
        this.table = table;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }

    private String radioStatus = "No";

    public String getRadioStatus() {
        return radioStatus;
    }

    public void setRadioStatus(String radioStatus) {
        this.radioStatus = radioStatus;
    }

    public List<userBean> getAllImage() {
        List<userBean> imageInfo = new ArrayList<userBean>();
        try {
            String strSql = "select l.username,l.password,l.name,l.usertype,i.image_id,i.image_name from login_table as l Join user_image as i on i.username = l.username order by image_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                userBean tbl = new userBean();
                tbl.setUserName(rs.getString(1));
                tbl.setPassword(rs.getString(2));
                tbl.setName(rs.getString(3));
                tbl.setUserType(rs.getString(4));
                tbl.setImageID(rs.getString(5));
                tbl.setImageName(rs.getString(6));
                imageInfo.add(tbl);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    BufferedImage resizeImageJpg;
    InputStream is;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void upload() {
        FacesContext fc = FacesContext.getCurrentInstance();
//        RequestContext context = RequestContext.getCurrentInstance();
//        FacesMessage message = null;
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        id = params.get("id");

//        System.out.println(file.getSize());
        if (radioStatus.equalsIgnoreCase("Yes")) {
            if (file.getSize() != 0) {
                if (file.getSize() > 5000000) {
                    //  message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Image size too large!!!", "");
                    fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Image size too large!!!", ""));
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

                        //         PreparedStatement pre = con.prepareStatement("INSERT INTO upload_image (image_name,image) VALUES(?,?)");
                        PreparedStatement pre = con.prepareStatement("UPDATE user_image SET image_name=?,image=? WHERE image_id=?");
                        pre.setString(1, file.getFileName().toString());
                        pre.setBlob(2, is);
                        pre.setString(3, id);
                        pre.executeUpdate();
                        pre.close();

                        PreparedStatement pre1 = con.prepareStatement("UPDATE Login_table SET name=?,UserType=? WHERE UserName=?");
                        pre1.setString(1, table.name);
                        pre1.setString(2, table.userType);
                        pre1.setString(3, table.userName);
                        pre1.executeUpdate();

                        System.out.println("Update Successfully!");
                        pre1.close();
                        //                    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully update", "");
                        fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successsfully Updated", ""));
                        this.setFile(null);

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                    }
                }
            } else {
                //   message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Please Select image", "");
                fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Please select image", ""));

            }
        } else {
            try {

                PreparedStatement pre1 = con.prepareStatement("UPDATE Login_table SET name=?,UserType=? WHERE UserName=?");
                pre1.setString(1, table.name);
                pre1.setString(2, table.userType);
                pre1.setString(3, table.userName);
                pre1.executeUpdate();

                System.out.println("Update Successfully!");
                pre1.close();
                //      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully update", "");
                fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successsfully Updated", ""));
                this.setFile(null);

            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }

            setRadioStatus("No");
        }

        //  FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(1000, 1000, type);//set width and height of image
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1000, 1000, null);
        g.dispose();

        return resizedImage;
    }

    public void delete() {
        FacesContext fc = FacesContext.getCurrentInstance();

        try {
            PreparedStatement pre1 = con.prepareStatement("DELETE FROM login_table where UserName=?");
            pre1.setString(1, table.userName);
            pre1.executeUpdate();

            PreparedStatement pre2 = con.prepareStatement("DELETE FROM user_image where UserName=?");
            pre1.setString(1, table.userName);
            pre1.executeUpdate();
            fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successsfully deleted", ""));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

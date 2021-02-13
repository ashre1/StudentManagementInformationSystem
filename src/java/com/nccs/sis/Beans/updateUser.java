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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class updateUser {

    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    BufferedImage resizeImageJpg;
    InputStream is;
    Statement stmt = null;
    Connection con = null;

    private String name;
    private String usertype;
    private String userName;
    private userBean bean;

    public userBean getBean() {
        return bean;
    }

    public void setBean(userBean bean) {
        this.bean = bean.getTable();
    }
    
    

    public updateUser() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();

        } catch (Exception ex) {
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private String radioStatus = "No";

    public String getRadioStatus() {
        return radioStatus;
    }

    public void setRadioStatus(String radioStatus) {
        this.radioStatus = radioStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void update() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String id = params.get("id");

        if (radioStatus.equalsIgnoreCase("Yes")) {
            System.out.println(file.getSize());
            if (file.getSize() != 0) {
                if (file.getSize() > 5000000) {
                    fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Image size too large!!!!", ""));
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

                        PreparedStatement pre = con.prepareStatement("UPDATE login_table SET Name=?,UserType=? WHERE UserName=?");
                        PreparedStatement pre1 = con.prepareStatement("UPDATE user_image SET image_name=?,image=? WHERE UserName=? AND image_id=?");

                        pre.setString(1, bean.getTable().getName());
                        pre.setString(2, bean.getTable().getUserType());
                        pre.setString(3, bean.getTable().getUserName());
                        pre.executeUpdate();

                        pre1.setString(1, file.getFileName().toString());
                        pre1.setBlob(2, is);
                        pre1.setString(3, bean.getTable().getUserName());
                        pre1.setString(4, id);
                        pre1.executeUpdate();

                        System.out.println("Update Successfully");
                        pre.close();
                        fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Updated", ""));

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                    }
                }
            } else {
                fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Please select image", ""));
            }
        } else {
            try {
                PreparedStatement pre = con.prepareStatement("UPDATE login_table SET Name=?,UserType=? WHERE UserName=?");

                pre.setString(1, bean.getName());
                pre.setString(2, bean.getUserType());
                pre.setString(3, bean.getUserName());
                pre.executeUpdate();
                
                System.out.println("Update Successfully! w/o image");
                pre.close();
                fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Updated", ""));
            } catch (SQLException ex) {
                Logger.getLogger(addUser.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        setRadioStatus("No");
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(1000, 1000, type);//set width and height of image
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1000, 1000, null);
        g.dispose();

        return resizedImage;
    }
}

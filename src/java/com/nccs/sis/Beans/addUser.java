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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class addUser {

    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    BufferedImage resizeImageJpg;
    InputStream is;
    Statement stmt = null;
    Connection con = null;

    private String name;
    private String userName;
    private String password;
    private String userType;

    public addUser() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(addUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean valid() {
        int flag = 0;
        String query = "SELECT UserName FROM login_table WHERE UserName='" + userName + "'";
        try {
            //Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //setCheck("false");
                System.out.println("m inside");
                setUserName(null);
                flag = 1;
                // ok = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == 1) {
            System.out.println("false");
            return false;
        } else {
            System.out.println("true");
            return true;
        }
    }
    
    public void upload() {
        FacesContext fc = FacesContext.getCurrentInstance();
        //     Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        //     id = params.get("id");

        System.out.println(file.getSize());
        if (valid()) {
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

                        PreparedStatement pre = con.prepareStatement("INSERT INTO user_image (UserName,image_name,image) VALUES(?,?,?)");
                        PreparedStatement pre1 = con.prepareStatement("INSERT INTO login_table (UserName,password,name,usertype) VALUES(?,?,?,?)");
                        //        PreparedStatement pre = con.prepareStatement("UPDATE upload_image SET image_name=?,image=? WHERE image_id=?");
                        pre.setString(1, userName);
                        pre.setString(2, file.getFileName().toString());
                        pre.setBlob(3, is);
                        pre.executeUpdate();

                        pre1.setString(1, userName);
                        pre1.setString(2, password);
                        pre1.setString(3, name);
                        pre1.setString(4, userType);
                        pre1.executeUpdate();
                        System.out.println("Update Successfully!");
                        pre.close();
                        fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Inserted", ""));
                        this.setFile(null);
//                    this.setFirstName(null);
//                    this.setLastName(null);
                        this.setName(null);
                        this.setUserName(null);
                        this.setUserType(null);

                    } catch (Exception e) {
                        System.out.println("Exception-File Upload." + e.getMessage());
                    }
                }
            } else {
                fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Please select image!!!", ""));

            }
        } else {
            fc.addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "UserName already exist!!!", ""));
        }

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(1000, 1000, type);//set width and height of image
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1000, 1000, null);
        g.dispose();

        return resizedImage;
    }


}

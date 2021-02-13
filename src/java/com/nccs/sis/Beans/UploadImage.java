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
import java.sql.Statement;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "uploadImagee")
@SessionScoped
public class UploadImage {

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
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        id = params.get("id");
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
                //  PreparedStatement pre = con.prepareStatement("INSERT INTO upload_image (image_name,image) VALUES(?,?)");
                    PreparedStatement pre = con.prepareStatement("UPDATE upload_image SET image_name=?,image=? WHERE image_id=?");
                    pre.setString(1, file.getFileName().toString());
                    pre.setBlob(2, is);
                    pre.setString(3,id);
                    pre.executeUpdate();
                    System.out.println("Update Successfully!");
                    pre.close();
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
}

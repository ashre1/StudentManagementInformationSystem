package com.nccs.sis.Beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class imageBean {

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;

    private imageBean table;
    private String imageID;
    private String imageName;
    private String imageLength;

    public imageBean getTable() {
        return table;
    }

    public void setTable(imageBean table) {
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

    public List<imageBean> getAllImage() {
        List<imageBean> imageInfo = new ArrayList<imageBean>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();

            String strSql = "select image_id,Image_name from upload_image order by image_id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                imageBean tbl = new imageBean();
                tbl.setImageID(rs.getString(1));
                tbl.setImageName(rs.getString(2));
                imageInfo.add(tbl);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

}

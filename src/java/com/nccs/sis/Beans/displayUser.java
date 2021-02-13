package com.nccs.sis.Beans;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@SessionScoped

public class displayUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Statement stmt = null;
        ResultSet rs;
        InputStream sImage;
        Blob d;
        try {

            String id = request.getParameter("image_id");
            System.out.println("inside servlet–>" + id);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/studentinformationststem", "root", "12345");
            stmt = con.createStatement();

            String strSql = "SELECT image FROM user_image WHERE image_id=" + id + " ";
            rs = stmt.executeQuery(strSql);
            if (rs.next()) {

                d = rs.getBlob(1);
                byte[] bytearray = new byte[10485769];
                int size = (int) d.length();
                sImage = d.getBinaryStream();

                response.reset();
                response.setContentType("image/jpg");
                while ((size = sImage.read(bytearray)) != -1) {
                    response.getOutputStream().
                            write(bytearray, 0, size);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

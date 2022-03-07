/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.manager_product.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author saotr
 */
public class DataHelper {

    public static Connection openConnect() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String Url = "jdbc:mysql://localhost:3306/qlsinhvien";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(Url, username, password);
        return connection;
    }
}

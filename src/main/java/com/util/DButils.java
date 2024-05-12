package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


    public class DButils {
        static final String JDBC_DRIVER = "";
        static final String DB_URL = "";
        static final String USER = "";
        static final String PASS = "";
        static Connection conn = null;

        public static Connection getConnection() {

            try {
                //加载oracle驱动
                Class.forName(JDBC_DRIVER);
                //通过驱动获取数据库的连接
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("连接成功");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return conn;
        }

        public static void Close() {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

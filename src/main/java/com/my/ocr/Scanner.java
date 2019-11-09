package com.my.ocr;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 扫描所有文件夹，识别图片中的数据库，写入文件
 */
public class Scanner {
    public static void main(String[] args) throws Exception {
        List<File> files = getPicFiles("e:/图片");

        Connection conn = getConnection();
        PreparedStatement insertPs = conn.prepareStatement("insert into pic_desc(path,pic_desc) values (?,?)");


        for (File file : files) {
            System.out.println(file);
            String path = file.getPath();
            String picDesc = OcrUtil.getPicDesc(path);

            insertPs.setString(1, path);
            insertPs.setString(2, picDesc);
            insertPs.execute();
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/picocr?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8";
        String user = "root";
        String pw = "root";
        return DriverManager.getConnection(url, user, pw);
    }

    public static List<File> getPicFiles(String rootPath) {
        File rootFile = new File(rootPath);
        List<File> files = (List<File>) FileUtils.listFiles(rootFile, new String[]{"jpg", "JPG", "png", "PNG", "jpeg", "JPEG"}, true);
        System.out.println("文件数：" + files.size());
        return files;
    }
}

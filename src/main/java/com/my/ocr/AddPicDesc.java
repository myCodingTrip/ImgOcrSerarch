package com.my.ocr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddPicDesc {
    public static void main(String[] args) throws Exception {
        Connection conn = Scanner.getConnection();
        String sql = "SELECT path FROM pic_desc t WHERE t.`pic_desc` IS NULL ORDER BY t.`last_modified` DESC LIMIT 1000";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        PreparedStatement updatePs = conn.prepareStatement("update pic_desc set pic_desc=? where path=?");
        while (rs.next()) {
            String path = rs.getString(1);
            if (!path.contains("美女")) {
                String picDesc = OcrUtil.getPicDesc(path);
                System.out.println(path);
                updatePs.setString(1, picDesc);
                updatePs.setString(2, path);
                updatePs.addBatch();
                Thread.sleep(500);
            }
        }
        updatePs.executeBatch();
    }
}
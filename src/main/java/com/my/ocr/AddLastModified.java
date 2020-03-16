package com.my.ocr;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * 为文件添加最后修改时间
 */
public class AddLastModified {
    public static void main(String[] args) throws Exception {
        Connection conn = Scanner.getConnection();
        PreparedStatement ps = conn.prepareStatement("select path from pic_desc where last_modified IS NULL");
        PreparedStatement updatePs = conn.prepareStatement("update pic_desc set last_modified=? where path=?");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String path = rs.getString(1);
            //获取文件修改时间
            long l = new File(path).lastModified();
            if (l > 0) {
                Timestamp lastModified = new Timestamp(l);
                updatePs.setTimestamp(1, lastModified);
                updatePs.setString(2, path);
                updatePs.addBatch();
            }
        }
        updatePs.executeBatch();
    }
}

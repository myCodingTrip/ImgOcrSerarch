package com.my.ocr;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 移动图片，同时更新数据库
 */
public class PicFileUtil {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection conn = Scanner.getConnection();
        String sql = "update pic_desc set path=? where path=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        List<File> picFiles = Scanner.getPicFiles("E:\\图片\\3美女\\二次元\\动漫\\食戟之灵\\");
        String destPath = "E:\\图片\\3美女\\二次元\\动漫\\食戟之灵1\\";
        for (File picFile : picFiles) {
            moveFile(ps, destPath, picFile);
        }
        //ps.executeBatch();
    }

    /**
     * @param ps
     * @param destPath 目标路径
     * @param picFile  原文件
     * @throws SQLException
     */
    public static void moveFile(PreparedStatement ps, String destPath, File picFile) throws SQLException {
        //移动文件
        picFile.renameTo(new File(destPath + picFile.getName()));
        //更新表数据
        ps.setString(1, destPath + picFile.getName());
        ps.setString(2, picFile.getPath());
        ps.addBatch();
    }
}

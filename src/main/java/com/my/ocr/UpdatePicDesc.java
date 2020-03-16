package com.my.ocr;

import com.google.common.collect.Sets;

import java.io.File;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 同步数据库与文件夹，在新增、移动、删除文件后运行
 */
public class UpdatePicDesc {
    public static void main(String[] args) throws Exception {
        Connection conn = Scanner.getConnection();
        PreparedStatement ps = conn.prepareStatement("select path from pic_desc");
        ResultSet rs = ps.executeQuery();
        Set<String> dbSet = new HashSet<>();
        while (rs.next()) {
            dbSet.add(rs.getString(1));
        }

        List<File> files = Scanner.getPicFiles("e:/图片");
        Set<String> fileSet = new HashSet<>();
        for (File file : files) {
            fileSet.add(file.getPath());
        }

        deleteObsolete(conn, dbSet, fileSet);

        insertNew(conn, dbSet, fileSet, true);

    }

    /**
     * 移除已经不存在的文件
     *
     * @param conn
     * @param dbSet
     * @param fileSet
     * @throws SQLException
     */
    public static void deleteObsolete(Connection conn, Set<String> dbSet, Set<String> fileSet) throws SQLException {
        //数据库存在文件不存在，删除这条记录
//        Set<String> deleteSet = new HashSet<>(dbSet);
//        deleteSet.removeAll(fileSet);
        Set<String> deleteSet = Sets.difference(dbSet, fileSet);
        System.out.println("delete: " + deleteSet.size());
        PreparedStatement deletePs = conn.prepareStatement("delete from pic_desc where path=?");
        for (String path : deleteSet) {
            deletePs.setString(1, path);
            deletePs.addBatch();
            System.out.println("delete: " + path);
        }
        deletePs.executeBatch();
    }

    /**
     * 插入新增的文件并进行ocr识别
     *
     * @param conn
     * @param dbSet
     * @param fileSet
     * @param ocrCheck
     * @throws SQLException
     */
    public static void insertNew(Connection conn, Set<String> dbSet, Set<String> fileSet, boolean ocrCheck) throws SQLException {
        //文件中存在数据库中不存在，识别图片插入数据库
//        Set<String> insertSet = new HashSet<>(fileSet);
//        insertSet.removeAll(dbSet);
        Set<String> insertSet = Sets.difference(fileSet, dbSet);
        System.out.println("insert: " + insertSet.size());
        PreparedStatement insertPs = conn.prepareStatement("insert into pic_desc(path,pic_desc,last_modified) values (?,?,?)");
        for (String path : insertSet) {
            String picDesc = null;
            if (ocrCheck) {
                picDesc = OcrUtil.getPicDesc(path);
            }
            //获取文件修改时间
            Timestamp lastModified = new Timestamp(new File(path).lastModified());
            insertPs.setString(1, path);
            insertPs.setString(2, picDesc);
            insertPs.setTimestamp(3, lastModified);
            insertPs.addBatch();
            System.out.println("insert: " + path);
        }
        insertPs.executeBatch();
    }
}

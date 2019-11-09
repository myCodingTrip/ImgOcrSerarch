package com.my.ocr;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateAsFileName {
    public static void main(String[] args) {
        String path = "E:\\图片\\3美女\\偷拍\\李雪";
        File rootFile = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH-mm-ss");
        List<File> files = (List<File>) FileUtils.listFiles(rootFile, new String[]{"jpg", "JPG", "png", "PNG", "jpeg", "JPEG"}, true);
        for (File file : files) {
            String sj = sdf.format(new Date(file.lastModified()));
            String newName = path + File.separator + sj + ".jpg";
            System.out.println(file.getName() + "\t" + newName);
            file.renameTo(new File(newName));
        }
    }
}

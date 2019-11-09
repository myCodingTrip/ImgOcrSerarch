package com.my.ocr;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class FileNameCleansing {
    public static void main(String[] args) {
        String path = "E:\\图片\\1这些年\\2012";
        File rootFile = new File(path);
        List<File> files = (List<File>) FileUtils.listFiles(rootFile, new String[]{"jpg", "JPG", "png", "PNG", "jpeg", "JPEG"}, true);
        for (File file : files) {
            String name = file.getName();
            String newName = name;
            newName = removeFirst(newName, "Screenshot_");
            newName = removeFirst(newName, "IMG_");
            newName = removeFirst(newName, "C360_");
            if (newName.indexOf("-") == 4) {
                newName = removeFirst(newName, "-");
                newName = removeFirst(newName, "-");
            }
            if (!name.equals(newName)) {
                System.out.println(name);
                System.out.println(newName);
                file.renameTo(new File(file.getParent() + File.separator + newName));
            }
        }

    }

    private static String removeFirst(String name, String pre) {
        if (name.contains(pre)) {
            String newName = name.replaceFirst(pre, "");
            return newName;
        }
        return name;
    }

}

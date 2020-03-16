package com.my.ocr;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据文件大小寻找相同的文件
 */
public class FindSamePic {
    public static void main(String[] args) {
        List<File> files = Scanner.getPicFiles("e:/图片");
        Map<Long, String> map = new HashMap<>();
        for (File file : files) {
            String path = map.get(file.length());
            if (path != null) {
                if (file.getName().equals(new File(path).getName()) && !path.contains("6.21东大计算机21333")) {
                    System.out.println(path + "\t" + file.getPath());
                }
            } else {
                map.put(file.length(), file.getPath());
            }
        }
    }
}

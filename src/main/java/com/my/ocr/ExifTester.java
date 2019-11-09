package com.my.ocr;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试用于读取图片的EXIF信息
 *
 * @author Winter Lau
 */
public class ExifTester {
    public static void main(String[] args) throws Exception {
        File jpegFile = new File("e:/图片/3美女/偷拍/李雪/20190603-09-27-00.jpg");
        Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
        Map<String, String> map = new HashMap<>();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
//                System.out.print(tag.getTagName() + "  -->");
//                System.out.println(tag.getDescription());
                map.put(tag.getTagName(), tag.getDescription());
            }
        }
        System.out.println(map.get("Orientation"));
    }


//    压缩类型->基线
//    数据精度-> 8位
//    图像高度-> 3024像素
//    图像宽度-> 4032像素
//    组件数量-> 3
//    分量1-> Y分量：量化表0，采样因子2 horiz / 2 vert
//    分量2-> Cb分量：量化表1，采样因子1 horiz / 1 vert
//    分量3-> Cr分量：量化表1，采样因子1 horiz / 1 vert
//    曝光时间-> 0.02秒
//    F编号-> F1.8
//    曝光程序->正常程序
//    ISO感光度等级-> 721
//    Exif版本-> 2.20
//    日期/时间原始-> 2019：06：03 09:27:08
//    数字化日期/时间-> 2019：06：03 09:27:08
//    组件配置-> YCbCr
//    快门速度值-> 1/49秒
//    光圈值-> F1.8
//    亮度值->-226/100
//    曝光偏差值-> 0 EV
//    最大光圈值-> F1.8
//    测光模式->平均
//    白平衡->未知
//    闪光灯->闪光灯没有闪光，自动
//    焦距-> 4.216 mm
//    次时间-> 388961
//    次段时间原始-> 388961
//    亚秒级时间数字化-> 388961
//    FlashPix版本-> 1.00
//    色彩空间-> sRGB
//    Exif图像宽度-> 4032像素
//    Exif图像高度-> 3024像素
//    感应方式->
//    场景类型->直接拍摄的图像
//    曝光模式->自动曝光
//    白平衡模式->自动白平衡
//    焦距35->未知
//    未知标签（0x0100）-> 4032
//    未知标签（0x0101）-> 3024
//    制作->小米
//    型号-> MIX 2S
//    方向->底部右侧（旋转180度）
//    X分辨率->每英寸72点
//    Y分辨率->每英寸72点
//    分辨率单位->英寸
//    日期/时间-> 2019：06：03 09:27:08
//    YCbCr定位->像素阵列的中心
//    互操作性索引->推荐的Exif互操作性规则（ExifR98）
//    互操作性版本-> 1.00
//    未知标签（0xa002）-> 320
//    未知标签（0xa003）-> 240
//    缩略图压缩-> JPEG（旧样式）
//    方向->底部右侧（旋转180度）
//    X分辨率->每英寸72点
//    Y分辨率->每英寸72点
//    分辨率单位->英寸
//    缩略图偏移量-> 870字节
//    缩略图长度-> 22541字节
}
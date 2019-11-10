package com.my.search.controller;

import com.my.search.bean.PicDesc;
import com.my.search.mapper.PicDescMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PicDescController {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("main/resources/mybatis/mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        factory.openSession();
    }

    @GetMapping("/index")
    private String index() {
        return "index";
    }

    @Autowired
    PicDescMapper mapper;

    @RequestMapping("/search/{keyword}")
    public String search(@PathVariable("keyword") String keyword, Model model) {
        List<PicDesc> list = mapper.search("%" + keyword + "%");
        for (PicDesc picDesc : list) {
            String desc = picDesc.getPicDesc();
            Pattern p = Pattern.compile("(?i)" + keyword);
            Matcher m = p.matcher(desc);
            Set<String> kwSet = new HashSet<>();
            while (m.find()) {
                kwSet.add(m.group());
            }
            //关键字标红，不区分大小写
            for (String kw : kwSet) {
                desc = desc.replaceAll(kw, "<font color=\"red\">" + kw + "</font>");
            }
            desc = desc.replace("\n", "<br/>");
            picDesc.setPicDesc(desc);
            handlePath(picDesc);
        }
        model.addAttribute("pics", list);
        return "searchPages/ocrSearchList";
    }

    /**
     * 文件路径转为虚拟路径
     *
     * @param picDesc
     */
    private void handlePath(PicDesc picDesc) {
        String path = picDesc.getPath();
        path = "/image" + path.replace("e:\\图片", "")
                .replace("E:\\图片", "")
                .replace("\\", "/");
        picDesc.setPath(path);
    }

    /**
     * 按文件名搜索
     *
     * @param keyword
     * @param model
     * @return
     */
    @RequestMapping("/searchByPath/{keyword}")
    public String searchByPath(@PathVariable("keyword") String keyword, Model model) {
//        keyword = keyword.replace(" ", "/");
        List<PicDesc> list = mapper.searchByPath("%" + keyword + "%");
        for (PicDesc picDesc : list) {
            handlePath(picDesc);
        }
        model.addAttribute("pics", list);
        return "searchPages/pathSearchList";
    }

//暂不实现
//    @RequestMapping("/searchByPath.ajax")
//    public Map<String, Object> searchByPathAjax(String keyword) {
//        List<PicDesc> list = mapper.searchByPath("%" + keyword + "%");
//        for (PicDesc picDesc : list) {
//            handlePath(picDesc);
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("pics", list.subList());
//
//        return map;
//    }

    //    @Test
    @RequestMapping("/lsr")
    public String lsr(Model model) {
        String path = "e:/图片";
        File file = new File(path);
        StringBuilder sb = new StringBuilder();
        sb.append(file.getName() + "<br/>");
//        System.out.println(file.getName());
        tree(file, 1, sb);
//        System.out.println(sb);
        model.addAttribute("data", sb.toString());
        return "lsr";
    }

    public void tree(File f, int level, StringBuilder sb) {
        String preStr = "";
        for (int i = 0; i < level; i++) {
            preStr += "----";
        }
        File[] childs = f.listFiles();
        for (int i = 0; i < childs.length; i++) {
            if (childs[i].isDirectory()) {
//                System.out.println(preStr + childs[i].getName());
                sb.append(preStr + childs[i].getName() + "<br/>");
                tree(childs[i], level + 1, sb);
            }
        }
    }

}

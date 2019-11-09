//package com.my;
//
//import com.my.search.bean.PicDesc;
//import com.my.search.mapper.PicDescMapper;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//public class PicDescControllerTest{
//
//    @Autowired
//    PicDescMapper mapper;
//
//    @RunWith(SpringRunner.class)
//    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    public void search(){
////        SqlSession sqlSession=getSqlSession();
////        PicDescMapper mapper = sqlSession.getMapper(PicDescMapper.class);
//        List<PicDesc> av = mapper.search("AV");
//        System.out.println(av);
//    }
//}

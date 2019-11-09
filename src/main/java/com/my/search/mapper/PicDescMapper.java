package com.my.search.mapper;

import com.my.search.bean.PicDesc;

import java.util.List;
import java.util.Map;

public interface PicDescMapper {
    List<PicDesc> search(String keyword);

    List<PicDesc> searchByPath(String keyword);

    List<PicDesc> searchByPathPaged(String keyword, Map<String, Object> map);
}

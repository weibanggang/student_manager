package com.bm.student_manager.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author bm
 * @date 2021/4/12 20:41
 * @describe
 */
@Repository
public interface MapperDao {

    @Select({"${sql}"})
    @ResultType(List.class)
    List<Map<String,Object>> executeQueryByList(@Param("sql") String sql);
}

package com.bm.student_manager.dao;

import com.bm.student_manager.entity.StudentCheck;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCheckMapper {
    /**
     * 根据主键删除操作
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 添加操作
     *
     * @param studentCheck
     * @return
     */
    int insert(StudentCheck studentCheck);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    StudentCheck selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<StudentCheck> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param studentCheck
     * @return
     */
    int updateByPrimaryKey(StudentCheck studentCheck);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}
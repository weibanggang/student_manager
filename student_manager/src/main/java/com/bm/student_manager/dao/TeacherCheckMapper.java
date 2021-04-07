package com.bm.student_manager.dao;

import com.bm.student_manager.entity.TeacherCheck;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCheckMapper {
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
     * @param teacherCheck
     * @return
     */
    int insert(TeacherCheck teacherCheck);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    TeacherCheck selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<TeacherCheck> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param teacherCheck
     * @return
     */
    int updateByPrimaryKey(TeacherCheck teacherCheck);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}
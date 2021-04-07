package com.bm.student_manager.service;

import com.bm.student_manager.entity.TeacherCheck;

import java.util.List;

public interface TeacherCheckService {

    /**
     * 删除操作 根据主键
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
     * 修改操作
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
package com.bm.student_manager.service;

import com.bm.student_manager.entity.StudentCheck;

import java.util.List;

public interface StudentCheckService {

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
     * 修改操作
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
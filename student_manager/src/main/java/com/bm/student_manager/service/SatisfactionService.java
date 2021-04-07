package com.bm.student_manager.service;

import com.bm.student_manager.entity.Satisfaction;

import java.util.List;

public interface SatisfactionService {

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
     * @param satisfaction
     * @return
     */
    int insert(Satisfaction satisfaction);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Satisfaction selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Satisfaction> selectAll();

    /**
     * 修改操作
     *
     * @param satisfaction
     * @return
     */
    int updateByPrimaryKey(Satisfaction satisfaction);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}
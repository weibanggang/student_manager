package com.bm.student_manager.service;

import com.bm.student_manager.entity.ClassScheduleCard;

import java.util.List;

public interface ClassScheduleCardService {

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
     * @param classScheduleCard
     * @return
     */
    int insert(ClassScheduleCard classScheduleCard);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    ClassScheduleCard selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<ClassScheduleCard> selectAll();

    /**
     * 修改操作
     *
     * @param classScheduleCard
     * @return
     */
    int updateByPrimaryKey(ClassScheduleCard classScheduleCard);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}
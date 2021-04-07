package com.bm.student_manager.dao;

import com.bm.student_manager.entity.ClassScheduleCard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassScheduleCardMapper {
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
     * 根据主键全部修改操作
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
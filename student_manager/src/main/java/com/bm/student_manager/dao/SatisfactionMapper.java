package com.bm.student_manager.dao;

import com.bm.student_manager.entity.Satisfaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatisfactionMapper {
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
     * 根据主键全部修改操作
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
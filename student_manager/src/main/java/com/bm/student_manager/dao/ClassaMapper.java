package com.bm.student_manager.dao;

import com.bm.student_manager.entity.Classa;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassaMapper {
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
     * @param classa
     * @return
     */
    int insert(Classa classa);

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    Classa selectByPrimaryKey(int id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<Classa> selectAll();

    /**
     * 根据主键全部修改操作
     *
     * @param classa
     * @return
     */
    int updateByPrimaryKey(Classa classa);

    /**
     * 查询所有数据
     * @return
     */
    int count();
}
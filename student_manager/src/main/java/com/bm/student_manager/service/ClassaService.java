package com.bm.student_manager.service;

import com.bm.student_manager.entity.Classa;

import java.util.List;

public interface ClassaService {

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

    List<Classa> selectPage(String className,String teacherName);
    /**
     * 修改操作
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
    int countPage(String className,String teacherName);
}
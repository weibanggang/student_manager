package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.ClassScheduleCardMapper;
import com.bm.student_manager.entity.ClassScheduleCard;
import com.bm.student_manager.service.ClassScheduleCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassScheduleCardServiceImpl implements ClassScheduleCardService {

    @Autowired
    private ClassScheduleCardMapper classScheduleCardMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return classScheduleCardMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param classScheduleCard
     * @return
     */
    @Override
    public int insert(ClassScheduleCard classScheduleCard) {
        return classScheduleCardMapper.insert(classScheduleCard);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public ClassScheduleCard selectByPrimaryKey(int id) {
        return classScheduleCardMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<ClassScheduleCard> selectAll() {
        return classScheduleCardMapper.selectAll();
    }

    @Override
    public List<ClassScheduleCard> selectPage(String week, String courseName, String courseType, String teacherName) {
        return classScheduleCardMapper.selectPage(week,courseName,courseType,teacherName);
    }

    @Override
    public int countPage(String week, String courseName, String courseType, String teacherName) {
        return classScheduleCardMapper.countPage(week,courseName,courseType,teacherName);
    }

    /**
     * 修改操作
     *
     * @param classScheduleCard
     * @return
     */
    @Override
    public int updateByPrimaryKey(ClassScheduleCard classScheduleCard) {
        return classScheduleCardMapper.updateByPrimaryKey(classScheduleCard);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return classScheduleCardMapper.count();
    }
}

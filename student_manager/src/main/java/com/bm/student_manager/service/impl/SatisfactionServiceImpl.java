package com.bm.student_manager.service.impl;

import com.bm.student_manager.dao.SatisfactionMapper;
import com.bm.student_manager.entity.Satisfaction;
import com.bm.student_manager.service.SatisfactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SatisfactionServiceImpl implements SatisfactionService {

    @Autowired
    private SatisfactionMapper satisfactionMapper;

    /**
     * 删除操作 根据主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(int id) {
        return satisfactionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加操作
     *
     * @param satisfaction
     * @return
     */
    @Override
    public int insert(Satisfaction satisfaction) {
        return satisfactionMapper.insert(satisfaction);
    }

    /**
     * 根据主键查询操作
     *
     * @param id
     * @return
     */
    @Override
    public Satisfaction selectByPrimaryKey(int id) {
        return satisfactionMapper.selectByPrimaryKey(id);
    }

    /**
     * 全部查询操作
     *
     * @return
     */
    @Override
    public List<Satisfaction> selectAll() {
        return satisfactionMapper.selectAll();
    }

    @Override
    public List<Satisfaction> selectPage(String investigator, String surveySatisfaction) {
        return satisfactionMapper.selectPage(investigator,surveySatisfaction);
    }

    @Override
    public int countPage(String investigator, String surveySatisfaction) {
        return satisfactionMapper.countPage(investigator,surveySatisfaction);
    }

    /**
     * 修改操作
     *
     * @param satisfaction
     * @return
     */
    @Override
    public int updateByPrimaryKey(Satisfaction satisfaction) {
        return satisfactionMapper.updateByPrimaryKey(satisfaction);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public int count() {
        return satisfactionMapper.count();
    }
}

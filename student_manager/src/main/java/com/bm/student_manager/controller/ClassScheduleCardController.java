package com.bm.student_manager.controller;

import com.bm.student_manager.entity.ClassScheduleCard;
import com.bm.student_manager.service.ClassScheduleCardService;
import com.bm.student_manager.util.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classScheduleCard")
public class ClassScheduleCardController {
    @Autowired
    private ClassScheduleCardService classScheduleCardService;

    /**
     * 根据主键删除
     * 要求转入 aId
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(int id) {
        try {

            return classScheduleCardService.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功") : Result.error("删除失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 添加对象classScheduleCard
     *
     * @param classScheduleCard
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody ClassScheduleCard classScheduleCard) {
        try {
            return classScheduleCardService.insert(classScheduleCard) > 0 ? new Result().successMessage("添加成功！") : Result.error("添加失败！");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }

    }

    /**
     * 根据主键查找对象  最多只能返回一个对象
     *
     * @param id
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int id) {
        try {
            ClassScheduleCard classScheduleCard1 = classScheduleCardService.selectByPrimaryKey(id);
            if (classScheduleCard1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(classScheduleCard1);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        try {
            List<ClassScheduleCard> list = classScheduleCardService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }

    /**
     * 根据主键修改全部字段
     *
     * @param classScheduleCard
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody ClassScheduleCard classScheduleCard) {
        try {
            return classScheduleCardService.updateByPrimaryKey(classScheduleCard) > 0 ? new Result().successMessage("修改成功") : Result.error("修改失败");
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
    
    
     /* 查询所有数据分页
     *
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        try {
            PageHelper.startPage(page, limit);
            List<ClassScheduleCard> list = classScheduleCardService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result(200, "ok", list, classScheduleCardService.count());
            }
        } catch (Exception ex) {
            return new Result().error(ex.getMessage());
        }
    }
}

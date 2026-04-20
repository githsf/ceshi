package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工实体类
 */
@Data
public class Employee {
    /**
     * 员工ID
     */
    private Integer id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 性别 1:男，2:女
     */
    private Integer gender;
    
    /**
     * 手机号
     */
    private String phone;
    
    /**
     * 职位 1:班主任，2:讲师，3:学工主管，4:教研主管，5:咨询师
     */
    private Integer position;
    
    /**
     * 部门ID
     */
    private Integer deptId;
    
    /**
     * 头像路径
     */
    private String image;
    
    /**
     * 入职日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime entryDate;
    
    /**
     * 薪资
     */
    private Integer salary;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 工作经历列表
     */
    private List<EmployeeExperience> exprList;
} 
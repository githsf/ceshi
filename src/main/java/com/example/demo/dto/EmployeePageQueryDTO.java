package com.example.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 员工分页查询参数DTO
 */
@Data
public class EmployeePageQueryDTO {
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 性别
     */
    private Integer gender;
    
    /**
     * 入职开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    
    /**
     * 入职结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
    
    /**
     * 页码
     */
    private Integer page;
    
    /**
     * 每页显示记录数
     */
    private Integer pageSize;
} 
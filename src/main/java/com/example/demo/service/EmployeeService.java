package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.dto.EmployeePageQueryDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 员工服务接口
 */
public interface EmployeeService {
    /**
     * 分页查询员工列表
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    PageInfo<Employee> page(EmployeePageQueryDTO queryDTO);

    /**
     * 根据ID查询员工
     * @param id 员工ID
     * @return 员工信息
     */
    Employee getById(Integer id);

    /**
     * 新增员工
     * @param employee 员工信息
     */
    void save(Employee employee);

    /**
     * 更新员工信息
     * @param employee 员工信息
     */
    void update(Employee employee);

    /**
     * 删除员工
     * @param ids 员工ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 查询所有员工
     * @return 员工列表
     */
    List<Employee> list();
} 
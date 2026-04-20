package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Employee;
import com.example.demo.dto.EmployeePageQueryDTO;
import com.example.demo.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 员工管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询员工列表
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    @GetMapping
    public Result<Map<String, Object>> page(EmployeePageQueryDTO queryDTO) {
        log.info("分页查询员工列表：{}", queryDTO);
        PageInfo<Employee> pageInfo = employeeService.page(queryDTO);
        Map<String, Object> data = new HashMap<>();
        data.put("total", pageInfo.getTotal());
        data.put("rows", pageInfo.getList());
        return Result.success(data);
    }

    /**
     * 根据ID查询员工
     * @param id 员工ID
     * @return 员工信息
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Integer id) {
        log.info("根据ID查询员工：{}", id);
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 新增员工
     * @param employee 员工信息
     * @return 操作结果
     */
    @PostMapping
    public Result<Void> save(@RequestBody Employee employee) {
        log.info("新增员工：{}", employee);
        employeeService.save(employee);
        return Result.success(null);
    }

    /**
     * 更新员工信息
     * @param employee 员工信息
     * @return 操作结果
     */
    @PutMapping
    public Result<Void> update(@RequestBody Employee employee) {
        log.info("更新员工信息：{}", employee);
        employeeService.update(employee);
        return Result.success(null);
    }

    /**
     * 删除员工
     * @param ids 员工ID列表，多个ID用逗号分隔
     * @return 操作结果
     */
    @DeleteMapping
    public Result<Void> delete(@RequestParam String ids) {
        log.info("删除员工：{}", ids);
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        employeeService.delete(idList);
        return Result.success(null);
    }

    /**
     * 查询所有员工
     * @return 员工列表
     */
    @GetMapping("/list")
    public Result<List<Employee>> list() {
        log.info("查询所有员工");
        List<Employee> list = employeeService.list();
        return Result.success(list);
    }
} 
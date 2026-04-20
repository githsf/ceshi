package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Dept;
import com.example.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result<List<Dept>> list() {
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id) {
        deptService.delete(id);
        return Result.success(null);
    }

    @PostMapping
    public Result<Void> add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success(null);
    }

    @GetMapping("/{id}")
    public Result<Dept> getById(@PathVariable Integer id) {
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result<Void> update(@RequestBody Dept dept) {
        deptService.update(dept);
        return Result.success(null);
    }
} 
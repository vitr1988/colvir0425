package com.colvir.webinar26.controller;

import com.colvir.webinar26.dto.DepartmentDto;
import com.colvir.webinar26.mapper.DepartmentMapper;
import com.colvir.webinar26.repostiory.DepartmentRepository;
import com.colvir.webinar26.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Validated
@Controller
@RequiredArgsConstructor
public class DepartmentPageController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @GetMapping("/departments")
    public String index() {
        return "department/departments";
    }

    @GetMapping("/department/add")
    public String newDepartment() {
        return "department/department";
    }

    @GetMapping("/department/edit")
    public Mono<Rendering> currentDepartment(@RequestParam("id") String departmentId/*, Model model*/) {
        return departmentService.findById(departmentId)
                .map(it ->
                        Rendering.view("department/department")
                                .modelAttribute("department", it)
                                .build()
                );
    }

    @PostMapping("/department/save")
    public Mono<String> saveDepartment(@Valid DepartmentDto department) {
        return departmentService.save(departmentMapper.toEntity(department)).map(it ->
                "redirect:/departments"
        );
    }
}

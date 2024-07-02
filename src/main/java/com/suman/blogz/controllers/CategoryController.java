package com.suman.blogz.controllers;

import com.suman.blogz.entities.Category;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.CategoryDto;
import com.suman.blogz.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogz/category/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category newCategory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(newCategory));
    }
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updatecategory(@PathVariable Integer categoryId, @Valid @RequestBody Category updatedCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryId, updatedCategory));
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryById(categoryId));
    }
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Integer categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategoryById(categoryId));
    }
}

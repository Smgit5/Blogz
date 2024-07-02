package com.suman.blogz.services;

import com.suman.blogz.entities.Category;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    ApiResponse createCategory(Category newCategory);
    ApiResponse updateCategory(Integer categoryId, Category updatedCategory);
    CategoryDto getCategoryById(Integer categoryId);
    List<CategoryDto> getAllCategories();
    ApiResponse deleteCategoryById(Integer categoryId);
}

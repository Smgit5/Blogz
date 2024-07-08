package com.suman.blogz.services.impl;

import com.suman.blogz.entities.Category;
import com.suman.blogz.repository.CategoryRepository;
import com.suman.blogz.exceptions.ResourceNotFoundException;
import com.suman.blogz.payloads.response.ApiResponse;
import com.suman.blogz.payloads.dto.CategoryDto;
import com.suman.blogz.services.CategoryService;
import com.suman.blogz.utils.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GenericMapper genericMapper;
    @Override
    public ApiResponse createCategory(Category newCategory) {
        categoryRepository.save(newCategory);
        return new ApiResponse("Successfully created category!", true);
    }

    @Override
    public ApiResponse updateCategory(Integer categoryId, Category updatedCategory) {
        if(categoryRepository.existsById(categoryId)) {
            updatedCategory.setId(categoryId);
            categoryRepository.save(updatedCategory);
            return new ApiResponse("Successfully updated category!", true);
        }
        else {
            throw new ResourceNotFoundException("Category", categoryId);
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category categoryFromDb = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
        return modelMapper.map(categoryFromDb, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return genericMapper.mapList(categoryRepository.findAll(), CategoryDto.class);
    }

    @Override
    public ApiResponse deleteCategoryById(Integer categoryId) {
        Category categoryToDelete = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
        categoryRepository.delete(categoryToDelete);
        return new ApiResponse("Successfully deleted category", true);
    }
}

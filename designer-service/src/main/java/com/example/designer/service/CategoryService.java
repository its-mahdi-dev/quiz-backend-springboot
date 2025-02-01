package com.example.designer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.designer.model.Category;
import com.example.designer.repository.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Map<String, Object> getCategories(int page, int limit, String search) {
        int offset = (page - 1) * limit;

        List<Category> categories;
        long totalItems;

        if (search != null && !search.isEmpty()) {
            categories = categoryRepository.findByNameContaining(search, offset, limit);
            totalItems = categoryRepository.countByNameContaining(search);
        } else {
            categories = categoryRepository.findAll(offset, limit);
            totalItems = categoryRepository.count();
        }

        categories = categories.stream().map(category -> {
            Category filteredCategory = new Category();
            filteredCategory.setId(category.getId());
            filteredCategory.setName(category.getName());
            return filteredCategory;
        })
                .collect(Collectors.toList());
        long totalPages = (long) Math.ceil((double) totalItems / limit);

        Map<String, Object> response = new HashMap<>();
        response.put("data", categories);
        response.put("meta", Map.of(
                "currentPage", page,
                "totalPages", totalPages,
                "totalItems", totalItems));

        return response;
    }

    public void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }
}

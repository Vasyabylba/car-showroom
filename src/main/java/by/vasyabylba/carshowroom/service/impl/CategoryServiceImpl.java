package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.category.CategoryRequest;
import by.vasyabylba.carshowroom.dto.category.CategoryResponse;
import by.vasyabylba.carshowroom.entity.Category;
import by.vasyabylba.carshowroom.excteption.CategoryNotFoundException;
import by.vasyabylba.carshowroom.mapper.CategoryMapper;
import by.vasyabylba.carshowroom.repository.CategoryRepository;
import by.vasyabylba.carshowroom.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse getOne(UUID categoryId) {
        Category category = getCategoryById(categoryId);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(savedCategory);
    }

    @Override
    public CategoryResponse update(UUID categoryId, CategoryRequest categoryRequest) {
        Category category = getCategoryById(categoryId);

        categoryMapper.updateWithNull(categoryRequest, category);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(savedCategory);
    }

    @Override
    public void delete(UUID categoryId) {
        if (categoryId == null) {
            return;
        }

        categoryRepository.deleteById(categoryId);
    }

    private Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.byCategoryId(id));
    }

}

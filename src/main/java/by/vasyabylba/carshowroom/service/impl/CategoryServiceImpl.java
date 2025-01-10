package by.vasyabylba.carshowroom.service.impl;

import by.vasyabylba.carshowroom.dto.CategoryRequest;
import by.vasyabylba.carshowroom.dto.CategoryResponse;
import by.vasyabylba.carshowroom.entity.Category;
import by.vasyabylba.carshowroom.excteption.CategoryNotFoundException;
import by.vasyabylba.carshowroom.mapper.CategoryMapper;
import by.vasyabylba.carshowroom.repository.CategoryRepository;
import by.vasyabylba.carshowroom.repository.impl.CategoryRepositoryImpl;
import by.vasyabylba.carshowroom.service.CategoryService;

import java.util.List;
import java.util.UUID;

public class CategoryServiceImpl implements CategoryService {

    private static final CategoryService INSTANCE = new CategoryServiceImpl();

    private final CategoryRepository categoryRepository = CategoryRepositoryImpl.getInstance();

    public static CategoryService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper.INSTANCE::toCategoryResponse)
                .toList();
    }

    @Override
    public CategoryResponse getOne(UUID id) {
        Category category = getCategoryById(id);

        return CategoryMapper.INSTANCE.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryRequest);

        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.INSTANCE.toCategoryResponse(savedCategory);
    }

    @Override
    public CategoryResponse update(UUID id, CategoryRequest categoryRequest) {
        Category category = getCategoryById(id);

        CategoryMapper.INSTANCE.updateWithNull(categoryRequest, category);

        Category savedCategory = categoryRepository.update(category);
        return CategoryMapper.INSTANCE.toCategoryResponse(savedCategory);
    }

    @Override
    public void delete(UUID id) {
        if (id == null) {
            return;
        }

        categoryRepository.deleteById(id);
    }

    private Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.byCategoryId(id));
    }

}

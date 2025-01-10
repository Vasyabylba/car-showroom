package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.category.CategoryRequest;
import by.vasyabylba.carshowroom.dto.category.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryResponse> getAll();

    CategoryResponse getOne(UUID categoryId);

    CategoryResponse create(CategoryRequest categoryRequest);

    CategoryResponse update(UUID categoryId, CategoryRequest categoryRequest);

    void delete(UUID categoryId);

}

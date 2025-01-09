package by.vasyabylba.carshowroom.service;

import by.vasyabylba.carshowroom.dto.CategoryRequest;
import by.vasyabylba.carshowroom.dto.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryResponse> getAll();

    CategoryResponse getOne(UUID id);

    CategoryResponse create(CategoryRequest categoryRequest);

    CategoryResponse update(UUID id, CategoryRequest categoryRequest);

    void delete(UUID id);

}

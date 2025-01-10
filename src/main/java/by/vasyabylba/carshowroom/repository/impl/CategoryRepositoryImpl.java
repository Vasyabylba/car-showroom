package by.vasyabylba.carshowroom.repository.impl;

import by.vasyabylba.carshowroom.entity.Category;
import by.vasyabylba.carshowroom.repository.CategoryRepository;

import java.util.UUID;

public class CategoryRepositoryImpl extends CrudRepositoryImpl<Category, UUID> implements CategoryRepository {

    private static final CategoryRepository INSTANCE = new CategoryRepositoryImpl();

    private CategoryRepositoryImpl() {
        super(Category.class);
    }

    public static CategoryRepository getInstance() {
        return INSTANCE;
    }

}

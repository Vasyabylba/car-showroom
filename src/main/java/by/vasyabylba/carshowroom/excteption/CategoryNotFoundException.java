package by.vasyabylba.carshowroom.excteption;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {

    public static final String CATEGORY_NOT_FOUND_BY_ID = "Category with id '%s' not found";

    private CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException byCategoryId(UUID categoryId) {
        return new CategoryNotFoundException(
                String.format(CATEGORY_NOT_FOUND_BY_ID, categoryId)
        );
    }

}

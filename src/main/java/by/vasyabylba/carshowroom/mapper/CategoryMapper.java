package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.category.CategoryRequest;
import by.vasyabylba.carshowroom.dto.category.CategoryResponse;
import by.vasyabylba.carshowroom.entity.Category;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Category toEntity(CategoryRequest categoryRequest);

    @InheritInverseConfiguration(name = "toEntity")
    CategoryResponse toCategoryResponse(Category category);

    @InheritConfiguration(name = "toEntity")
    Category updateWithNull(CategoryRequest categoryRequest, @MappingTarget Category category);

}
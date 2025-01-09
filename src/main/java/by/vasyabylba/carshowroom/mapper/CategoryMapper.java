package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.CategoryRequest;
import by.vasyabylba.carshowroom.dto.CategoryResponse;
import by.vasyabylba.carshowroom.entity.Category;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    Category toEntity(CategoryRequest categoryRequest);

    @InheritInverseConfiguration(name = "toEntity")
    CategoryResponse toCategoryResponse(Category category);

    @InheritConfiguration(name = "toEntity")
    Category updateWithNull(CategoryRequest categoryRequest, @MappingTarget Category category);

}
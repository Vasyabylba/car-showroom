package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.car.CarRequest;
import by.vasyabylba.carshowroom.dto.car.CarResponse;
import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.Category;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {

    @Mapping(target = "category", source = "categoryId", qualifiedByName = "categoryIdToCategory")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "showroom", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Car toEntity(CarRequest carRequest);

    @Mapping(target = "categoryId", source = "category.id")
    CarResponse toCarResponse(Car car);

    @InheritConfiguration(name = "toEntity")
    Car updateWithNull(CarRequest carRequest, @MappingTarget Car car);

    @Named("categoryIdToCategory")
    default Category categoryIdToCategory(UUID categoryId) {
        return categoryId != null ? Category.builder().id(categoryId).build() : null;
    }

}
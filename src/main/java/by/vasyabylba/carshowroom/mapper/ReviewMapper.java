package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.ReviewRequest;
import by.vasyabylba.carshowroom.dto.ReviewResponse;
import by.vasyabylba.carshowroom.entity.Car;
import by.vasyabylba.carshowroom.entity.Client;
import by.vasyabylba.carshowroom.entity.Review;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(target = "car", source = "carId", qualifiedByName = "carIdToCar")
    @Mapping(target = "client", source = "clientId", qualifiedByName = "clientIdToCar")
    Review toEntity(ReviewRequest reviewRequest);

    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "clientId", source = "client.id")
    ReviewResponse toReviewResponse(Review review);

    @InheritConfiguration(name = "toEntity")
    Review updateWithNull(ReviewRequest reviewRequest, @MappingTarget Review review);

    @Named("carIdToCar")
    default Car carIdToCar(UUID carId) {
        return carId != null ? Car.builder().id(carId).build() : null;
    }

    @Named("clientIdToCar")
    default Client clientIdToCar(UUID clientId) {
        return clientId != null ? Client.builder().id(clientId).build() : null;
    }

}
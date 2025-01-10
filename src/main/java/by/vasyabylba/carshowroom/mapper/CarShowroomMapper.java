package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomRequest;
import by.vasyabylba.carshowroom.dto.carshowroom.CarShowroomResponse;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarShowroomMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    CarShowroom toEntity(CarShowroomRequest carShowroomRequest);

    CarShowroomResponse toCarShowroomResponse(CarShowroom carShowroom);

    @InheritConfiguration(name = "toEntity")
    CarShowroom updateWithNull(CarShowroomRequest carShowroomRequest, @MappingTarget CarShowroom carShowroom);

}
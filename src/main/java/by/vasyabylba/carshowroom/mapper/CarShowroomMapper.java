package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.CarShowroomRequest;
import by.vasyabylba.carshowroom.dto.CarShowroomResponse;
import by.vasyabylba.carshowroom.entity.CarShowroom;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarShowroomMapper {

    CarShowroomMapper INSTANCE = Mappers.getMapper(CarShowroomMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    CarShowroom toEntity(CarShowroomRequest carShowroomRequest);

    CarShowroomResponse toCarShowroomResponse(CarShowroom carShowroom);

    @InheritConfiguration(name = "toEntity")
    CarShowroom updateWithNull(CarShowroomRequest carShowroomRequest, @MappingTarget CarShowroom carShowroom);

}
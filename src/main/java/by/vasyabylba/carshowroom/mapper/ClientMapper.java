package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.ClientRequest;
import by.vasyabylba.carshowroom.dto.ClientResponse;
import by.vasyabylba.carshowroom.entity.Client;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Client toEntity(ClientRequest clientRequest);

    ClientResponse toClientResponse(Client client);

    @InheritConfiguration(name = "toEntity")
    Client updateWithNull(ClientRequest clientRequest, @MappingTarget Client client);

}
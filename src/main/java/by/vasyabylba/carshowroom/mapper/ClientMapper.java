package by.vasyabylba.carshowroom.mapper;

import by.vasyabylba.carshowroom.dto.client.ClientRequest;
import by.vasyabylba.carshowroom.dto.client.ClientResponse;
import by.vasyabylba.carshowroom.entity.Client;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cars", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    Client toEntity(ClientRequest clientRequest);

    ClientResponse toClientResponse(Client client);

    @InheritConfiguration(name = "toEntity")
    Client updateWithNull(ClientRequest clientRequest, @MappingTarget Client client);

}
package com.senla.courses.autoservice.dto.mappers;

import com.senla.courses.autoservice.dto.GarageDto;
import com.senla.courses.autoservice.model.Garage;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring", uses = GaragePlaceMapper.class)
public interface GarageMapper {
    GarageDto garageToGarageDto(Garage garage);
    Garage garageDtoToGarage(GarageDto garage);
}

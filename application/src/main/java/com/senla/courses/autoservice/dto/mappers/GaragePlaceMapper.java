package com.senla.courses.autoservice.dto.mappers;

import com.senla.courses.autoservice.dto.GarageDto;
import com.senla.courses.autoservice.dto.GaragePlaceDto;
import com.senla.courses.autoservice.dto.OrderDto;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel="spring")
public interface GaragePlaceMapper {
    GaragePlaceDto garagePlaceToGaragePlaceDto(GaragePlace garage);
    GaragePlace garagePlaceDtoToGaragePlace(GaragePlaceDto garage);

    @Mappings({
            @Mapping(target = "masters", ignore = true),
            @Mapping(target = "garagePlace", ignore = true)
    })
    OrderDto orderToOrderDto(Order order);

    @Mappings({
            @Mapping(target = "garagePlaces", ignore = true)
    })
    GarageDto garageToGarageDto(Garage garage);
}

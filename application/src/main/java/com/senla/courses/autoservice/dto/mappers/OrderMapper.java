package com.senla.courses.autoservice.dto.mappers;

import com.senla.courses.autoservice.dto.GarageDto;
import com.senla.courses.autoservice.dto.GaragePlaceDto;
import com.senla.courses.autoservice.dto.MasterDto;
import com.senla.courses.autoservice.dto.OrderDto;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.GaragePlace;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel="spring")
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto order);

    @Mappings({
            @Mapping(target = "garagePlaces", ignore = true)
    })
    GarageDto garageToGarageDto(Garage garage);

    @Mappings({
            @Mapping(target = "order", ignore = true)
    })
    MasterDto masterToMasterDto(Master master);

    @Mappings({
            @Mapping(target = "order", ignore = true)
    })
    GaragePlaceDto garagePlaceToGaragePlaceDto(GaragePlace garagePlace);
}

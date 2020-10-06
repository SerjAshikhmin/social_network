package com.senla.courses.autoservice.dto.mappers;

import com.senla.courses.autoservice.dto.GarageDto;
import com.senla.courses.autoservice.dto.MasterDto;
import com.senla.courses.autoservice.dto.OrderDto;
import com.senla.courses.autoservice.model.Garage;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel="spring")
public interface MasterMapper {
    MasterDto masterToMasterDto(Master master);
    Master masterDtoToMaster(MasterDto masterDto);

    @Mappings({
            @Mapping(target = "garagePlaces", ignore = true)
    })
    GarageDto garageToGarageDto(Garage garage);

    @Mappings({
            @Mapping(target = "masters", ignore = true),
            @Mapping(target = "garagePlace", ignore = true)
    })
    OrderDto orderToOrderDto(Order order);

}

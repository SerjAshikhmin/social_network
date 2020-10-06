package com.senla.courses.autoservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senla.courses.autoservice.model.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class OrderDto implements Serializable {

    private int id;
    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime submissionDate;
    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private String kindOfWork;
    private int cost;
    private GaragePlaceDto garagePlace;
    private List<MasterDto> masters;
    private OrderStatus status;

    public OrderDto(int id, LocalDateTime submissionDate, LocalDateTime startDate, LocalDateTime endDate, String kindOfWork, int cost,
                    GaragePlaceDto garagePlace, List<MasterDto> masters, OrderStatus status) {
        this.id = id;
        this.submissionDate = submissionDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kindOfWork = kindOfWork;
        this.cost = cost;
        this.garagePlace = garagePlace;
        this.masters = masters;
        this.status = status;
        this.garagePlace.setBusy(true);
        for (MasterDto master : this.masters) {
            master.setOrder(this);
            master.setBusy(true);
        }
    }

    @Override
    public String toString() {
        String result = String.format("Order № %d, submission date: %s; start date: %s; end date: %s; kind of work: %s," +
                " cost: %d, status: %s, garage place: %s; masters: %s", id, submissionDate, startDate, endDate,
                kindOfWork, cost, status.toString(), garagePlace, masters);

        return result;
    }
}

package com.senla.courses.autoservice.model;

import com.senla.courses.autoservice.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -4862926644813433704L;
    @Id
    private int id;
    private LocalDateTime submissionDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String kindOfWork;
    private int cost;
    @OneToOne
    @JoinColumn(name = "garagePlace_id")
    private GaragePlace garagePlace;
    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Master> masters;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
    }

    public Order(int id, LocalDateTime submissionDate, LocalDateTime startDate, LocalDateTime endDate, String kindOfWork, int cost,
                 GaragePlace garagePlace, List<Master> masters, OrderStatus status) {
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
        for (Master master : this.masters) {
            master.setOrder(this);
            master.setBusy(true);
        }
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setKindOfWork(String kindOfWork) {
        this.kindOfWork = kindOfWork;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getKindOfWork() {
        return kindOfWork;
    }

    public int getCost() {
        return cost;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public GaragePlace getGaragePlace() {
        return garagePlace;
    }

    @Override
    public String toString() {
        String result = String.format("Order № %d, submission date: %s; start date: %s; end date: %s; kind of work: %s," +
                " cost: %d, status: %s, garage place: %s; masters: %s", id, submissionDate, startDate, endDate,
                kindOfWork, cost, status.toString(), garagePlace, masters);

        return result;
    }
}

package com.senla.courses.autoservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class MasterDto implements Serializable {

    private int id;
    private String name;
    private int category;
    private boolean busy;
    @JsonIgnore
    private OrderDto order;

    public MasterDto(int id, String name, int category) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.busy = false;
    }

    public MasterDto(int id, String name, int category, boolean busy, OrderDto order) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.busy = busy;
        this.order = order;
    }

    @Override
    public String toString() {
        String result = String.format("Master № %d, name: %s, category: %d, is %s busy", id, name, category, busy ? "" : "not");
        return result;
    }
}

package com.senla.courses.autoservice.controller;

import com.senla.courses.autoservice.dto.MasterDto;
import com.senla.courses.autoservice.dto.OrderDto;
import com.senla.courses.autoservice.dto.mappers.MasterMapper;
import com.senla.courses.autoservice.dto.mappers.OrderMapper;
import com.senla.courses.autoservice.service.interfaces.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/masters")
public class MasterController {

    @Autowired
    private IMasterService masterService;
    @Autowired
    MasterMapper mapper;
    @Autowired
    OrderMapper orderMapper;

    @GetMapping("")
    public ResponseEntity<List<MasterDto>> onGetAllMasters() {
        final List<MasterDto> masters = new ArrayList<>();
        masterService.getAllMasters().forEach(master -> {
            masters.add(mapper.masterToMasterDto(master));
        });
        return masters != null &&  !masters.isEmpty()
                ? new ResponseEntity<>(masters, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterDto> onFindMasterById(@PathVariable(name = "id") int id) {
        final MasterDto master = mapper.masterToMasterDto(masterService.findMasterById(id));
        return master != null
                ? new ResponseEntity<>(master, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sorted/{sortBy}")
    public ResponseEntity<List<MasterDto>> onGetAllMastersSorted(@PathVariable(name = "sortBy") String sortBy) {
        final List<MasterDto> masters = new ArrayList<>();
        masterService.getAllMastersSorted(sortBy).forEach(master -> {
            masters.add(mapper.masterToMasterDto(master));
        });
        return masters != null && !masters.isEmpty()
                ? new ResponseEntity<>(masters, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> masters(@RequestBody MasterDto master) {
        return masterService.addMaster(mapper.masterDtoToMaster(master)) == 1
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("")
    public ResponseEntity<?> onUpdateMaster(@RequestBody MasterDto master) {
        return masterService.updateMaster(mapper.masterDtoToMaster(master)) == 1
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> onRemoveMaster(@PathVariable("name") String name) {
        return masterService.removeMaster(name) == 1
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/order/{name}")
    public ResponseEntity<?> getCurrentOrder(@PathVariable("name") String name) {
        OrderDto order = orderMapper.orderToOrderDto(masterService.getCurrentOrder(name));
        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public int importMaster(String fileName) {
        return masterService.importMaster(fileName);
    }

    public boolean exportMaster(int id, String fileName) {
        return masterService.exportMaster(id, fileName);
    }

    public void saveState() {
        masterService.saveState();
    }

    public void loadState() {
        masterService.loadState();
    }
}

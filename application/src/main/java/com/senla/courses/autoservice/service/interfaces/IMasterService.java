package com.senla.courses.autoservice.service.interfaces;

import com.senla.courses.autoservice.dto.MasterDto;
import com.senla.courses.autoservice.dto.OrderDto;
import com.senla.courses.autoservice.model.Master;
import com.senla.courses.autoservice.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IMasterService {

    int addMaster(Master master);
    int removeMaster(String name);
    int updateMaster(Master master);
    List<Master> getAllMasters();
    List<Master> getAllMastersSorted(String sortBy);
    List<Master> getAllFreeMasters();
    Order getCurrentOrder(String name);
    Master findMasterByName(String name);
    Master findMasterById(int id);
    int importMaster(String fileName);
    boolean exportMaster(int id, String fileName);
    List<String> toList(Master master);
    void saveState();
    void loadState();
}

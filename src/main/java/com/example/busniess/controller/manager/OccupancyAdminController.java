package com.example.busniess.controller.manager;

import com.example.busniess.entity.Occupancy;
import com.example.busniess.resultpackage.ReturnResult;
import com.example.busniess.service.OccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class OccupancyAdminController {

    @Autowired
    OccupancyService occupancyServiceImpl;
    public ReturnResult selectAdminOccupancy(Occupancy occupancy,Integer pageNumber,Integer pageSize){
        occupancyServiceImpl.showByPage(occupancy, pageNumber, pageSize);


        return ReturnResult.success(occupancyServiceImpl.showByPage(occupancy, pageNumber, pageSize));
    }


}

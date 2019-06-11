package com.backend.api.controller;


import com.backend.api.helper.validation_objects.DoctorBasicHelper;
import com.backend.api.model.service.IdoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DoctorController {


    /**
     * dependency injection for doctor service
     */
    @Autowired
    IdoctorService idoctorService;


    /**
     * for saving hospital by hospital id
     * @param hospitalId containing hospital id
     * @param doctorBasicHelper containing doctor details needed for saving
     * @return object containing basic doctor details
     */
    @PostMapping(value = "/doctors/{hospitalId}", consumes = "application/json")
    public Object addDoctor(@PathVariable("hospitalId") Integer hospitalId,
                            @RequestBody @Valid DoctorBasicHelper doctorBasicHelper) {
        return idoctorService.doctorAdd(hospitalId, doctorBasicHelper);
    }


    /**
     * for getting doctor list by their specialization
     * @param specialization containing specialization
     * @param hospitaId containing hospital id
     * @param start needed for providing starting index in pagination
     * @param max needed for providing maximum results per request in pagination
     * @return object containing doctors list by their specializations
     */
    @GetMapping(value = "/doctors/{specialization}/{hospitalId}/{start}/{max}")
    public Object getDoctorListBySpecialization(@PathVariable("specialization") String specialization,
                                             @PathVariable("hospitalId") Integer hospitaId, @PathVariable("start") Integer start,
                                             @PathVariable("max") Integer max) {
        return idoctorService.doctorListBySpecialization(specialization, hospitaId, start, max);
    }

    /**
     * for getting doctor by their doctor id
     * @param doctorId containing doctor id
     * @return object containing all data related to doctor
     */
    @GetMapping(value = "/doctors/{doctorId}")
    public Object getDoctor(@PathVariable("doctorId") Integer doctorId) {
        return idoctorService.doctorGet(doctorId);
    }

}

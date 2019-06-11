package com.backend.api.controller;

import com.backend.api.helper.validation_objects.HospitalUpdateHelper;
import com.backend.api.helper.validation_objects.Signup;
import com.backend.api.model.service.IhospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class HospitalController {


    /**
     * Dependency injection from hospital service bean
     */
    @Autowired
    private IhospitalService ihospitalService;

    /**
     * for adding hospital
     * @param signup
     * @accepts application/json object
     * @return object containing basic data
     */
    @PostMapping(value = "/hospitals", consumes = "application/json")
    public Object addHospital(@RequestBody @Valid Signup signup) {
        return ihospitalService.hospitalAdd(signup);
    }


    /**
     * for updation of hospital
     * @param hospitalId
     * @param hospitalUpdateHelper
     * @accepts application/json object
     * @return object containing updated data
     */
    @PutMapping(value = "/hospitals/{hospitalId}", consumes = "application/json")
    public Object updateHospital(@PathVariable("hospitalId") Integer hospitalId, @RequestBody @Valid HospitalUpdateHelper hospitalUpdateHelper) {
        return ihospitalService.hospitalUpdate(hospitalId, hospitalUpdateHelper);
    }


    /**
     * for getting hospital by hospital id
     * @param hospitalId
     * @accepts application/json object
     * @return object containing complete data of hospital
     */
    @GetMapping(value = "/hospitals/{hospitalId}")
    public Object getHospital(@PathVariable("hospitalId") int hospitalId) {
        return ihospitalService.hospitalGet(hospitalId);
    }


    /**
     * for getting hospital list by city
     * @param city
     * @param start for starting index per request
     * @param max   for maximum results per request
     * @return object containing eager hospital list
     */
    @GetMapping(value = "/hospitals/{city}/{start}/{max}")
    public Object getHospitalListByCity(@PathVariable("city") String city, @PathVariable("start") Integer start,
                                     @PathVariable("max") Integer max) {
        return ihospitalService.hospitalListByCity(city, start, max);
    }


    /**
     * for getting hospital list
     * @param start for starting index per request
     * @param max   for maximum results per request
     * @return object containing eager hospital list
     */
    @GetMapping(value = "/hospitals/{start}/{max}")
    public Object getHospitalList(@PathVariable("start") Integer start,
                               @PathVariable("max") Integer max) {
        return ihospitalService.hospitalList(start, max);
    }


    /**
     * for getting static specialization list
     * @return object containing static specializations
     */
    @GetMapping(value = "/hospitals/specializations")
    public Object getHospitalSpecializations() {
        return ihospitalService.hospitalSpecializations();
    }


    /**
     * for getting static cities list
     * @return object containing static cities
     */
    @GetMapping(value = "/hospitals/cities")
    public Object getHospitalCities() {
        return ihospitalService.hospitalCities();
    }


    /**
     * for getting doctor list by hospital id
     * @param hospitalId
     * @param start for starting index per request
     * @param max   for maximum results per request
     * @return object containing
     */
    @GetMapping(value = "/doctors/{hospitalId}/{start}/{max}")
    public Object getDoctorsListByHospitalId(@PathVariable("hospitalId") Integer hospitalId,
                                          @PathVariable("start") Integer start, @PathVariable("max") Integer max) {
        return ihospitalService.doctorsListByHospitalId(hospitalId, start, max);
    }


}

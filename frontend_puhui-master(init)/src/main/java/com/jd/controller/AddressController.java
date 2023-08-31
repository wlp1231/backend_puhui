package com.jd.controller;

import com.jd.bean.Areas;
import com.jd.bean.Cities;
import com.jd.bean.Provinces;
import com.jd.service.IAreasService;
import com.jd.service.ICitiesService;
import com.jd.service.IProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/city")
public class AddressController {

    @Autowired
    private IProvincesService provincesService;

    @Autowired
    private ICitiesService citiesService;

    @Autowired
    private IAreasService areasService;

    @RequestMapping("/pro")
    @ResponseBody
    public List<Provinces> findProvinces(){
        return provincesService.selectProvincesAll();
    }

    @RequestMapping("/cit")
    @ResponseBody
    public List<Cities> findCity(String cId){
        return citiesService.selectCitiesByProId(cId);
    }

    @RequestMapping("/are")
    @ResponseBody
    public List<Areas> findArea(String aId){
        return areasService.selectAreaById(aId);
    }
}

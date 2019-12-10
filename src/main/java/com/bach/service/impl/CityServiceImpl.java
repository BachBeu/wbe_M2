package com.bach.service.impl;

import com.bach.models.City;
import com.bach.repository.CityRepository;
import com.bach.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

public class CityServiceImpl  implements CityService {
    @Autowired
    private CityRepository cityRepository;


    @Override
    public Iterable<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long CityId) {
        return cityRepository.findOne(CityId);
    }

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public void remove(Long CityId) {
        cityRepository.delete(CityId);
    }
}

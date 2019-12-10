package com.bach.service;

import com.bach.models.City;

public interface CityService {
    Iterable<City> findAll();
    City findById(Long CityId);
    void save(City city);
    void remove(Long CityId);

}

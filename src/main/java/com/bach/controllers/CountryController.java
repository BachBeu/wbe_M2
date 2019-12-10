package com.bach.controllers;

import com.bach.models.Country;
import com.bach.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;


    @GetMapping("/countries")
    public ModelAndView listCountry(){
        Iterable<Country> countries = countryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/country/list");
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }
    @GetMapping("/create-country")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/country/create");
        modelAndView.addObject("country", new Country());
        return modelAndView;
    }

    @PostMapping("/create-country")
    public ModelAndView saveCity(@ModelAttribute("country") Country country) {
        countryService.save(country);
        ModelAndView modelAndView = new ModelAndView("/country/create");
        modelAndView.addObject("country", new Country());
        modelAndView.addObject("message","Add Country Successfully");
        return modelAndView;
    }

    @GetMapping("/edit-country/{countryId}")
    public ModelAndView showEditCountry(@PathVariable Long countryId) {
        Country country = countryService.findById(countryId);
        if (country != null) {
            ModelAndView modelAndView = new ModelAndView("/country/edit");
            modelAndView.addObject("country",country);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-country")
    public ModelAndView updateCity(@ModelAttribute("country") Country country) {
        countryService.save(country);
        ModelAndView modelAndView = new ModelAndView("/country/edit");
        modelAndView.addObject("country",country);
        modelAndView.addObject("message", "Update country Successfully");
        return modelAndView;
    }

    @GetMapping("/delete-country/{countryId}")
    public ModelAndView showDeleteForm(@PathVariable Long countryId){
        Country country = countryService.findById(countryId);
        if(country != null) {
            ModelAndView modelAndView = new ModelAndView("/country/delete");
            modelAndView.addObject("country", country);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-country")
    public String deleteCity(@ModelAttribute("country") Country country){
        countryService.remove(country.getCountryId());
        return "redirect:countries";
    }
}

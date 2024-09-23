package com.vastrika.backend.city_request.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vastrika.backend.city_request.model.CityRequest;
import com.vastrika.backend.city_request.service.CityRequestService;

@RestController
@RequestMapping("/api/city-requests")
public class CityRequestController {

    @Autowired
    private CityRequestService cityRequestService;

    @PostMapping("/add")
    public ResponseEntity<CityRequest> addCityRequest(@RequestBody CityRequest cityRequest) {
        CityRequest newCityRequest = cityRequestService.addCityRequest(cityRequest);
        return ResponseEntity.ok(newCityRequest);
    }

    @GetMapping("/unresolved")
    public ResponseEntity<List<CityRequest>> getAllUnresolvedRequests() {
        List<CityRequest> unresolvedRequests = cityRequestService.getAllUnresolvedRequests();
        return ResponseEntity.ok(unresolvedRequests);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CityRequest> updateCityRequestStatus(@PathVariable int id, @RequestParam boolean status) {
        CityRequest updatedRequest = cityRequestService.updateCityRequestStatus(id, status);
        return ResponseEntity.ok(updatedRequest);
    }
}

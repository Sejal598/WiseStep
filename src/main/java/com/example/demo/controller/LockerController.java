package com.example.demo.controller;


import com.example.demo.model.Locker;
import com.example.demo.model.StoreDetails;
import com.example.demo.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LockerController {

    @Autowired
    LockerService lockerService;

    @GetMapping("/get-free-locker")
    public List<String> getFreeLocker() {
        List<String> freeLockers = lockerService.getFreeLockers();
        return freeLockers;
    }

    @PostMapping("/book-locker")
    public void bookLocker(@RequestBody Locker locker) throws Exception {
        lockerService.bookLocker(locker);
    }

    @PostMapping("/checkout-locker")

    public void checkOut(@RequestBody Locker locker) throws Exception {
        lockerService.checkout(locker);
    }

    @PostMapping("/store-belongings")
    public void storeBelongings(@RequestBody List<StoreDetails> storeDetailsList) throws Exception {
        lockerService.storeBelonging(storeDetailsList);
    }
}



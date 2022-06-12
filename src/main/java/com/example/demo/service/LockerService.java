package com.example.demo.service;


import com.example.demo.dao.LockerDao;
import com.example.demo.model.Locker;
import com.example.demo.model.StoreDetails;
import com.example.demo.utils.CommonConstants;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LockerService {


    @Autowired
    LockerDao lockerDao;

    public List<String> getFreeLockers() {
        List<Locker> lockers = lockerDao.getLockers();
        List<String> freeLockers = new ArrayList<>();
        for (Locker locker : lockers) {
            if (StringUtils.equals(locker.getLockerStatus(), CommonConstants.LOCKERFREE)) {
                freeLockers.add(locker.getLockerCode());
            }

        }
        return freeLockers;
    }

    public void bookLocker(Locker locker) throws Exception {

        Locker lockerDetails = lockerDao.getLockerDetailsByCode(locker.getLockerCode());
        if (validatePass(locker, lockerDetails)) {
            validateUserForBooking(locker, lockerDetails);
            updateLockerStatus(locker);
        } else {
            throw new Exception("Incorrect PassCode");
        }


    }


    public void updateLockerStatus(Locker locker) {
        lockerDao.saveLocker(locker);
    }


    public Locker validateUserForBooking(Locker locker, Locker lockerDetails) throws Exception {
        if (lockerDetails.getUserAssociated() == null) {
            locker.setUserAssociated(locker.getUserAssociated());
            locker.setLockerStatus(CommonConstants.LOCKEROCCUPIED);
            return locker;
        } else if (StringUtils.equals(locker.getUserAssociated(), lockerDetails.getUserAssociated())) {
            throw new Exception("Already booked by you");
        } else {
            throw new Exception("Booked by someone");
        }
    }


    public Boolean validatePass(Locker locker, Locker lockerDetails) {
        if (StringUtils.equals(locker.getPassword(), lockerDetails.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public Locker validateUserForCheckout(Locker locker, Locker lockerDetails) throws Exception {
        if (lockerDetails.getUserAssociated() == null) {
            throw new Exception("Already checked out");
        }
        if (StringUtils.equals(locker.getUserAssociated(), lockerDetails.getUserAssociated())) {
            throw new Exception("locker  does not belongs to you");
        }
        if (locker.getLockerPrice() != lockerDetails.getLockerPrice()) {
            throw new Exception("Please make correct payment for locker");
        }
        locker.setLockerStatus(CommonConstants.LOCKERFREE);
        locker.setUserAssociated(null);

        return locker;
    }


    public void checkout(Locker locker) throws Exception {
        Locker lockerDetails = lockerDao.getLockerDetailsByCode(locker.getLockerCode());
        if (validatePass(locker, lockerDetails)) {
            locker = validateUserForCheckout(locker, lockerDetails);
            lockerDao.saveLocker(locker);
        } else {
            throw new Exception("Incorrect PassCode");
        }

    }

    public void storeBelonging(List<StoreDetails> storeDetails) throws Exception {
        Locker lockerDetails = lockerDao.getLockerDetailsByCode(storeDetails.get(0).getLockerId());
        Locker locker = new Locker();
        locker.setLockerCode(storeDetails.get(0).getLockerId());
        if (validatePass(locker, lockerDetails)) {
            lockerDao.storeBelongings(storeDetails);
        } else {
            throw new Exception("Incorrect PassCode");
        }

    }

    public void storeDetails(List<StoreDetails> storeDetailsList) {
          lockerDao.storeBelongings(storeDetailsList);
    }


}

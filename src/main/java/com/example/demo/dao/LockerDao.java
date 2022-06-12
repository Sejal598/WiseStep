package com.example.demo.dao;

import com.example.demo.model.Locker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LockerDao {


    public List<Locker> getLockers() {
        ArrayList<Locker> lockers = new ArrayList<>();

        /// will populate from db
        return lockers;
    }

    public Locker getLockerDetailsByCode(String lockerCode) {

        // will fetch data by locker Id from db
        Locker  lockerDetails= new Locker();
        return lockerDetails ;

    }

    public void saveLocker(Locker locker)
    {
        /// will save data onto table
    }
}

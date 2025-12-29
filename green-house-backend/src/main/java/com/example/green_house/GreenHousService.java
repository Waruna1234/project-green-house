package com.example.green_house;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

@Service
public class GreenHousService {

    private final GreenHouseRepo repository;

    public GreenHousService(GreenHouseRepo repository) {
        this.repository = repository;
    }

    public GreenHouseModel create(GreenHouseModel data) {
        data.setId(UUID.randomUUID().toString());
        data.setTimestamp(System.currentTimeMillis());
        repository.save(data);
        return data;
    }

    public GreenHouseModel getById(String id)
            throws ExecutionException, InterruptedException {
        return repository.findById(id);
    }

    public List<GreenHouseModel> getAll()
            throws ExecutionException, InterruptedException {
        return repository.findAll();
    }

    public GreenHouseModel update(String id, GreenHouseModel data) {
        data.setId(id);
        repository.save(data);
        return data;
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public GreenHouseModel getLatest() throws InterruptedException {
    return repository.findLatest();
}


    





    
}

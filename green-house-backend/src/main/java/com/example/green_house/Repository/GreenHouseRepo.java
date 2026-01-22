package com.example.green_house.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.example.green_house.Model.GreenHouseModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

@Repository
public class GreenHouseRepo {

    private final DatabaseReference databaseReference;

    public GreenHouseRepo() {
        this.databaseReference =
            FirebaseDatabase.getInstance().getReference("green-hous");
    }
     public void save(GreenHouseModel data) {
        databaseReference.child(data.getId()).setValueAsync(data);
    }

    public GreenHouseModel findById(String id) throws InterruptedException {

        final GreenHouseModel[] result = new GreenHouseModel[1];
        CountDownLatch latch = new CountDownLatch(1);

        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                result[0] = snapshot.getValue(GreenHouseModel.class);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        latch.await();
        return result[0];
    }

    
     public List<GreenHouseModel> findAll() throws InterruptedException {

        List<GreenHouseModel> list = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    GreenHouseModel data = child.getValue(GreenHouseModel.class);
                    list.add(data);
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        latch.await(); // wait for Firebase response
        return list;
    }


   

    public void delete(String id) {
        databaseReference.child(id).removeValueAsync();
    }

    public GreenHouseModel findLatest() throws InterruptedException {

    final GreenHouseModel[] latest = new GreenHouseModel[1];
    CountDownLatch latch = new CountDownLatch(1);

    databaseReference
      .orderByChild("timestamp")
      .limitToLast(1)
      .addListenerForSingleValueEvent(new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot snapshot) {
            for (DataSnapshot child : snapshot.getChildren()) {
                latest[0] = child.getValue(GreenHouseModel.class);
            }
            latch.countDown();
        }

        @Override
        public void onCancelled(DatabaseError error) {
            latch.countDown();
        }
    });

    latch.await();
    return latest[0];
}

public boolean existsByTimestamp(String timestamp) throws InterruptedException {
    final boolean[] exists = {false};
    CountDownLatch latch = new CountDownLatch(1);

    databaseReference
        .orderByChild("timestamp")
        .equalTo(timestamp)
        .addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                exists[0] = snapshot.exists(); // true if there is any record with this timestamp
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

    latch.await();
    return exists[0];
}



    

}

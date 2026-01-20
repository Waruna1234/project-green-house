package com.example.green_house;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class GreenHouseController {

    private final GreenHousService service;

    public GreenHouseController(GreenHousService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping("/save-ui")
    public ResponseEntity<GreenHouseModel> create(  

            @RequestBody GreenHouseModel data) throws InterruptedException {

                System.out.println(data);               
                
        return ResponseEntity.ok(service.create(data));
        
    }

    // READ ALL
    @GetMapping("/history")
    public ResponseEntity<List<GreenHouseModel>> getAll()
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<GreenHouseModel> get(
            @PathVariable String id)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<GreenHouseModel> update(
            @PathVariable String id,
            @RequestBody GreenHouseModel data) {
        return ResponseEntity.ok(service.update(id, data));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("History record deleted");
    }

    // LATEST RECORD
    @GetMapping("/latest")
    public ResponseEntity<GreenHouseModel> getLatest()
            throws InterruptedException {
        return ResponseEntity.ok(service.getLatest());
    }
}

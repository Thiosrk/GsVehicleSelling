package com.gsvehicleselling.Repository;

import com.gsvehicleselling.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    List<Vehicle> findAllByEngineid(int id);
    List<Vehicle> findAllByDrivingmodeid(int id);
    List<Vehicle> findAllByGearboxid(int id);
    List<Vehicle> findAllByTyreid(int id);
}

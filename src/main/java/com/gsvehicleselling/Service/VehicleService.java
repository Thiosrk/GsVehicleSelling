package com.gsvehicleselling.Service;

import com.gsvehicleselling.Model.*;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAllVehicle();

    List<Vehicle> getVehicleByEngine(int id);

    List<Vehicle> getVehicleByGearbox(int id);

    List<Vehicle> getVehicleByDrivingmodel(int id);

    List<Vehicle> getVehicleByTyre(int id);

    List<Tyre> getAllTyre();

    List<Gearbox> getAllGearbox();

    List<Engine> getAllEngine();

    List<DrivingModel> getAllDrivingModel();

    Vehicle getVehicle(int id);

    void saveVehicle(Vehicle vehicle);

    void saveEngine(Engine engine);

    void saveGearbox(Gearbox gearbox);

    void saveDrivingModel(DrivingModel drivingModel);

    void saveTyre(Tyre tyre);


    Engine getEngine(int engineid);

    Gearbox getGearbox(int gearboxid);

    Tyre getTyre(int tyreid);

    Components getComponents(int componentsid);

    void deleteVehicle(int vehicleid);

    void deleteTyre(int tyreid);

    void deleteGearbox(int gearboxid);

    void deleteEngine(int engineid);

    void deleteDrivingmodel(int drivingmodelid);
}

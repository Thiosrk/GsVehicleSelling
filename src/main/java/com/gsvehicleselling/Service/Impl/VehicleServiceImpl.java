package com.gsvehicleselling.Service.Impl;

import com.gsvehicleselling.Model.*;
import com.gsvehicleselling.Repository.*;
import com.gsvehicleselling.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    TyreRepository tyreRepository;
    @Autowired
    GearboxRepository gearboxRepository;
    @Autowired
    EngineRepository engineRepository;
    @Autowired
    DrivingModelRepository drivingModelRepository;
    @Autowired
    ComponentsRepository componentsRepository;

    public List<Vehicle> getAllVehicle(){
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehicleByEngine(int id) {
        return vehicleRepository.findAllByEngineid(id);
    }

    @Override
    public List<Vehicle> getVehicleByGearbox(int id) {
        return vehicleRepository.findAllByGearboxid(id);
    }

    @Override
    public List<Vehicle> getVehicleByDrivingmodel(int id) {
        return vehicleRepository.findAllByDrivingmodeid(id);
    }

    @Override
    public List<Vehicle> getVehicleByTyre(int id) {
        return vehicleRepository.findAllByTyreid(id);
    }

    @Override
    public List<Tyre> getAllTyre() {
        return tyreRepository.findAll();
    }

    @Override
    public List<Gearbox> getAllGearbox() {
        return gearboxRepository.findAll();
    }

    @Override
    public List<Engine> getAllEngine() {
        return engineRepository.findAll();
    }

    @Override
    public List<DrivingModel> getAllDrivingModel() {
        return drivingModelRepository.findAll();
    }

    public Vehicle getVehicle(int id){
        return vehicleRepository.getOne(id);
    }

    public void saveVehicle(Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }

    @Override
    public void saveEngine(Engine engine) {
        engineRepository.save(engine);
    }

    @Override
    public void saveGearbox(Gearbox gearbox) {
        gearboxRepository.save(gearbox);
    }

    @Override
    public void saveDrivingModel(DrivingModel drivingModel) {
        drivingModelRepository.save(drivingModel);
    }

    @Override
    public void saveTyre(Tyre tyre) {
        tyreRepository.save(tyre);
    }

    @Override
    public Engine getEngine(int engineid) {
        return engineRepository.getOne(engineid);
    }

    @Override
    public Gearbox getGearbox(int gearboxid) {
        return gearboxRepository.getOne(gearboxid);
    }

    @Override
    public Tyre getTyre(int tyreid) {
        return tyreRepository.getOne(tyreid);
    }

    @Override
    public Components getComponents(int componentsid) {
        return componentsRepository.getOne(componentsid);
    }

    @Override
    public void deleteVehicle(int vehicleid) {
        vehicleRepository.deleteById(vehicleid);
    }

    @Override
    public void deleteTyre(int tyreid) {
        tyreRepository.deleteById(tyreid);
    }

    @Override
    public void deleteGearbox(int gearboxid) {
        gearboxRepository.deleteById(gearboxid);
    }

    @Override
    public void deleteEngine(int engineid) {
        engineRepository.deleteById(engineid);
    }

    @Override
    public void deleteDrivingmodel(int drivingmodelid) {
        drivingModelRepository.deleteById(drivingmodelid);
    }

}

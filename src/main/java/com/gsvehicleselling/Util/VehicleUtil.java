package com.gsvehicleselling.Util;

import com.gsvehicleselling.Model.DrivingModel;
import com.gsvehicleselling.Model.Engine;
import com.gsvehicleselling.Model.Gearbox;
import com.gsvehicleselling.Model.Tyre;

import java.util.List;

public class VehicleUtil {

    public static Tyre findTyre(int tyreid, List<Tyre> tyres){

        for (Tyre tyre : tyres){
            if (tyre.getId() == tyreid){
                return tyre;
            }
        }
        return null;
    }

    public static Engine findEngine(int engineid, List<Engine> engines){

        for (Engine engine : engines){
            if (engine.getId() == engineid){
                return engine;
            }
        }
        return null;
    }

    public static Gearbox findGearbox(int gearboxid, List<Gearbox> gearboxs){

        for (Gearbox gearbox : gearboxs){
            if (gearbox.getId() == gearboxid){
                return gearbox;
            }
        }
        return null;
    }

    public static DrivingModel findDrivingModel(int drivingmodelid, List<DrivingModel> drivingmodels){

        for (DrivingModel drivingmodel : drivingmodels){
            if (drivingmodel.getId() == drivingmodelid){
                return drivingmodel;
            }
        }
        return null;
    }


}

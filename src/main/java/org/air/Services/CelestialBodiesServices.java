package org.air.Services;

import lombok.AllArgsConstructor;
import org.air.BD.Data;
import org.air.Classes.CelestialBodies;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CelestialBodiesServices {
    private final Data data;

    //region GET OBJECT TO ID
    public ArrayList<CelestialBodies> getCelestialBodiesToID(int ID){
        try{
            return data.ReadDataToID(ID);
        } catch (IOException ex) { return null;}
    }
    //endregion

    //region GET ALL OBJECT
    public ArrayList<CelestialBodies> getCelestialBodies(){
        try{
            return data.ReadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

    //region GET OBJECT WITH RADIATION
    public ArrayList<CelestialBodies> getCelestialBodiesWithRadiation(int ID, boolean rad){
        try{
            return data.CelestialBodiesWithRadiation(ID, rad);
        } catch (IOException ex) { return null;}
    }
    //endregion

    //region GET REGRESSION ANALYSIS
    public String getRegressionAnalysisWithRadius() throws IOException {
        if (data.massVersusRadius()){
            ArrayList<CelestialBodies> celestialBodiesArrayList = data.ReadData();
            float attitide = celestialBodiesArrayList.get(0).getWeight()/celestialBodiesArrayList.get(0).getRadius();
            return "Зависимость между весом и радиусом:" + attitide;
        }else {return "Зависимость между весом и радиусом: отсутствует";}
    }

    public String getRegressionAnalysisWithSpeed() throws IOException {
        if (data.massVersusSpeed()){
            ArrayList<CelestialBodies> celestialBodiesArrayList = data.ReadData();
            float attitide = celestialBodiesArrayList.get(0).getWeight()/celestialBodiesArrayList.get(0).getSpeed();
            return "Зависимость между весом и скоростью:" + attitide;
        }else {return "Зависимость между весом и скоростью: отсутствует";}
    }
    //endregion

    //region ADD DATA
    public void AddCelestialBodies(CelestialBodies celestialBodies) throws IOException {
        data.AddData(celestialBodies);
    }
    //endregion

    //region UPDATE DATA
    public ArrayList<CelestialBodies> UpdateCelestialBodies(int ID, CelestialBodies celestialBodies) throws IOException {
        var data1 = data.ReadData();
        ArrayList<CelestialBodies> newCelestialBodies = data.UpdateCelestialBodies(ID, celestialBodies);
        data.UpdateData(newCelestialBodies);
        return newCelestialBodies;
    }
    //endregion

    //region DELETE DATA
    public boolean DeleteCelestialBodies(int ID) throws IOException {
        ArrayList<CelestialBodies> celestialBodies = data.ReadData();
        CelestialBodies celestialBodies3 =  celestialBodies.stream().filter(celestialBodies1 -> celestialBodies1.getID() == ID).findAny().orElse(null);
        if (celestialBodies3 == null) {
            return false;
        }
        else {
            data.DeleteCelestialBodies(ID);
            return true;
        }
    }
    //endregion
}

package org.air.BD;

import com.opencsv.*;
import org.air.Classes.CelestialBodies;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class Data {
    File file;
    String path;
    ArrayList<CelestialBodies> DataCelestialBodies = new ArrayList<>();
    public Data() { path = "C:\\Users\\Dr Diana\\IdeaProjects\\Air\\src\\main\\java\\org\\air\\BD\\data.csv"; }

    //region Add Data
    public void AddData (CelestialBodies celestialBodies) throws IOException{
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(path, true));
            String[] line = {Integer.toString(celestialBodies.getID()), celestialBodies.getName(), Float.toString(celestialBodies.getWeight()), Float.toString(celestialBodies.getRadius()), Float.toString(celestialBodies.getSpeed()), celestialBodies.getCategory()};
            writer.writeNext(line);
            writer.close();
        }
        catch (IOException ex) {
            System.out.println("We don't connect to file...");
        }
    }
    //endregion

    //region Update Data
    public void UpdateData (ArrayList<CelestialBodies> celestialBodies) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        try {
            for(CelestialBodies celestialBodies1: celestialBodies) {
                String[] line = {Integer.toString(celestialBodies1.getID()), celestialBodies1.getName(), Float.toString(celestialBodies1.getWeight()), Float.toString(celestialBodies1.getRadius()), Float.toString(celestialBodies1.getSpeed()), celestialBodies1.getCategory()};
                writer.writeNext(line);
            }
            writer.close();
        }
        catch (IOException ex) {
            System.out.println("We don't connect to file...");
        }
    }
    //endregion

    //region Read Data from CSV File
    public ArrayList<CelestialBodies> ReadData () throws IOException {
        file = new File(path);
        ArrayList<CelestialBodies> data = null;
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                    .withCSVParser(parser)
                    .build();
            List<String[]> list = reader.readAll();
            data = new ArrayList<>();
            for (String[] row : list) {
                data.add(new CelestialBodies(Integer.parseInt(row[0]), row[1], Float.parseFloat(row[2]), Float.parseFloat(row[3]), Float.parseFloat(row[4]), row[5]));
            }
            reader.close();
            return data;
        } catch (IOException ex) {
            System.out.println("We don't connect to file...");
        }
        return data;
    }
    //endregion

    //region Data Storage
    public ArrayList<CelestialBodies> DataCelestial() throws IOException{
        DataCelestialBodies = ReadData();
        return DataCelestialBodies;
    }
    //endregion

    //region Read Data from Data Storage with ID
    public ArrayList<CelestialBodies> ReadDataToID(int ID) throws IOException{
        ArrayList<CelestialBodies> DataToID = new ArrayList<>();
        DataToID.add(DataCelestial().get(ID));
        return DataToID;
    }
    //endregion

    //region Celestial Bodies with Radiation
    public ArrayList<CelestialBodies> CelestialBodiesWithRadiation (int ID, boolean rad) throws IOException {
        ArrayList<CelestialBodies> Radiation = null;
        if (rad == true) {
            Radiation = new ArrayList<>();
            Radiation.add(DataCelestial().get(ID));
            CelestialBodies celestialBodies = Radiation.get(0);
            float radWeight = celestialBodies.getWeight();
            float radWeight1 = (float) (radWeight * 0.5);
            celestialBodies.setWeight(radWeight1);
            Radiation.set(0, celestialBodies);
        }
        else {
            Radiation = new ArrayList<>();
            Radiation.add(DataCelestial().get(ID));
        }
        return Radiation;
    }
    //endregion

    //region Update CelestialBodies
    public ArrayList<CelestialBodies> UpdateCelestialBodies(int id, CelestialBodies newCelestialBodies) throws IOException {
        DataCelestialBodies = ReadData();
        DataCelestialBodies.set(id, newCelestialBodies);
        return DataCelestialBodies;
    }
    //endregion

    //region Delete CelestialBodies
    public ArrayList<CelestialBodies> DeleteCelestialBodies(int ID) throws IOException {
        DataCelestialBodies = ReadData();
        DataCelestialBodies.remove(ID);
        UpdateData(DataCelestialBodies);
        return DataCelestialBodies;
    }
    //endregion

    //region REGRESSION ANALYSIS
    public boolean massVersusRadius() throws IOException {
        DataCelestialBodies = ReadData();
        float dependence;
        boolean bool = true;
        float attitude = DataCelestialBodies.get(0).getWeight()/DataCelestialBodies.get(0).getRadius();
        for (int i = 1; i < DataCelestialBodies.size(); i++){
            dependence = DataCelestialBodies.get(i).getWeight()/DataCelestialBodies.get(i).getRadius();
            if (dependence == attitude)
            {
                bool = true;
            } else {
                bool = false;
                break;
            }
        }
        return bool;
    }

    public boolean massVersusSpeed() throws IOException {
        DataCelestialBodies = ReadData();
        float dependence;
        boolean bool = false;
        float attitude = DataCelestialBodies.get(0).getWeight()/DataCelestialBodies.get(0).getSpeed();
        for (int i = 1; i < DataCelestialBodies.size(); i++){
            dependence = DataCelestialBodies.get(i).getWeight()/DataCelestialBodies.get(i).getSpeed();
            if (dependence == attitude)
            {
                bool = true;
            } else {
                bool = false;
                break;
            }
        }
        return bool;
    }
    //endregion
}
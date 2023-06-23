package org.air.Controller;

import lombok.AllArgsConstructor;
import org.air.BD.Data;
import org.air.Classes.CelestialBodies;
import org.air.Services.CelestialBodiesServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController()
@AllArgsConstructor
public class CelestialBodiesController {
    private final CelestialBodiesServices celestialBodiesServices;
    private final Data data;

    @GetMapping("/CelestialBodies")
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<CelestialBodies> ReadDataOfCelestialBodies(){
        return celestialBodiesServices.getCelestialBodies();
    }

    @GetMapping(value="/CelestialBodies/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<CelestialBodies> ReadDataOfCelestialBodiesToID(@PathVariable (value="id") int id) {
        return celestialBodiesServices.getCelestialBodiesToID(id);
    }

    @GetMapping(value="/CelestialBodies/{id}/{rad}")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<CelestialBodies> ReadCelestialBodiesWithRadiation(@PathVariable (value="id") int id, @PathVariable (value="rad") boolean rad) {
        return celestialBodiesServices.getCelestialBodiesWithRadiation(id, rad);
    }

    @GetMapping("/CelestialBodies/RegressionAnalysis")
    @ResponseStatus(HttpStatus.OK)
    public String getRegressionAnalysis() throws IOException {
        return celestialBodiesServices.getRegressionAnalysisWithRadius() +"\n" + celestialBodiesServices.getRegressionAnalysisWithSpeed();
    }

    @PostMapping("/CelestialBodies/Add")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddDataToFile(@RequestBody CelestialBodies celestialBodies) throws IOException {
       celestialBodiesServices.AddCelestialBodies(celestialBodies);
       return "Небесное тело успешно добавлено!";
    }

    @PutMapping("/CelestialBodies/{id}/Update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ArrayList<CelestialBodies> UpdateInformation (@PathVariable int id, @RequestBody CelestialBodies celestialBodies) throws IOException {
       return celestialBodiesServices.UpdateCelestialBodies(id, celestialBodies);
    }

    @DeleteMapping("/CelestialBodies/Delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ArrayList<CelestialBodies>> DeleteCelestialBodies (@RequestParam int id) throws IOException {
        if (celestialBodiesServices.DeleteCelestialBodies(id)){ return new ResponseEntity<>(data.ReadData(), HttpStatus.OK); }
        else{ return new ResponseEntity<>(data.ReadData(), HttpStatus.NOT_FOUND); }
    }
}

package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/covid/result")
public class CovidAggregateController {

    @Qualifier("ICovidAggregateServiceStub")
    @Autowired
    ICovidAggregateService covidAggregateService;

    //TODO: Implemente todos los metodos POST que hacen falta.

    @RequestMapping(value = "/true-positive", method = RequestMethod.POST)
    public Result addTruePositiveResult(Result result) {
        return result;
    }

    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/true-positive", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult(@PathVariable("autor") ResultType r) {
        return new ResponseEntity<>(covidAggregateService.getResult(r), HttpStatus.ACCEPTED);
    }


    //TODO: Implemente el método.

    @RequestMapping(value = "persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity savePersonaWithMultipleTests(@PathVariable("persona") ResultType Persona,@PathVariable("id") UUID id) {
        try {
            return new ResponseEntity<>(covidAggregateService.upsertPersonWithMultipleTests(id,Persona), HttpStatus.ACCEPTED);
        } catch (CovidException e) {
            Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
}
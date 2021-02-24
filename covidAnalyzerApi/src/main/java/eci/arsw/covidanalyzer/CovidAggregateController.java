package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addTruePositiveResult(@RequestBody Result result) throws CovidException {
        covidAggregateService.aggregateResult(result,ResultType.TRUE_POSITIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/true-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getTruePositiveResult() {
        return new ResponseEntity<>(covidAggregateService.getResults(ResultType.TRUE_POSITIVE), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/false-positive", method = RequestMethod.GET)
    public ResponseEntity<?> getFalsePositiveResult() {
        return new ResponseEntity<>(covidAggregateService.getResults(ResultType.FALSE_POSITIVE), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/false-positive", method = RequestMethod.POST)
    public ResponseEntity<?> addFalsePositiveResult(@RequestBody Result result) throws CovidException {
        covidAggregateService.aggregateResult(result,ResultType.FALSE_POSITIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @RequestMapping(value = "/true-negative", method = RequestMethod.GET)
    public ResponseEntity<?> getTrueNegativeResult() {
        return new ResponseEntity<>(covidAggregateService.getResults(ResultType.TRUE_NEGATIVE), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/true-negative", method = RequestMethod.POST)
    public ResponseEntity<?> addTrueNegativeResult(@RequestBody Result result) throws CovidException {
        covidAggregateService.aggregateResult(result,ResultType.TRUE_NEGATIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/false-Negative", method = RequestMethod.GET)
    public ResponseEntity<?> getFalseNegativeResult() {
        return new ResponseEntity<>(covidAggregateService.getResults(ResultType.FALSE_NEGATIVE), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/false-Negative", method = RequestMethod.POST)
    public ResponseEntity<?> addFalseNegativeResult(@RequestBody Result result) throws CovidException {
        covidAggregateService.aggregateResult(result,ResultType.FALSE_POSITIVE);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    //TODO: Implemente el método.
//
//    @RequestMapping(value = "persona/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> savePersonaWithMultipleTests(@PathVariable("id") UUID id) {
//        try {
//            return new ResponseEntity<>(covidAggregateService.upsertPersonWithMultipleTests(id), HttpStatus.ACCEPTED);
//        } catch (CovidException e) {
//            Logger.getLogger(CovidAggregateController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        }
//    }
    
}
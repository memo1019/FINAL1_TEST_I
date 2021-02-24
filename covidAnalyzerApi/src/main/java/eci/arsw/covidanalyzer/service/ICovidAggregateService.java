package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.CovidException;
import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ICovidAggregateService {
    /**
     * Add a new result into the specified result type storage.
     *
     * @param result
     * @param type
     * @return
     */
    void aggregateResult(Result result, ResultType type) throws CovidException;

    /**
     * Get all the results for the specified result type.
     *
     * @param type
     * @return
     */
    List<Result> getResults(ResultType type);
    Result getResult(UUID id) throws CovidException;

    /**
     *
     * @param id
     * @param type
     */
    void upsertPersonWithMultipleTests(UUID id, ResultType type) throws CovidException;



}

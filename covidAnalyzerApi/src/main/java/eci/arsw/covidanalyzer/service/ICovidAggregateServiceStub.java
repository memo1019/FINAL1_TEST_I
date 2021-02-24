package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

import java.util.UUID;

public class ICovidAggregateServiceStub implements ICovidAggregateService{


    public ICovidAggregateServiceStub() {
    }
    @Override
    public boolean aggregateResult(Result result) {
        return false;
    }

    @Override
    public boolean getResult(ResultType type) {
        return false;
    }

    @Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type) {

    }
}

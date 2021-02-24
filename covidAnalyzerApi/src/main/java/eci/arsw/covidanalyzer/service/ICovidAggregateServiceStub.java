package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.CovidException;
import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service("ICovidAggregateServiceStub")
public class ICovidAggregateServiceStub implements ICovidAggregateService{
    private List<Result> res1 = new CopyOnWriteArrayList<>();


    @Override
    public synchronized void aggregateResult(Result result,ResultType resultType) throws CovidException {
        for (Result res: res1){
            if (res.getId().equals(result.getId())){
                throw new CovidException("This account already exists");
            }
        }
        res1.add(result);
    }

    @Override
    public List<Result> getResults(ResultType type) {
        return res1;
    }
    @Override
    public Result getResult(UUID id)throws CovidException{
        for (Result sa: res1){
            if (sa.getId().equals(id)){
                return sa;
            }
        }
        throw new CovidException("Account not found");
    }

    @Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type) throws CovidException {

    }

}

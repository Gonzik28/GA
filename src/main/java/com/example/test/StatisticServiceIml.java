package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceIml implements StatisticService {
    private final StatisticRepository statisticRepository;

    @Autowired
    public StatisticServiceIml(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Statistic create(long inc){
        Statistic statisticNew = new Statistic();
        statisticNew.setSum(getSum() + inc);
        statisticNew.setIncrement(inc);
        statisticNew = statisticRepository.save(statisticNew);
        return statisticNew;
    }

    private long getSum(){
        return statisticRepository.findFirstByOrderByTimeSaveDesc().isPresent()?
                statisticRepository.findFirstByOrderByTimeSaveDesc().get().getSum():0l;
    }
}

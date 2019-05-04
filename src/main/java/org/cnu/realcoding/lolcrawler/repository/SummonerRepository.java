package org.cnu.realcoding.lolcrawler.repository;

import org.cnu.realcoding.lolcrawler.domain.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class SummonerRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    //MongoDB에 Summoner를 삽입하는 메소드 추가
    public Summoner insertSummoner(Summoner summoner) {
        return mongoTemplate.insert(summoner);
    }

    //Update 메소드 추가할것!!
}

package org.cnu.realcoding.lolcrawler.repository;

import org.cnu.realcoding.lolcrawler.domain.Summoner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    //Summoner의 이름을 가지고 DB에서 해당 Summoner을 찾아 반환해주는 메소드 추가
    public Summoner findSummoner(String name) {
        Query query = new Query();

        query.addCriteria(Criteria.where("name").is(name));

        return mongoTemplate.findOne(query, Summoner.class);
    }

    //Update 메소드 추가할것!!
    public void updateSummoner(String name, Summoner newSummoner) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        Update update = new Update();

        update.set("leagueName", newSummoner.getLeagueName());
        update.set("queueType", newSummoner.getQueueType());
        update.set("position", newSummoner.getPosition());
        update.set("tier", newSummoner.getTier());
        update.set("rank", newSummoner.getRank());
        update.set("points", newSummoner.getPoints());
        update.set("wins", newSummoner.getWins());
        update.set("losses", newSummoner.getLosses());

        mongoTemplate.updateMulti(query, update, "summoner");

    }
}

package org.cnu.realcoding.lolcrawler.Controller;


import org.cnu.realcoding.lolcrawler.domain.Summoner;
import org.cnu.realcoding.lolcrawler.service.SummonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummonerController {
    @Autowired
    private SummonerService summonerService;

    @GetMapping("/summoner/{name}")
    public Summoner getInfo(@PathVariable String name) {
        return summonerService.getInfo(name);
    }

}
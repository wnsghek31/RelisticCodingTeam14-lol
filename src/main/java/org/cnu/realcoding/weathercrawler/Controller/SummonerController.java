package org.cnu.realcoding.weathercrawler.Controller;


import org.cnu.realcoding.weathercrawler.domain.Summoner;
import org.cnu.realcoding.weathercrawler.service.SummonerService;
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
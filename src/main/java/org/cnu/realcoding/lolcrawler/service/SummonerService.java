package org.cnu.realcoding.lolcrawler.service;

import lombok.extern.slf4j.Slf4j;
import org.cnu.realcoding.lolcrawler.domain.Summoner;
import org.cnu.realcoding.lolcrawler.repository.SummonerRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import org.json.simple.parser.JSONParser;

@Service
@Slf4j
public class SummonerService {

    static final String API = "RGAPI-bbb84869-5e00-4eac-b360-f583f85bcb8d";
    private Summoner summoner = new Summoner();

    //DB작업을 수행할 SummonerRepository 필드를 선언한다.
    @Autowired
    private SummonerRepository summonerRepository;

    public Summoner getInfo(String name) {

        String encryptedId = getEncryptedID(name);
        getSummonerInfo(encryptedId);
        return summoner;
    }

    public URLConnection connectTo(String url , String name){
        try{
            String urlString = url + URLEncoder.encode(name,"UTF-8").replace("+", "%20") +"?api_key=" + API;
            System.out.println(urlString);

            URL connectedUrl = new URL(urlString);
            return connectedUrl.openConnection();
        }catch (Exception e){
            return null;
        }
    }

    public String getEncryptedID(String name) {
        BufferedReader in = null;
        String encryptedId = "";
        try{

            URLConnection conn = connectTo("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/",name);

            BufferedReader br = null;

            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line = null;
            StringBuffer bfStr = new StringBuffer();

            JSONParser jsonParse = new JSONParser();

            while ((line = br.readLine()) != null) {

                JSONObject jsonObj = (JSONObject) jsonParse.parse(line);
                encryptedId = (String)jsonObj.get("id");


            }
            br.close();

        }catch(Exception e) { e.printStackTrace(); }

        return encryptedId;
    }

    public void getSummonerInfo(String encryptedId){
        BufferedReader in = null;

        try{

            String urlString = "https://kr.api.riotgames.com/lol/league/v4/positions/by-summoner/" + URLEncoder.encode(encryptedId,"UTF-8") +"?api_key=" + API;
            System.out.println(urlString);

            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader br = null;

            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line = null;
            StringBuffer bfStr = new StringBuffer();

            JSONParser jsonParse = new JSONParser();

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                line = line.substring(1,line.length()-1);
                JSONObject jsonObj = (JSONObject) jsonParse.parse(line);

                summoner.setName((String)jsonObj.get("summonerName"));
                summoner.setLeagueName((String)jsonObj.get("leagueName"));
                summoner.setQueueType((String)jsonObj.get("queueType"));
                summoner.setPosition((String)jsonObj.get("position"));
                summoner.setTier((String)jsonObj.get("tier"));
                summoner.setRank((String)jsonObj.get("rank"));
                summoner.setPoints((long)jsonObj.get("leaguePoints"));
                summoner.setWins((long)jsonObj.get("wins"));
                summoner.setLosses((long)jsonObj.get("losses"));


                if(checkSummonerInfoByName(summoner.getName())) {
                    updateSummoner(summoner);
                }
                else {
                    insertSummoner(summoner);
                }
            }
            br.close();

        }catch(Exception e) { e.printStackTrace(); }
        //System.out.println(summoner.name);
    }

    //소환사의 정보가 있는지 없는지를 소환사의 이름으로 검색합니다.
    public boolean checkSummonerInfoByName(String name) {

        Summoner findedSummoner = summonerRepository.findSummoner(name);

        if(findedSummoner == null) {
            return false;
        }
        else {
            return true;
        }
    }

    //소환사의 정보를 업데이트하는 메소드 생성
    public void updateSummoner(Summoner summoner) {
        summonerRepository.updateSummoner(summoner.getName(), summoner);
    }

    //소환사의 정보를 추가하는 메소드 생성
    public void insertSummoner(Summoner summoner) {
        //DB에 API를 통해 받아온 Summoner 객체를 저장하고
        //저장되었으면 로그를 찍는다
        Summoner insertedSummoner = summonerRepository.insertSummoner(summoner);
        log.info("Summoner has inserted successfully. Summoner : {}", insertedSummoner);
    }
}
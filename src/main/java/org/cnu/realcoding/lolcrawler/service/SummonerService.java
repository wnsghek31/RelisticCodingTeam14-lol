package org.cnu.realcoding.lolcrawler.service;

import org.cnu.realcoding.lolcrawler.domain.Summoner;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import org.json.simple.parser.JSONParser;

@Service
public class SummonerService {

    private Summoner summoner = new Summoner();

    public Summoner getInfo(String name) {

        String encryptedId = getEncryptedID(name);
        getSummonerInfo(encryptedId);
        return summoner;
    }

    public String getEncryptedID(String name) {
        BufferedReader in = null;
        String API = "RGAPI-bbb84869-5e00-4eac-b360-f583f85bcb8d";
        String encryptedId = "";
        try{

            String urlString = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + URLEncoder.encode(name,"UTF-8").replace("+", "%20") +"?api_key=" + API;
            System.out.println(urlString);

            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
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

}
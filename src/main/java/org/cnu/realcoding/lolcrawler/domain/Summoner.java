package org.cnu.realcoding.lolcrawler.domain;

import lombok.Data;

@Data
public class Summoner {
    public String leagueName;
    public String queueType;
    public String position;
    public String tier;
    public String rank;
    public String name;
    public long points;
    public long wins;
    public long losses;
}

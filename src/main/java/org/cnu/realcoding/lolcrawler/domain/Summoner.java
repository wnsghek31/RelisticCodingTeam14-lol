package org.cnu.realcoding.lolcrawler.domain;

import lombok.Data;

@Data
public class Summoner {
    private String leagueName;
    private String queueType;
    private String position;
    private String tier;
    private String rank;
    private String name;
    private long points;
    private long wins;
    private long losses;

    public String getLegueName() {
        return this.leagueName;
    }
    public String getQueueType() {
        return this.queueType;
    }
    public String getPosition() {
        return this.position;
    }
    public String getTier() {
        return this.tier;
    }
    public String getRank() {
        return this.rank;
    }
    public String getName() {
        return this.name;
    }
    public long getPoints() {
        return this.points;
    }
    public long getWins() {
        return this.wins;
    }
    public long getLosses() {
        return this.losses;
    }

    public void setLegueName(String newLeagueName) {
        this.leagueName = newLeagueName;
    }
    public void setQueueType(String newQueueType) {
        this.queueType = newQueueType;
    }
    public void setPosition(String newPosition) {
        this.position = newPosition;
    }
    public void setTier(String newTier) {
        this.tier = newTier;
    }
    public void setRank(String newRank) {
        this.rank = newRank;
    }
    public void setName(String newName) {
        this.name = newName;
    }
    public void setPoints(long newPoints) {
        this.points = newPoints;
    }
    public void setWins(long newWins) {
        this.wins = newWins;
    }
    public void setLosses(long newLosses) {
        this.losses = newLosses;
    }
}

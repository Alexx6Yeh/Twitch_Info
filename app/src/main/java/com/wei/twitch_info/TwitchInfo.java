
package com.wei.twitch_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TwitchInfo {

    @SerializedName("status")
    @Expose
    private String status;


    @SerializedName("display_name")
    @Expose
    private String displayName;

    @SerializedName("game")
    @Expose
    private String game;


    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("partner")
    @Expose
    private Boolean partner;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("followers")
    @Expose
    private Integer followers;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Boolean getPartner() {
        return partner;
    }

    public void setPartner(Boolean partner) {
        this.partner = partner;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }



}

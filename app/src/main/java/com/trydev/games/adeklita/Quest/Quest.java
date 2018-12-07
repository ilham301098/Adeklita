package com.trydev.games.adeklita.Quest;

/**
 * Created by user on 6/11/2018.
 */

public class Quest {
    private String trueanswer;
    private int url;
    private String tipe;

    public Quest(int url, String trueanswer, String tipe) {
        this.url = url;
        this.trueanswer = trueanswer;
        this.tipe = tipe;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getTrueanswer() {
        return trueanswer;
    }

    public void setTrueanswer(String trueanswer) {
        this.trueanswer = trueanswer;
    }
}

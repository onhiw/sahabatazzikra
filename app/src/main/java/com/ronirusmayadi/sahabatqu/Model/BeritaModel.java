package com.ronirusmayadi.sahabatqu.Model;

public class BeritaModel {
    private String title_news;
    private String link_img;
    private String author;
    private String description_news;
    private String time;

    public String getTitle_news() {
        return title_news;
    }

    public String getLink_img() {
        return link_img;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription_news() {
        return description_news;
    }

    public String getTime() {
        return time;
    }

    public String getLink() {
        return link;
    }

    private String link;

    public BeritaModel (String title_news, String link_img, String author, String description_news, String time, String link){
        this.title_news = title_news;
        this.link_img = link_img;
        this.author = author;
        this.description_news = description_news;
        this.time = time;
        this.link = link;
    }
}

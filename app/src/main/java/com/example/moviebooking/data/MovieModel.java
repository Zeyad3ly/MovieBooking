package com.example.moviebooking.data;

public class MovieModel {

    private String id,image,name,releaseData ;

    public MovieModel(String name, String id, String image, String releaseData) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.releaseData = releaseData;
    }

    public MovieModel(){}

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReleaseData(String releaseData) {
        this.releaseData = releaseData;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getReleaseData() {
        return releaseData;
    }
}

package com.mycompany.gatosapi;

public class Gatos {
    
    private String id;
    private String url;
    private String APIkey = "08d4a56d-f773-4261-bb2b-f5631e1bcb05";
    private String image;

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAPIkey() {
        return APIkey;
    }

    public void setAPIkey(String APIkey) {
        this.APIkey = APIkey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}

package com.mycompany.gatosapi;

public class GatosFav {
    
    private String id;
    private String image_id;
    private String apiKey = "08d4a56d-f773-4261-bb2b-f5631e1bcb05" ;
    private ImageX image; 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ImageX getImage() {
        return image;
    }

    public void setImage(ImageX image) {
        this.image = image;
    }
}

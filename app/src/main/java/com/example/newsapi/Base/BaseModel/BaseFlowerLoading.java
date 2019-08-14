package com.example.newsapi.Base.BaseModel;

public class BaseFlowerLoading {

    private String message;
    private Boolean progress;

    public BaseFlowerLoading(String messag, Boolean progress) {
        this.message = messag;
        this.progress = progress;
    }

    public String getMessag() {
        return message;
    }

    public void setMessag(String messag) {
        this.message = messag;
    }

    public Boolean getProgress() {
        return progress;
    }

    public void setProgress(Boolean progress) {
        this.progress = progress;
    }
}

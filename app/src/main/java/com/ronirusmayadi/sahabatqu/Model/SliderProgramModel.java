package com.ronirusmayadi.sahabatqu.Model;

public class SliderProgramModel {
    private int mImage;

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    private String mTitle;

    public SliderProgramModel(int mImage, String mTitle){
        this.mImage = mImage;
        this.mTitle = mTitle;
    }
}

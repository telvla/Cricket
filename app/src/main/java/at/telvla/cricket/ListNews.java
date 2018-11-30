package at.telvla.cricket;


import android.content.Context;

public class ListNews {

    String array_title;
    String array_abstr;
    String array_img;

    public ListNews (String array_title, String array_abstr, String array_img) {
        this.array_title= array_title;
        this.array_abstr= array_abstr;
        this.array_img= array_img;
    }

     public void SetTitle (String array_title) {
        this.array_title = array_title;
    }

    public String getTitle() {
        return array_title;
    }

    public void SetAbstr (String array_abstr) {
        this.array_abstr = array_abstr;
    }

    public String getAbstr() {
        return array_abstr;
    }

    public void SetImg (String array_img) {
        this.array_img = array_img;
    }

    public String getImg() {
        return array_img;
    }


}


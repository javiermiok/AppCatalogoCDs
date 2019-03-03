package com.example.a21752434.appcatalogocds.model;

import android.content.Context;

import com.example.a21752434.appcatalogocds.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("flag")
    @Expose
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getFlagId(String idFlag, Context context) {
        int idIcon = context.getResources().getIdentifier(flag, "drawable", context.getPackageName());

        return idIcon;
    }

    /*public int getFlagId(String idFlag) {
        int idIcon = R.drawable.usa;
        switch (Integer.parseInt(idFlag)) {
            case 1:
                idIcon = R.drawable.usa;
                break;
            case 2:
                idIcon = R.drawable.uk;
                break;
            case 3:
                idIcon = R.drawable.australia;
                break;
            case 4:
                idIcon = R.drawable.spain;
                break;
            case 5:
                idIcon = R.drawable.italy;
                break;
            case 6:
                idIcon = R.drawable.denmark;
                break;
            default:
                idIcon = R.drawable.usa;
        }
        return idIcon;
    }*/

}

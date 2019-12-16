package com.example.duanmot.model;

import androidx.annotation.NonNull;

public class Modelgio {
    private String gio;

    public Modelgio(String gio) {
        this.gio = gio;
    }

    public Modelgio() {
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    @NonNull
    @Override
    public String toString() {
        return gio;
    }
}

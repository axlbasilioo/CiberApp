package com.example.mikelciber;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class infoElements {
    String FBCodigoBarras,FBNombre, FBNumid,FBPrecioUnitario,FBUnidades;
    public infoElements(){

    }

    public infoElements(String FBCodigoBarras, String FBNombre, String FBNumid, String FBPrecioUnitario, String FBUnidades) {
        this.FBCodigoBarras = FBCodigoBarras;
        this.FBNombre = FBNombre;
        this.FBNumid = FBNumid;
        this.FBPrecioUnitario = FBPrecioUnitario;
        this.FBUnidades = FBUnidades;
    }

    public String getFBCodigoBarras() {
        return FBCodigoBarras;
    }

    public void setFBCodigoBarras(String FBCodigoBarras) {
        this.FBCodigoBarras = FBCodigoBarras;
    }

    public String getFBNombre() {
        return FBNombre;
    }

    public void setFBNombre(String FBNombre) {
        this.FBNombre = FBNombre;
    }

    public String getFBNumid() {
        return FBNumid;
    }

    public void setFBNumid(String FBNumid) {
        this.FBNumid = FBNumid;
    }

    public String getFBPrecioUnitario() {
        return FBPrecioUnitario;
    }

    public void setFBPrecioUnitario(String FBPrecioUnitario) {
        this.FBPrecioUnitario = FBPrecioUnitario;
    }

    public String getFBUnidades() {
        return FBUnidades;
    }

    public void setFBUnidades(String FBUnidades) {
        this.FBUnidades = FBUnidades;
    }
}

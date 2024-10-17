package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Database;

public class Ciudad {
    private int id;
    private String nombre;
    private int cveEst;
    
    public Ciudad() {}
    
    public Ciudad(int id, String nombre) {
    	this.id = id;
    	this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCveEst() {
        return cveEst;
    }

    public void setCveEst(int cveEst) {
        this.cveEst = cveEst;
    }
    
    public String toString(){
        return nombre;
    }
    
}

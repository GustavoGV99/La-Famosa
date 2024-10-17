package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Database;

public class CodigoPostal {
    private int codigo;
    private int cveCiu;
    
    public CodigoPostal() {}
    
    public CodigoPostal(int codigo) {
    	this.codigo = codigo;
    }

    public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCveCiu() {
        return cveCiu;
    }

    public void setCveCiu(int cveCiu) {
        this.cveCiu = cveCiu;
    }
    
    public String toString(){
        return String.valueOf(codigo);
    }
}

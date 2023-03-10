package br.com.fiap.dreamcontrol.models;

import java.util.ArrayList;
import java.util.List;

public class Historico {
    
    private List<Registro> registros = new ArrayList<Registro>();

    public Historico(List<Registro> registros) {
        this.registros = registros;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    @Override
    public String toString() {
        String texto = "";

        for (Registro registro : registros)
            texto += "Data: " + registro.getData() + "\nTempo: " + registro.getTempo() + "\n";
        
        return texto;
    }
}

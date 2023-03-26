package br.com.fiap.dreamcontrol.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "historico", cascade = CascadeType.ALL)
    private List<Registro> registros = new ArrayList<Registro>();

    public Historico() {
    }

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

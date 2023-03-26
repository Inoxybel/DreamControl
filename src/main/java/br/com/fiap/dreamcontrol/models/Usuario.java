package br.com.fiap.dreamcontrol.models;

import java.util.List;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String senha;

    @OneToOne
    private Objetivo objetivo;

    @OneToMany
    private List<Registro> registro;

    public Usuario(String nome, String email, String senha) {
        if(!setNome(nome))
            throw new IllegalArgumentException("Nome inválido");

        if(!setEmail(email))
            throw new IllegalArgumentException("Email inválido");

        if(!setSenha(senha))
            throw new IllegalArgumentException("Senha inválida");
        
    }

    public String getNome() {
        return nome;
    }

    public boolean setNome(String novoNome) {
        if(validarNome(novoNome))
        {
            nome = novoNome;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String novoEmail) {
        if(validarEmail(novoEmail))
        {
            email = novoEmail;
            return true;
        }
        return false;
    }

    public String getSenha() {
        return senha;
    }

    public boolean setSenha(String novaSenha) {
        if(validarSenha(novaSenha))
        {
            senha = novaSenha;
            return true;
        }
        return false;
    }

    public List<Registro> getRegistros()
    {
        return registro;
    }

    public Objetivo getObjetivo()
    {
        return objetivo;
    }

    public long getId()
    {
        return id;
    }

    private boolean validarNome(String nome)
    {
        String regex = "^[a-zA-Z]{3,}$";
        Pattern padrao = Pattern.compile(regex);

        return padrao.matcher(nome).matches();
    }

    private boolean validarSenha(String senha)
    {
        if(senha.length() >= 8)
            return true;
        return false;
    }

    private boolean validarEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern padrao = Pattern.compile(regex);

        return padrao.matcher(email).matches();
    }

    @Override
    public String toString() {
        return "Nome : " + nome + 
             "\nEmail: " + email + 
             "\nSenha: " + senha;
    }
}

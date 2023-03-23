package br.com.fiap.dreamcontrol.models;

/*
Isso deveria ser um objeto de transferência que abstrai somente
o atributo "nome" da classe "Usuario" para fins de consulta no banco.
Porém, entretanto, toda via, aqui por hora será tratado como objeto 
de domínio até a sequência das demais aulas.
*/

public class Login {
    private String email;
    private String senha;
    
	public Login(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
        return "Email: " + email + "\nSenha: " + senha;
    }
    
    
}

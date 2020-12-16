package br.com.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id
	@Column(length = 20)
	private String username;
	
	@Column(length = 10)
	private String password;
	
	@Column(length = 40)
	private String nome;
	
	@Column(length = 1)
	private char sexo;

	public Usuario() {
		super();
	}

	public Usuario(String username, String password, String nome, char sexo) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.sexo = sexo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "" + username + "";
	}
	
}

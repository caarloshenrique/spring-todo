package br.com.todo.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 150)
	private String descricao;
	
	private Date dataLimite;
	
	private Date dataLembrete;
	
	private Date dataRealizacao;
	
	@Column(length = 1)
	private char status;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Usuario usuario;
	
	@ManyToOne
	private Categoria categoria;

	public Tarefa() {
		super();
	}

	public Tarefa(Integer id, String descricao, Date dataLimite, Date dataLembrete, Date dataRealizacao, char status,
			Usuario usuario, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataLimite = dataLimite;
		this.dataLembrete = dataLembrete;
		this.dataRealizacao = dataRealizacao;
		this.status = status;
		this.usuario = usuario;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}

	public Date getDataLembrete() {
		return dataLembrete;
	}

	public void setDataLembrete(Date dataLembrete) {
		this.dataLembrete = dataLembrete;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}

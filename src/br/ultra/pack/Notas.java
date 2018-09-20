package br.ultra.pack;

import java.sql.Timestamp;

//import java.util.Calendar;

public class Notas {
	
	private Integer id;
	private Integer idcor;
	private String conteudo;
	private Timestamp datacriacao;
	
	public Integer getId() {return this.id;}
	public void setId(Integer id) {this.id = id;}
	
	public Integer getIdcor() {return this.idcor;}
	public void setIdcor(Integer idcor) {this.idcor = idcor;}
	
	public String getConteudo() {return this.conteudo;}
	public void setConteudo(String conteudo) {this.conteudo = conteudo;}
	
	public Timestamp getDatacriacao() {return this.datacriacao;}
	public void setDatacriacao(Timestamp datacriacao) {this.datacriacao = datacriacao;}
	
//	public Double getAltura() {return this.altura;}
//	public void setAltura(Double altura) {this.altura = altura;}
}
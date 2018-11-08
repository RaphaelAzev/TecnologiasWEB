package mvc.model;

public class Nota {

	private Long id;
	private String titulo;
	private String conteudo;
	private String datacriacao;
	private String cor;
	private Boolean delete;
	private Long id_pokenota;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId_pokenota() {
		return this.id_pokenota;
	}

	public void setId_pokenota(Long id_pokenota) {
		this.id_pokenota = id_pokenota;
	}

	public String getConteudo() {
		return this.conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getDatacriacao() {
		return this.datacriacao;
	}

	public void setDatacriacao(String datacriacao) {
		this.datacriacao = datacriacao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}

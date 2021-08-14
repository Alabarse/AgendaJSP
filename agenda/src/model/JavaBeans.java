package model;

public class JavaBeans {
	private String idCon;
	private String nome;
	private String fone;
	private String email;
	
	public JavaBeans() {
		super();
	}
	
	public JavaBeans(String idCon, String nome, String fone, String email) {
		super();
		this.idCon = idCon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	
	public void setIdCon(String idCon) {
		this.idCon = idCon;
	}
	
	public String getIdCon() {
		return idCon;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public String getFone() {
		return fone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}

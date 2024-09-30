package org.merdverse.models;

public class LoginResult {
	private int codigo;
	private Usuario usuario;
	
	public LoginResult(int Codigo, Usuario usuario) {
		setCodigo(Codigo);
		setUsuario(usuario);
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

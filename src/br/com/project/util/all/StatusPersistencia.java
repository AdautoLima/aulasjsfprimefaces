package br.com.project.util.all;

public enum StatusPersistencia {

	ERROR("Erro"), SUCCESS("Sucesso"), 
	OBJETO_REFERENCIADO("Esso objeto não pode ser apagado por possuir referências ao mesmo.");

	private String name;

	private StatusPersistencia(String s) {
		this.name = s;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

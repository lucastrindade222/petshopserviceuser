package br.com.lucas.petshopserviceuser.model.enums;

 



public enum TypeFone {

    CELULAR("Celular"),
	FIXO("Fixo"),
	INDEFINIDO("indefinido");
	
	private final String type;

	private TypeFone(String type) {
		this.type = type;
	}

	public String gettype() {
		return type;
	}

}

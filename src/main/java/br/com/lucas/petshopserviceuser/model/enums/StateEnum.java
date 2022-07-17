package br.com.lucas.petshopserviceuser.model.enums;

public enum StateEnum {

	AC(1, "Acre"),
	AL(2, "Alagoas"),
	AM(3, "Amazonas"),
	AP(4, "Amapá"),
	BA(5, "Bahia"),
	CE(6, "Ceará"),
	DF(7, "Distrito Federal"),
	ES(8, "Espirito Santo"),
	GO(9, "Goias"),
	MA(10, "Maranhão"),
	MG(11, "Minas Gerais"),
	MS(12, "Mato Grosso Sul"),
	MT(13, "Mato Grosso"),
	PA(14, "Pará"),
	PB(15, "Paraiba"),
	PE(16, "Pernanbuco"),
	PI(17, "Piaui"),
	PR(18, "Parana"),
	RJ(19, "Rio de Janeiro"),
	RN(20, "Rio Grande do Norte"),
	RO(21, "Rondônia"),
	RR(22, "Roraima"),
	RS(23, "Rio Grande do Sul"),
	SC(24, "Santa Catarina"),
	SE(25, "Sergipe"),
	SP(26, "São Paulo"),
	TO(27, "Tocantins"),

	INDEFINIDO(0, "indefinido");

	private int cod;
	private String estado;

	StateEnum(int cod, String estado) {
		this.estado = estado;
		this.cod = cod;
	}

	public int getCod() {
		return cod;
	}

	public String getState() {
		return estado;
	}

	public static StateEnum toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (StateEnum x : StateEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
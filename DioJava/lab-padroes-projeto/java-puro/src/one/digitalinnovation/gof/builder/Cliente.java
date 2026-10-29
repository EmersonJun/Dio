package one.digitalinnovation.gof.builder;

public final class Cliente {

	private final String nome;
	private final String cep;
	private final String cidade;
	private final String estado;

	private Cliente(Builder builder) {
		this.nome = builder.nome;
		this.cep = builder.cep;
		this.cidade = builder.cidade;
		this.estado = builder.estado;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Builder toBuilder() {
		return new Builder().nome(nome).cep(cep).cidade(cidade).estado(estado);
	}

	public String getNome() {
		return nome;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	@Override
	public String toString() {
		return "Cliente{nome=" + nome + ", cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + "}";
	}

	public static final class Builder {

		private String nome;
		private String cep;
		private String cidade;
		private String estado;

		private Builder() {
			super();
		}

		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder cep(String cep) {
			this.cep = cep;
			return this;
		}

		public Builder cidade(String cidade) {
			this.cidade = cidade;
			return this;
		}

		public Builder estado(String estado) {
			this.estado = estado;
			return this;
		}

		public Cliente build() {
			return new Cliente(this);
		}
	}
}

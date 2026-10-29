package one.digitalinnovation.gof.chain;

import one.digitalinnovation.gof.builder.Cliente;

public abstract class ValidadorCadastro {

	private ValidadorCadastro proximo;

	public ValidadorCadastro encadear(ValidadorCadastro proximo) {
		this.proximo = proximo;
		return proximo;
	}

	public void validar(Cliente cliente) {
		verificar(cliente);
		if (proximo != null) {
			proximo.validar(cliente);
		}
	}

	protected abstract void verificar(Cliente cliente);
}

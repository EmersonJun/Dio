package one.digitalinnovation.gof.chain;

import one.digitalinnovation.gof.builder.Cliente;

public class ValidadorCep extends ValidadorCadastro {

	@Override
	protected void verificar(Cliente cliente) {
		String cep = cliente.getCep();
		if (cep == null || !cep.matches("\\d{8}")) {
			throw new ValidacaoException("CEP deve conter exatamente 8 digitos numericos");
		}
	}
}

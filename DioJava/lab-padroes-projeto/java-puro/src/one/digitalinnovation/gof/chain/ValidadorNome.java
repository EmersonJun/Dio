package one.digitalinnovation.gof.chain;

import one.digitalinnovation.gof.builder.Cliente;

public class ValidadorNome extends ValidadorCadastro {

	private static final int TAMANHO_MINIMO = 3;

	@Override
	protected void verificar(Cliente cliente) {
		String nome = cliente.getNome();
		if (nome == null || nome.trim().length() < TAMANHO_MINIMO) {
			throw new ValidacaoException("Nome deve ter no minimo " + TAMANHO_MINIMO + " caracteres");
		}
	}
}

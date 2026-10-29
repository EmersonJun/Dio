package one.digitalinnovation.gof.chain;

import java.util.HashSet;
import java.util.Set;

import one.digitalinnovation.gof.builder.Cliente;

public class ValidadorDuplicidade extends ValidadorCadastro {

	private final Set<String> cadastrados = new HashSet<>();

	@Override
	protected void verificar(Cliente cliente) {
		String chave = cliente.getNome().trim().toLowerCase() + "|" + cliente.getCep();
		if (!cadastrados.add(chave)) {
			throw new ValidacaoException("Cliente ja cadastrado: " + cliente.getNome());
		}
	}
}

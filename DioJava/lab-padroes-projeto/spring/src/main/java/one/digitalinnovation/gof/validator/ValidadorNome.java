package one.digitalinnovation.gof.validator;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.exception.ValidacaoClienteException;
import one.digitalinnovation.gof.model.Cliente;

@Component
@Order(1)
public class ValidadorNome implements ValidadorCliente {

	private static final int TAMANHO_MINIMO = 3;

	@Override
	public void validar(Cliente cliente) {
		String nome = cliente.getNome();
		if (nome == null || nome.trim().length() < TAMANHO_MINIMO) {
			throw new ValidacaoClienteException("Nome deve ter no minimo " + TAMANHO_MINIMO + " caracteres");
		}
	}
}

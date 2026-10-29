package one.digitalinnovation.gof.validator;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import one.digitalinnovation.gof.exception.ValidacaoClienteException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.util.Cep;

@Component
@Order(2)
public class ValidadorCep implements ValidadorCliente {

	@Override
	public void validar(Cliente cliente) {
		if (cliente.getEndereco() == null || !Cep.valido(cliente.getEndereco().getCep())) {
			throw new ValidacaoClienteException("CEP deve conter exatamente 8 digitos numericos");
		}
	}
}

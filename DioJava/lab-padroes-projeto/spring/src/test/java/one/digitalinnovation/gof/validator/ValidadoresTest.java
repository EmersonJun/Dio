package one.digitalinnovation.gof.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import one.digitalinnovation.gof.exception.ValidacaoClienteException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;

class ValidadoresTest {

	private final ValidadorNome validadorNome = new ValidadorNome();
	private final ValidadorCep validadorCep = new ValidadorCep();

	@Test
	void nomeCurtoDeveSerRejeitado() {
		assertThrows(ValidacaoClienteException.class, () -> validadorNome.validar(cliente("Jo", "14801788")));
	}

	@Test
	void nomeNuloDeveSerRejeitado() {
		assertThrows(ValidacaoClienteException.class, () -> validadorNome.validar(cliente(null, "14801788")));
	}

	@Test
	void nomeValidoDeveSerAceito() {
		assertDoesNotThrow(() -> validadorNome.validar(cliente("Maria", "14801788")));
	}

	@Test
	void cepCurtoDeveSerRejeitado() {
		assertThrows(ValidacaoClienteException.class, () -> validadorCep.validar(cliente("Maria", "123")));
	}

	@Test
	void enderecoNuloDeveSerRejeitado() {
		Cliente cliente = new Cliente();
		cliente.setNome("Maria");
		assertThrows(ValidacaoClienteException.class, () -> validadorCep.validar(cliente));
	}

	@Test
	void cepComHifenDeveSerAceito() {
		assertDoesNotThrow(() -> validadorCep.validar(cliente("Maria", "14801-788")));
	}

	private Cliente cliente(String nome, String cep) {
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		return cliente;
	}
}

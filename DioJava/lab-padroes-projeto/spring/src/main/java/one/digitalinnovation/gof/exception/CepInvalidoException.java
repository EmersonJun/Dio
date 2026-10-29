package one.digitalinnovation.gof.exception;

public class CepInvalidoException extends ValidacaoClienteException {

	private static final long serialVersionUID = 1L;

	public CepInvalidoException(String cep) {
		super("CEP invalido ou nao encontrado: " + cep);
	}
}

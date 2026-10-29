package one.digitalinnovation.gof.exception;

public class ValidacaoClienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacaoClienteException(String mensagem) {
		super(mensagem);
	}
}

package one.digitalinnovation.gof.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(Long id) {
		super("Cliente nao encontrado: " + id);
	}
}

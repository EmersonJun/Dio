package one.digitalinnovation.gof.strategy;

public final class ComportamentoFactory {

	private ComportamentoFactory() {
		super();
	}

	public static Comportamento criar(TipoComportamento tipo) {
		if (tipo == null) {
			throw new IllegalArgumentException("Tipo de comportamento nao pode ser nulo");
		}
		return tipo.criar();
	}
}

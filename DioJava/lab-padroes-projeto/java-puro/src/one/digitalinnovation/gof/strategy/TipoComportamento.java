package one.digitalinnovation.gof.strategy;

import java.util.function.Supplier;

public enum TipoComportamento {

	NORMAL(ComportamentoNormal::new),
	DEFENSIVO(ComportamentoDefensivo::new),
	AGRESSIVO(ComportamentoAgressivo::new),
	EVASIVO(ComportamentoEvasivo::new);

	private final Supplier<Comportamento> criador;

	TipoComportamento(Supplier<Comportamento> criador) {
		this.criador = criador;
	}

	Comportamento criar() {
		return criador.get();
	}
}

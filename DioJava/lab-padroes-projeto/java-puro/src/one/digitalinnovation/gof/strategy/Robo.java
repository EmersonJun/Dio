package one.digitalinnovation.gof.strategy;

public class Robo {

	private Comportamento comportamento = new ComportamentoNormal();

	public void setComportamento(Comportamento comportamento) {
		if (comportamento == null) {
			throw new IllegalArgumentException("Comportamento nao pode ser nulo");
		}
		this.comportamento = comportamento;
	}

	public void mover() {
		comportamento.mover();
	}
}

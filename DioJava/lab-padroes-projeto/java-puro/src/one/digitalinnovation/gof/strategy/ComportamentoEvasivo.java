package one.digitalinnovation.gof.strategy;

public class ComportamentoEvasivo implements Comportamento {

	@Override
	public void mover() {
		System.out.println("Movendo-se evasivamente...");
	}
}

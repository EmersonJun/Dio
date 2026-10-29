package one.digitalinnovation.gof.observer;

import java.util.ArrayList;
import java.util.List;

public class Publicador<E> {

	private final List<Observador<E>> observadores = new ArrayList<>();

	public void inscrever(Observador<E> observador) {
		observadores.add(observador);
	}

	public void cancelar(Observador<E> observador) {
		observadores.remove(observador);
	}

	public void publicar(E evento) {
		for (Observador<E> observador : observadores) {
			observador.atualizar(evento);
		}
	}
}

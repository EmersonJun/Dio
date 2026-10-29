package one.digitalinnovation.gof.observer;

public interface Observador<E> {

	void atualizar(E evento);
}

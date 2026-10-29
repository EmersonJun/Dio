package one.digitalinnovation.gof.singleton;

public class SingletonEager {

	private static final SingletonEager INSTANCIA = new SingletonEager();

	private SingletonEager() {
		super();
	}

	public static SingletonEager getInstancia() {
		return INSTANCIA;
	}
}

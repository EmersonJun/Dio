package one.digitalinnovation.gof.singleton;

public class SingletonLazyHolder {

	private static class InstanceHolder {
		private static final SingletonLazyHolder INSTANCIA = new SingletonLazyHolder();
	}

	private SingletonLazyHolder() {
		super();
	}

	public static SingletonLazyHolder getInstancia() {
		return InstanceHolder.INSTANCIA;
	}
}

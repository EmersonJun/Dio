package one.digitalinnovation.gof.singleton;

public enum SingletonEnum {

	INSTANCIA;

	private int acessos;

	public int registrarAcesso() {
		return ++acessos;
	}
}

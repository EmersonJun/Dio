package one.digitalinnovation.gof.util;

public final class Cep {

	private static final int TAMANHO = 8;

	private Cep() {
		super();
	}

	public static String normalizar(String cep) {
		if (cep == null) {
			return null;
		}
		return cep.replaceAll("\\D", "");
	}

	public static boolean valido(String cep) {
		String normalizado = normalizar(cep);
		return normalizado != null && normalizado.length() == TAMANHO;
	}
}

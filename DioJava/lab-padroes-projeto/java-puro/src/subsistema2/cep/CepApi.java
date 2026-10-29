package subsistema2.cep;

public class CepApi {

	private static final CepApi INSTANCIA = new CepApi();

	private CepApi() {
		super();
	}

	public static CepApi getInstancia() {
		return INSTANCIA;
	}

	public String recuperarCidade(String cep) {
		return "Araraquara";
	}

	public String recuperarEstado(String cep) {
		return "SP";
	}
}

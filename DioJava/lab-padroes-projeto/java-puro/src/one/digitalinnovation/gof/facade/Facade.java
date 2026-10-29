package one.digitalinnovation.gof.facade;

import one.digitalinnovation.gof.builder.Cliente;
import one.digitalinnovation.gof.chain.ValidadorCadastro;
import one.digitalinnovation.gof.chain.ValidadorCep;
import one.digitalinnovation.gof.chain.ValidadorDuplicidade;
import one.digitalinnovation.gof.chain.ValidadorNome;
import one.digitalinnovation.gof.observer.ClienteMigradoEvento;
import one.digitalinnovation.gof.observer.Observador;
import one.digitalinnovation.gof.observer.Publicador;
import subsistema1.crm.CrmService;
import subsistema2.cep.CepApi;

public class Facade {

	private final ValidadorCadastro validadores;
	private final Publicador<ClienteMigradoEvento> publicador = new Publicador<>();

	public Facade() {
		ValidadorCadastro inicio = new ValidadorNome();
		inicio.encadear(new ValidadorCep()).encadear(new ValidadorDuplicidade());
		this.validadores = inicio;
	}

	public void adicionarObservador(Observador<ClienteMigradoEvento> observador) {
		publicador.inscrever(observador);
	}

	public Cliente migrarCliente(String nome, String cep) {
		Cliente rascunho = Cliente.builder().nome(nome).cep(cep).build();
		validadores.validar(rascunho);

		String cidade = CepApi.getInstancia().recuperarCidade(cep);
		String estado = CepApi.getInstancia().recuperarEstado(cep);
		Cliente cliente = rascunho.toBuilder().cidade(cidade).estado(estado).build();

		CrmService.gravarCliente(cliente.getNome(), cliente.getCep(), cliente.getCidade(), cliente.getEstado());
		publicador.publicar(new ClienteMigradoEvento(cliente));
		return cliente;
	}
}

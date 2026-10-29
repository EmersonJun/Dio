package one.digitalinnovation.gof.observer;

public class NotificadorBoasVindas implements Observador<ClienteMigradoEvento> {

	@Override
	public void atualizar(ClienteMigradoEvento evento) {
		System.out.println("Boas-vindas enviadas para " + evento.getCliente().getNome());
	}
}

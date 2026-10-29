package one.digitalinnovation.gof.observer;

import one.digitalinnovation.gof.builder.Cliente;

public final class ClienteMigradoEvento {

	private final Cliente cliente;

	public ClienteMigradoEvento(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
}

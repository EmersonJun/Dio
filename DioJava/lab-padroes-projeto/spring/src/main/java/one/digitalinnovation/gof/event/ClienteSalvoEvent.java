package one.digitalinnovation.gof.event;

import one.digitalinnovation.gof.model.Cliente;

public class ClienteSalvoEvent {

	private final Cliente cliente;
	private final boolean novo;

	public ClienteSalvoEvent(Cliente cliente, boolean novo) {
		this.cliente = cliente;
		this.novo = novo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public boolean isNovo() {
		return novo;
	}
}

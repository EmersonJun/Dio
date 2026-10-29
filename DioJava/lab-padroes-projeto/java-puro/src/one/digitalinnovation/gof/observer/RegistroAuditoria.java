package one.digitalinnovation.gof.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistroAuditoria implements Observador<ClienteMigradoEvento> {

	private final List<String> registros = new ArrayList<>();

	@Override
	public void atualizar(ClienteMigradoEvento evento) {
		registros.add("Migracao concluida: " + evento.getCliente());
	}

	public List<String> getRegistros() {
		return Collections.unmodifiableList(registros);
	}
}

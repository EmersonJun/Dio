package one.digitalinnovation.gof.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteSalvoListener {

	private static final Logger LOG = LoggerFactory.getLogger(ClienteSalvoListener.class);

	@EventListener
	public void aoSalvar(ClienteSalvoEvent evento) {
		String acao = evento.isNovo() ? "cadastrado" : "atualizado";
		LOG.info("Cliente {} {} (id={})", evento.getCliente().getNome(), acao, evento.getCliente().getId());
	}
}

package one.digitalinnovation.gof.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ClienteNaoEncontradoException.class)
	public ResponseEntity<Map<String, Object>> tratarNaoEncontrado(ClienteNaoEncontradoException e) {
		return resposta(HttpStatus.NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(ValidacaoClienteException.class)
	public ResponseEntity<Map<String, Object>> tratarValidacao(ValidacaoClienteException e) {
		return resposta(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	private ResponseEntity<Map<String, Object>> resposta(HttpStatus status, String mensagem) {
		Map<String, Object> corpo = new LinkedHashMap<>();
		corpo.put("timestamp", Instant.now().toString());
		corpo.put("status", status.value());
		corpo.put("erro", status.getReasonPhrase());
		corpo.put("mensagem", mensagem);
		return ResponseEntity.status(status).body(corpo);
	}
}

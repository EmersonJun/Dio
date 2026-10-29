package one.digitalinnovation.gof.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import one.digitalinnovation.gof.event.ClienteSalvoEvent;
import one.digitalinnovation.gof.exception.CepInvalidoException;
import one.digitalinnovation.gof.exception.ClienteNaoEncontradoException;
import one.digitalinnovation.gof.exception.ValidacaoClienteException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.impl.ClienteServiceImpl;
import one.digitalinnovation.gof.validator.ValidadorCep;
import one.digitalinnovation.gof.validator.ValidadorNome;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

	@Mock
	private ClienteRepository clienteRepository;
	@Mock
	private EnderecoRepository enderecoRepository;
	@Mock
	private ViaCepService viaCepService;
	@Mock
	private ApplicationEventPublisher publicador;

	private ClienteServiceImpl service;

	@BeforeEach
	void configurar() {
		service = new ClienteServiceImpl(clienteRepository, enderecoRepository, viaCepService,
				Arrays.asList(new ValidadorNome(), new ValidadorCep()), publicador);
	}

	@Test
	void inserirDeveConsultarViaCepQuandoEnderecoNaoExiste() {
		when(enderecoRepository.findById("14801788")).thenReturn(Optional.empty());
		Endereco retornoViaCep = endereco("14801-788");
		when(viaCepService.consultarCep("14801788")).thenReturn(retornoViaCep);
		when(enderecoRepository.save(any(Endereco.class))).thenAnswer(i -> i.getArgument(0));
		when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> i.getArgument(0));

		Cliente salvo = service.inserir(cliente("Venilton", "14801-788"));

		assertEquals("14801788", salvo.getEndereco().getCep());
		verify(viaCepService).consultarCep("14801788");
		ArgumentCaptor<ClienteSalvoEvent> evento = ArgumentCaptor.forClass(ClienteSalvoEvent.class);
		verify(publicador).publishEvent(evento.capture());
		assertEquals(true, evento.getValue().isNovo());
	}

	@Test
	void inserirDeveReaproveitarEnderecoExistente() {
		Endereco existente = endereco("14801788");
		when(enderecoRepository.findById("14801788")).thenReturn(Optional.of(existente));
		when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> i.getArgument(0));

		Cliente salvo = service.inserir(cliente("Venilton", "14801788"));

		assertSame(existente, salvo.getEndereco());
		verify(viaCepService, never()).consultarCep(any());
	}

	@Test
	void inserirDeveFalharQuandoViaCepNaoEncontraCep() {
		when(enderecoRepository.findById("99999999")).thenReturn(Optional.empty());
		when(viaCepService.consultarCep("99999999")).thenReturn(new Endereco());

		assertThrows(CepInvalidoException.class, () -> service.inserir(cliente("Venilton", "99999999")));
		verify(clienteRepository, never()).save(any());
		verify(publicador, never()).publishEvent(any(Object.class));
	}

	@Test
	void inserirDeveRejeitarNomeInvalidoAntesDeQualquerIntegracao() {
		assertThrows(ValidacaoClienteException.class, () -> service.inserir(cliente("Jo", "14801788")));
		verify(viaCepService, never()).consultarCep(any());
		verify(clienteRepository, never()).save(any());
	}

	@Test
	void buscarPorIdInexistenteDeveLancarExcecao() {
		when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ClienteNaoEncontradoException.class, () -> service.buscarPorId(1L));
	}

	@Test
	void atualizarInexistenteDeveLancarExcecao() {
		when(clienteRepository.existsById(1L)).thenReturn(false);

		assertThrows(ClienteNaoEncontradoException.class, () -> service.atualizar(1L, cliente("Venilton", "14801788")));
	}

	@Test
	void atualizarDevePreservarOIdInformado() {
		when(clienteRepository.existsById(7L)).thenReturn(true);
		when(enderecoRepository.findById("14801788")).thenReturn(Optional.of(endereco("14801788")));
		when(clienteRepository.save(any(Cliente.class))).thenAnswer(i -> i.getArgument(0));

		Cliente salvo = service.atualizar(7L, cliente("Venilton", "14801788"));

		assertEquals(7L, salvo.getId());
	}

	@Test
	void deletarInexistenteDeveLancarExcecao() {
		when(clienteRepository.existsById(3L)).thenReturn(false);

		assertThrows(ClienteNaoEncontradoException.class, () -> service.deletar(3L));
		verify(clienteRepository, never()).deleteById(any());
	}

	private Endereco endereco(String cep) {
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		endereco.setLocalidade("Araraquara");
		endereco.setUf("SP");
		return endereco;
	}

	private Cliente cliente(String nome, String cep) {
		Endereco endereco = new Endereco();
		endereco.setCep(cep);
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		return cliente;
	}
}

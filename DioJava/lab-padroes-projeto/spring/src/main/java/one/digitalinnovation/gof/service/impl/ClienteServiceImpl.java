package one.digitalinnovation.gof.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.event.ClienteSalvoEvent;
import one.digitalinnovation.gof.exception.CepInvalidoException;
import one.digitalinnovation.gof.exception.ClienteNaoEncontradoException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;
import one.digitalinnovation.gof.util.Cep;
import one.digitalinnovation.gof.validator.ValidadorCliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final EnderecoRepository enderecoRepository;
	private final ViaCepService viaCepService;
	private final List<ValidadorCliente> validadores;
	private final ApplicationEventPublisher publicador;

	public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository,
			ViaCepService viaCepService, List<ValidadorCliente> validadores, ApplicationEventPublisher publicador) {
		this.clienteRepository = clienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.viaCepService = viaCepService;
		this.validadores = validadores;
		this.publicador = publicador;
	}

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException(id));
	}

	@Override
	public Cliente inserir(Cliente cliente) {
		cliente.setId(null);
		return salvarClienteComCep(cliente, true);
	}

	@Override
	public Cliente atualizar(Long id, Cliente cliente) {
		if (!clienteRepository.existsById(id)) {
			throw new ClienteNaoEncontradoException(id);
		}
		cliente.setId(id);
		return salvarClienteComCep(cliente, false);
	}

	@Override
	public void deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new ClienteNaoEncontradoException(id);
		}
		clienteRepository.deleteById(id);
	}

	private Cliente salvarClienteComCep(Cliente cliente, boolean novo) {
		validadores.forEach(validador -> validador.validar(cliente));

		String cep = Cep.normalizar(cliente.getEndereco().getCep());
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> consultarESalvarEndereco(cep));
		cliente.setEndereco(endereco);

		Cliente salvo = clienteRepository.save(cliente);
		publicador.publishEvent(new ClienteSalvoEvent(salvo, novo));
		return salvo;
	}

	private Endereco consultarESalvarEndereco(String cep) {
		Endereco encontrado = viaCepService.consultarCep(cep);
		if (encontrado == null || encontrado.getCep() == null) {
			throw new CepInvalidoException(cep);
		}
		encontrado.setCep(cep);
		return enderecoRepository.save(encontrado);
	}

}

package one.digitalinnovation.gof;

import one.digitalinnovation.gof.builder.Cliente;
import one.digitalinnovation.gof.chain.ValidacaoException;
import one.digitalinnovation.gof.facade.Facade;
import one.digitalinnovation.gof.observer.NotificadorBoasVindas;
import one.digitalinnovation.gof.observer.RegistroAuditoria;
import one.digitalinnovation.gof.singleton.SingletonEager;
import one.digitalinnovation.gof.singleton.SingletonEnum;
import one.digitalinnovation.gof.singleton.SingletonLazy;
import one.digitalinnovation.gof.singleton.SingletonLazyHolder;
import one.digitalinnovation.gof.strategy.Comportamento;
import one.digitalinnovation.gof.strategy.ComportamentoFactory;
import one.digitalinnovation.gof.strategy.Robo;
import one.digitalinnovation.gof.strategy.TipoComportamento;

public class Main {

	public static void main(String[] args) {
		demonstrarSingleton();
		demonstrarStrategyComFactory();
		demonstrarFacadeComChainBuilderEObserver();
	}

	private static void demonstrarSingleton() {
		titulo("Singleton");

		SingletonLazy lazy1 = SingletonLazy.getInstancia();
		SingletonLazy lazy2 = SingletonLazy.getInstancia();
		System.out.println("Lazy: " + lazy1 + " | mesma instancia: " + (lazy1 == lazy2));

		SingletonEager eager1 = SingletonEager.getInstancia();
		SingletonEager eager2 = SingletonEager.getInstancia();
		System.out.println("Eager: " + eager1 + " | mesma instancia: " + (eager1 == eager2));

		SingletonLazyHolder holder1 = SingletonLazyHolder.getInstancia();
		SingletonLazyHolder holder2 = SingletonLazyHolder.getInstancia();
		System.out.println("Lazy Holder: " + holder1 + " | mesma instancia: " + (holder1 == holder2));

		SingletonEnum.INSTANCIA.registrarAcesso();
		System.out.println("Enum: acessos = " + SingletonEnum.INSTANCIA.registrarAcesso());
	}

	private static void demonstrarStrategyComFactory() {
		titulo("Strategy + Factory Method");

		Robo robo = new Robo();
		robo.mover();

		for (TipoComportamento tipo : TipoComportamento.values()) {
			Comportamento comportamento = ComportamentoFactory.criar(tipo);
			robo.setComportamento(comportamento);
			System.out.print(tipo + ": ");
			robo.mover();
		}
	}

	private static void demonstrarFacadeComChainBuilderEObserver() {
		titulo("Facade + Chain of Responsibility + Builder + Observer");

		Facade facade = new Facade();
		RegistroAuditoria auditoria = new RegistroAuditoria();
		facade.adicionarObservador(new NotificadorBoasVindas());
		facade.adicionarObservador(auditoria);

		Cliente migrado = facade.migrarCliente("Venilton", "14801788");
		System.out.println("Retorno: " + migrado);

		tentarMigrar(facade, "Jo", "14801788");
		tentarMigrar(facade, "Maria", "123");
		tentarMigrar(facade, "Venilton", "14801788");

		System.out.println("Auditoria: " + auditoria.getRegistros());
	}

	private static void tentarMigrar(Facade facade, String nome, String cep) {
		try {
			facade.migrarCliente(nome, cep);
		} catch (ValidacaoException e) {
			System.out.println("Rejeitado (" + nome + ", " + cep + "): " + e.getMessage());
		}
	}

	private static void titulo(String texto) {
		System.out.println();
		System.out.println("=== " + texto + " ===");
	}
}

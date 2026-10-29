# CV Match — Protótipo de UX/UI

Protótipo navegável de média fidelidade para uma ferramenta que compara um currículo com uma descrição de vaga e apresenta um diagnóstico de compatibilidade.

> **Status:** projeto acadêmico / protótipo demonstrativo. A interface usa resultados fictícios; não existe backend ou análise real de IA.

## Problema
Candidatos podem ter dificuldade para perceber quais competências e palavras-chave de uma vaga estão evidenciadas no currículo. O CV Match organiza essa comparação em uma leitura visual simples e orientada à melhoria.

## Público-alvo
Pessoas em busca de estágio ou oportunidades júnior que desejam adaptar e revisar seus currículos para vagas específicas.

## Objetivo
Permitir que a pessoa envie um currículo em PDF, cole a descrição da vaga e entenda rapidamente:
- uma pontuação de compatibilidade de 0 a 100;
- palavras-chave encontradas e ausentes;
- pontos fortes e sugestões de melhoria;
- análises anteriores e evolução.

## Como navegar
1. Abra `index.html` no navegador.
2. No painel, clique em **Nova análise**.
3. Selecione um PDF e cole uma descrição de vaga (os dados não são enviados nem processados).
4. Clique em **Analisar compatibilidade** para visualizar a tela de resultado ilustrativa.
5. Acesse **Histórico** e alterne entre **Lista** e **Grade**.

## Fluxo principal
`Visão geral → Nova análise → Resultado → Histórico`

## Decisões de UX/UI
- **Hierarquia visual:** título, contexto e ação principal aparecem no topo de cada tela.
- **Consistência:** navegação lateral mantém as áreas principais acessíveis.
- **Reconhecimento em vez de memorização:** palavras encontradas e ausentes usam rótulos e cores consistentes.
- **Feedback:** seleção de arquivo mostra o nome escolhido; botões levam a telas correspondentes.
- **List View e Grid View:** histórico pode ser consultado em dois formatos.
- **Responsividade:** layout adapta-se a telas menores.
- **Transparência:** a interface informa que a análise é apenas demonstrativa e que os dados não são processados.

## Escopo e limitações
Este é um protótipo de interface em HTML/CSS/JavaScript para demonstração do fluxo. O campo de arquivo apenas permite selecionar um PDF local; não há upload, persistência, autenticação, API ou modelo de IA. As pontuações e palavras-chave mostradas são exemplos estáticos.

## Como publicar no GitHub Pages
1. Crie um repositório público chamado `cv-match` no GitHub.
2. Envie `index.html` e `README.md` para a raiz do repositório.
3. No repositório, abra **Settings → Pages**.
4. Em **Build and deployment**, selecione **Deploy from a branch**, branch `main` e pasta `/ (root)`; salve.
5. Aguarde a publicação e copie o endereço exibido pelo GitHub Pages.

## Como apresentar no Figma
Recrie as telas principais como frames (desktop 1440 × 900 e versão mobile 390 × 844):
- Visão geral
- Nova análise
- Resultado
- Histórico em List View
- Histórico em Grid View

Conecte os botões seguindo o fluxo principal e configure as interações de alternância Lista/Grade. Use a página do Figma para documentar cores, tipografia, componentes e estados.

## Estrutura
```text
cv-match/
├── index.html
└── README.md
```

## Créditos
Projeto desenvolvido para desafio de prototipagem e design de interfaces da DIO.

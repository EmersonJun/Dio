# Saborê — Protótipo de aplicativo de pedidos

Protótipo acadêmico de alta fidelidade para um aplicativo de pedidos de uma pizzaria artesanal. O projeto foi construído em HTML, CSS e JavaScript puro para ser fácil de abrir, avaliar e publicar no GitHub Pages.

> **Aviso:** Saborê, os preços, o prazo e os dados apresentados são fictícios. O protótipo não envia pedidos reais nem processa pagamentos.

## Como visualizar
1. Baixe ou clone este repositório.
2. Abra `index.html` no navegador. Não é necessário instalar dependências.
3. Para publicar, habilite GitHub Pages nas configurações do repositório e selecione a branch principal e a raiz.

## Escopo entregue
- Briefing e análise do problema (`docs/briefing.md`)
- Diretrizes visuais e componentes (`docs/design-system.md`)
- Fluxo de navegação (`docs/user-flow.md`)
- Orçamento estimativo (`docs/orcamento.md`)
- Interface responsiva com filtros, sacola, controle de quantidades e feedback de interação
- Critérios de usabilidade e próximos passos

## Jornada demonstrada
Cardápio → filtro por categoria → adicionar produto → abrir sacola → ajustar quantidade → continuar para entrega. A última ação informa que endereço e pagamento pertencem à etapa seguinte, sem simular uma compra real.

## Estrutura
```text
.
├── index.html
├── styles.css
├── app.js
├── docs/
│   ├── briefing.md
│   ├── design-system.md
│   ├── user-flow.md
│   └── orcamento.md
└── images/
```

## Decisões de UX
- Navegação e categorias com rótulos diretos.
- Botões de adicionar com área de interação evidente.
- Sacola atualiza quantidade e subtotal em tempo real.
- Estado vazio e confirmação de adição comunicam o que aconteceu.
- Layout adaptado para celular, tablet e desktop.
- A ação de checkout não promete processamento que não existe.

## Melhorias futuras
- Criar telas de endereço, entrega e pagamento.
- Implementar autenticação e persistência da sacola.
- Integrar API/backend, disponibilidade e cálculo de entrega.
- Adicionar testes automatizados e validação com usuários.
- Substituir ilustrações CSS/emoji por fotografias licenciadas da marca.

## Créditos
Projeto de estudo inspirado no desafio de prototipagem e design de interação da DIO. Todo o conteúdo foi criado para fins educacionais.

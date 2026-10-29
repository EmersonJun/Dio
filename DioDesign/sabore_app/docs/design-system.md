# Design System — Saborê

## Conceito
Uma pizzaria contemporânea, acolhedora e artesanal. O visual combina fundo creme, verde profundo e detalhes quentes para transmitir cuidado sem deixar a interface pesada.

## Tokens
| Token | Valor | Uso |
|---|---|---|
| Verde profundo | `#244D3B` | Ação principal, marca |
| Creme | `#F8F6EF` | Fundo |
| Branco quente | `#FFFDF8` | Cards e áreas de conteúdo |
| Verde menta | `#DCE9D9` | Ações secundárias |
| Laranja | `#E18B51` | Destaques decorativos |
| Texto | `#202B28` | Texto principal |
| Texto secundário | `#6C7771` | Descrições |

## Tipografia
- Títulos: Playfair Display, serif; contraste editorial e personalidade.
- Interface e corpo: DM Sans, sans-serif; leitura rápida e consistente.
- Títulos de seção em escala aproximada de 40–72 px conforme viewport.
- Corpo de texto entre 12–16 px nos componentes apresentados.

## Componentes
- **Botão principal:** fundo verde, texto branco, formato arredondado.
- **Filtro:** chip com borda; categoria selecionada usa verde preenchido.
- **Card de produto:** ilustração, nome, descrição, preço e botão `+`.
- **Sacola:** painel lateral no desktop e tela cheia em viewport estreita; mostra estado vazio, itens, quantidades e subtotal.
- **Feedback:** mensagem temporária após adicionar produto ou tentar avançar com sacola vazia.

## Espaçamento e forma
- Base de espaçamento: múltiplos de 4/8 px.
- Cards com raio de 18 px; chips e ações com raio alto.
- Área clicável recomendada de pelo menos 44 × 44 px nos controles críticos.
- Layout usa grid no desktop e reduz para uma coluna em telas pequenas.

## Acessibilidade e usabilidade
- Contraste de texto e ação deve ser verificado com ferramenta de contraste antes da entrega final.
- Botões possuem rótulos acessíveis; diálogo da sacola informa `aria-modal`.
- Escape e clique fora fecham o painel.
- Categorias, quantidades e checkout usam termos familiares.
- Próxima etapa é explicitada; não há promessa de pagamento ou pedido real.

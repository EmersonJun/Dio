const cart = new Map();
const money = n => new Intl.NumberFormat('pt-BR',{style:'currency',currency:'BRL'}).format(n);
const overlay = document.querySelector('#overlay');
const cartItems = document.querySelector('#cart-items');
const count = document.querySelector('#cart-count');
const subtotal = document.querySelector('#subtotal');
const toast = document.querySelector('#toast');

function notify(message){
  toast.textContent = message; toast.classList.add('show');
  window.setTimeout(()=>toast.classList.remove('show'),1800);
}
function addItem(card){
  const name=card.dataset.name, price=Number(card.dataset.price);
  const item=cart.get(name)||{name,price,qty:0};
  item.qty++; cart.set(name,item); renderCart(); notify(`${name} adicionado à sacola`);
}
function changeQty(name,delta){
  const item=cart.get(name); if(!item)return;
  item.qty+=delta; if(item.qty<=0)cart.delete(name); renderCart();
}
function renderCart(){
  const total=[...cart.values()].reduce((sum,item)=>sum+item.price*item.qty,0);
  const units=[...cart.values()].reduce((sum,item)=>sum+item.qty,0);
  count.textContent=units; subtotal.textContent=money(total);
  if(!cart.size){cartItems.innerHTML='<p class="empty">Sua sacola está vazia. Que tal escolher algo gostoso?</p>';return;}
  cartItems.innerHTML=[...cart.values()].map(item=>`
    <div class="cart-row">
      <div><h3>${item.name}</h3><p>${money(item.price)} cada</p></div>
      <div class="qty"><button aria-label="Diminuir ${item.name}" data-action="minus" data-name="${item.name}">−</button><strong>${item.qty}</strong><button aria-label="Aumentar ${item.name}" data-action="plus" data-name="${item.name}">+</button></div>
    </div>`).join('');
}
document.querySelectorAll('.add').forEach(button=>button.addEventListener('click',()=>addItem(button.closest('.product-card'))));
document.querySelectorAll('.filter').forEach(button=>button.addEventListener('click',()=>{
  document.querySelectorAll('.filter').forEach(b=>b.classList.toggle('active',b===button));
  document.querySelectorAll('.product-card').forEach(card=>card.hidden=button.dataset.filter!=='todos'&&card.dataset.category!==button.dataset.filter);
}));
cartItems.addEventListener('click',event=>{
  const button=event.target.closest('button[data-action]'); if(!button)return;
  changeQty(button.dataset.name,button.dataset.action==='plus'?1:-1);
});
function openCart(){overlay.hidden=false;document.querySelector('#close-cart').focus();}
function closeCart(){overlay.hidden=true;document.querySelector('#open-cart').focus();}
document.querySelector('#open-cart').addEventListener('click',openCart);
document.querySelector('#close-cart').addEventListener('click',closeCart);
overlay.addEventListener('click',event=>{if(event.target===overlay)closeCart();});
document.addEventListener('keydown',event=>{if(event.key==='Escape'&&!overlay.hidden)closeCart();});
document.querySelector('#checkout').addEventListener('click',()=>{
  if(!cart.size){notify('Adicione pelo menos um item para continuar.');return;}
  notify('Próxima etapa: informar endereço e pagamento (fluxo demonstrativo).');
});
renderCart();

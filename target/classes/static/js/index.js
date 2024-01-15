// Adicione suas credenciais do SDK
// Substitua com sua chave pública disponível em: https://developers.mercadopago.com/panel
const mercadopago = new MercadoPago('SUA CREDENCIAL DE TESTE', {
  locale: 'pt-BR'
});

// Lidar com a chamada ao backend e gerar preferência.
document.getElementById("checkout-btn").addEventListener("click", function () {

  $('#checkout-btn').attr("disabled", true);

  const dadosPedido = {
    nome_produto: document.getElementById("produc-name").innerHTML,
    quantidade: document.getElementById("quantity").value,
    url_imagem: document.getElementById("url1").innerHTML,
    descricao: document.getElementById("product-description").innerHTML,
    preco: document.getElementById("unit-price").innerHTML
  };

  fetch("/pago/create_preference", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(dadosPedido),
  })
    .then(response => response.json())
    .then(response => {
      console.log("preferência::" + response.id);
      criarBotaoCheckout(response.id);

      $(".shopping-cart").fadeOut(500);
      setTimeout(() => {
        $(".container_payment").show(500).fadeIn();
      }, 500);
    })
    .catch(() => {
      alert("Erro inesperado");
      $('#checkout-btn').attr("disabled", false);
    });
});

// Criar botão de checkout ao clicar no botão de pagamento
function criarBotaoCheckout(idPreferencia) {
  // Inicializar o checkout
  mercadopago.checkout({
    preference: {
      id: idPreferencia
    },
    render: {
      container: '#button-checkout', // Nome da classe onde o botão de pagamento será exibido
      label: 'Pagar', // Alterar o texto do botão de pagamento (opcional)
    }
  });
}

// Lidar com a atualização do preço
function atualizarPreco() {
  let quantidade = document.getElementById("quantity").value;
  let precoUnitario = document.getElementById("unit-price").innerHTML;
  let total = parseInt(precoUnitario) * parseInt(quantidade);

  document.getElementById("cart-total").innerHTML = "R$ " + total;
  document.getElementById("summary-price").innerHTML = "R$ " + precoUnitario;
  document.getElementById("summary-quantity").innerHTML = quantidade;
  document.getElementById("summary-total").innerHTML = "R$ " + total;
}

document.getElementById("quantity").addEventListener("change", atualizarPreco);
atualizarPreco();

// Voltar
document.getElementById("go-back").addEventListener("click", function () {
  $(".container_payment").fadeOut(500);
  setTimeout(() => {
    $(".shopping-cart").show(500).fadeIn();
  }, 500);
  $('#checkout-btn').attr("disabled", false);
});

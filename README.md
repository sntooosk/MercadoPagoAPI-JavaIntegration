# Projeto de Integração com API MercadoPago em Java

Este projeto em Java realiza a integração com a API MercadoPago, proporcionando a gestão de preferências de pagamento. A implementação utiliza a versão 2.0.0 da SDK MercadoPago.

## Início

Para configurar e executar o aplicativo localmente, siga as etapas abaixo.

### Pré-requisitos

- Java 8
- Spring Framework (versão 5.3.13)
- SDK MercadoPago (versão 2.0.0)

### Configuração

1. Crie uma conta de desenvolvedor no [MercadoPago Developer](https://www.mercadopago.com.br/developers/pt).
2. Obtenha suas credenciais de teste no [Painel do Desenvolvedor](https://developers.mercadopago.com/panel).
3. Substitua `'SUA CREDENCIAL DE TESTE'` e `'SEU TOKEN DE ACESSO'` no código pelo seu ID de cliente e token de acesso.

```java
const mercadopago = new MercadoPago('SUA CREDENCIAL DE TESTE'); 

MercadoPagoConfig.setAccessToken("SEU TOKEN DE ACESSO");
```

### Execução

Clone o repositório:

```bash
git clone <url-do-repositorio>
cd <diretorio-do-repositorio>
```

Compile e execute o aplicativo utilizando sua ferramenta de construção preferida.

Acesse o aplicativo em seu navegador através de `http://localhost:8080`.

## Galeria

### Tela Loja
<img src="https://raw.githubusercontent.com/DSantosxTech/MercadoPagoAPI/main/github/galeria1.png" alt="Tela Loja"/>

### Tela Checkout
<img src="https://raw.githubusercontent.com/DSantosxTech/MercadoPagoAPI/main/github/galeria2.png" alt="Tela Checkout"/>

### Tela Pagamento
<img src="https://raw.githubusercontent.com/DSantosxTech/MercadoPagoAPI/main/github/galeria3.png" alt="Tela Pagamento"/>

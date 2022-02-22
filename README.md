<h1>☕ Projeto Vaga Java JR. ☕</h1>
<p>API REST feita 100% em Java com Spring. </p>

<hr>
<h2> ⚒️ Ferramentas Usadas ⚒️ </h2>
<ul>
  <li><h3>SprigBoot</h3></li>
  <li><h3>SpringData</h3></li>
  <li><h3>Spring WEB</h3></li>
  <li><h3>Banco de Dados H2</h3></li>
</ul>
<hr>
 
<h2>🗒 Documentação 🗒</h2>
<p> Clique em Clientes, Games e Carrinho de compra para ler a documentação de cada um.</p>
<details>
<summary><h2> 🔰 CLIENTES (clique aqui)🔰 </h2></summary>
 
<h3> ➤ [POST] save() </h3>
 http://localhost:8080/api/clientes

<pre>
<code>
{
  "nome": "Tommy Dalvino",
  "contato": "(92) 0000-000",
  "endereco": {
        "rua": "av. max teixeira",
        "numCasa": 333
   }
}
</code>
</pre>
 
 
 <ul>
   <li><h3>se salvar retorná status 201;</h3></li>
   <li><h3>todos os campos são obrigatórios.</h3></li>
  </ul>
<h3> ➤ [GET] FindAll() </h3>
http://localhost:8080/api/clientes

<ul>
  <li><h3>Retornará todos os clientes;</h3></li>
 </ul>
<h3> ➤ [GET] FindById() </h3>
http://localhost:8080/api/clientes/{id}

  <ul>
   <li><h3>se informar um id inexistente retornará um status 404;</h3></li>
  </ul>
<h3> ➤ [DELETE] DeleteById() </h3>
http://localhost:8080/api/clientes/{id}
 
 <ul>
  <li><h3>id do cliente a ser deletado deve ser passado na URI;</h3></li>
   <li><h3>se deletar retornará status 204;</h3></li>
   <li><h3>se passar um id inexistente retornará um status 404;</h3></li>
 </ul>

<h3> ➤ [PUT] update() </h3>
 http://localhost:8080/api/clientes/{id}

<pre>
<code>
{
   "nome": "novoNome",
    "contato": "novoContato",
    "endereco": {
          "rua": "novaRua",
          "numCasa": 000
       }
}
</code>
</pre>
 <ul>
 <li><h3>Id do cliente a ser atualizado deve ser informado na URI;</h3></li>
 <li><h3>se atualizar retonará um status 200 OK;</h3></li>
 <li><h3>se informar um id inexistente retornará um status 404;</h3></li>
</ul>
</details>

<details>
<summary><h2> 🔰 GAMES (clique aqui)🔰 </h2></summary>
<h3> ➤ [GET] findAll() </h3>
http://localhost:8080/api/games 
 <ul>
  <ul>
 <li><h3>Trará todos os games do banco de dados;</h3></li>
</ul>
 </ul>
<h3> ➤ [GET] findById() </h3>
http://localhost:8080/api/games/{id}

 <ul>
  <li><h3>pesquisa um game por id</h3></li>
  <li><h3>id do game deve ser informado na URI;</h3></li>
  <li><h3>retornará status 404 caso o id não seja encontrado;</h3></li>
 </ul>
 
<h3> ➤ [DELETE] deleteById() </h3>
 http://localhost:8080/api/games/{id}
 
 <ul>
  <li><h3>Id do game a ser deletado deve ser passado na URI</h3></li>
  <li><h3>se o game estiver associado a um carrinho vai disparar um erro 409</h3></li>
  <li><h3>se id n corresponder a nenhum carrinho vai disparar um erro 404</h3></li>
 </ul>
 
<h3> ➤ [GET] findByPrecoAsc() </h3>
 http://localhost:8080/api/games/preco-asc
 
 <ul>
  <li><h3>Ordena os preços de maneira ascendente</h3></li>
  </ul>
<h3> ➤ [GET] findByPrecoDesc()</h3>
 http://localhost:8080/api/games/preco-desc
 
 <ul>
  <li><h3>Ordena os preços de maneira descendente</h3></li>
  </ul>

<h3> ➤ [GET] findByOrdemAlfabetica()</h3>
 http://localhost:8080/api/games/ordemnome

 <ul>
  <li><h3>Retorna os games em ordem alfabética</h3></li>
  </ul>
<h3> ➤ [POST] save() </h3>
 http://localhost:8080/api/games
 <pre>
 <code>
{
  "nome": "nomeGame",
  "urlImagem": null,
  "descricao": null,
  "preco": 0.0
}
</code>
</pre>
<ul>
 <li><h3>Nome e Preço são obrigarórios, o restante dos campos não.</h3></li>
 <li><h3>Retorna um status 201 se salvar;</h3></li>
 </ul>
<h3> ➤ [PUT] update()</h3>
http://localhost:8080/api/games/{id}
 <pre>
 <code>
{
  "nome": "nomeGame",
  "urlImagem": null,
  "descricao": null,
  "preco": 0.0
}
</code>
</pre>
 <ul>
  <li><h3>o id do game a ser atualizado deve ser passado na URI</h3></li>
  <li><h3>retornará um status 200 OK se atualizar</h3></li>
  </ul>
</details>

<details>
<summary><h2> 🔰 CARRINHO DE COMPRA (clique aqui) 🔰 </h2></summary>
<h3> ➤ [GET] findAll() </h3>
http://localhost:8080/api/carrinhos
 <ul>
  <li><h3>retorna todos os carrinhos</h3></li>
  </ul>

<h3> ➤ [GET] findById() </h3>
http://localhost:8080/api/carrinhos/{id}
<ul>
 <li><h3>busca um carrinho pelo id</h3></li>
 <li><h3>Id do carrinho deve ser passado na URI</h3></li>
 <li><h3>Se o id não existir lançará um status 404</h3></li>
 </ul>
<h3> ➤ [DELETE] deleteById() </h3>
http://localhost:8080/api/carrinhos/{id}
 
 <ul>
  <li><h3>id do carrinho deve ser informado na URI</h3></li>
  <li><h3>deleta um carrinho pelo id</h3></li>
  <li><h3>se id não existir lançará um erro 404</h3></li>
  </ul>

<h3> ➤ [POST] save()</h3>
http://localhost:8080/api/carrinhos/{idCliente}

<pre>
<code>
{
"itens": [
         {
           "quantidade": 1,
            "game":{
                 "id": 1,
                 "nome": "Clash Royale"
                 "urlImagem": "http://image/linkFicticio/royale",
                 "descricao": "Destrua a torre do seu inimigo!",
 "preco": 20.0
            }
         },
         {
 "quantidade": 1,
            "game":{
                  "id": 3,
                  "nome": "Minecraft",            
                  "urlImagem": "http://image/linkFicticio/minecraft",
                  "descricao": "Crie um mundo e explore-o.",
                  "preco": 10.0,
            }
          }
]}
</code>
</pre>
 
 <ul>
  <li><h3>salva um carrinho de compras</h3></li>
  <li><h3>id do cliente associado ao carrinho deve ser passado na URI</h3></li>
  <li><h3>quantidade e game são obrigatórios</h3></li>
  </ul>

<h3> ➤ [PUT] update() </h3>
http://localhost:8080/api/carrinhos/{idCarrinho}

<pre>
<code>
{
"itens": [
         {
           "quantidade": 1,
            "game":{
                "id": 1,
                "nome": "Clash Royale"
                "urlImagem": "http://image/linkFicticio/royale",
                "descricao": "Destrua a torre do seu inimigo!",
                "preco": 20.0
            }
         }
]}
</code>
</pre>
 <ul>
  <li><h3>id do carrinho a ser atualizado deve ser passado na URI</h3></li>
  <li><h3>quantidade e game são obrigatórios</h3></li>
  <li><h3>Não passe um game não-salvo no banco de dados</h3></li>
  </ul>
</details>



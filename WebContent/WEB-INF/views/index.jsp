<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, mvc.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUDnotes</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
#map {
	height: 100%;
}
</style>
</head>
<body>
	<div class="jumbotron">
		<h3>
			CRUD<span style="color:blue">notes</span> <small class="text-muted">Poupe sua memoria para
				coisas realmente importantes.</small>
		</h3>
	</div>

	<div class="ml-3">
		<h1 class="h4">Criar novo lembrete</h1>
	</div>
	<div class="ml-3">
		<div class="card w-75">
			<div class="card-body">
				<form action="adicionaNota" method="post">
					<h5 class="card-title">
						<input name="titulo" type="text" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" placeholder="Titulo">
					</h5>
					<textarea name="conteudo" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Conteudo"></textarea>
					<br><button type="submit" value="Adicionar" class="btn btn-primary">Criar</button>
				</form>
			</div>
		</div>
	</div>
	<br>
	
	<div class ="ml-3">
		<h2>Pokecriador</h2>
		<br>
		<div id='poke' style="display: none;">
			<h4>Carregando...</h4>
			<img class='img-responsive' src="http://orig04.deviantart.net/f60e/f/2017/042/0/7/pokeball_gif_by_termatior0-dayo3ow.gif" height='20%' width='20%'>
		</div>
		<h4 id='done' style="display: none;">Carregado!</h4>
		<p id = "erro"></p>
	</div>
	
	
	
	<form action="adicionaPoke" method="post" id='creator'>
					<h5 class="card-title">
						<input id="move1" name="move1" type="text" READONLY class="form-control">
						<input id="move2" name="move2" type="text" READONLY class="form-control">
						<input id="move3" name="move3" type="text" READONLY class="form-control">
						<input id="move4" name="move4" type="text" READONLY class="form-control">
						<input id="pokename" name="pokename" type="text" READONLY class="form-control">
						<input id="evolutions" name="evolutions" type="text" READONLY class="form-control">
						<input id="sprite" name="sprite" type="text" READONLY class="form-control">					
					</h5>
					<br>
					<label  class="btn btn-info">
	            			<input id="shiny" type="checkbox"> Shiny
	       			</label>
					<button type="button" value="CriarPokemon" class="btn btn-primary" onclick="urlFetch();setTimeout(checkApi, 8200);">SelecionarPokemon</button>
					 
					
					<button type="submit" id='submit' class="btn btn-primary" disabled>Criar</button>				
	</form>
	
	<div class="ml-3">
		<h1 class="h1">Lembretes</h1>
		
		
	</div>
	
	
	<div class="container" style='float:left;'>
  		<h4>Pokemons Existentes</h4> 
  		<table class="table">
    		<thead>
      			<tr>
        			<th>ID</th>
        			<th>Pokémon</th>
     			</tr>
    		</thead>
    	<tbody>
	
	<%
		NotasDAO dao = new NotasDAO();
		List<Nota> notas = dao.getLista();
		List<Poke> pokes = dao.getPokelista();
		String pokesprite = "";
		String pokemon = "";
		String move1 = "";
		String move2 = "";
		String move3 = "";
		String move4 = "";
		int i = 0;
		for (Poke poke : pokes) {
			out.println("<tr><td>" + poke.getId_poke() + "</td><td>" + poke.getPokename() + "</td></tr>");
		}
		out.println("</tbody></table></div>");
		
		
		for (Nota nota : notas) {
			i += 1;
			//System.out.println(nota.getId_pokenota());
			for (Poke poke : pokes) {
				if (poke.getId_poke() == nota.getId_pokenota()) {
					String pokelow = poke.getPokename();
					if (pokelow.length() > 1) { 
						pokemon = pokelow.substring(0, 1).toUpperCase() + pokelow.substring(1);
						move1 = poke.getMove1().substring(0, 1).toUpperCase() + poke.getMove1().substring(1);
						move2 = poke.getMove2().substring(0, 1).toUpperCase() + poke.getMove2().substring(1);
						move3 = poke.getMove3().substring(0, 1).toUpperCase() + poke.getMove3().substring(1);
						move4 = poke.getMove4().substring(0, 1).toUpperCase() + poke.getMove4().substring(1);
						pokesprite = poke.getSprite();
					}
				}
				else {
					pokemon = "";
					move1 = "";
					move2 = "";
					move3 = "";
					move4 = "";
					pokesprite = "";
				}
			
			}
					//System.out.println(pokesprite);
				//System.out.println(pokesprite);
				//System.out.println(poke.getId_poke());
			out.println("<div class='ml-3'>" +
			"<div class='card w-75' style='background: " + nota.getCor() + "'>" +
				"<div class='card-body'>"+
					"<form action='alteraNota' method='post'>"+
						"<input type='hidden' name='Id' value='" + nota.getId() + "'>"+
						"<h5 class='card-title'>"+
							"<input name='titulo' type='text' class='form-control' id='exampleInputEmail1'"+
								"aria-describedby='emailHelp' value='" + nota.getTitulo() + "'>"+
						"</h5>"+
						"<textarea name='conteudo' class='form-control' id='content"+ Integer.toString(i) + "' rows='3' placeholder='Conteudo'>" + nota.getConteudo() + "</textarea>"+
						"<input name='id_pokenota' type='number' class ='form-control' placeholder='Selecione um id de pokemon para expo-lo na nota(deixar isto vazio removerá o pokemon da nota)'>"+
						"<br><input type='color' style='border-radius: 25px; background: " + nota.getCor() + "' name='cor' value='" + nota.getCor() + "'>"+
						"   <input type='date' style='border-radius: 25px' name='datacriacao' value='" + nota.getDatacriacao() + "'>"+
						"   <input type='checkbox' name='delete' > Deletar lembrete?"+
						"<br><br><button type='submit' value='Editar' class='btn btn-primary'>Aplicar Edição</button> &nbsp;&nbsp;&nbsp;  "+
						"<button type='button' class='btn btn-primary' onclick='urlFetchLeet" + "(\"content" + Integer.toString(i) + "\")'>Traduzir Conteudo</button>"+
						"<br><br>"+
						"<div class='panel panel-info'>" +
					    "<div class='panel-heading'>" + pokemon + "</div>" +
					    "<div class='panel-body'><img style='float: left;' class='img-responsive' src='" + pokesprite + "' float:right;>" + 
					    "<h4>Move 1:  " + move1 +
					    "<br> Move 2:  " + move2 +
					    "<br> Move 3:  " + move3 +
					    "<br> Move 4:  " + move4 + 
					    "<h4></div>"+"</div>"+
					"</form>"+
				"</div>"+
			"</div>"+
		"</div><br>");


		}
	%>
	
		<script>
		function resetarForm() {
				document.getElementById("creator").reset();
				}
		
		</script>
		<script>
		function checkApi() {
		//    var form = document.forms["creator"].elements;
	    //    var cansubmit = true;
	//
	  //      for (var i = 0; i < form.length; i++) {
	    //        if (form[i].value.length == 0) cansubmit = false;
	     //   }
	
	      //  if (cansubmit) {
	    	  document.getElementById("submit").disabled = false;
	          document.getElementById("done").style.display = 'block';
	          document.getElementById("poke").style.display = 'none';
		}
		
		</script>
		<script>
		function urlFetch() {
			var texto = prompt("Escolha um Pokemon para gerar com 4 golpes aleatórios" + 
					" que ele pode aprender. Se você quiser gerar uma versão com cor " +
					"diferente, clique a checkbox shiny. Espere os dados aparecerem e então clique criar.", "Pikachu").toLowerCase();
	    	if (texto != null) {
			    //texto = escape(texto);
			    //var dest = "https://api.funtranslations.com/translate/leetspeak.json?text=";
			    var dest = "http://pokeapi.salestock.net/api/v2/pokemon/"
			    var url = dest.concat(texto);
			    var submit = false;
			    document.getElementById("poke").style.display = 'block';
			    document.getElementById("done").style.display = 'none';
			    //
			    fetch(url) // Call the fetch function passing the url of the API as a parameter
					.then(function(response) {
						if (response.status !== 200) {
					        console.log('Looks like there was a problem. Status Code: ' +
					                response.status);
					              return;
					    }
						//console.log(response.json());
						return response.json();
					})
					.then(function(data) {
						document.getElementById("move1").value = data.moves[Math.floor(Math.random() * (40))].move.name;
						document.getElementById("move2").value = data.moves[Math.floor(Math.random() * (40))].move.name;
						document.getElementById("move3").value = data.moves[Math.floor(Math.random() * (40))].move.name;
						document.getElementById("move4").value = data.moves[Math.floor(Math.random() * (40))].move.name;
						document.getElementById("pokename").value = data.name;
						document.getElementById("evolutions").value = data.forms.length.toString();
						if (document.getElementById('shiny').checked == true) {
							document.getElementById("sprite").value = data.sprites.front_shiny;
						}
						else {
							document.getElementById("sprite").value = data.sprites.front_default;
						}
							
						//console.log(data.sprites.front_shiny)
						//JSONstring = JSON.stringify(data);
					})
					.catch(function(erro) {
						document.getElementById("erro").innerHTML = erro;
					});
			    //var json = $http.get(url)
			
			    //document.getElementById("demo").innerHTML = json;
			}
		}
	</script>
	
	<script>
		function urlFetchLeet(x) {
			var content = document.getElementById(x).value;
	    	if (content != null) {
			    content = escape(content);
			    content = content.replace(/%0A/g,'');
			    
			    var dest = "https://api.funtranslations.com/translate/leetspeak.json?text=";
			    
			    var url = dest.concat(content);
			    //
			    fetch(url) // Call the fetch function passing the url of the API as a parameter
					.then(function(response) {
						if (response.status !== 200) {
					        console.log('Looks like there was a problem. Status Code: ' +
					                response.status);
					              return;
					    }
						//console.log(response.json());
						return response.json();
					})
					.then(function(data) {
						document.getElementById(x).value = data.contents.translated;	
						//console.log(data.sprites.front_shiny)
						//JSONstring = JSON.stringify(data);
					})
					.catch(function(erro) {
						document.getElementById("erro").innerHTML = erro;
					});
			    //var json = $http.get(url)
			    //document.getElementById("demo").innerHTML = json;
			}
		}
	</script>
	
	

</body>
</html>
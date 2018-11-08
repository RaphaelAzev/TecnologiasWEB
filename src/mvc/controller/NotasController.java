package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Nota;
import mvc.model.NotasDAO;
import mvc.model.Poke;

@Controller
public class NotasController {
	@RequestMapping("/")
	public String execute() {
		return "index";
	}

	@RequestMapping("criaTarefa")
	public String form() {
		return "formTarefa";
	}

	@RequestMapping("adicionaNota")
	public String adiciona(Nota nota) {
		NotasDAO dao = new NotasDAO();
		dao.adicionaConteudo(nota);
		return "index";
	}

	@RequestMapping("alteraNota")
	public String altera(Nota nota) {
		NotasDAO dao = new NotasDAO();
		if (nota.getDelete() == null) {
			dao.altera(nota);
		} else {
			dao.remove(nota);
		}
		return "index";
	}
	
	@RequestMapping("adicionaPoke")
	public String addPoke(Poke poke) {
		NotasDAO dao = new NotasDAO();
		dao.adicionaPoke(poke);
		
		return "index";
		
	}
}
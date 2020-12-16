package br.com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.todo.model.Categoria;
import br.com.todo.model.CategoriaRepo;
import br.com.todo.model.Tarefa;
import br.com.todo.model.TarefaRepo;

@Controller
public class CategoriaController {
	
	@Autowired
	private CategoriaRepo categoriaRepo;
	
	@Autowired
	TarefaRepo tarefaRepo;
	
	@PostMapping("/categoria/save")
	public String salvar(@ModelAttribute Categoria categoria) {
		categoriaRepo.save(categoria);
		
		return "redirect:/categoria/list";
	}
	
	@GetMapping("/categoria/list")
	public String listar(Model model) {
		List<Categoria> lista = categoriaRepo.findAll();
		
		model.addAttribute("lista", lista);
		
		return "categorias";
	}
	
	@GetMapping("/categoria/delete/{id}")
	public String excluir(@PathVariable Integer id) {
		
		if(verificarRelacionamento(id)) {
			categoriaRepo.deleteById(id);
		}
		
		return "redirect:/categoria/list";
	}
	
	@GetMapping("/categoria/update/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Categoria categoria = categoriaRepo.findById(id).get();
		
		model.addAttribute("categoria", categoria);
		
		return "categoriaform";
	}
	
	public boolean verificarRelacionamento(Integer id) {
		List<Tarefa> lista = tarefaRepo.findAll();
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCategoria().getId() == id) {
				return false;
			}
		}
		
		return true;
	}
	
}

package br.com.todo.controller;

import java.util.ArrayList;
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
public class TarefaController {
	@Autowired
	TarefaRepo tarefaRepo;
	
	@Autowired
	CategoriaRepo categoriaRepo;
	
	@PostMapping("/tarefa/save")
	public String salvar(@ModelAttribute Tarefa tarefa) {
		tarefaRepo.save(tarefa);
		
		return "redirect:/tarefa/list";
	}
	
	@GetMapping("/tarefa/list")
	public String exibirTarefas(Model model) {
		List<Tarefa> lista = tarefaRepo.findAll();
	
		model.addAttribute("lista", lista);
		return "tarefas";
	}
	
	@GetMapping("/tarefasrealizadas")
	public String exibirTarefasRealizadas(Model model) {
		List<Tarefa> lista = tarefaRepo.findAll();
		List<Tarefa> listaFiltrada = new ArrayList<Tarefa>();
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getStatus() == 'R') {
				listaFiltrada.add(lista.get(i));
			}
		}
	
		model.addAttribute("lista", listaFiltrada);
		return "tarefasfinalizadas";
	}
	
	@GetMapping("/tarefa/delete/{id}")
	public String excluir(@PathVariable Integer id) {
		tarefaRepo.deleteById(id);
		
		return "redirect:/tarefa/list";
	}
	
	@GetMapping("/tarefa/update/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Tarefa tarefa = tarefaRepo.findById(id).get();
		
		model.addAttribute("categorias", getCategorias());
		
		model.addAttribute("tarefa", tarefa);
		
		return "tarefaform";
	}
	
	@GetMapping("/tarefa/update/status/{id}")
	public String editar(@PathVariable Integer id) {
		Tarefa tarefa = tarefaRepo.findById(id).get();
		tarefa.setStatus('R');
		
		tarefaRepo.save(tarefa);
		
		return "redirect:/tarefa/list";
	}
	
	private List<Categoria> getCategorias() {
		List<Categoria> categorias = categoriaRepo.findAll();
		
		return categorias;
	}
	
}

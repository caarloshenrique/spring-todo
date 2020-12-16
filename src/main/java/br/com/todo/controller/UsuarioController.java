package br.com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.todo.model.Tarefa;
import br.com.todo.model.TarefaRepo;
import br.com.todo.model.Usuario;
import br.com.todo.model.UsuarioRepo;

@Controller
public class UsuarioController {
	@Autowired
	UsuarioRepo usuarioRepo;
	
	@Autowired
	TarefaRepo tarefaRepo;

	@PostMapping("/usuario/save")
	public String salvar(@ModelAttribute Usuario usuario) {
		usuarioRepo.save(usuario);
		
		return "redirect:/";
	}
	
	@PostMapping("/usuario/update")
	public String editar(@ModelAttribute Usuario usuario) {
		
		usuarioRepo.save(usuario);
		
		return "perfil";
	}
	
	@GetMapping("/usuario/delete/{username}")
	public String excluir(@PathVariable String username) {
		deletarTarefasDoUsuario(username);
		
		usuarioRepo.deleteById(username);
		
		return "redirect:/";
	}
	
	
	public void deletarTarefasDoUsuario(String username) {
		List<Tarefa> lista = tarefaRepo.findAll();
		
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getUsuario().getUsername().equals(username)) {
				tarefaRepo.deleteById(lista.get(i).getId());
			}
		}
	}
	
}

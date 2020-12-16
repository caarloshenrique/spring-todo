package br.com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.todo.model.Categoria;
import br.com.todo.model.CategoriaRepo;
import br.com.todo.model.Tarefa;
import br.com.todo.model.Usuario;
import br.com.todo.model.UsuarioRepo;

@Controller
public class SistemaController {
	
	@Autowired
	private CategoriaRepo categoriaRepo;
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	private Usuario usuarioLogado;
	
	@GetMapping("/home")
	public String exibirTelaHome() {
		return "home";
	}

	@GetMapping("/")
	public String exibirTelaSignin(Model model) {
		Usuario usuario = new Usuario();
		
		model.addAttribute("usuario", usuario);
		
		return "signin";
	}
	
	@GetMapping("/usuario/perfil")
	public String exibirTelaSignup(Model model) {
		
		Usuario authUser = usuarioRepo.findById(usuarioLogado.getUsername()).get();
		
		instanciarUsuarioLogado(authUser);
		
		model.addAttribute("usuario", usuarioLogado);
		
		return "perfil";
	}
	
	@GetMapping("/novacategoria")
	public String exibirTelaNovaCategoria(Model model) {
		Categoria categoria = new Categoria();
		
		model.addAttribute("categoria", categoria);
		
		return "categoriaform";
	}
	
	@GetMapping("/novatarefa")
	public String exibirTelaNovaTarefa(Model model) {
		Tarefa tarefa = new Tarefa();
		tarefa.setUsuario(usuarioLogado);
		
		model.addAttribute("tarefa", tarefa);
		
		model.addAttribute("categorias", getCategorias());
		
		return "tarefaform";
	}
	
	@GetMapping("/signup")
	public String exibirTelaNovoUsuario(Model model) {
		Usuario usuario = new Usuario();
		
		model.addAttribute("usuario", usuario);
		
		return "signup";
	}
	
	private List<Categoria> getCategorias() {
		List<Categoria> categorias = categoriaRepo.findAll();
		
		return categorias;
	}
	
	public void instanciarUsuarioLogado(Usuario usuario) {
		usuarioLogado = new Usuario();
		usuarioLogado.setUsername(usuario.getUsername());
		usuarioLogado.setNome(usuario.getNome());
		usuarioLogado.setPassword(usuario.getPassword());
		usuarioLogado.setSexo(usuario.getSexo());
	}
	
	public boolean verificarCredenciais(String passwordInput, String passwordData) {
		if (passwordInput.equals(passwordData)) {
			return true;
		} else {
			return false;
		}
	}

	@PostMapping("/signin")
	public String signin(@ModelAttribute Usuario usuario) {
		Usuario authUser = usuarioRepo.findById(usuario.getUsername()).get();
		
		String passwordInput = authUser.getPassword();
		String passwordData = usuario.getPassword();
		
		if (verificarCredenciais(passwordInput, passwordData)) {
			instanciarUsuarioLogado(usuario);
			
			return "redirect:/home";
		} else {
			return "redirect:/";
		}
		
	}

}

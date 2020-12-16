package br.com.todo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepo extends JpaRepository<Tarefa, Integer> {

}

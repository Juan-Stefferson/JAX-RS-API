package br.com.treinaweb.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.treinaweb.modal.Pessoa;

public class PessoaRepository {

	private final static HashMap<Integer, Pessoa> pessoas = new HashMap<>();

	// listar todos as pessas da classe modal
	public List<Pessoa> GetAll() {
		return new ArrayList<Pessoa>(pessoas.values());
	}

	// lista uma pessoa por id
	public Pessoa GetById(final int id) {
		return pessoas.get(id);
	}

	// adicionar pessoa
	public void AddPessoa(final Pessoa pessoa) {
		if (pessoa.getId() == 0)
			pessoa.setId(generateId(pessoas.size() + 1));
		pessoas.put(pessoa.getId(), pessoa);
	}

	// Editar pessoa selecionada
	public void EditarPessoa(final Pessoa pessoa) {
		pessoas.remove(pessoa.getId());
		pessoas.put(pessoa.getId(), pessoa);

	}

	// Deletar pessoa por id
	public void DeletarPessoa(final int id) {
		pessoas.remove(id);
	}

	// incrementa os ids(mesma função do @Generate.Values do spring)
	private int generateId(final int possible) {
		if (pessoas.containsKey(possible))
			return generateId(possible + 1);
		return possible;
	}
}

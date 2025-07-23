package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.Pessoa;
import models.Problema;
import models.Status;

public class Pessoas extends Controller {

	public static void form() {
		List<Problema> problemas = Problema.findAll();
		render(problemas);
	}

	public static void detalhar(Pessoa pessoa) {
		render(pessoa);
	}

	public static void listar(String termo) {
		List<Pessoa> pessoas = null;
		if (termo == null) {
			pessoas = Pessoa.find("status = ?1", Status.PENDENTE).fetch();
		} else {
			pessoas = Pessoa.find("(lower(nome) like ?1 " + 
			"or lower(email) like ?1) and status = ?2",
			"%" + termo.toLowerCase() + "%",
			Status.PENDENTE).fetch();
		}
		render(pessoas, termo);
	}

	public static void editar(Long id) {
		Pessoa p = Pessoa.findById(id);
		List<Problema> problemas = Problema.findAll();

		renderTemplate("Pessoas/form.html", p, problemas);
	}

	public static void salvar(Pessoa pessoa) {
		if (pessoa.nome != null) {
			pessoa.nome = pessoa.nome.toUpperCase();
		}
		if (pessoa.email != null) {
			pessoa.email = pessoa.email.toLowerCase();
		}
		if (pessoa.tel != null) {
			pessoa.tel = pessoa.tel;
		}
		pessoa.save();
		detalhar(pessoa);
	}

	public static void remover(Long id) {
		Pessoa pessoa = Pessoa.findById(id);
		pessoa.status = Status.RESOLVIDO;
		pessoa.save();
		listar(null);
	}

}

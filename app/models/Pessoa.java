package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;
import javax.persistence.ManyToOne;

@Entity
public class Pessoa extends Model {

	public String nome;
	public String email;
	public String tel;

	@ManyToOne
	public Problema problema;

	@Enumerated(EnumType.STRING)
	public Status status;

	public Pessoa() {
		this.status = Status.PENDENTE;
	}
}

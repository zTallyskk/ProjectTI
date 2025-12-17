package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import play.data.validation.Match;
import play.data.validation.Required;
import play.db.jpa.Model;
import net.sf.oval.constraint.Email;
import javax.persistence.CascadeType;

@Entity
public class Pessoa extends Model {

	@Required(message = "O campo Nome é obrigatório.")
	public String nome;
	
	@Required(message = "O campo E-mail é obrigatório.")
    @Email(message = "O formato do e-mail é inválido. Ex: nome@dominio.com")
	public String email;
	
	@Required(message = "O campo Telefone (com DDD) é obrigatório.")
	// Regex para formatos como (84) 99999-9999 ou 84999999999
	@Match(value="^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$", message="Formato de telefone inválido. Ex: (84) 98888-7777")
	public String tel; // Alterado de Integer para String
	@Enumerated(EnumType.STRING)
	public Perfil perfil;
	
	@OneToOne(cascade = CascadeType.ALL)
	public Usuario usuario;
	
	public String textoProblem;
	
	@ManyToOne
	public Problema problema;

	@Enumerated(EnumType.STRING)
	public Status status;

	public Pessoa() {
		this.status = Status.PENDENTE;
		this.perfil = Perfil.PESSOA;
	}
}
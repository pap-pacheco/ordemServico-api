package br.com.workservices.ordemServico.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<OrdemServico> list = new ArrayList<>();

	public List<OrdemServico> getList() {
		return list;
	}

	public void setList(List<OrdemServico> list) {
		this.list = list;
	}

	public Tecnico() {
		super();
	}

	public Tecnico(Long id, String nome, @CPF String cpf,
			LocalDateTime dataNasc, String endereco, String cidade, String bairro,
			String cep, String telefone, String celular, String email) {
		super(id, nome, cpf, dataNasc, endereco, cidade, bairro, cep, telefone, celular, email);
	}

	public Tecnico(Long id, String nome, @CPF String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

}

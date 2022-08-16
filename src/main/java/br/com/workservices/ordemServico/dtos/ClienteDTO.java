package br.com.workservices.ordemServico.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.workservices.ordemServico.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "O campo NOME é requerido!") // UTILIZANDO A ANOTAÇÃO @Valid NO RESOURCE
	private String nome;

	@CPF
	@NotEmpty(message = "O campo CPF é requerido!")
	private String cpf;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataNasc;

	private String endereco;
	private String cidade;
	private String bairro;
	private String cep;

	@NotEmpty(message = "O campo TELEFONE é requerido!")
	private String telefone;
	private String celular;
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDateTime dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.dataNasc = obj.getDataNasc();
		this.endereco = obj.getEndereco();
		this.cidade = obj.getCidade();
		this.bairro = obj.getBairro();
		this.cep = obj.getCep();
		this.telefone = obj.getTelefone();
		this.celular = obj.getCelular();
		this.email = obj.getEmail();
	}

}

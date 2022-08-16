package br.com.workservices.ordemServico.resources.exception;

import java.util.ArrayList;
import java.util.List;

//PRECISA POSSUIR UM MANIPULADOR DE EXCEÇÕES NO ResourceExceptionHandler

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> erros = new ArrayList<>();

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addErros(String fieldName, String message) {
		this.erros.add(new FieldMessage(fieldName, message));
	}

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

}

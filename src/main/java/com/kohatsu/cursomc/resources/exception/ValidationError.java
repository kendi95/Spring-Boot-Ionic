package com.kohatsu.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> erros = new ArrayList<>();

	public ValidationError(Integer status, String message, Long timeStamp) {
		super(status, message, timeStamp);
		// TODO Auto-generated constructor stub
	}

	public List<FieldMessage> getErros() {
		return erros;
	}

	public void addError(String fieldName, String message) {
		
		erros.add(new FieldMessage(fieldName, message));
		
	}

	
	
	

}

package com.kevinvolanski.cursomc.resources.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long timestamp;	
	private Integer status;
	private String error;
	private String message;
	private String path;

}

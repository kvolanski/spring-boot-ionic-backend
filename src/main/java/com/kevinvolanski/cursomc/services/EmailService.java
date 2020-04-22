package com.kevinvolanski.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.kevinvolanski.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
}

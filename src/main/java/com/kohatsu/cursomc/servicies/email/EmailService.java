package com.kohatsu.cursomc.servicies.email;

import org.springframework.mail.SimpleMailMessage;

import com.kohatsu.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}

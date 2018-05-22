package com.kohatsu.cursomc.servicies;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.cursomc.domain.Pedido;
import com.kohatsu.cursomc.repositories.PedidoRepository;
import com.kohatsu.cursomc.servicies.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Pedido.class.getName()));
		
	}
	
}

package com.kohatsu.cursomc.servicies;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.cursomc.domain.Categoria;
import com.kohatsu.cursomc.repositories.CategoriaRepository;
import com.kohatsu.cursomc.servicies.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado: Id: "+id+", Tipo: "+Categoria.class.getName()));
		
	}
	
}

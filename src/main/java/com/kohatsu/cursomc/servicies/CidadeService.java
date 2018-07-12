package com.kohatsu.cursomc.servicies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kohatsu.cursomc.domain.Cidade;
import com.kohatsu.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado(Integer estado_id){
		
		return repo.findCidades(estado_id);
		
	}
	
}

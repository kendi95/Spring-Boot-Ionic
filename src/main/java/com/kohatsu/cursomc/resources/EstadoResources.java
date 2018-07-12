package com.kohatsu.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kohatsu.cursomc.domain.Cidade;
import com.kohatsu.cursomc.domain.Estado;
import com.kohatsu.cursomc.dto.CidadeDTO;
import com.kohatsu.cursomc.dto.EstadoDTO;
import com.kohatsu.cursomc.repositories.CidadeRepository;
import com.kohatsu.cursomc.servicies.CidadeService;
import com.kohatsu.cursomc.servicies.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResources {

	@Autowired
	private EstadoService estadoService;
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		
		List<Estado> list = estadoService.findAll();
		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
	@RequestMapping(value="/{id}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable("id") Integer id){
		
		List<Cidade> list = cidadeService.findByEstado(id);
		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
}

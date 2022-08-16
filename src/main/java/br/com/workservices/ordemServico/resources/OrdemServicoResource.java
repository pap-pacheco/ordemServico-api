package br.com.workservices.ordemServico.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.workservices.ordemServico.dtos.OrdemServicoDTO;
import br.com.workservices.ordemServico.services.OrdemServicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/ordens")
public class OrdemServicoResource {

	@Autowired
	private OrdemServicoService ordemServicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Long id) {
		OrdemServicoDTO obj = new OrdemServicoDTO(ordemServicoService.findById(id));
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll() {
		List<OrdemServicoDTO> list = ordemServicoService.findAll().stream().map(obj -> new OrdemServicoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoDTO obj) {
		obj = new OrdemServicoDTO(ordemServicoService.create(obj));

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping
	public ResponseEntity<OrdemServicoDTO> update(@Valid @RequestBody OrdemServicoDTO obj) {
		obj = new OrdemServicoDTO(ordemServicoService.update(obj));

		return ResponseEntity.ok().body(obj);
	}

//	@DeleteMapping
//	public ResponseEntity<Void> delete(@PathVariable Long id){
//		ordemServicoService.delete(id);
//		
//		return ResponseEntity.noContent().build();
//	}
	
}

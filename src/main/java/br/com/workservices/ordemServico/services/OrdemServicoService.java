package br.com.workservices.ordemServico.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workservices.ordemServico.domain.Cliente;
import br.com.workservices.ordemServico.domain.OrdemServico;
import br.com.workservices.ordemServico.domain.Tecnico;
import br.com.workservices.ordemServico.domain.enuns.Prioridade;
import br.com.workservices.ordemServico.domain.enuns.Status;
import br.com.workservices.ordemServico.dtos.OrdemServicoDTO;
import br.com.workservices.ordemServico.repositories.OrdemServicoRepository;
import br.com.workservices.ordemServico.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OrdemServico findById(Long id) {
		Optional<OrdemServico> obj = ordemServicoRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
	}

	public List<OrdemServico> findAll() {
		return ordemServicoRepository.findAll();
	}

	public OrdemServico create(@Valid OrdemServicoDTO obj) {
		return fromDTO(obj);
	}

	public OrdemServico update(@Valid OrdemServicoDTO obj) {
		findById(obj.getId());

		return fromDTO(obj);
	}

	private OrdemServico fromDTO(OrdemServicoDTO obj) {
		OrdemServico newObj = new OrdemServico();

		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		newObj.setDescProblema(obj.getDescProblema());

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		newObj.setTecnico(tec);

		Cliente cli = clienteService.findById(obj.getCliente());
		newObj.setCliente(cli);

		if (newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}

		return ordemServicoRepository.save(newObj);
	}

//	public void delete(Long id) {
//		OrdemServico obj = findById(id);
//
//		if (obj != null) {
//			ordemServicoRepository.delete(obj);
//		}
//	}

}

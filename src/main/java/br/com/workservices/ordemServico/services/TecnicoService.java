package br.com.workservices.ordemServico.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workservices.ordemServico.domain.Pessoa;
import br.com.workservices.ordemServico.domain.Tecnico;
import br.com.workservices.ordemServico.dtos.TecnicoDTO;
import br.com.workservices.ordemServico.repositories.PessoaRepository;
import br.com.workservices.ordemServico.repositories.TecnicoRepository;
import br.com.workservices.ordemServico.services.exceptions.DataIntegratyViolationException;
import br.com.workservices.ordemServico.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * BUSCA TECNICO PELO ID
	 */
	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = tecnicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	/*
	 * BUSCA LISTA DE TECNICOS
	 */
	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	/*
	 * CRIANDO TECNICO
	 */
	public Tecnico create(TecnicoDTO objDTO) {
//		Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
//		return repository.save(newObj);

		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return tecnicoRepository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	/*
	 * ATUALIZAR TECNICO PELO ID
	 */
	public Tecnico update(Long id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return tecnicoRepository.save(oldObj);
	}

	/*
	 * DELETAR TECNICO PELO ID
	 */
	public void delete(Long id) {
		Tecnico obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado!");
		}

		if (obj != null) {
			tecnicoRepository.delete(obj);
		}

	}

	/*
	 * BUSCA PESSOA PELO CPF
	 */
//	private Tecnico findByCPF(TecnicoDTO objDTO) {
//		Tecnico obj = tecnicoRepository.findByCPF(objDTO.getCpf());
//		if(obj != null) {
//			return obj;
//		}
//		return null;
//	}
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}

package br.com.workservices.ordemServico.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workservices.ordemServico.domain.Cliente;
import br.com.workservices.ordemServico.domain.Pessoa;
import br.com.workservices.ordemServico.dtos.ClienteDTO;
import br.com.workservices.ordemServico.repositories.ClienteRepository;
import br.com.workservices.ordemServico.repositories.PessoaRepository;
import br.com.workservices.ordemServico.services.exceptions.DataIntegratyViolationException;
import br.com.workservices.ordemServico.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * BUSCA TECNICO PELO ID
	 */
	public Cliente findById(Long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	/*
	 * BUSCA LISTA DE TECNICOS
	 */
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	/*
	 * CRIANDO TECNICO
	 */
	public Cliente create(ClienteDTO objDTO) {

		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return clienteRepository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	/*
	 * ATUALIZAR TECNICO PELO ID
	 */
	public Cliente update(Long id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return clienteRepository.save(oldObj);
	}

	/*
	 * DELETAR TECNICO PELO ID
	 */
	public void delete(Long id) {
		Cliente obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Ordens de Serviço, não pode ser deletado!");
		}

		if (obj != null) {
			clienteRepository.delete(obj);
		}

	}

	/*
	 * BUSCA PESSOA PELO CPF
	 */
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}

package br.com.workservices.ordemServico.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.workservices.ordemServico.domain.Cliente;
import br.com.workservices.ordemServico.domain.OrdemServico;
import br.com.workservices.ordemServico.domain.Tecnico;
import br.com.workservices.ordemServico.domain.enuns.Prioridade;
import br.com.workservices.ordemServico.domain.enuns.Status;
import br.com.workservices.ordemServico.repositories.ClienteRepository;
import br.com.workservices.ordemServico.repositories.OrdemServicoRepository;
import br.com.workservices.ordemServico.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public void instaciaDB() {
		Tecnico t1 = new Tecnico(null, "Valdir", "144.785.300-84", "(11)91234-5678");
		Tecnico t2 = new Tecnico(null, "Linus", "641.760.040-88", "(11)91234-5566");
		Cliente c1 = new Cliente(null, "Betina", "598.508.200-80", "(11)91234-5690");
		OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Observacoes", "Teste create OS", Status.ANDAMENTO,
				t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));
	}

}

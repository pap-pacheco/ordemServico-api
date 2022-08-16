package br.com.workservices.ordemServico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.workservices.ordemServico.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("SELECT obj FROM Cliente obj WHERE obj.cpf = :cpf")
	Cliente findByCPF(@Param("cpf") String cpf);
	
}

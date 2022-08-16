package br.com.workservices.ordemServico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.workservices.ordemServico.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf = :cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);
	
}

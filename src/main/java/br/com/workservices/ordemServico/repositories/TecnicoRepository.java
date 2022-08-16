package br.com.workservices.ordemServico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.workservices.ordemServico.domain.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long>{

	@Query("SELECT obj FROM Tecnico obj WHERE obj.cpf = :cpf")
	Tecnico findByCPF(@Param("cpf") String cpf);

}

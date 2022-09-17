package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{
	@Query("select c from Medicamento c where"+ "( :p_nom is '' or c.nombre like :p_nom) ")
public List<Medicamento> findByNombre(@Param("p_nom")String nom);
}

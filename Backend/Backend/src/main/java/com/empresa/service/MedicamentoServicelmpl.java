package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Medicamento;
import com.empresa.repository.MedicamentoRepository;

@Service
public class MedicamentoServicelmpl implements MedicamentoService {

@Autowired
private MedicamentoRepository repository;
	
@Override
public List<Medicamento> listaTodos() {
	return repository.findAll();
}

@Override
public Medicamento insertaActualizaMedicamento(Medicamento obj) {
	return repository.save(obj);
}

@Override
public void Delete(int id) {
	repository.deleteById(id);
}

@Override
public List<Medicamento> listaPorNombres(String nombre) {
	return repository.findByNombre(nombre);
}

}

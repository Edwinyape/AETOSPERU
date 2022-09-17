package com.empresa.service;

import java.util.List;


import com.empresa.entity.Medicamento;

public interface MedicamentoService {
		public abstract List<Medicamento> listaTodos();
		public abstract List<Medicamento> listaPorNombres(String nombre);
		public abstract Medicamento insertaActualizaMedicamento(Medicamento obj);
		void Delete(int id);
}

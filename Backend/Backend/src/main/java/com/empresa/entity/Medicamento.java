package com.empresa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicamento")
public class Medicamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMedicamento;
	private String nombre;
	private int precio;
	private int Stock;
	private String laboratorio;
	
	public int getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(int idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	
		
	
	
}

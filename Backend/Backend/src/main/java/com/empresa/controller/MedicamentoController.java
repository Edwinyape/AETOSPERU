package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;
import com.empresa.util.Constantes;

@RestController
@RequestMapping("/rest/medicamento")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {
	private Logger log = LoggerFactory.getLogger(MedicamentoController.class);
	
	@Autowired
	private MedicamentoService medicamentoService ;
	
   //Listado
	@GetMapping("/listaMedicamentoPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamentoPorNombreLike(@PathVariable("nom") String nom) {
		log.info("==> listaMedicamentoPorNombreLike ==> nom : " + nom);
		List<Medicamento> lista = null;
		
		try {
			if(nom.equals("todo")) {
				lista = medicamentoService.listaTodos();				
			}else if(nom.length()>0) {
				lista = medicamentoService.listaPorNombres(nom + "%");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}

	//Registra 
	@PostMapping("/registrarMedicamento")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaMedicamento(@RequestBody Medicamento obj) {
		log.info("==> insertaMedicamento ==> ID : " + obj.getIdMedicamento());
		log.info("==> insertaMedicamento ==> Nombres : " + obj.getNombre());
		
		Map<String,Object> salida = new HashMap<>();
		List<Medicamento> lista=null;
		
		try {
			lista = medicamentoService.listaPorNombres(obj.getNombre());
			
			if(obj.getIdMedicamento()!= 0) {
				salida.put("mensaje", "El Id no debe ser cero");
				return ResponseEntity.ok(salida);
			}
			
			if(!CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "El nombre ya existe" +   obj.getNombre());
				return ResponseEntity.ok(salida);
			}
			
			Medicamento objSalida = medicamentoService.insertaActualizaMedicamento(obj);
			if(objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		}catch(Exception e){
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		
		return ResponseEntity.ok(salida);
	}
    
	//Actualizar
	@PutMapping("/actualizaMedicamento")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaMedicamento(@RequestBody Medicamento obj) {
		log.info("==> actualizaMedicamneto   ==> ID : " + obj.getIdMedicamento());
		log.info("==> actualizaMedicamneto   ==> Nombre : " + obj.getNombre());
		log.info("==> actualizaMedicamneto   ==> Precio : " + obj.getPrecio());
		log.info("==> actualizaMedicamneto   ==> Stock : " + obj.getStock());
		log.info("==> actualizaMedicamneto   ==> Laboratorio : " + obj.getLaboratorio());
		Map<String, Object> salida = new HashMap<>();
		try {
			if (obj.getIdMedicamento() == 0) {
				salida.put("mensaje", "El ID debe ser diferente cero");
				return ResponseEntity.ok(salida);
			}
			Medicamento objSalida = medicamentoService.insertaActualizaMedicamento(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
			} else {
				salida.put("mensaje", Constantes.MENSAJE_ACT_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_ACT_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	//Eliminar
	 @DeleteMapping("/deleteMedicamento/{id}")
	 @ResponseBody
	    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id")int id){

	       Map<String, Object> salida = new HashMap<>();
			try {
				medicamentoService.Delete(id);
				salida.put("mensaje", Constantes.MENSAJE_ELI_EXITOSO);
				
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", Constantes.MENSAJE_ELI_NO_EXISTE_ID);
			}
			return ResponseEntity.ok(salida);

	    }
}

package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.entidades.BinTitulos;
import com.funciones.BuscaTitulos;
import com.service.TitulosService;
import com.util.CustomErrorType;

@Controller
@RequestMapping(value="/v1")
public class TitulosController {
	
	@Autowired
	private TitulosService _titulosService;
	private  Scanner input;
	
	//CREATE
	@RequestMapping(value = "/titulos", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> InsertarTitulo(@RequestBody BinTitulos titulo, UriComponentsBuilder ucBuilder) {
		
		_titulosService.InsertaNuevo(titulo);	 
	    HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/v1/titulos/").buildAndExpand(titulo.getIdreg_cat_titulos()).toUri());
	    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
	//GET ALL
	@RequestMapping(value = "/titulos", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<BinTitulos>> obtenerTitulos(){
			
		List<BinTitulos> titulos = new ArrayList<BinTitulos>();		
		titulos  = _titulosService.BuscaTodosTitulos();
		return new ResponseEntity<List<BinTitulos>>(titulos, HttpStatus.OK);		
	}
	
	
	//GET BY ID
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/titulos/{idreg_cat_titulos}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<BinTitulos> ObtenerTituloID(@PathVariable("idreg_cat_titulos") int idreg_cat_titulos){
		BinTitulos titulo = _titulosService.BuscarID(idreg_cat_titulos); //@requestboby
	    
		if (titulo == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
		return new ResponseEntity<BinTitulos>(titulo, HttpStatus.OK);
	}
	
	
	//GET BY TITULOS
	@RequestMapping(value = "/listaTitulos/{tituloOriginal}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<BinTitulos>> ListarTitulos(@PathVariable("tituloOriginal") String tituloOriginal){
		
		//BuscaTitulos buscar = new BuscaTitulos();
		tituloOriginal = _titulosService.capturarTitulo();			
		String palabraL = _titulosService.procesarCadena(tituloOriginal);
		
		List<BinTitulos> listaTitulos = new ArrayList<BinTitulos>();			
		listaTitulos = _titulosService.BuscarTitulos(palabraL);		
		
		//si no encuntra titulos similares, se inseta uno nuevo
		if (listaTitulos == null || listaTitulos.isEmpty()) {
			_titulosService.GuardarTituloNuevo();
			
		}else {
			//muestra titulos similares
			_titulosService.titulosEncotrados(palabraL);
			
			//llama al metodo para modificar registro? 
			_titulosService.ActualizarTituloExistente();
		}
		    
		return new ResponseEntity<List <BinTitulos>>(listaTitulos, HttpStatus.OK);
	}
	
	
	//ACTUALIZAR
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/titulos/{idreg_cat_titulos}", method = RequestMethod.PATCH)
	public ResponseEntity<?> actualizarTitulo(@PathVariable("idreg_cat_titulos") int idreg_cat_titulos, @RequestBody BinTitulos titulo){
		
		//busca por id el titulo
		BinTitulos tituloActual = _titulosService.BuscarID(idreg_cat_titulos);
		
		if (tituloActual == null) {
			return new ResponseEntity(new CustomErrorType("No se puede actualizar los datos del TITULO Num:  " + idreg_cat_titulos),
                    HttpStatus.NOT_FOUND);
		}
		
		//actualizar campos de equipo
		tituloActual.setIdreg_cat_titulos(titulo.getIdreg_cat_titulos());
		tituloActual.setTitulo_original(titulo.getTitulo_original());
		tituloActual.setTitulo_en_espa(titulo.getTitulo_en_espa());
		tituloActual.setAnio(titulo.getAnio());
		tituloActual.setRealizador(titulo.getRealizador());
		tituloActual.setPais(titulo.getPais());
		tituloActual.setObservaciones_cat_titulos(titulo.getObservaciones_cat_titulos());
		tituloActual.setProductor(titulo.getProductor());
		tituloActual.setCirca(titulo.getCirca());
		tituloActual.setT_revisado(titulo.getT_revisado());
		tituloActual.setPosible_dup(titulo.getPosible_dup());
		
		_titulosService.ActualizarTitulo(titulo);
		return new ResponseEntity<BinTitulos>(tituloActual, HttpStatus.OK);			
	}
}

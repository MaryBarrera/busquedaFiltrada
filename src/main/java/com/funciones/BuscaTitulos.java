package com.funciones;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;

import com.controller.TitulosController;
import com.entidades.BinTitulos;
import com.service.TitulosService;

import java.util.ArrayList;
import java.util.List;

public class BuscaTitulos {
	
	//variables 
	
	
	@Autowired
	private TitulosService _titulosService;	
	
	
	//metodos
//	public String capturarTitulo(){
//		String titulo;
//		input = new Scanner(System.in);
//		
//		System.out.println("\nIngresar titulo original: ");
//		titulo = input.nextLine();
//		System.out.println("\nBuscar titulo: " + titulo);
//		return titulo;
//	}
//	
//	public String procesarCadena(String titulo) {
//		//convierte la cadena en arreglo y separa las palabras por espacio
//		String arrayTitulo[] = titulo.split(" "); 
//		//imprime cada palabra 
//		for (String temp: arrayTitulo) {
//			System.out.print("[" +temp+ "]");
//		}		
//		return contarPalabras(titulo);						
//	}
//
//	public String contarPalabras(String titulo) {		
//		String palabraL = "";
//		int caracPalabraL = 0;		
//		StringTokenizer strTitulo = new StringTokenizer (titulo);
//		
//		//obtener el numero de palabras del titulo
//		int numPalabras = strTitulo.countTokens(); 
//		System.out.println("\n\nEl titulo tiene: " + numPalabras + " palabras");
//		
//		for (int i=0; i<numPalabras; i++) {
//			String strPalabra = strTitulo.nextToken();
//			
//			if (strPalabra.length() > caracPalabraL) {
//				palabraL = strPalabra;
//				caracPalabraL = strPalabra.length();
//			}
//		}
//		System.out.println("Buscar por palabra más larga: " + palabraL + "\n");
//		
//		//devuelve le numero de palabras en una cadena
//		return palabraL;	
//	}
//		
//
//	public List<BinTitulos> titulosEncotrados(String palabraL ){
//		List<BinTitulos> listaTitulos = new ArrayList<BinTitulos>();			
//		listaTitulos = _titulosService.BuscarTitulos(palabraL);
//		
//		System.out.println("\nCoincidencias encontradas: ");
//		for(BinTitulos t : listaTitulos){
//			System.out.println(t.getTitulo_original());
//			System.out.println(t.getAnio());
//			System.out.println(t.getRealizador());		
//		}
//		return listaTitulos;
//	}
//	
//	public void GuardarTituloNuevo() {
//		String res = "N";
//		input = new Scanner(System.in);
//		
//		System.out.println("Titulo no encontrado, ¿desea guardarlo? S/N: ");		
//		res = input.nextLine(); //convierte char a string
//		char respuesta = res.charAt(0);
//		
//		if  (respuesta == 'S' || respuesta == 's') {
//						
//			BinTitulos titulo = new BinTitulos();	
//			
//			titulo.setIdreg_cat_titulos(0); 	
//			
//			System.out.println("Titulo Original: ");
//			String tituloOrig = input.nextLine();
//			titulo.setTitulo_original(tituloOrig);			
//		
//			System.out.println("Titulo español: ");
//			String tituloEsp = input.nextLine();
//			titulo.setTitulo_en_espa(tituloEsp);
//			
//			System.out.println("Año: "); 	
//			int anio = input.nextInt();
//			titulo.setAnio(anio);
//			
//			System.out.println("Realizador: "); 	
//			String realizador = input.nextLine();
//			titulo.setRealizador(realizador);
//			
//			System.out.println("Pais: "); 	
//			String pais = input.nextLine();
//			titulo.setPais(pais);
//			
//			System.out.println("Observaciones: "); 	
//			String Obs = input.nextLine();
//			titulo.setObservaciones_cat_titulos(Obs);
//			
//			System.out.println("Productor: "); 	
//			String produc = input.nextLine();
//			titulo.setProductor(produc);
//			
//			System.out.println("Circa: "); 	
//			byte circa = input.nextByte();
//			titulo.setCirca(circa);
//			
//			System.out.println("T revisado: "); 	
//			int t_revizado = input.nextInt();
//			titulo.setT_revisado(t_revizado);
//			
//			System.out.println("Posible dup: "); 	
//			int Pdup = input.nextInt();
//			titulo.setPosible_dup(Pdup);
//			
//			_titulosService.InsertaNuevo(titulo);
//			
//		}else {
//			System.out.println("Registro NO guardado: ");
//		}
//	}
//	
//	public void ActualizarTituloExistente() {
//		String res = "N";		
//		int idreg_cat_titulos = 0;		
//		input = new Scanner(System.in);
//		
//		System.out.println("¿Desea modificar registro? S/N: ");
//		res = input.nextLine();
//		char respuesta = res.charAt(0);
//		
//		if (respuesta == 'S' || respuesta == 's') {			
//			System.out.println("Ingresar ID titulo: ");
//			idreg_cat_titulos = input.nextInt();
//		}
//		
//		//busca por id el titulo
//		BinTitulos tituloModif = _titulosService.BuscarID(idreg_cat_titulos);
//		
//		tituloModif.setIdreg_cat_titulos(0); 	
//		
//		//actualizar campos de equipo
//		System.out.println("Titulo Original: "); 	
//		String titulo = input.nextLine();
//		tituloModif.setTitulo_original(titulo);	
//		
//		System.out.println("Titulo en espera: "); 	
//		String tituloEsp = input.nextLine();
//		tituloModif.setTitulo_en_espa(tituloEsp);
//		
//		System.out.println("Año: "); 	
//		int anio = input.nextInt();
//		tituloModif.setAnio(anio);
//		
//		System.out.println("Realizador: "); 	
//		String realizador = input.nextLine();
//		tituloModif.setRealizador(realizador);
//		
//		System.out.println("Pais: "); 	
//		String pais = input.nextLine();
//		tituloModif.setPais(pais);
//		
//		System.out.println("Observaciones: "); 	
//		String Obs = input.nextLine();
//		tituloModif.setObservaciones_cat_titulos(Obs);
//		
//		System.out.println("Productor: "); 	
//		String produc = input.nextLine();
//		tituloModif.setProductor(produc);
//		
//		System.out.println("Circa: "); 	
//		byte circa = input.nextByte();
//		tituloModif.setCirca(circa);
//		
//		System.out.println("T revizado: "); 	
//		int t_revizado = input.nextInt();
//		tituloModif.setT_revisado(t_revizado);
//		
//		System.out.println("Posible dup: "); 	
//		int Pdup = input.nextInt();
//		tituloModif.setPosible_dup(Pdup);
//
//		_titulosService.ActualizarTitulo(tituloModif);
//	}
}

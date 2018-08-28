package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TitulosDao;
import com.entidades.BinTitulos;

@Service("titulosService")
@Transactional
public class TitulosServiceImpl implements TitulosService {

	private  Scanner input;
	
	@Autowired
	private TitulosDao _titulosDao;
	
	@Override
	public void InsertaNuevo(BinTitulos titulo) {
		_titulosDao.InsertaNuevo(titulo);
	}

	@Override
	public List<BinTitulos> BuscaTodosTitulos() {
		return _titulosDao.BuscaTodosTitulos();
	}
	
	@Override
	public List<BinTitulos> MostrarSoloTitulos() {
		return _titulosDao.MostrarSoloTitulos();
	}

	@Override
	public List<BinTitulos> BuscarTitulos(String palabraClave) {
		return (List<BinTitulos>) _titulosDao.BuscarTitulos(palabraClave);
	}

	@Override
	public void ActualizarTitulo(BinTitulos titulo) {
		_titulosDao.ActualizarTitulo(titulo);		
	}

	@Override
	public BinTitulos BuscarID(int idreg_cat_titulos) {
		return _titulosDao.BuscarID(idreg_cat_titulos);
	}
	
	
	
	////////////////////////
	public String capturarTitulo(){
		String titulo;
		input = new Scanner(System.in);
		
		System.out.println("\nIngresar titulo original: ");
		titulo = input.nextLine();
		System.out.println("\nBuscar titulo: " + titulo);
		return titulo;
	}
	
	public String procesarCadena(String titulo) {
		//convierte la cadena en arreglo y separa las palabras por espacio
		String arrayTitulo[] = titulo.split(" "); 
		//imprime cada palabra 
		for (String temp: arrayTitulo) {
			System.out.print("[" +temp+ "]");
		}		
		return contarPalabras(titulo);						
	}

	public String contarPalabras(String titulo) {		
		String palabraL = "";
		int caracPalabraL = 0;		
		StringTokenizer strTitulo = new StringTokenizer (titulo);
		
		//obtener el numero de palabras del titulo
		int numPalabras = strTitulo.countTokens(); 
		System.out.println("\n\nEl titulo tiene: " + numPalabras + " palabras");
		
		for (int i=0; i<numPalabras; i++) {
			String strPalabra = strTitulo.nextToken();
			
			if (strPalabra.length() > caracPalabraL) {
				palabraL = strPalabra;
				caracPalabraL = strPalabra.length();
			}
		}
		System.out.println("Buscar por palabra más larga: " + palabraL + "\n");
		
		//devuelve le numero de palabras en una cadena
		return palabraL;	
	}
		

	public List<BinTitulos> titulosEncotrados(String palabraL ){
		List<BinTitulos> listaTitulos = new ArrayList<BinTitulos>();			
		listaTitulos = this.BuscarTitulos(palabraL);
		
		System.out.println("---------------Coincidencias encontradas-----------------\n");
		for(BinTitulos t : listaTitulos){
			System.out.println("ID: " + t.getIdreg_cat_titulos()
			+ "\nTitulo: " + t.getTitulo_original() 
			+ "\nAño: " + t.getAnio() 
			+ "\nRealizador: " +t.getRealizador() + "\n");		
		}
		return listaTitulos;
	}
	
	public void GuardarTituloNuevo() {
		String res = "N";
		input = new Scanner(System.in);
		
		System.out.println("Titulo no encontrado!, ¿desea guardarlo? S/N: ");		
		res = input.nextLine(); //convierte char a string
		char respuesta = res.charAt(0);
		
		if  (respuesta == 'S' || respuesta == 's') {
						
			BinTitulos titulo = new BinTitulos();	
			
			titulo.setIdreg_cat_titulos(0); 	
			
			System.out.println("Titulo Original: ");
			String tituloOrig = input.nextLine();
			titulo.setTitulo_original(tituloOrig);			
		
			System.out.println("Titulo español: ");
			String tituloEsp = input.nextLine();
			titulo.setTitulo_en_espa(tituloEsp);
			
			System.out.println("Año: "); 	
			int anio = input.nextInt();
			titulo.setAnio(anio);
			
			System.out.println("Realizador: "); 	
			String realizador = input.nextLine();
			titulo.setRealizador(realizador);
			
			System.out.println("Pais: "); 	
			String pais = input.nextLine();
			titulo.setPais(pais);
			
			System.out.println("Observaciones: "); 	
			String Obs = input.nextLine();
			titulo.setObservaciones_cat_titulos(Obs);
			
			System.out.println("Productor: "); 	
			String produc = input.nextLine();
			titulo.setProductor(produc);
			
			System.out.println("Circa: "); 	
			byte circa = input.nextByte();
			titulo.setCirca(circa);
			
			System.out.println("T revisado: "); 	
			int t_revizado = input.nextInt();
			titulo.setT_revisado(t_revizado);
			
			System.out.println("Posible dup: "); 	
			int Pdup = input.nextInt();
			titulo.setPosible_dup(Pdup);
			
			this.InsertaNuevo(titulo);
			System.out.println("Nuevo registro guardado!!!");
			
		}else if (respuesta == 'N' || respuesta == 'n') {
			System.out.println("Ok registro NO guardado: ");
			
		}else if (respuesta != 'N' || respuesta != 'n' || respuesta != 'S' || respuesta != 's' ){			
			System.out.println("Opcion no valida ");
			this.GuardarTituloNuevo();
		}
	}
	
	public void ActualizarTituloExistente() {
		String res = "N";		
		int idreg_cat_titulos = 0;		
		input = new Scanner(System.in);
		
		System.out.println("¿Desea modificar algún registro? S/N: ");
		res = input.nextLine();
		char respuesta = res.charAt(0);
		
		if (respuesta == 'S' || respuesta == 's') {			
			System.out.println("Ingresar ID titulo para modificar: ");
			idreg_cat_titulos = input.nextInt();
			
			//busca por id el titulo
			BinTitulos tituloModif = this.BuscarID(idreg_cat_titulos);
			
			//actualizar campos de equipo
			System.out.println("Titulo Original: "); 	
			String titulo = input.nextLine();
			tituloModif.setTitulo_original(titulo);	
			
			System.out.println("Titulo español: "); 	
			String tituloEsp = input.nextLine();
			tituloModif.setTitulo_en_espa(tituloEsp);
			
			System.out.println("Año: "); 	
			int anio = input.nextInt();
			tituloModif.setAnio(anio);
			
			System.out.println("Realizador: "); 	
			String realizador = input.nextLine();
			tituloModif.setRealizador(realizador);
			
			System.out.println("Pais: "); 	
			String pais = input.nextLine();
			tituloModif.setPais(pais);
			
			System.out.println("Observaciones: "); 	
			String Obs = input.nextLine();
			tituloModif.setObservaciones_cat_titulos(Obs);
			
			System.out.println("Productor: "); 	
			String produc = input.nextLine();
			tituloModif.setProductor(produc);
			
			System.out.println("Circa: "); 	
			byte circa = input.nextByte();
			tituloModif.setCirca(circa);
			
			System.out.println("T revisado: "); 	
			int t_revizado = input.nextInt();
			tituloModif.setT_revisado(t_revizado);
			
			System.out.println("Posible dup: "); 	
			int Pdup = input.nextInt();
			tituloModif.setPosible_dup(Pdup);

			this.ActualizarTitulo(tituloModif);
			System.out.println("Titulo Actualizado!!!"); 
			
		}else if (respuesta == 'N' || respuesta == 'n' ){			
			System.out.println("OK No se modifico nada"); 
			
		}else if (respuesta != 'N' || respuesta != 'n' || respuesta != 'S' || respuesta != 's' ){			
			System.out.println("Opcion no valida ");
			this.ActualizarTituloExistente();
		}
			 
				
	}
	////////////////////////
	
	
	////////////BUSQUEDA BINARIA
	public String buscar(String palabraL) {
		
		//intento de buscar un string dentro de un arrayList por medio de la busqueda binaria

		//crear un arrayList solo de la columna titulo_original
		ArrayList<BinTitulos> listaTitulosOriginales = new ArrayList();		
		listaTitulosOriginales = (ArrayList<BinTitulos>) this.MostrarSoloTitulos();		
		String titulo =  capturarTitulo();
		
		busquedaBin(listaTitulosOriginales, titulo); 
		return null;
		
	}	
	
	public String busquedaBin(ArrayList<BinTitulos> listaTitulosOriginales, String tituloBuscado) {
		
		// Este método devuelve como resultado la posicion del valor. Si el valor no se localiza, devuelve -1

		if (listaTitulosOriginales.size() == 0) {
			return "Titulo no encontrado"; //-1
		}

		int mitad, inferior = 0;
		int superior = (listaTitulosOriginales.size()) - 1;

		do {
			mitad = (int) Math.ceil(listaTitulosOriginales.size()/2); //calcula el indice medio de la lista
//			
//			if (tituloBuscado > listaTitulosOriginales.get(mitad) {  
//				inferior = mitad + 1;
//			} else {
//				superior = mitad - 1;
//			}
		} while (inferior <= superior); //listaTitulosOriginales.get(mitad) != tituloBuscado && inferior <= superior
//
//		if (listaTitulosOriginales.get(mitad) == tituloBuscado) {
//			return mitad;
//		} else {
//			String arregloTitulos[] = listaTitulosOriginales.toArray(new String[listaTitulosOriginales.size()]);
			return "No se encontro la palabra"; //-1
//		}

	}
	
}

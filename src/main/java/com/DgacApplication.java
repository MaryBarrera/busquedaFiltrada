package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.net.URL;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
public class DgacApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(DgacApplication.class, args);
		System.out.println("OK aplicacion corriendo !!! \n");	
		
		//URL url = new url ();
		//capturar y guardar el titulo 
//		BuscaTitulos buscar = new BuscaTitulos();		
//		String tituloOriginal = buscar.capturarTitulo();			
//		String palabraL = buscar.procesarCadena(tituloOriginal);
		
//		@Autowired
//		private TitulosService _titulosService;
				
//		List<BinTitulos> listaTitulos = new ArrayList<BinTitulos>();			
//		listaTitulos = _titulosService.BuscarTitulos(palabraL);		

//		for (BinTitulos t : listaTitulos) {
//			System.out.println(t.getTitulo_original());
//			System.out.println(t.getAnio());
//			System.out.println(t.getRealizador());
//		}
	}	 
}

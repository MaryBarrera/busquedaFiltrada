package com.service;

import java.util.List;

import com.entidades.BinTitulos;

public interface TitulosService {

	// inserta nuevo titulo para el caso de no existir
	void InsertaNuevo(BinTitulos titulo);

	// muestra todos los titulos existentes
	List<BinTitulos> BuscaTodosTitulos();

	// muestra todos los titulos existentes
	List<BinTitulos> MostrarSoloTitulos();

	// busca los titulos similares por indice de unicidad
	List<BinTitulos> BuscarTitulos(String palabraClave);

	// buscar por id registro
	BinTitulos BuscarID(int idreg_cat_titulos);

	// actualiza el campo
	void ActualizarTitulo(BinTitulos titulo);

	public String capturarTitulo();

	public String procesarCadena(String titulo);

	public String contarPalabras(String titulo);

	public List<BinTitulos> titulosEncotrados(String palabraL);

	public void GuardarTituloNuevo();

	public void ActualizarTituloExistente();

}

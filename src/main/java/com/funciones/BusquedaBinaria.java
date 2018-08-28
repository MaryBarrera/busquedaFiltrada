package com.funciones;

public class BusquedaBinaria {
	
	public int busquedaBin(double[] matriz, double valorBuscado) {

		// Este método devuelve como resultado la posicion
		// del valor. Si el valor no se localiza, devuelve -1

		if (matriz.length == 0) {
			return -1;
		}

		int mitad, inferior = 0;
		int superior = (matriz.length) - 1;

		do {
			mitad = (inferior + superior) / 2; //calcula la media del vector
			if (valorBuscado > matriz[mitad]) {
			inferior = mitad + 1;
			} else {
			superior = mitad - 1;
			}
		} while (matriz[mitad] != valorBuscado && inferior <= superior);

		if (matriz[mitad] == valorBuscado) {
			return mitad;
		} else {
			return -1;
		}

	}

}

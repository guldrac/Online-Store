package ar.edu.unlz.grupo7.iterators;

import java.util.Iterator;

//Iterador de Strings. Recorre un String y devuelve el valor que encuentra en cada posici�n de Character.
public class StringIterator implements Iterator<Character> {

	private int cursor = -1; // Asignar -1 para que comience antes del primer elemento de la lista.
	private String string;

	public StringIterator(String string) {
		super();
		this.string = string;
	}

	@Override
	public boolean hasNext() {
		return cursor < this.string.length() - 1;
	}

	@Override
	public Character next() {
		this.cursor += 1; // Aumento el valor del cursor para indicar su posici�n en el elemento
							// siguiente.
		return string.charAt(cursor);
	}

}

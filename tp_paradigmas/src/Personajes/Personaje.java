package Personajes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Hechizos.HechizoFactory;
import Hechizos.HechizoStrategy;

public abstract class Personaje {
	protected String nombre;
	protected int nivel_de_magia;
	protected int puntos_de_vida;
	protected Set<HechizoStrategy> lista_de_hechizos;
	protected boolean desarmado = false;
	protected boolean protegido = false;
	protected boolean vivo = true;
	protected final int vida_inicial;
	protected int energia;
	protected final int energia_inicial;
	
	public Personaje(String nombre, int nivel_de_magia, int puntos_de_vida, int energia, Set<HechizoStrategy> hechizos) {
		this.nombre = nombre;
		this.nivel_de_magia = nivel_de_magia;
		this.puntos_de_vida = puntos_de_vida;
		this.lista_de_hechizos = hechizos;
		this.vida_inicial = puntos_de_vida;
		this.energia = energia;
		this.energia_inicial = energia;
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNivel_de_magia() {
		return nivel_de_magia;
	}

	public void setNivel_de_magia(int nivel_de_magia) {
		this.nivel_de_magia = nivel_de_magia;
	}

	public int getPuntos_de_vida() {
		return puntos_de_vida;
	}

	public void setPuntos_de_vida(int puntos_de_vida) {
		this.puntos_de_vida = puntos_de_vida;
	}

	public Set<HechizoStrategy> getLista_de_hechizos() {
		return lista_de_hechizos;
	}


	public boolean isDesarmado() {
		return desarmado;
	}

	public void setDesarmado(boolean desarmado) {
		this.desarmado = desarmado;
	}
	public boolean isProtegido() {
		return this.protegido;
	}
	public void setProtegido(boolean protegido) {
		this.protegido=protegido;
	}

	public int getVida_inicial() {
		return vida_inicial;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void morir() {
		this.vivo = false;
	}
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	public int getEnergia() {
		return this.energia;
	}

	
}

package Personajes;

import java.util.ArrayList;
import java.util.List;

public class Batallon {
	private List<Personaje> miembros;
	
	public Batallon(){
		this.miembros = new ArrayList<>();
	}
	public Batallon(List<Personaje> miembros){
		this.miembros = miembros;
	}

	public List<Personaje> getMiembros() {
		return miembros;
	}
	public void agregarPersonaje(Personaje p) {
		this.miembros.add(p);	
	}
	public boolean tienePersonajesVivos() {
		
		for(Personaje p : miembros) {
			if(!p.isVivo()) {
				return false;
			}
		}
		return true;
	}
	public void atacar(Batallon enemigos, Juego juego) {
		for(Personaje p : miembros) {
			juego.tomarDecision(p, enemigos.getMiembros());
		}
		
	}
	public void procesarEfectos() {
		for(Personaje p : this.miembros) {
			p.procesarEfectos();
		}
	}

}

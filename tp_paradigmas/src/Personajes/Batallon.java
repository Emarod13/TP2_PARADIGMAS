package Personajes;

import java.util.ArrayList;
import java.util.List;

public class Batallon {
	private List<Personaje> miembros;
	
	public Batallon(){
		this.miembros = new ArrayList<>();
	}

	public List<Personaje> getMiembros() {
		return miembros;
	}
	public boolean tienePersonajesVivos() {
		
		for(Personaje p : miembros) {
			if(!p.isVivo()) {
				return false;
			}
		}
		return true;
	}
	public void atacar(Batallon b) {
		
	}
	public void procesarEfectos() {
		for(Personaje p : this.miembros) {
			p.procesarEfectos();
		}
	}

	
	
	

}

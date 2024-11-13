package Personajes;

import java.util.HashMap;
import java.util.Map;

import Hechizos.HechizoStrategy;

public abstract class Personaje {
	protected String nombre;
	protected int puntos_de_vida;
	protected Map<String, HechizoStrategy> lista_de_hechizos;
	private Map<String, Integer> efectos_aplicados;
	protected boolean vivo = true;
	protected final int vida_inicial;
	protected int energia;
	private final int energia_inicial;

	public Personaje(String nombre,  int puntos_de_vida, int energia,
			Map<String, HechizoStrategy> hechizos) {
		this.nombre = nombre;
		this.puntos_de_vida = puntos_de_vida;
		this.lista_de_hechizos = hechizos;
		this.vida_inicial = puntos_de_vida;
		this.energia = energia;
		this.energia_inicial = energia;
		this.setEfectos_aplicados(new HashMap<>());

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos_de_vida() {
		return puntos_de_vida;
	}

	public void setPuntos_de_vida(int puntos_de_vida) {
		this.puntos_de_vida = puntos_de_vida;
	}

	public Map<String, HechizoStrategy> getLista_de_hechizos() {
		return lista_de_hechizos;
	}

	public void setLista_de_hechizos(Map<String, HechizoStrategy> hechizos) {
		this.lista_de_hechizos = hechizos;
	}

	public boolean isDesarmado() {
		return getEfectos_aplicados().containsKey("Desarmado") && getEfectos_aplicados().get("Desarmado") > 0;
	}

	public boolean isProtegido() {
		return getEfectos_aplicados().containsKey("Protegido") && getEfectos_aplicados().get("Protegido") > 0;
	}

	public int getVida_inicial() {
		return vida_inicial;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void morir() {
		this.vivo = false;
		this.puntos_de_vida = 0;
		System.out.println(this.nombre+ " ha muerto!!");	
		}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getEnergia() {
		return this.energia;
	}

	public void aplicarEfecto(String efecto, int duracion) {
		if(duracion>0) {
			getEfectos_aplicados().put(efecto, duracion); // Aplica un efecto con una duración
		}
	}

	public void procesarEfectos() {
		// Itera sobre los efectos y los aplica en cada round
		final int DAÑO_SANGRADO = 25;
		final int AGOTAMIENTO = 40;
		getEfectos_aplicados().forEach((efecto, duracion) -> {
			if (duracion > 0) {
				switch (efecto) {
				case "Sangrado":
					this.recibirDaño(DAÑO_SANGRADO); // Daño por sangrado
					System.out.println(nombre + " sufre " + DAÑO_SANGRADO+ " de daño por sangrado"); // el estar protegido, no lo protege del sangrado
					break;
				case "Desarmado":
					//System.out.println(nombre + "esta desarmado, no puede pelear");
					break;
				case "Protegido":
					//System.out.println(nombre + " esta protegido, no puede ser atacado");
					break;
				case "Agotado":
					this.setEnergia(this.energia-AGOTAMIENTO);
					System.out.println(nombre + " sufre de agotamiento, pierde 40 puntos de energia");
					break;
				}
				getEfectos_aplicados().put(efecto, duracion - 1); // Reducir duración del efecto
			}
		});

		// Eliminar efectos que ya no tienen duración
		getEfectos_aplicados().entrySet().removeIf(entry -> entry.getValue() <= 0);
	}

	public void atacar(Personaje enemigo, String hechizoNombre) {
		HechizoStrategy hechizo = lista_de_hechizos.get(hechizoNombre);
		if (!this.isDesarmado()) {
			
			if (hechizo != null && energia >= hechizo.getCostoEnergia() && hechizo.getTipo().equals("Ataque")) {
				System.out.println(nombre + " ataca a " + enemigo.getNombre() + " con " + hechizo.getNombre());
				hechizo.ejecutar(enemigo);
				energia -= hechizo.getCostoEnergia();
				
			} else {
				System.out.println(nombre + " no puede usar " + hechizoNombre
						+ " por falta de energía o por tipo de hechizo incorrecto.");
			}
		}
		else {
			System.out.println(nombre + " se encuentra desarmado y no puede atacar");
		}

	}

	public void defender(String hechizoNombre) {
		HechizoStrategy hechizo = lista_de_hechizos.get(hechizoNombre);
		if(!this.isDesarmado()) {
			if (hechizo != null && energia >= hechizo.getCostoEnergia() && hechizo.getTipo().equals("Defensa")) {
				hechizo.ejecutar(this);
				energia -= hechizo.getCostoEnergia();
				System.out.println(nombre + " se defiende con " + hechizo.getNombre());
			} else {
				System.out.println(nombre + " no puede usar " + hechizoNombre
						+ " por falta de energía o por tipo de hechizo incorrecto.");
			}
		}
		else {
			System.out.println(nombre + " se encuentra desarmado y no puede defenderse");
		}
		
		
	}

	public boolean estaDebilitado() {
		return (this.puntos_de_vida / this.vida_inicial) <= 0.4;

	}

	
	public void recibirDaño(int daño) {
			this.puntos_de_vida -= daño;
			if (this.puntos_de_vida <= 0) {
				this.morir();
			}
		}
	public void recuperarEnergia(int energia) {
		this.energia += energia;
	}
		
	
	public abstract String getTipo(); // usado mas que nada para las consultas de prolog

	public int getEnergia_inicial() {
		return energia_inicial;
	}

	public Map<String, Integer> getEfectos_aplicados() {
		return efectos_aplicados;
	}

	public void setEfectos_aplicados(Map<String, Integer> efectos_aplicados) {
		this.efectos_aplicados = efectos_aplicados;
	}

}

package Hechizos;

public class HechizoFactory {
	public static HechizoStrategy crearHechizo(String tipo) {
		switch(tipo) {
		case "AvadaKedavra":
			return new AvadaKedavra();
		case "Episkey":
			return new Episkey();
		case "Expelliarmus":
			return new Expelliarmus();
		case "ExpectoPatronum":
			return new ExpectoPatronum();
		case "Protego":
			return new Protego();
		case "SectumSempra":
			return new SectumSempra();
		default:
			return null;
		}
		
	}
}

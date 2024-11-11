package JuegoClases;

public class Expelliarmus extends HechizoStrategy { // ESTUDIANTE
	private static int DURACION = 2;
	private static int COSTO = 75;

	public Expelliarmus() {
		super(COSTO, "Ataque");
	}

	@Override
	public void ejecutar(Personaje p) { /// DESARMA A UN ENEMIGO
		if (p.isProtegido())

		{
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		} else {
			p.aplicarEfecto("Desarmado", DURACION);
		}

	}

	@Override
	public String getTipo() {
		// TODO Auto-generated method stub
		return this.tipo;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Expelliarmus";
	}

}

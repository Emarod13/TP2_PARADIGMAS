package JuegoClases;


public class LaceroMortis extends HechizoStrategy {
	private static int DAÑO = 100;
	private static int COSTO = 120;
	private static int DURACION = 1;

	public LaceroMortis() {
		super(COSTO, "Ataque");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar(Personaje p) {

		if (p.isProtegido()) {
			System.out.println(p.getNombre() + " esta protegido, repele el ataque");
		} else {
			p.recibirDaño(DAÑO);
			if (p.isVivo()) {
				p.aplicarEfecto("Desarmado", DURACION);
			}
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
		return "LaceroMortis";
	}

}

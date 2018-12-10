package memoria;

public class RAM implements Memoria {

	int valor;

	int delay = 1000;


	private void demora() {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void escrever(int valor) {
		demora();
		this.valor = valor;
	}

	@Override
	public int ler() {
		demora();
		return valor;
	}
}

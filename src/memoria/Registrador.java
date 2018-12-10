package memoria;

public class Registrador implements Memoria {

	int valor;

	@Override
	public void escrever(int valor) {
		this.valor = valor;

	}

	@Override
	public int ler() {
		return valor;
	}

}

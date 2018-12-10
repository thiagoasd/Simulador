package memoria;

public class SimuladorUtils {
	// Se resultado não for binario, retorna -1
	// Se resultado for maior que 8 bits, retorna -2
	public int checaInput(String input) {
		if (input == null) {
			return -1;
		}

		if (input.length() > 8) {
			return -2;
		}
		


		for (int i = 0; i < input.length(); i++) {
			if (!(input.substring(i, i+1).compareTo("0") == 0 || input.substring(i, i+1).compareTo("1") == 0)) {
				
				return -1;
			}
		}
		
		return 0;
	}
	
	
	public int valorDaOperação(String input) {
			
		return Integer.parseInt(input, 2);		
	}

}

package memoria;

public class Processador {

	RAM ram;
	IO io;
	Registrador r1;
	Registrador r2;
	Registrador r3;
	Registrador r4;
	Registrador r5;
	Registrador r6;
	Registrador r7;
	Registrador r8;
	Registrador r9;
	Registrador r10;
	private Registrador r11;
	Registrador pc;
	int CARRY;
	int ZERO;
	int SIGN;
	int OVERFLOW;

	public Processador() {
		r1 = new Registrador();
		r2 = new Registrador();
		r3 = new Registrador();
		r4 = new Registrador();
		r5 = new Registrador();
		r6 = new Registrador();
		r7 = new Registrador();
		r8 = new Registrador();
		r9 = new Registrador();
		r10 = new Registrador();
		r11 = new Registrador();
		pc = new Registrador();
		ram = new RAM();
		io = new IO();
	}

	public void MOV(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);
		if (op1 == null || !op1.getClass().equals(r1.getClass())) {
			System.out.println("ERRO NO ARGUMENTO 1");
			return;
		}

		if (op2 == null) {
			if (!ehNumero(arg2)) {
				System.out.println("ERRO NO ARGUMENTO 2");
				return;
			}
			op2 = r11;
			op2.escrever(Integer.parseInt(arg2, 2));
		}

		op1.escrever(op2.ler());

	}

	public void MOV2(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);
		if (op1 == null) {
			System.out.println("ERRO NO ARGUMENTO 1");
			return;
		}

		if (op2 == null) {

			System.out.println("ERRO NO ARGUMENTO 2");
			return;
		}

		op1.escrever(op2.ler());

	}

	public void AND(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);
		if (op1 == null) {
			System.out.println("ERRO NO ARGUMENTO 1");
			return;
		}

		if (op2 == null) {
			System.out.println("ERRO NO ARGUMENTO 2");
			return;
		}

		String bin1 = adicionaZero(Integer.toBinaryString(op1.ler()));
		String bin2 = adicionaZero(Integer.toBinaryString(op2.ler()));
		String res = "";
		for (int i = 0; i < bin1.length(); i++) {
			if (bin1.substring(i, i + 1).compareTo("1") == 0 && bin2.substring(i, i + 1).compareTo("1") == 0) {
				res += "1";
			} else {
				res += "0";
			}
		}

		op1.escrever(Integer.parseInt(res));
	}

	public void OR(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);
		if (op1 == null) {
			System.out.println("ERRO NO ARGUMENTO 1");
			return;
		}

		if (op2 == null) {
			System.out.println("ERRO NO ARGUMENTO 2");
			return;
		}

		String bin1 = adicionaZero(Integer.toBinaryString(op1.ler()));
		String bin2 = adicionaZero(Integer.toBinaryString(op2.ler()));
		String res = "";
		for (int i = 0; i < bin1.length(); i++) {
			if (bin1.substring(i, i + 1).compareTo("1") == 0 || bin2.substring(i, i + 1).compareTo("1") == 0) {
				res += "1";
			} else {
				res += "0";
			}
		}

		op1.escrever(Integer.parseInt(res));
	}

	public void NOT(String arg1) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		if (op1 == null) {
			System.out.println("ERRO NO ARGUMENTO 1");
			return;
		}

		String bin1 = adicionaZero(Integer.toBinaryString(op1.ler()));
		String res = "";
		for (int i = 0; i < bin1.length(); i++) {
			if (bin1.substring(i, i + 1).compareTo("1") == 0) {
				res += "0";
			} else {
				res += "1";
			}
		}

		op1.escrever(Integer.parseInt(res));
	}

	public void ADD(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);

		int v1 = op1.ler();
		int v2 = op2.ler();

		int aux = v1 + v2;

		if (aux == 0) {
			ZERO = 1;
		}

		if (aux > 255) {
			CARRY = 1;
			aux = 0;
		}

		op1.escrever(aux);
	}

	public void SUB(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);

		int v1 = op1.ler();
		int v2 = op2.ler();

		int aux = v1 - v2;

		if (aux < 0) {
			SIGN = 1;
		}

		if (aux == 0) {
			ZERO = 1;
		}

		op1.escrever(aux);
	}

	public void MUL(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);

		int v1 = op1.ler();
		int v2 = op2.ler();

		int aux = v1 * v2;

		if (aux == 0) {
			ZERO = 1;
		}

		if (aux > 255 || aux < -254) {
			CARRY = 1;
			aux = 0;
		}

		op1.escrever(aux);
	}

	public void DIV(String arg1, String arg2) {
		pc.valor++;
		zeraFlags();
		Memoria op1 = getOperador(arg1);
		Memoria op2 = getOperador(arg2);

		int v1 = op1.ler();
		int v2 = op2.ler();
		if (v2 == 0) {
			System.out.println("DIV POR 0 PROIBIDA");
			return;
		}

		int aux = v1 / v2;
		int aux2 = v1 % v2;
		if (aux == 0) {
			ZERO = 1;
		}

		op1.escrever(aux);
		if (op1.equals(r2)) {
			r3.escrever(aux2);
		} else {
			r2.escrever(aux2);
		}
	}

	public void PULAIGUAL() {
		pc.valor++;
		if (r1.valor == r2.valor) {
			System.out.println("PULOU");
		} else {
			System.out.println("NÃO PULOU");
		}
	}

	public void PULANAOIGUAL() {
		pc.valor++;
		if (r1.valor != r2.valor) {
			System.out.println("PULOU");
		} else {
			System.out.println("NÃO PULOU");
		}
	}

	public void PULASEMAIOR() {
		pc.valor++;
		if (r1.valor > r2.valor) {
			System.out.println("PULOU");
		} else {
			System.out.println("NÃO PULOU");
		}
	}

	public void PULASEMENOR() {
		pc.valor++;
		if (r1.valor < r2.valor) {
			System.out.println("PULOU");
		} else {
			System.out.println("NÃO PULOU");
		}
	}

	public void PULA() {
		pc.valor++;
		System.out.println("PULOU");
	}

	public void NOP() {
		pc.valor++;
		System.out.println("NOP");
	}

	public void info() {
		String res = "";
		res += "PC: " + pc.valor;
		res += "\nr1: " + r1.valor;
		res += "\nr2: " + r2.valor;
		res += "\nr3: " + r3.valor;
		res += "\nr4: " + r4.valor;
		res += "\nr5: " + r5.valor;
		res += "\nr6: " + r6.valor;
		res += "\nr7: " + r7.valor;
		res += "\nr8: " + r8.valor;
		res += "\nr9: " + r9.valor;
		res += "\nr10: " + r10.valor;
		res += "\nram: " + ram.valor;
		res += "\nio: " + io.valor;
		res += "\n***FLAGS***";
		res += "\nCARRY: " + CARRY;
		res += "\nZERO: " + ZERO;
		res += "\nSIGN: " + SIGN;
		res += "\nOVERFLOW: " + OVERFLOW;

		System.out.println(res);
	}

	private Memoria getOperador(String arg) {
		Memoria op = null;

		switch (arg) {

		case "ram":
			op = ram;
			break;
		case "io":
			op = io;
			break;
		case "r1":
			op = r1;
			break;
		case "r2":
			op = r2;
			break;
		case "r3":
			op = r3;
			break;
		case "r4":
			op = r4;
			break;
		case "r5":
			op = r5;
			break;
		case "r6":
			op = r6;
			break;
		case "r7":
			op = r7;
			break;
		case "r8":
			op = r8;
			break;
		case "r9":
			op = r9;
			break;
		}

		return op;

	}

	private boolean ehNumero(String arg) {
		boolean aux = false;
		try {
			Integer.parseInt(arg, 2);
			aux = true;
		} catch (NumberFormatException e) {

		}

		return aux;

	}

	private void zeraFlags() {
		CARRY = ZERO = SIGN = OVERFLOW = 0;
	}

	private String adicionaZero(String num) {
		while (num.length() < 8) {
			num = "0" + num;
		}

		return num;

	}
}

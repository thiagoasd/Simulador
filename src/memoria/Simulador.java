package memoria;

import java.util.Scanner;

public class Simulador {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Processador proc = new Processador();
		SimuladorUtils utils = new SimuladorUtils();
		String input;

		System.out.println("Bem vindo ao simulador!");
		while (true) {

			System.out.println("Digite a instrução:");
			input = sc.next();
			int aux = utils.checaInput(input);
			if (aux == -1) {
				System.out.println("Error com input");
				continue;
			} else if (aux == -2) {
				System.out.println("Tamanho da instrução errada");
				continue;
			}
			aux = utils.valorDaOperação(input);


			String[] argumentos;
			
			switch (aux) {
			
			
			
			case 0:
				proc.info();
				break;
			
			// 1 - Carregar um valor ou o valor de um endereço da memória para em um
			// registrador
			case 1:
				System.out.println("MOV - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.MOV(argumentos[0], argumentos[1]);
				break;

			// 2 - Carregar um valor de algum registrador para a memória (com o endereço no
			// registrador Imediato)
			case 2:
				System.out.println("MOV2 - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.MOV2(argumentos[0], argumentos[1]);
				break;

			// 3 - AND
			case 3:
				System.out.println("AND - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.AND(argumentos[0], argumentos[1]);
				break;

			// 4 - OR
			case 4:
				System.out.println("OR - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.OR(argumentos[0], argumentos[1]);
				break;

			// 5 - NOT
			case 5:
				System.out.println("NOT - Digite o argumento");
				input = sc.next();

				proc.NOT(input);
				break;

			// 6 - ADD
			case 6:

				System.out.println("ADD - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.ADD(argumentos[0], argumentos[1]);

				break;

			// 7 - SUB
			case 7:

				System.out.println("SUB - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.SUB(argumentos[0], argumentos[1]);

				break;

			// 8 - MULT
			case 8:

				System.out.println("MULT - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.MUL(argumentos[0], argumentos[1]);

				break;

			// 9 - DIV
			case 9:

				System.out.println("div - Digite os argumentos separados por virgula");
				input = sc.next();
				argumentos = input.split(",");
				// se nao conseguir dividir, erro.
				if (argumentos.length != 2) {
					System.out.println("Erro nos argumentos");
					break;
				}

				proc.DIV(argumentos[0], argumentos[1]);
				break;

			// 10 - Pulo se igual
			case 10:
				proc.PULAIGUAL();
				break;

			// 11 - Pulo se não igual
			case 11:
				proc.PULANAOIGUAL();
				break;

			// 12 - Pulo se maior
			case 12:
				proc.PULASEMAIOR();
				break;

			// 13 - Pulo se menor
			case 13:
				proc.PULASEMENOR();
				break;

			// 14 - Pulo incondicional
			case 14:
				proc.PULA();
				break;
				
			// 15 - NOP (nenhuma operação)
			case 15:
				proc.NOP();
			}

		}

	}

}

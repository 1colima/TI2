package ex1;
import java.util.*;

public class start {
	static Scanner sc = new Scanner(System.in);
	public static void main(String args[]) {
		//declaração de variáveis
		int num1, num2;
		
		//ler primeira variavel
		System.out.println("digite o primeiro número");
		num1 = sc.nextInt();
		
		//ler segunda variavel
		System.out.println("digite o segundo número");
		num2 = sc.nextInt();
		
		int soma = num1 + num2;
		
		//mostrar resultado
		System.out.println("a soma dos números é: " + soma);
	}
}

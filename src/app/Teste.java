package app;

import java.util.Locale;
import java.util.Scanner;

import tabuleiro.Posicao;

public class Teste {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc= new Scanner(System.in);
		
		try {
			Posicao pos=new Posicao(3,5);
			System.out.println(pos);
			
			pos.setPosition(1, 8);
			System.out.println(pos);
		}
		catch(Throwable e){
			System.out.println("error");
		}

		sc.close();
	}

}

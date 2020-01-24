package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;
//import xadrez.Partida;

public class Rei extends ChessPiece{
	//Partida partida;

	public Rei(Board board, Cor cor) {
		super(board, cor);
		//this.partida = partida;
	}
	
	@Override
	public String toString() {
		return "K"; //King
	}
}

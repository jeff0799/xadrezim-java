package xadrez.tiposDePecas;

import tabuleiro.Board;
import xadrez.ChessPiece;
import xadrez.Cor;

public class RainhaBolada extends ChessPiece{

	public RainhaBolada(Board board, Cor cor) {
		super(board, cor);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Q";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat=new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		
		
		
		return mat;
	}

}

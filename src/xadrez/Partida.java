package xadrez;

import tabuleiro.Board;
import xadrez.tiposDePecas.Rei;
import xadrez.tiposDePecas.Torre;

public class Partida {
	private Board board;
	
	public Partida() {
		board=new Board(8,8);
		initalSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat=new ChessPiece[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				mat[i][j]=(ChessPiece)board.piece(i, j);
			}
		}
		
		
		return mat;
	}
	private void ColocarPeca(ChessPiece piece,char col,int lin) {
		if(col<'a' || col>'h' || lin<1 || lin>8) {
			throw new ChessException("ei retardado, a posição nem existe aqui, como que vai ter uma peca nela?");
		}
		
		board.placePiece(piece, new PosicaoXadrez(col,lin).toPosicao());
	}
	
	private void initalSetup() {
		//board.placePiece(null, new Posicao(0, 5));
		ColocarPeca(new Rei(board, Cor.BLACK),'e',8);
		ColocarPeca(new Rei(board, Cor.WHITE),'e',1);
		
		ColocarPeca(new Torre(board, Cor.BLACK), 'a',8 );
		ColocarPeca(new Torre(board, Cor.BLACK), 'h',8);
		ColocarPeca(new Torre(board, Cor.WHITE), 'a',1);
		ColocarPeca(new Torre(board, Cor.WHITE), 'h',1);
		
	}
}

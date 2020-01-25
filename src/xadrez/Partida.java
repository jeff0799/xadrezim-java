package xadrez;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Posicao;
import xadrez.tiposDePecas.Rei;
import xadrez.tiposDePecas.Torre;

public class Partida {
	private Board board;
	//private Cor currentPlayer;
	
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
	
	private Piece mover(Posicao from, Posicao to) {
		Piece piece =board.removePiece(from);
		Piece captured=board.piece(to);
		board.placePiece(piece, to);
		
		return captured;
	}
	private void validateSourcePos(Posicao pos) {
		if(!board.temPeca(pos)) {
			throw new ChessException("ué pivete, que peça?");
		}
		if(!board.piece(pos).temPossibleMove()) {
			throw new ChessException("a peça não pode se mover");
		}
		/*if(currentPlayer!=board.piece(pos).cor) {
			throw new ChessException("a peça não é sua");
		}*/
	}
	public ChessPiece fazerMovim(PosicaoXadrez source, PosicaoXadrez target) {
		Posicao from=source.toPosicao();
		Posicao to=target.toPosicao();
		validateSourcePos(from);
		Piece capturedPiece=mover(from,to);
		
		return (ChessPiece) capturedPiece;
	}
}

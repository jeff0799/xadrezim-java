package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Posicao;
import xadrez.tiposDePecas.Rei;
import xadrez.tiposDePecas.Torre;

public class Partida {
	private Board board;
	private Cor currentPlayer;
	private int turno;
	
	private List<ChessPiece> capturedWhite=new ArrayList<>();
	private List<ChessPiece> capturedBlack=new ArrayList<>();
	private List<ChessPiece> onBoard=new ArrayList<>();
	
	public Cor getCurrentPlayer() {
		return currentPlayer;
	}

	public int getTurno() {
		return turno;
	}

	public List<ChessPiece> getCapturedWhite() {
		return capturedWhite;
	}

	public List<ChessPiece> getCapturedBlack() {
		return capturedBlack;
	}

	public List<ChessPiece> getOnBoard() {
		return onBoard;
	}

	public Partida() {
		board=new Board(8,8);
		turno =1;
		currentPlayer=Cor.WHITE;
		
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
		onBoard.add(piece);
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
		ChessPiece captured=(ChessPiece) board.piece(to);
		board.placePiece(piece, to);
		
		if(captured.getCor()==Cor.BLACK) {
			capturedBlack.add(captured);
		}
		else {
			capturedWhite.add(captured);
		}
		
		return captured;
	}
	private void validateSourcePos(Posicao pos) {
		if(!board.temPeca(pos)) {
			throw new ChessException("ué pivete, que peça?");
		}
		if(!board.piece(pos).temPossibleMove()) {
			throw new ChessException("a peça não pode se mover");
		}
		ChessPiece piece=(ChessPiece) board.piece(pos);
		if(currentPlayer!=piece.getCor()) {  //((ChessPiece)board.piece(pos)).getCor();
			throw new ChessException("a peça não é sua");
		}//*/
	}
	private void validateTargetPos(Posicao from,Posicao to) {
		if(!board.piece(from).possibleMove(to)) {
			throw new ChessException("eu estou mostrando os movimentos possíveis,\n"
					+ "como tu não entendeu que essa peça não pode se mover para aí,\n"
					+ "não vai durar 1 min contra a Skynet");
		}
	}
	
	public boolean[][] possibleMoves(PosicaoXadrez source){
		Posicao from=source.toPosicao();
		validateSourcePos(from);
		return board.piece(from).possibleMoves();
	}
	
	public ChessPiece fazerMovim(PosicaoXadrez source, PosicaoXadrez target) {
		Posicao from=source.toPosicao();
		Posicao to=target.toPosicao();
		validateSourcePos(from);
		validateTargetPos(from,to);
		Piece capturedPiece=mover(from,to);
		nextTurn();
		
		return (ChessPiece) capturedPiece;
	}
	
	private void nextTurn() {
		//currentPlayer=(currentPlayer==Cor.WHITE)? Cor.BLACK: Cor.WHITE;
		turno++;
		if(currentPlayer==Cor.WHITE) {
			currentPlayer=Cor.BLACK;
		}
		else {
			currentPlayer=Cor.WHITE;
		}
	}
}

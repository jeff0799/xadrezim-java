package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Posicao;
import xadrez.tiposDePecas.Rei;
import xadrez.tiposDePecas.Torre;

public class Partida {
	private Board board;
	private Cor currentPlayer;
	private int turno;
	private boolean cheque=false;
	
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
	
	public boolean getCheque() {
		return cheque;
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
			throw new ChessException("ei retardado, a posi��o nem existe aqui, como que vai ter uma peca nela?");
		}
		
		board.placePiece(piece, new PosicaoXadrez(col,lin).toPosicao());
		onBoard.add(piece);
	}
	
	private Cor oponente(Cor cor) {
		return (cor==Cor.WHITE)?Cor.BLACK : Cor.WHITE;
	}
	
	private ChessPiece rei(Cor cor) {
		for(Piece p:onBoard) {
			if(((ChessPiece)p).getCor()==cor && p instanceof Rei) {
				return (ChessPiece) p;
			}
		}
		
		throw new ChessException("WTF, nao tem rei "+cor);
	}
	
	private boolean emCheque(Cor cor) {
		Posicao  posicaoRei=rei(cor).getPosicaoXadrez().toPosicao();
		List<Piece> inimigos=onBoard.stream().filter(x->((ChessPiece)x).getCor()==oponente(cor)).collect(Collectors.toList());
		
		for(Piece p:inimigos) {
			if(p.possibleMove(posicaoRei)) {
				return true;
			}
		}
		return false;
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
		
		if (captured!= null) {
			onBoard.remove(captured);
			
			if(captured.getCor()==Cor.BLACK) {
				capturedBlack.add(captured);
			}
			else {
				capturedWhite.add(captured);
			}
		}
		
		return captured;
	}
	private void undoMove(Posicao source,Posicao target, Piece captured) {
		Piece piece =board.removePiece(target);
		board.placePiece(piece, source);
		
		if(captured!=null) {
			board.placePiece(captured, target);
			ChessPiece cap=(ChessPiece)captured;
			if(cap.getCor()==Cor.BLACK) {
				capturedBlack.remove(cap);
			}
			else {
				capturedWhite.remove(cap);
			}
			onBoard.add(cap);
		}
	}
	private void validateSourcePos(Posicao pos) {
		if(!board.temPeca(pos)) {
			throw new ChessException("u� pivete, que pe�a?");
		}
		if(!board.piece(pos).temPossibleMove()) {
			throw new ChessException("a pe�a n�o pode se mover");
		}
		ChessPiece piece=(ChessPiece) board.piece(pos);
		if(currentPlayer!=piece.getCor()) {  //((ChessPiece)board.piece(pos)).getCor();
			throw new ChessException("a pe�a n�o � sua");
		}//*/
	}
	private void validateTargetPos(Posicao from,Posicao to) {
		if(!board.piece(from).possibleMove(to)) {
			throw new ChessException("eu estou mostrando os movimentos poss�veis,\n"
					+ "como tu n�o entendeu que essa pe�a n�o pode se mover para a�,\n"
					+ "n�o vai durar 1 min contra a Skynet");
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
		
		if(emCheque(currentPlayer)) {
			undoMove(from, to, capturedPiece);
			throw new ChessException("voce vai estar em cheque meu garoto");
		}
		
		cheque=emCheque(oponente(currentPlayer));
		
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

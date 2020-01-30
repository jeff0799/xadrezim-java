package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Board;
import tabuleiro.Piece;
import tabuleiro.Posicao;
import xadrez.tiposDePecas.Bispo;
import xadrez.tiposDePecas.Cavaleiro;
import xadrez.tiposDePecas.Peao;
import xadrez.tiposDePecas.RainhaBolada;
import xadrez.tiposDePecas.Rei;
import xadrez.tiposDePecas.Torre;

public class Partida {
	private Board board;
	private Cor currentPlayer;
	private int turno;
	private boolean xeque=false;
	private boolean xequemate=false;
	private ChessPiece enPasVulnerable;
	private ChessPiece promovido;
	
	private List<ChessPiece> capturedWhite=new ArrayList<>();
	private List<ChessPiece> capturedBlack=new ArrayList<>();
	private List<ChessPiece> onBoard=new ArrayList<>();
	
	public ChessPiece getPromovido() {
		return promovido;
	}

	public ChessPiece getEnPasVulnerable() {
		return enPasVulnerable;
	}

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
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequemate() {
		return xequemate;
	}

	
	public Partida() {
		board=new Board(8,8);
		turno =1;
		currentPlayer=Cor.WHITE;
		
		//initalSetup();
		ColocarPeca(new Peao(board, Cor.BLACK, this),'a',2);
		ColocarPeca(new Peao(board, Cor.WHITE, this),'b',7);
		ColocarPeca(new Rei(board, Cor.BLACK, this),'e',7);
		ColocarPeca(new Rei(board, Cor.WHITE, this),'e',2);
		
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
	
	private boolean emXeque(Cor cor) {
		Posicao  posicaoRei=rei(cor).getPosicaoXadrez().toPosicao();
		List<Piece> inimigos=onBoard.stream().filter(x->((ChessPiece)x).getCor()==oponente(cor)).collect(Collectors.toList());
		
		for(Piece p:inimigos) {
			if(p.possibleMove(posicaoRei)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean emXequemate(Cor cor) {
		if(!emXeque(cor)) {
			return false;
		}
		List<Piece> lista=onBoard.stream().filter(x->((ChessPiece)x).getCor()==cor).collect(Collectors.toList());
		for(Piece p:lista) {
			boolean[][] mat=p.possibleMoves();
			for(int i=0;i<mat.length;i++) {
				for(int j=0;j<mat[0].length;j++) {
					if(mat[i][j]) {
						Posicao source = ((ChessPiece)p).getPosicaoXadrez().toPosicao();
						Posicao target = new Posicao(i, j);
						
						Piece cap=mover(source, target);
						
						boolean emXeque=emXeque(cor);
						undoMove(source, target, cap);
						if(!emXeque) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	private void initalSetup() {
		//board.placePiece(null, new Posicao(0, 5));
		ColocarPeca(new Rei(board, Cor.BLACK, this),'e',8);
		ColocarPeca(new Rei(board, Cor.WHITE, this),'e',1);
		
		ColocarPeca(new Torre(board, Cor.BLACK), 'a',8 );
		ColocarPeca(new Torre(board, Cor.BLACK), 'h',8);
		ColocarPeca(new Torre(board, Cor.WHITE), 'a',1);
		ColocarPeca(new Torre(board, Cor.WHITE), 'h',1);
		
		for(char col='a';col<='h';col++) {
			ColocarPeca(new Peao(board, Cor.BLACK,this),col,7);
		}
		for(char col='a';col<='h';col++) {
			ColocarPeca(new Peao(board, Cor.WHITE,this),col,2);
		}//*/
				
		ColocarPeca(new Bispo(board, Cor.BLACK), 'c',8 );
		ColocarPeca(new Bispo(board, Cor.BLACK), 'f',8 );
		ColocarPeca(new Bispo(board, Cor.WHITE), 'c',1 );
		ColocarPeca(new Bispo(board, Cor.WHITE), 'f',1 );
		//*/
		ColocarPeca(new RainhaBolada(board, Cor.BLACK),'d',8);
		ColocarPeca(new RainhaBolada(board, Cor.WHITE),'d',1);
		//*/
		ColocarPeca(new Cavaleiro(board, Cor.BLACK), 'b',8 );
		ColocarPeca(new Cavaleiro(board, Cor.BLACK), 'g',8 );
		ColocarPeca(new Cavaleiro(board, Cor.WHITE), 'b',1 );
		ColocarPeca(new Cavaleiro(board, Cor.WHITE), 'g',1 );
		//*/
	}
	
	private Piece mover(Posicao from, Posicao to) {
		ChessPiece piece =(ChessPiece) board.removePiece(from);
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
		piece.increaseCounter();
		
		if(piece instanceof Rei) {
			//Posicao posRei=piece.getPosicao();
			//roque menor
			if(to.getLinha() == from.getLinha() && to.getColuna()==from.getColuna()+2) {
				Posicao fromT =new Posicao(to.getLinha(), to.getColuna()+1);
				Posicao toT =new Posicao(to.getLinha(), to.getColuna()-1);
				mover(fromT,toT);
			}
			//roque maior
			if(to.getLinha() == from.getLinha() && to.getColuna()==from.getColuna()-2) {
				Posicao fromT =new Posicao(to.getLinha(), to.getColuna()-2);
				Posicao toT =new Posicao(to.getLinha(), to.getColuna()+1);
				mover(fromT,toT);
			}
		}
		//en passant
		if(piece instanceof Peao) {
			//int frente=(piece.getCor()==Cor.WHITE)? -1:1;
			if(/*to.getLinha()==from.getLinha()+frente && */to.getColuna()==from.getColuna()+1 && captured==null) {
				captured=(ChessPiece) board.removePiece(new Posicao(from.getLinha(), from.getColuna()+1));
				onBoard.remove(captured);
				if(captured.getCor()==Cor.WHITE) {
					capturedWhite.add(captured);
				}
				else {
					capturedBlack.add(captured);
				}
			}
			else if(/*to.getLinha()==from.getLinha()+frente &&*/ to.getColuna()==from.getColuna()-1 && captured==null) {
				captured=(ChessPiece) board.removePiece(new Posicao(from.getLinha(), from.getColuna()-1));
				onBoard.remove(captured);
				if(captured.getCor()==Cor.WHITE) {
					capturedWhite.add(captured);
				}
				else {
					capturedBlack.add(captured);
				}
			}
		}
		
		return captured;
	}
	private void undoMove(Posicao source,Posicao target, Piece captured) {
		ChessPiece piece =(ChessPiece) board.removePiece(target);
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
		piece.decreaseCounter();
		
		if(piece instanceof Rei) {
			//Posicao posRei=piece.getPosicao();
			//roque menor
			if(source.getLinha() == target.getLinha() && target.getColuna()==source.getColuna()+2) {
				Posicao fromT =new Posicao(target.getLinha(), target.getColuna()+1);
				Posicao toT =new Posicao(target.getLinha(), target.getColuna()-1);
				undoMove(fromT,toT,null);
			}
			//roque maior
			if(target.getLinha() == source.getLinha() && target.getColuna()==source.getColuna()-2) {
				Posicao fromT =new Posicao(target.getLinha(), target.getColuna()-2);
				Posicao toT =new Posicao(target.getLinha(), target.getColuna()+1);
				undoMove(fromT,toT,null);
			}
		}
		if(piece instanceof Peao && captured == enPasVulnerable) {
			int frente=(piece.getCor()==Cor.WHITE)? -1:1;
			//Piece passado=board.removePiece(target);
			board.removePiece(target);
			board.placePiece(captured, new Posicao(target.getLinha()+frente, target.getColuna()));
		}
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
		ChessPiece movida=(ChessPiece) board.piece(to);
		
		//promocao
		promovido=null;
		int endLine=(movida.getCor()==Cor.BLACK) ? 1 : 8;
		if(movida instanceof Peao && movida.getPosicaoXadrez().getLinha()==endLine) {
			promovido=movida;
			promovido=replacePromoted("Q");
		}
		
		if(emXeque(currentPlayer)) {
			undoMove(from, to, capturedPiece);
			throw new ChessException("voce vai estar em cheque meu garoto");
		}
		
		xeque=emXeque(oponente(currentPlayer));
		
		if(emXequemate(oponente(currentPlayer))) {
			xequemate=true;
		}
		else {
			nextTurn();
		}
		//en passant
		
		if(movida instanceof Peao && (to.getLinha()==from.getLinha()+2 || to.getLinha()==from.getLinha()-2)) {
			enPasVulnerable=movida;
		}
		else {
			enPasVulnerable=null;
		}
		
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
	public ChessPiece replacePromoted(String tipo) {
		if(promovido==null) {
			throw new ChessException("promover quem?");
		}
		Posicao pos=promovido.getPosicao();
		Cor cor=promovido.getCor();
		ChessPiece nova;
		onBoard.remove(promovido);
		
		if(tipo.equals("Q")) nova=new RainhaBolada(board, cor);
		else if(tipo.equals("C")) nova=new Cavaleiro(board, cor);
		else if(tipo.equals("B")) nova=new Bispo(board, cor);
		else if(tipo.equals("T")) nova=new Torre(board, cor);
		else if(tipo.equals("K")) throw new ChessException("tu quer 2 reis eim, muito engracado");
		else throw new ChessException("ei jegue, é só digitar o simbolo da peça");
		
		board.placePiece(nova, pos);
		onBoard.add(nova);
		
		return nova;
	}
}

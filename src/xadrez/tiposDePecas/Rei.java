package xadrez.tiposDePecas;

import tabuleiro.Board;
import tabuleiro.Posicao;
import xadrez.ChessPiece;
import xadrez.Cor;
import xadrez.Partida;
//import xadrez.Partida;

public class Rei extends ChessPiece{
	Partida partida;

	public Rei(Board board, Cor cor, Partida partida) {
		super(board, cor);
		this.partida = partida;
	}
	
	@Override
	public String toString() {
		return "K"; //King
	}
	
	private boolean testeTorreRoque(Posicao p) {
		if(!getBoard().posicaoExiste(p)) {
			return false;
		}
		ChessPiece piece=(ChessPiece) getBoard().piece(p);
		return piece!=null && piece instanceof Torre &&
				piece.getCor()==getCor() && piece.getMoveCounter()==0;
	}
	
	private boolean podeMover(int lin,int col) {
		ChessPiece p=(ChessPiece) getBoard().piece(lin, col);
		//return getBoard().posicaoExiste(lin,col) && p!=null || p.getCor()!=getCor();
		return p==null || p.getCor()!=getCor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat=new boolean[getBoard().getLinhas()][getBoard().getColunas()];
		int lin,col;
		
		//cima
		lin=posicao.getLinha() - 1;
		col=posicao.getColuna();
		if(getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col]=true;
		}
		//baixo
		lin = posicao.getLinha() + 1;
		col = posicao.getColuna();
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		//direita
		lin = posicao.getLinha();
		col = posicao.getColuna() + 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		//esquerda
		lin = posicao.getLinha();
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// cima-esq
		lin = posicao.getLinha() - 1;
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// cima-dir
		lin = posicao.getLinha() - 1;
		col = posicao.getColuna() +1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// baixo-esq
		lin = posicao.getLinha() +1;
		col = posicao.getColuna() - 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		// baixo-dir
		lin = posicao.getLinha() + 1;
		col = posicao.getColuna() + 1;
		if (getBoard().posicaoExiste(lin,col) && podeMover(lin, col)) {
			mat[lin][col] = true;
		}
		//movimento especial: Roque
		if(getMoveCounter()==0 && !partida.getXeque()) {
			//roque menor
			Posicao posT=new Posicao(posicao.getLinha(), posicao.getColuna()+3);
			Posicao p1=new Posicao(posicao.getLinha(), posicao.getColuna()+1);
			Posicao p2=new Posicao(posicao.getLinha(), posicao.getColuna()+2);
			Posicao p3;
			if(testeTorreRoque(posT) && !getBoard().temPeca(p1) && !getBoard().temPeca(p2)) {
				mat[posicao.getLinha()][posicao.getColuna()+2]=true;
			}
			//roque maior
			posT.setPosition(posicao.getLinha(), posicao.getColuna()-4);
			p1.setPosition(posicao.getLinha(), posicao.getColuna()-1);
			p2.setPosition(posicao.getLinha(), posicao.getColuna()-2);
			p3=new Posicao(posicao.getLinha(), posicao.getColuna()-3);
			
			if(testeTorreRoque(posT) && !getBoard().temPeca(p1) && !getBoard().temPeca(p2) && !getBoard().temPeca(p3)) {
				mat[posicao.getLinha()][posicao.getColuna()-2]=true;
			}
		}

		return mat;
	}
}

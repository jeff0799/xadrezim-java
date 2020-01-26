package tabuleiro;

public class Board {
	private int linhas, colunas;
	private Piece[][] pecas;
	
	public Board(int linhas, int colunas) {
		if(linhas<1 || colunas<1) {
			throw new BoardException(linhas+" linhas e "+colunas+" colunas nunca vi um tabuleiro desses");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		pecas=new Piece[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Piece piece(int l,int c) {
		if(!posicaoExiste(l,c)) {
			throw new BoardException("a posição não existe, claro que não vai ter uma peça aí animal");
		}
		return pecas[l][c];
	}
	
	public Piece piece(Posicao p) {
		if(!posicaoExiste(p)) {
			throw new BoardException("a posição não existe, claro que não vai ter uma peça aí animal");
		}
		return pecas[p.getLinha()][p.getColuna()];
	}
	
	/*
	 if(){
	 	throw new BoardException("");
	 }
	 */
	
	public void placePiece(Piece piece, Posicao posicao) {
		if(piece==null) {
			throw new BoardException("espera ae, tu quer colocar uma peça nula? -100 respeito");
		}

		/*if(temPeca(posicao)) {
			throw new BoardException("ocupado, já tem uma peca aqui");
		}*/
		
		pecas[posicao.getLinha()][posicao.getColuna()]=piece;
		piece.posicao=posicao;
	}
	public Piece removePiece(Posicao posicao) {
		Piece p=piece(posicao);
		if(p==null) {
			return null;
		}
		p.posicao=null;
		pecas[posicao.getLinha()][posicao.getColuna()]=null;
		//return piece(posicao); //null
		return p;
	}
	
	public boolean posicaoExiste(int linha,int coluna) {
		return linha>=0 && linha<linhas && coluna>=0 && coluna<colunas;
	}
	
	public boolean posicaoExiste(Posicao p) {
		return posicaoExiste(p.getLinha(),p.getColuna());
	}
	
	public boolean temPeca(Posicao p) {
		if(!posicaoExiste(p)){
		 	throw new BoardException("ei retardado, a posição nem existe aqui, como que vai ter uma peca nela?");
		 }
		return piece(p)!=null;
	}
	public boolean temPeca(int l,int c) {
		if(!posicaoExiste(l,c)){
		 	throw new BoardException("ei retardado, a posição nem existe aqui, como que vai ter uma peca nela?");
		 }
		return piece(l,c)!=null;
	}
}

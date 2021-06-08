package jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Chefao {

	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	boolean vai = true;  
	boolean volta = false; 
	
	//private static final int LARGURA = 938;
	private static int VELOCIDADE = 2;
	
	public Chefao(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\chefão.png");
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	
	public void update() {
		if (701 < x) {
			this.x -= VELOCIDADE;
		} else {
			VELOCIDADE = 0;
		}
		
		if (VELOCIDADE == 0) {
			mover();
		}
	}
	
	public void mover(){
		  if(vai){  this.y += 6;  }
		  if(volta){  this.y -= 6;  }
		  
		  if(this.y > 490){ vai = false; volta = true; }
		  if(this.y < 10){ vai = true; volta = false; }
		 }
	
	
	
	public Rectangle getBounds() {
		return new Rectangle (x, y, largura, altura);
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

}

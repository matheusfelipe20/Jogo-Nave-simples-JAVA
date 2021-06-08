package jogo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	
	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Chefao> chefao;
	private List<Nuvem> nuvem;
	private List<Sol> sol;
	private List<AtaqueChefao> ataquechefao;
	private boolean emJogo;
	private int score = 0;
	private int vidaChefe = 100;
	private int vidaHeroi = 3;
	
	
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("res//fundo.jpg");
		fundo = referencia.getImage();
		
		player = new Player();
		player.load();
		
		addKeyListener(new TecladoAdapter());
		
		timer = new Timer(5, this);
		timer.start();
		
		inicializaInimigos();
		inicializaAtaqueChefao();
		inicializaNuvem();
		inicializaChefao();
		inicializaSol();
		emJogo = true;
	}
	

	public void inicializaInimigos() {
		int coodernadas[] = new int[150];
		enemy1 = new ArrayList<Enemy1>();
		for (int i = 0; i < coodernadas.length; i++) {
			int x = (int)(Math.random() * 8000 + 1024);
			int y = (int)(Math.random() * 600 + 30);
			enemy1.add(new Enemy1(x, y));
		}
		
	}
	
	public void inicializaAtaqueChefao() {
		int coodernadas[] = new int[150];
		ataquechefao = new ArrayList<AtaqueChefao>();
		for (int i = 0; i < coodernadas.length; i++) {
			int x = (int)(Math.random() * 8000 + 19000);
			int y = (int)(Math.random() * 600 + 30);
			ataquechefao.add(new AtaqueChefao(x, y));
		}
		
	}
	
	public void inicializaNuvem() {
		int coodernadas[] = new int[6];
		nuvem = new ArrayList<Nuvem>();
		for (int i = 0; i < coodernadas.length; i++) {
			int x = (int)(Math.random() * 1050 + 1024);
			int y = (int)(Math.random() * 768 + 0);
			nuvem.add(new Nuvem(x, y));
		}
	}
	
	public void inicializaChefao() {
		int coodernadas[] = new int[1];
		chefao = new ArrayList<Chefao>();
		for (int i = 0; i < coodernadas.length; i++) {
			int x = 7000;
			int y = 300;
				chefao.add(new Chefao(x, y));
		}
	}
	
	public void inicializaSol() {
		int coodernadas[] = new int[1];
		sol = new ArrayList<Sol>();
		for (int s = 0; s < coodernadas.length; s++) {
			int x = 900;
			int y = 10;
			sol.add(new Sol(x, y));

		}
	}
	
	
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null);
			Font fonte = new Font("Dialog", Font.BOLD, 18);
			g.setFont(fonte);
			g.drawString("PONTOS: " + score, 20, 20);
			
			if (score < 1) {
				Font fonteInicial = new Font("Dialog", Font.BOLD, 35);
				g.setFont(fonteInicial);
				g.drawString("Aperte A para atirar", 350, 300);
				Font fonteSecundaria = new Font("Dialog", Font.BOLD, 20);
				g.setFont(fonteSecundaria);
				g.drawString("e utilize as setas para se mover", 360, 330);
			}
			
			
			for(int d = 0; d < sol.size(); d++) {
				Sol f = sol.get(d);
				f.load();
				graficos.drawImage(f.getImagem(), f.getX(), f.getY(), this);
			}
			
			for(int p = 0; p < nuvem.size(); p++) {
				Nuvem q = nuvem.get(p);
				q.load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			
			if (vidaHeroi == 3) {
				ImageIcon vida = new ImageIcon("vidaPlayer\\vida.png");
				graficos.drawImage(vida.getImage(), 12, 30, null);
			}
			if (vidaHeroi == 2) {
				ImageIcon vida = new ImageIcon("vidaPlayer\\vidaMetade.png");
				graficos.drawImage(vida.getImage(), 12, 30, null);
			}
			if (vidaHeroi == 1) {
				ImageIcon vida = new ImageIcon("vidaPlayer\\vidaUnica.png");
				graficos.drawImage(vida.getImage(), 12, 30, null);
			}
			if (vidaHeroi == 0) {
				emJogo = false;
			}
			
			for(int c = 0; c < chefao.size(); c++) {
				Chefao h = chefao.get(c);
				h.load();
				graficos.drawImage(h.getImagem(), h.getX(), h.getY(), this);
				
				if (704 > h.getX()) {
					Font fonte1 = new Font("Dialog", Font.BOLD, 18);
					g.setFont(fonte1);
					g.drawString("VIDA CHEFÃO", 628, 18);
					if (vidaChefe <= 100 && vidaChefe >= 91) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					} 
					if (vidaChefe <= 90 && vidaChefe >= 81) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao9.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 80 && vidaChefe >= 71) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao8.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 70 && vidaChefe >= 61) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao7.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 60 && vidaChefe >= 51) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao6.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 50 && vidaChefe >= 41) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao5.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 40 && vidaChefe >= 31) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao4.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 30 && vidaChefe >= 21) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao3.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 20 && vidaChefe >= 11) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao2.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}
					if (vidaChefe <= 10 && vidaChefe >= 0) {
						ImageIcon vida = new ImageIcon("vidaChefe\\vidaChefao1.png");
						graficos.drawImage(vida.getImage(), 600, 30, null);
					}

				}
			}
			
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			
			List<Tiro> tiros = player.getTiros();
			for(int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			
			
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			
			for (int t = 0; t < ataquechefao.size(); t++) {
				AtaqueChefao ti = ataquechefao.get(t);
				ti.load();
				graficos.drawImage(ti.getImagem(), ti.getX(), ti.getY(), this);
			}
			
			
		}
		if (emJogo == false) {
			ImageIcon fimJogo = new ImageIcon("res\\gameOver.jpg");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		if (score >= 1000) {
			emJogo = false;
			ImageIcon fimJogo = new ImageIcon("res\\vitoria.jpg");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}
		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e) {
		player.updates();
		
		for (int p = 0; p < nuvem.size(); p++) {
			Nuvem on = nuvem.get(p);
			if (on.isVisivel()) {
				on.update();
			} else {
				nuvem.remove(p);
			}
		}
		
		for (int s = 0; s < sol.size(); s++) {
			Sol so = sol.get(s);
			if (so.isVisivel()) {
				so.update();
			} else {
				sol.remove(s);
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for(int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}
		
		for (int t = 0; t < ataquechefao.size(); t++) {
			AtaqueChefao ti = ataquechefao.get(t);
			if (ti.isVisivel()) {
				ti.update();
			} else {
				ataquechefao.remove(t);
			}
			
		}
		
		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if (in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(o);
			}
		}
		
		for (int c = 0; c < chefao.size(); c++) {
			Chefao h = chefao.get(c);
			if (h.isVisivel()) {
				h.update();
			} else {
				chefao.remove(c);
			}
			
		}
		
		
		checarColisoes();
		repaint();
		
	}
	
	public void checarColisoes() {
		Rectangle formaHeroi = player.getBounds();
		Rectangle formaAtaqueChefao;
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		Rectangle formaChefao;
		Rectangle formaInimigoCarregador;
		
		for (int d = 0; d < ataquechefao.size(); d++) {
			AtaqueChefao tempAtaqueChefao = ataquechefao.get(d);
			formaAtaqueChefao = tempAtaqueChefao.getBounds();
			if (formaHeroi.intersects(formaAtaqueChefao)) {
				player.setVisivel(false);
				tempAtaqueChefao.setVisivel(false);
				vidaHeroi = vidaHeroi - 1;
			}
		}
		
		
		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if (formaHeroi.intersects(formaEnemy1)) {
				player.setVisivel(false);
				tempEnemy1.setVisivel(false);
				vidaHeroi = vidaHeroi - 1;
			}
		}
		
		
		for (int j = 0; j < chefao.size(); j++) {
			Chefao tempChefao = chefao.get(j);
			formaChefao = tempChefao.getBounds();
			if (formaHeroi.intersects(formaChefao)) {
				player.setVisivel(false);
				tempChefao.setVisivel(false);
				vidaHeroi = 0;
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for (int m = 0; m < tiros.size(); m++) {
			Tiro tempTiro = tiros.get(m);
			formaTiro = tempTiro.getBounds();
			
			for (int e = 0; e < enemy1.size(); e++) {
				Enemy1 tempEnemy1 = enemy1.get(e);
				formaEnemy1 = tempEnemy1.getBounds();
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
					score++;
					}
				}
			
			for (int h = 0; h < ataquechefao.size(); h++) {
				AtaqueChefao tempAtaqueChefao = ataquechefao.get(h);
				formaAtaqueChefao = tempAtaqueChefao.getBounds();
				if(formaTiro.intersects(formaAtaqueChefao)) {
					tempAtaqueChefao.setVisivel(false);
					tempTiro.setVisivel(false);
					score++;
					}
				}
			
			for (int y = 0; y < chefao.size(); y++) {
				Chefao tempChefao = chefao.get(y);
				formaChefao = tempChefao.getBounds();
				if(formaTiro.intersects(formaChefao)) {
					tempTiro.setVisivel(false);	
					vidaChefe --;
					if(vidaChefe == 0) {
						tempChefao.setVisivel(false);
						score =+ 2000;					
					}
				}
			}
		}
	}
	
	
	
	private class TecladoAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
		
		
	}
	
	
}

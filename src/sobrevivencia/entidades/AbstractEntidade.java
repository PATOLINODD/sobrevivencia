/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import sobrevivencia.Init;
import sobrevivencia.Sobrevivencia;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.astar.Node;
import sobrevivencia.explosoes.Explosao;
import sobrevivencia.janela.Janela;
import sobrevivencia.mapa.Parede;
import sobrevivencia.mapa.Tiles;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.som.Som;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author Administrator
 */
public abstract class AbstractEntidade {
    protected final Logger log = Logger.getLogger(AbstractEntidade.class.getName());

    private float x, y;
    private int largura, altura;
    protected boolean estouVendo;
    boolean moveu, estaAtirando;
    List<Node> path;
    Random r = new Random();
    SpriteSheet spriteCaminhada = new SpriteSheet("/SoldadoInimigo.png");
    SpriteSheet spriteAtira = new SpriteSheet("/SoldadoInimigoAtirando.png");
    BufferedImage[] caminhada;
    BufferedImage[] atira;

    protected int maskX, maskY, maskLargura, maskAltura;

    public AbstractEntidade(float x, float y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.maskLargura = largura;
        this.maskAltura = altura;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void paraCima(float velocidade) {
        this.y -= velocidade;
    }

    public void paraBaixo(float velocidade) {
        this.y += velocidade;
    }

    public void paraDireita(float velocidade) {
        this.x += velocidade;
    }

    public void paraEsquerda(float velocidade) {
        this.x -= velocidade;
    }

    protected boolean isEstouVendo() {
        return estouVendo;
    }

    protected void setEstouVendo(boolean estouVendo) {
        this.estouVendo = estouVendo;
    }

    protected boolean isMoveu() {
        return moveu;
    }

    protected void setMoveu(boolean moveu) {
        this.moveu = moveu;
    }

    protected boolean isEstaAtirando() {
        return estaAtirando;
    }

    protected void setEstaAtirando(boolean estaAtirando) {
        this.estaAtirando = estaAtirando;
    }

    protected List<Node> getPath() {
        return path;
    }

    protected void setPath(List<Node> path) {
        this.path = path;
    }

    protected Random getRandom() {
        return r;
    }

    protected void setRandom(Random r) {
        this.r = r;
    }

    protected SpriteSheet getSpriteCaminhada() {
        return spriteCaminhada;
    }

    protected void setSpriteCaminhada(SpriteSheet spriteCaminhada) {
        this.spriteCaminhada = spriteCaminhada;
    }

    protected SpriteSheet getSpriteAtira() {
        return spriteAtira;
    }

    protected void setSpriteAtira(SpriteSheet spriteAtira) {
        this.spriteAtira = spriteAtira;
    }

    protected BufferedImage[] getCaminhada() {
        return caminhada;
    }

    protected void setCaminhada(BufferedImage[] caminhada) {
        this.caminhada = caminhada;
    }

    protected BufferedImage[] getAtira() {
        return atira;
    }

    protected void setAtira(BufferedImage[] atira) {
        this.atira = atira;
    }

    protected int getMaskX() {
        return maskX;
    }

    protected void setMaskX(int maskX) {
        this.maskX = maskX;
    }

    protected int getMaskY() {
        return maskY;
    }

    protected void setMaskY(int maskY) {
        this.maskY = maskY;
    }

    protected int getMaskLargura() {
        return maskLargura;
    }

    protected void setMaskLargura(int maskLargura) {
        this.maskLargura = maskLargura;
    }

    protected int getMaskAltura() {
        return maskAltura;
    }

    protected void setMaskAltura(int maskAltura) {
        this.maskAltura = maskAltura;
    }

    protected int getTempoAtirando() {
        return tempoAtirando;
    }

    protected void setTempoAtirando(int tempoAtirando) {
        this.tempoAtirando = tempoAtirando;
    }

    protected int getFireRate() {
        return fireRate;
    }

    protected void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }

    protected void addFireRate(int fireRate) {
        this.fireRate += fireRate;
    }

    protected int getFire() {
        return fire;
    }

    protected void setFire(int fire) {
        this.fire = fire;
    }

    protected int getIndex() {
        return index;
    }

    protected void setIndex(int index) {
        this.index = index;
    }

    protected void addIndex(int index) {
        this.index += index;
    }

    protected int getTempoAni() {
        return tempoAni;
    }

    protected void setTempoAni(int tempoAni) {
        this.tempoAni = tempoAni;
    }

    protected void addTempoAni(int tempoAni) {
        this.tempoAni += tempoAni;
    }

    protected int getInd() {
        return ind;
    }

    protected void setInd(int ind) {
        this.ind = ind;
    }

    protected void addInd(int ind) {
        this.ind += ind;
    }

    protected int getTempoAti() {
        return tempoAti;
    }

    protected void setTempoAti(int tempoAti) {
        this.tempoAti = tempoAti;
    }

    protected void addTempoAti(int tempoAti) {
        this.tempoAti += tempoAti;
    }

    protected int getRange() {
        return range;
    }

    protected void setRange(int range) {
        this.range = range;
    }

    public LinkedList<Line2D.Float> getRays() {
        return rays;
    }

    protected void setRays(LinkedList<Line2D.Float> rays) {
        this.rays = rays;
    }

    protected double getAngulo() {
        return angulo;
    }

    protected void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }

    int tempoAtirando, fireRate, fire;

    public void acao() {
    }

    int index, tempoAni;
    int ind, tempoAti;

    void iniciaAni() {
        if (this.isMoveu()) {
            this.addTempoAni(1);
            if (this.getTempoAni() == 8) {
                this.setTempoAni(0);
                this.addIndex(1);
                if (this.getIndex() == this.getCaminhada().length) {
                    this.setIndex(0);
                }
            }
        }
        if (this.isEstaAtirando()) {
            this.addTempoAti(1);
            if (this.getTempoAti() == 5) {
                this.setEstaAtirando(false);
                this.setTempoAti(0);
                this.addInd(1);
                if (this.getInd() == this.getAtira().length) {
                    this.setInd(0);
                }
            }
        } else {
            this.setInd(0);
        }
    }

    public void graficos(Graphics g) {}

    protected double distancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    protected void setMask(int maskX, int maskY, int maskLargura, int maskAltura) {
        this.maskX = maskX;
        this.maskY = maskY;
        this.maskLargura = maskLargura;
        this.maskAltura = maskAltura;
    }

    public void gerarExplosao(int montante, int x, int y, int largura, int altura, int tempoMax, Color cor, double velocidade, Som som) {
        if (som != null) {
            som.play();
        }
        for (int i = 0; i < montante; i++) {
            Init.addExplosoes(new Explosao(x, y, largura, altura, tempoMax, cor, velocidade));
        }
    }

    protected int range = Janela.altura / 3;

    void iniciaRayCasting(Graphics g) {

        this.setRays(calcRays((int) this.getX() + 16, (int) this.getY() + 16, 1, getRange()));
        LinkedList<Line2D.Float> lines = this.getRays();
        for (int i = 0; i < lines.size(); i++) {
            this.setEstouVendo(lines.get(i).intersects(Init.getJogador().getRetangulo()));
        }
        this.mostraRayCasting(g);
    }

    void mostraRayCasting(Graphics g) {
        if (PontosSpawn.modoTreino) {
            g.setColor(Color.CYAN);
            for (Line2D.Float ray : this.getRays()) {
                g.drawLine((int) ray.x1, (int) ray.y1, (int) ray.x2, (int) ray.y2);
            }
        } else {
            switch (Opcoes.qualDificuldade()) {
                case Opcoes.FACIL:
                    g.setColor(Color.CYAN);
                    for (Line2D.Float ray : this.getRays()) {
                        g.drawLine((int) ray.x1, (int) ray.y1, (int) ray.x2, (int) ray.y2);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    boolean colidindo(int x2, int y2, int largura2, int altura2) {
        Rectangle este = new Rectangle((int) getX() + getMaskX(), (int) getY() + getMaskY(), getMaskLargura(), getMaskAltura());
        return este.intersects(x2, y2, largura2, altura2);
    }

    boolean colidindo(Rectangle rectangle) {
        Rectangle este = new Rectangle((int) getX() + getMaskX(), (int) getY() + getMaskY(), getMaskLargura(), getMaskAltura());
        return este.intersects(rectangle);
    }
    
    Rectangle retangulo = new Rectangle((int)this.getX(), (int)this.getY(), this.getLargura(), this.getAltura());
    
    protected Rectangle ene = new Rectangle();
    
    public boolean caminhoLivre(int x, int y) {
        this.setEne(new Rectangle(x + getMaskX(), y + getMaskY(), getMaskLargura(), getMaskAltura()));
        Inimigos ini = null;

        for (AbstractEntidade entidade : Init.getEntidades()) {
            if (entidade instanceof Inimigos) {
                ini = (Inimigos) entidade;
            }
            if (ini != null) {
                Rectangle outro = new Rectangle((int) ini.getX(), (int) ini.getY(), ini.getLargura(), ini.getAltura());

                if (ini == this) {
                    continue;
                }
                if (this.getEne().intersects(outro)) {
                    //regra que quiser
                    return true;
                }
                if (this.getEne().intersects(this.getRetangulo())) {
                    return true;
                }
            }
        }
        for (Parede p : Init.getParedes()) {
            if (this.getEne().intersects(p.getRectangle())) {
                return true;
            }
        }

        return false;
    }
    
    
    public Rectangle getEne() {
        return ene;
    }

    protected void setEne(Rectangle ene) {
        this.ene = ene;
    }

    public Rectangle getRetangulo(){
        return this.retangulo;
    }
    public void setRetangulo(Rectangle retangulo){
        this.retangulo = retangulo;
    }

    LinkedList<Line2D.Float> rays;

    LinkedList<Line2D.Float> calcRays(int x, int y, int resolution, int maxDist) {
        LinkedList<Line2D.Float> raios = new LinkedList<>();

        for (int i = 0; i < resolution; i++) {
            double dir = Math.atan2(Init.getJogador().getY() + Init.getJogador().getOffset() / 2 - 16 - (getY()), 
                                    Init.getJogador().getX() - (getX()));
            float minDist = maxDist;
            for (Line2D.Float line : Init.getLinesOnWalls()) {
                float dist = getRayCast((float) x, (float) y, (float) (x + (Math.cos(dir) * maxDist)), (float) (y + Math.sin(dir) * maxDist), line.x1, line.y1, line.x2, line.y2);
                if (dist < minDist && dist > 0) {
                    minDist = dist;
                }
            }
            raios.add(new Line2D.Float(x, y, (float) (x + (Math.cos(dir) * minDist)), (float) (y + Math.sin(dir) * minDist)));
        }
        return raios;
    }

    protected float getRayCast(float p0_x, float p0_y, float p1_x, float p1_y, float p2_x, float p2_y, float p3_x, float p3_y) {
        float s1_x, s1_y, s2_x, s2_y;
        s1_x = p1_x - p0_x;
        s1_y = p1_y - p0_y;
        s2_x = p3_x - p2_x;
        s2_y = p3_y - p2_y;

        float s, t;
        s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
        t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);
        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            float x1 = p0_x + (t * s1_x);
            float y1 = p0_y + (t * s1_y);
            return dist(p0_x, p0_y, x1, y1);
        }

        return -1; // No collision
    }

    double angulo;
    protected Rectangle hitBox = new Rectangle();

    void iniciaRectangle() {
        double rx = Math.round((float) (Math.cos(getAngulo())));
        double ry = Math.round((float) (Math.sin(getAngulo())));
        int rxx = (int) (rx + this.getX());
        int ryy = (int) (ry + this.getY());

        Rectangle rec = new Rectangle(rxx + 5, ryy +6, 17, 20);
        AffineTransform at = AffineTransform.getRotateInstance(this.getAngulo(), this.getX() + 16, this.getY() + 16);
        this.setHitBox(at.createTransformedShape(rec).getBounds());
    }

    static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

}

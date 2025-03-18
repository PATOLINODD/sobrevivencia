/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.jogador;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import sobrevivencia.Init;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.janela.Interface;
import sobrevivencia.mapa.Parede;
import sobrevivencia.mapa.ParedesLimite;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.mouse.MouseXY;
import sobrevivencia.projeteis.DanoAtkArmas;
import sobrevivencia.projeteis.Granada;
import sobrevivencia.projeteis.ProjeteisM4;
import sobrevivencia.som.Som;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author Administrator
 */
public class Jogador extends AbstractEntidade {

    public static String acao = "";
    public static boolean direita, esquerda, cima, baixo, recarregar, novoJogo;
    public static boolean mouseAtira, moveu, lancaGranada, miniMapa, emCombate, recebendoAtk, estaMirandoHelicoptero;
    public static int miraHelic;

    public double vida = 1000, tempoProximoAtk = 20;

    SpriteSheet sprit = new SpriteSheet("/Soldado.png");
    SpriteSheet spriteAtira = new SpriteSheet("/SoldadoAtirando.png");
    BufferedImage[] img;
    BufferedImage[] imgAtira;

    public Jogador(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
        int larg = 96;
        int framesCaminhada = sprit.getImg().getWidth() / larg,
                framesAtirando = spriteAtira.getImg().getWidth() / larg;

        img = new BufferedImage[framesCaminhada];
        imgAtira = new BufferedImage[framesAtirando];

        for (int i = 0; i < img.length; i++) {
            img[i] = sprit.getImg(i * larg, 0, larg, larg);
        }
        for (int i = 0; i < imgAtira.length; i++) {
            imgAtira[i] = spriteAtira.getImg(i * larg, 0, larg, larg);
        }
    }

    int ax, ay;

    private final float velocidade = 3.0f;

    @Override
    public void acao() {

//        vida = 1000;
        recebendoAtk = false;
        acao = "PARADO";
        moveu = false;

        int x = (int) (this.getX()), y = (int) (this.getY() + 32);

        if (direita && !this.caminhoLivre((int) (x + velocidade), y)) {
            moveu = true;
            paraDireita(velocidade);
        }
        if (esquerda && !this.caminhoLivre((int) (x - velocidade), y)) {
            moveu = true;
            paraEsquerda(velocidade);
        }
        if (cima && !this.caminhoLivre(x, (int) (y - velocidade))) {
            moveu = true;
            paraCima(velocidade);
        }
        if (baixo && !this.caminhoLivre(x, (int) (y + velocidade))) {
            moveu = true;
            paraBaixo(velocidade);
        }
        if (novoJogo) {
            novoJogo = false;
            vida = 1000;
        }

        if (vida >= 1000) {
            vida = 1000;
        }
        emCombate();
        this.retangulo();
        this.iniciaAnimacao();
        this.atira();
    }

    private Rectangle rectangle = new Rectangle();

    private void retangulo() {
        double angulo1 = Math.atan2(MouseXY.getY() - (this.getY() + this.offset / 2 + Init.getCam().getY()), MouseXY.getX() - (this.getX() + this.offset / 6 + Init.getCam().getX()));
        int xi = Math.round((float) (Math.cos(angulo1)));
        int yi = Math.round((float) (Math.sin(angulo1)));

        int aax = (int) (((int) xi) + this.getX() + 5);
        int aay = (int) (((int) yi) + this.getY() + 38);

        rectangle = new Rectangle(aax, aay, 17, 20);

        AffineTransform at = AffineTransform.getRotateInstance(angulo1, this.getX() + this.offset / 6, this.getY() + this.offset / 2);
        rectangle = at.createTransformedShape(rectangle).getBounds();
    }

    int tempoForaCombate;
    boolean recuperado;

    private void emCombate() {
        if (!recebendoAtk) {
            if (emCombate) {
                tempoForaCombate++;
                if (tempoForaCombate == 100) {
                    tempoForaCombate = 0;
                    emCombate = false;
                }
            }
        }
        if (recebendoAtk) {
            tempoForaCombate = 0;
            recuperado = true;
        }
        if (!emCombate) {
            if (vida < Interface.delay) {
                vida++;
            }
            if (vida >= Interface.delay && recuperado) {
                recuperado = false;
                vida = Interface.delay;
            }
        }
    }

    @Override
    public void graficos(Graphics g) {
        animacao(g);
        tempoCarregando(g);
//        mostraHitBox(g);
    }

    int tempo = 0, index, tempoAtira, ind;

    void iniciaAnimacao() {

        tempo++;
        if (tempo == 10) {
            tempo = 0;
            index++;
            if (index == img.length) {
                index = 0;
            }
        }
        if (Jogador.mouseAtira) {
            tempoAtira++;
            if (tempoAtira == 5) {
                tempoAtira = 0;
                ind++;
                if (ind == imgAtira.length) {
                    ind = 0;
                }
            }
        } else {
            ind = 0;
        }
    }

    void animacao(Graphics g) {
//        int x = (int) Init.getJogador().getX() - 38, y = (int) Init.getJogador().getY() - 35;
        int x = (int) this.getX(), y = (int) this.getY();

//        int tamanho = 100;
        int tamanho = 96;
        Graphics2D g2 = (Graphics2D) g.create();
        AffineTransform at = g2.getTransform();
        g2.rotate(angulo, this.getX() + this.offset / 6, this.getY() + this.offset / 2);
        if (!Jogador.moveu) {
            switch (Jogador.acao) {
                case "PARADO":
                    index = 0;
                    g2.drawImage(img[index], x, y, tamanho, tamanho, null);
                    break;

                case "ATIRANDO":
                    g2.drawImage(imgAtira[ind], x, y, tamanho, tamanho, null);
                    break;

                case "RECARREGANDO":
                    index = 0;
                    g2.drawImage(img[index], x, y, tamanho, tamanho, null);
                    break;
            }
        } else {
            switch (Jogador.acao) {
                case "PARADO":
                    g2.drawImage(img[index], x, y, tamanho, tamanho, null);
                    break;
                case "ATIRANDO":
                    g2.drawImage(imgAtira[ind], x, y, tamanho, tamanho, null);
                    break;
                case "RECARREGANDO":
                    g2.drawImage(img[index], x, y, tamanho, tamanho, null);
                    break;
            }
        }

        g2.setTransform(at);
    }

    void tempoCarregando(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect((int) this.getX() - 40, (int) this.getY() + this.getSpriteAtira().getImg().getHeight(), getTempoCarregamento(), 5);
    }

    int fireRate = 10;
    private int municao = 1000, tempoCarregamento = 0, cargaAtual = 30;
    private boolean atirou;

    private ProjeteisM4 proj;
    private double angulo;
    private int offset = 96;

    public void atira() {

        angulo = Math.atan2(MouseXY.getY() - 16 - (this.getY() + this.offset / 2 + Init.getCam().getY()),
                MouseXY.getX() - 16 - (this.getX() + this.offset / 6 + Init.getCam().getX()));
        double dx = Math.cos(angulo);
        double dy = Math.sin(angulo);

        int fullLenght = Math.round(15);
        int xx = Math.round((float) (dx * fullLenght));
        int yy = Math.round((float) (dy * fullLenght));

        int fullLenght2 = Math.round(40);
        int xx2 = Math.round((float) (dx * fullLenght2));
        int yy2 = Math.round((float) (dy * fullLenght2));

        if (mouseAtira) {
            if (cargaAtual > 0 && !recarregar) {
                acao = "ATIRANDO";
                atirou = true;
                if (fireRate == 10) {
                    cargaAtual -= 1;
                    int tempoVida = 50;
                    int tamanho = 10;
                    proj = new ProjeteisM4(this.getX() + this.offset / 6 - 5 + xx, this.getY() + this.offset / 2 - 5 + yy, tamanho, dx, dy, DanoAtkArmas.getDanoM4(), tempoVida, Color.yellow, new Som(SoundEffects.getTIRO_M4A1()));
                    Init.addProjetil(proj);

                    this.gerarExplosao(30, (int) (this.getX() + this.offset / 6 - 5 + xx2), (int) (this.getY() + this.offset / 2 - 5 + yy2), 3, 3, 10, Color.YELLOW.brighter().brighter(), 2, null);
                }
            }
        }
        this.recarregar();
        if (Jogador.lancaGranada) {
            Jogador.lancaGranada = false;
            Init.addProjetil(new Granada(this.getX() + 8 + xx, this.getY() + 8 + yy, 0, dx, dy, 1000, 1000000000, Color.yellow, null));
        }
        if (atirou) {
            fireRate--;
            if (fireRate <= 0) {
                if (cargaAtual <= 0 && !recarregar) {
                    new Som(SoundEffects.getEMPTY_GUN_SHOT()).play();
                }
                fireRate = 10;
                atirou = false;
            }
        }
    }

    private void recarregar() {
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                if (cargaAtual <= 0) {
                    recarregar = true;
                }
                break;
        }
        if (recarregar) {
            acao = "RECARREGANDO";
            if (municao > 0 && cargaAtual < 30) {
                tempoCarregamento++;
                if (tempoCarregamento == 1) {
                    new Som(SoundEffects.getRELOAD_M4()).play();
                }
                if (tempoCarregamento >= 130) {
                    recarregar = false;
                    tempoCarregamento = 0;
                    int carga = 30 - cargaAtual;
                    if (municao - carga < 0) {
                        cargaAtual += municao;
                        municao -= municao;
                    } else {
                        cargaAtual += carga;
//                        municao -= carga;
                    }
                }
            } else {
                recarregar = false;
            }
        }
    }

    @Override
    public boolean caminhoLivre(int x, int y) {
        this.setRetangulo(new Rectangle(x, y, this.getLargura(), this.getAltura()));
        for (AbstractEntidade ent : Init.getEntidades()) {
            if (ent == this) {
                continue;
            }
            if (ent instanceof Inimigos) {
                Inimigos ini = (Inimigos) ent;
                if (ini.getEne().intersects(this.getRetangulo())) {
                    return true;
                }
            }
        }
        for (Parede p : Init.getParedes()) {
            if (p.getRectangle().intersects(this.getRetangulo())) {
                return true;
            }
        }
        for (ParedesLimite pl : Init.getParedesParaJogador()) {
            if (pl.getRectangle().intersects(this.getRetangulo())) {
                return true;
            }
        }
        return false;
    }

    public void mostraHitBox(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.red);
        g2.draw(this.getRetangulo());
        g2.setColor(Color.white);
        g2.draw(rectangle);
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public void alteraVida(double danoAtk) {
        this.vida += danoAtk;
    }

    public ProjeteisM4 getProj() {
        return proj;
    }

    public int getMunicao() {
        return municao;
    }

    public void setMunicao(int municao) {
        this.municao = municao;
    }

    public void addMunicao(int municao) {
        this.municao += municao;
    }

    public int getTempoCarregamento() {
        return tempoCarregamento;
    }

    public void setTempoCarregamento(int tempoCarregamento) {
        this.tempoCarregamento = tempoCarregamento;
    }

    public int getCargaAtual() {
        return cargaAtual;
    }

    public void setCargaAtual(int cargaAtual) {
        this.cargaAtual = cargaAtual;
    }

    public boolean isAtirou() {
        return atirou;
    }

    public void setAtirou(boolean atirou) {
        this.atirou = atirou;
    }

    public Rectangle getRectangle() {
        return this.rectangle;
    }

    public int getOffset() {
        return this.offset;
    }
}

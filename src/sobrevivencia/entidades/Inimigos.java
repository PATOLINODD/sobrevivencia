/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sobrevivencia.entidades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;
import sobrevivencia.Init;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.astar.Astar;
import sobrevivencia.astar.Node;
import sobrevivencia.astar.Vector2;
import sobrevivencia.coletaveis.MedKit;
import sobrevivencia.soudEffects.SoundEffects;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.mapa.Parede;
import sobrevivencia.menu.Opcoes;
import sobrevivencia.projeteis.DanoAtkArmas;
import sobrevivencia.projeteis.ProjetilIni;
import sobrevivencia.som.Som;
import sobrevivencia.spritesheet.SpriteSheet;

/**
 *
 * @author Administrator
 */
public class Inimigos extends AbstractEntidade {

    protected double velocidade = 2, acresVel = 0.5;
    protected boolean hit;
    protected double vida;
    protected Som hurt = new Som(SoundEffects.getHURT());
    protected Som bloodSplat = new Som(SoundEffects.getBLOOD_SPLAT());

    public double tempo = 100;

    private int offset = 96;

    public Inimigos(float x, float y, int largura, int altura) {
        super(x, y, largura, altura);
        spriteCaminhada = new SpriteSheet("/SoldadoInimigo.png");
        spriteAtira = new SpriteSheet("/SoldadoInimigoAtirando.png");
        this.iniciaBufferedImg();
        this.updateVida();
    }

    void iniciaBufferedImg() {
        int tamanho = 96, frames = this.spriteCaminhada.getImg().getWidth() / tamanho;
        this.caminhada = new BufferedImage[frames];
        for (int i = 0; i < frames; i++) {
            caminhada[i] = spriteCaminhada.getImg(i * tamanho, 0, tamanho, tamanho);
        }
        this.atira = new BufferedImage[frames];
        for (int i = 0; i < frames; i++) {
            atira[i] = spriteAtira.getImg(i * tamanho, 0, tamanho, tamanho);
        }
    }

    @Override
    public void acao() {
        updateLocale();
        this.initAngle();
        this.segueOjogador();
        validaVida();
        iniciaAni();
        iniciaRectangle();
//        jogadorEstaVendo();
        this.updateFireRate();
        this.setMask(3, 3, this.getLargura() - 6, this.getAltura() - 6);
        this.setChanceDropMedKit(200);
    }

    int xi, yi;

    void updateLocale() {
        xi = (int) (this.getX() + this.offset / 6);
        yi = (int) (this.getY() + this.offset / 2);
    }

    public int getXi() {
        return this.xi;
    }

    public int getYi() {
        return this.yi;
    }

    double iniciaAngulo(double y1, double y2, double x1, double x2) {
        return Math.atan2(y1 - y2, x1 - x2);
    }

    void initAngle() {
        this.setAngulo(this.iniciaAngulo((double) Init.getJogador().getY() + Init.getJogador().getOffset() / 2 - 16, getY(), (double) Init.getJogador().getX(), this.getX()));
    }

    int chanceDropMedKit = 0, chanceDropBomba;

    void updateFireRate() {
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                this.setFire(20);
                break;
            case Opcoes.NORMAL:
                this.setFire(15);
                break;
            default:
                this.setFire(10);
                break;
        }
    }

    void updateVida() {
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                this.setVida(100);
                break;
            case Opcoes.NORMAL:
                this.setVida(100);
                break;
            default:
                this.setVida(150);
                break;
        }
    }

    void updateVelocidade() {
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                this.setVelocidade(2);
                break;
            case Opcoes.NORMAL:
                this.setVelocidade(2);
                break;
            default:
                this.setVelocidade(3);
                break;
        }
    }

    void validaVida() {
        if (isHit()) {
            setHit(false);
            if (!Jogador.estaMirandoHelicoptero) {
                if (Init.getJogador().getProj() != null) {
                    this.setMenosVida(Init.getJogador().getProj().getDanoAtk());
                }
                if (vida <= 0) {
                    PontosSpawn.alteraContador(1);
                    PontosSpawn.alteraRecord(1);
                    Init.removeIni(this);
                    this.getHurt().play();
                    gerarExplosao(80, (int) this.getX(), (int) this.getY(), 6, 6, 20, Color.red, 1.3, this.getBloodSplat());
                    if (this.getRandom().nextInt(100) >= this.getChanceDropMedKit()) {
                        Init.addVida(new MedKit((int) getX(), (int) getY(), 0, 0));
                    }
                    if (this.getChanceDropBomba() >= 2) {
                        Init.dropBomba(new Bomba((int) getX(), (int) getY(), 20, 20));
                    }
                }
            }
        }
    }
    int cargaAtual = 30, carga = 30;

    void atira() {
        double dx = Math.cos(this.getAngulo());
        double dy = Math.sin(this.getAngulo());

        int fullLenght = Math.round(18);
        int xxi = (int) Math.round(dx * fullLenght);
        int yyi = (int) Math.round(dy * fullLenght);

        int tamanho = 10;
        int tempoVida = 50;
        fireRate++;
        if (this.getFireRate() >= this.getFire()) {
            this.setFireRate(0);
            if (cargaAtual > 0) {
                this.setEstaAtirando(true);
                ProjetilIni pInimigo1 = new ProjetilIni(getX() + 8 + xxi, getY() + 8 + yyi, tamanho, dx, dy, DanoAtkArmas.getDanoM4(), tempoVida, Color.MAGENTA, new Som(SoundEffects.getTIRO_M4A1()));
                Init.addProjetil(pInimigo1);
                diminuiCargaAtual();
            }
        }
        this.recarregar();
        ///pa
//        if (estaAtirando) {
//            tempoAtirando++;
//            if (tempoAtirando >= 20) {
//                tempoAtirando = 0;
//                estaAtirando = false;
//            }
//        }
    }
    int tempoRecarregando;

    void diminuiCargaAtual() {
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                this.cargaAtual--;
                break;
            case Opcoes.NORMAL:
                this.cargaAtual--;
                break;
            case Opcoes.DIFICIL:
                this.cargaAtual--;
                break;
            default:
                break;
        }
    }

    void recarregar() {
        switch (Opcoes.qualDificuldade()) {
            case Opcoes.FACIL:
                this.iniciaReload();
                break;
            case Opcoes.NORMAL:
                this.iniciaReload();
                break;
            case Opcoes.DIFICIL:
                this.iniciaReload();
                break;
            default:
                break;
        }
    }

    void iniciaReload() {
        if (cargaAtual <= 0) {
            tempoRecarregando++;
            if (tempoRecarregando >= 100) {
                tempoRecarregando = 0;
                cargaAtual = carga;
            }
        }
    }

    void segueOjogador() {
        if (!this.isEstouVendo()) {
            if (!PontosSpawn.modoTreino) {
                this.setMoveu(true);
                this.setEstaAtirando(false);

                int jx = (int) Init.getJogador().getX() + Init.getJogador().getOffset() / 6,
                        jy = (int) Init.getJogador().getY() + Init.getJogador().getOffset() / 2;

                int tamanho = 32;

                if (this.path == null || this.path.isEmpty()) {
                    Vector2 start = new Vector2((int) getX() / tamanho, (int) getY() / tamanho);
                    Vector2 end = new Vector2((int) (jx / tamanho), (int) (jy / tamanho));
                    this.path = Astar.findPath(Init.getMapa(), start, end);
                }
                if (this.getRandom().nextInt(100) >= 10) {
                    Vector2 start = new Vector2((int) getX() / tamanho, (int) getY() / tamanho);
                    Vector2 end = new Vector2((int) (jx / tamanho), (int) (jy / tamanho));
                    this.path = Astar.findPath(Init.getMapa(), start, end);
                }

                followPath(this.path);
            }
        } else {
            //atira projeteis
            int chanceAtirar = r.nextInt(2);
//            if (chanceAtirar > 0) {
//                estaAtirando = true;
            this.setMoveu(false);
            atira();
//            }
//            if (estaAtirando) {
//            }
        }
    }

    int xx, yy;

    @Override
    public void graficos(Graphics g) {
        this.iniciaRayCasting(g);
        int tamanho = 96;
        setXx((int) getX());
        setYy((int) getY() - 32);

        Graphics2D g2 = (Graphics2D) g.create();
        mostraHitBox(g2);

        AffineTransform at = g2.getTransform();
        g2.rotate(this.getAngulo(), this.getXi(), this.getY() + 16);
//        if (jogadorEstaVendo) {
        if (!this.isEstaAtirando()) {
            g2.drawImage(this.getCaminhada()[this.getIndex()], this.getXx(), this.getYy(), tamanho, tamanho, null);
        } else {
            g2.drawImage(this.getAtira()[this.getInd()], this.getXx(), this.getYy(), tamanho, tamanho, null);
        }
//        }
        g2.dispose();
        g2.setTransform(at);
    }

    void mostraHitBox(Graphics2D g2) {
        if (PontosSpawn.modoTreino) {
            g2.setColor(Color.red);
            g2.draw(this.getHitBox());
            g2.setColor(Color.WHITE);
            g2.draw(this.getEne());
        } else {
            switch (Opcoes.qualDificuldade()) {
                case Opcoes.FACIL:
                    g2.setColor(Color.red);
                    g2.draw(this.getHitBox());
                    g2.setColor(Color.blue);
                    g2.draw(this.getEne());
                    break;
                default:
                    break;
            }
        }
    }

    boolean jogadorEstaVendo;

    protected void jogadorEstaVendo() {
        this.setJogadorEstaVendo(false);
        for (Line2D.Float ray : Init.getRays()) {
            this.setJogadorEstaVendo(ray.intersects(this.getEne()));
        }
    }

    protected void followPath(List<Node> path) {
//        this.path = path;

        int tamanho = 32;
        if (path != null) {
            if (!path.isEmpty()) {
                Vector2 target = path.get(path.size() - 1).tile;

                if (getX() < target.x * tamanho && !caminhoLivre((int) (getX() + this.getVelocidade()), (int) getY())) {
                    paraDireita((float) this.getVelocidade());
                } else if (getX() > target.x * tamanho && !caminhoLivre((int) (getX() - this.getVelocidade()), (int) getY())) {
                    paraEsquerda((float) this.getVelocidade());
                }
                if (getY() < target.y * tamanho && !caminhoLivre((int) getX(), (int) (getY() + this.getVelocidade()))) {
                    paraBaixo((float) this.getVelocidade());
                } else if (getY() > target.y * tamanho && !caminhoLivre((int) getX(), (int) (getY() - this.getVelocidade()))) {
                    paraCima((float) this.getVelocidade());
                } else if (getX() == target.x * tamanho && getY() == target.y * tamanho) {
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    protected double getVelocidade() {
        return velocidade;
    }

    protected void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    protected double getAcresVel() {
        return acresVel;
    }

    protected void setAcresVel(double acresVel) {
        this.acresVel = acresVel;
    }

    protected Som getHurt() {
        return hurt;
    }

    protected void setHurt(Som hurt) {
        this.hurt = hurt;
    }

    protected Som getBloodSplat() {
        return bloodSplat;
    }

    protected void setBloodSplat(Som bloodSplat) {
        this.bloodSplat = bloodSplat;
    }

    protected double getTempo() {
        return tempo;
    }

    protected void setTempo(double tempo) {
        this.tempo = tempo;
    }

    protected int getChanceDropMedKit() {
        return chanceDropMedKit;
    }

    protected void setChanceDropMedKit(int chanceDropMedKit) {
        this.chanceDropMedKit = chanceDropMedKit;
    }

    protected int getChanceDropBomba() {
        return chanceDropBomba;
    }

    protected void setChanceDropBomba(int chanceDropBomba) {
        this.chanceDropBomba = chanceDropBomba;
    }

    protected int getXx() {
        return xx;
    }

    protected void setXx(int xx) {
        this.xx = xx;
    }

    protected int getYy() {
        return yy;
    }

    protected void setYy(int yy) {
        this.yy = yy;
    }

    protected boolean isJogadorEstaVendo() {
        return jogadorEstaVendo;
    }

    protected void setJogadorEstaVendo(boolean jogadorEstaVendo) {
        this.jogadorEstaVendo = jogadorEstaVendo;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public void setMenosVida(double danoAtk) {
        this.vida -= danoAtk;
    }

    @Override
    public String toString() {
        return "Inimigos{" + "vida=" + vida + ", xx=" + xx + ", yy=" + yy + '}';
    }

}

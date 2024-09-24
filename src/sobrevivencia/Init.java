package sobrevivencia;

import java.awt.geom.Line2D.Float;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import sobrevivencia.Area.Armas;
import sobrevivencia.Area.Explosivos;
import sobrevivencia.Area.Sentinelas;
import sobrevivencia.Area.Suportes;
import sobrevivencia.acao.PontosSpawn;
import sobrevivencia.banco.Banco;
import sobrevivencia.chefes.Helicoptero;
import sobrevivencia.coletaveis.MedKit;
import sobrevivencia.coletaveis.Municao;
import sobrevivencia.entidades.AbstractEntidade;
import sobrevivencia.entidades.Bomba;
import sobrevivencia.entidades.Inimigos;
import sobrevivencia.explosoes.AbstractExplosao;
import sobrevivencia.janela.Camera;
import sobrevivencia.janela.IconMira;
import sobrevivencia.janela.Janela;
import sobrevivencia.jogador.Jogador;
import sobrevivencia.mapa.Mapa;
import sobrevivencia.mapa.Parede;
import sobrevivencia.mapa.ParedesLimite;
import sobrevivencia.mapa.Tiles;
import sobrevivencia.menu.Menu;
import sobrevivencia.menu.Pause;
import sobrevivencia.mouse.MouseXY;
import sobrevivencia.pontosDeSpawn.AbstractPontos;
import sobrevivencia.pontosDeSpawn.PontoDeSpawn;
import sobrevivencia.projeteis.AbstractProjetil;

public class Init {
   private static Jogador jogador;
   private Municao municao;
   private AbstractExplosao explosao;
   private static Mapa mapa;
   private IconMira mira;
   private Menu menu;
   private Pause pause;
   private static List<Municao> municoes = new CopyOnWriteArrayList();
   private static List<MedKit> medKits = new CopyOnWriteArrayList();
   private static List<AbstractProjetil> projeteis = new CopyOnWriteArrayList();
   private static List<AbstractEntidade> entidades = new CopyOnWriteArrayList();
   private static List<Inimigos> inimigos = new CopyOnWriteArrayList();
   private static List<Helicoptero> helicopteros = new CopyOnWriteArrayList();
   private static List<AbstractExplosao> explosoes = new CopyOnWriteArrayList();
   private static List<AbstractPontos> pontosSpawn = new ArrayList();
   private static List<Tiles> tiles1 = new ArrayList();
   private static List<Parede> paredes = new ArrayList();
   private static List<ParedesLimite> paredesParaJogador = new ArrayList();
   private static LinkedList<Float> rays = new LinkedList();
   private static final LinkedList<Float> linesOnWalls = new LinkedList();
   private final List<Tiles> allWalls = new LinkedList();
   private static Camera cam;
   private static PontosSpawn spawn;
   private static Banco banco;
   private static Armas armas;
   private static Explosivos explosivos;
   private static Suportes suportes;
   private static Sentinelas sentinelas;

   public Init() {
      cam = new Camera();
      jogador = new Jogador(10.0F, 20.0F, 32, 32);
      mapa = new Mapa("/mapamodelo006.png");
      this.mira = new IconMira(MouseXY.getX(), MouseXY.getY());
      addHelicopteros(new Helicoptero((float)Janela.altura, (float)Janela.altura, 96, 64));
      this.menu = new Menu();
      this.pause = new Pause();
      spawn = new PontosSpawn();
      banco = new Banco();
      List var10000 = getParedes();
      List var10001 = this.allWalls;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      var10000 = getParedesParaJogador();
      var10001 = this.allWalls;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      this.initLinesOnWalls();
   }

   private void initLinesOnWalls() {
      Iterator var1 = this.allWalls.iterator();

      while(var1.hasNext()) {
         Tiles p = (Tiles)var1.next();
         linesOnWalls.add(new Float((float)p.getX(), (float)(p.getY() + p.getHeight()), (float)(p.getX() + p.getWidth()), (float)p.getY()));
         linesOnWalls.add(new Float((float)p.getX(), (float)p.getY(), (float)(p.getX() + p.getWidth()), (float)(p.getY() + p.getHeight())));
      }

   }

   public static void addIni(Inimigos inimigo) {
      entidades.add(inimigo);
   }

   public static void dropBomba(Bomba bomba) {
      entidades.add(bomba);
   }

   public static void removeBomba(Bomba bomba) {
      entidades.remove(bomba);
   }

   public static List<Helicoptero> getHelicopteros() {
      return helicopteros;
   }

   public static void setHelicopteros(List<Helicoptero> helicopteros) {
      Init.helicopteros = helicopteros;
   }

   public static void addHelicopteros(Helicoptero helicoptero) {
      helicopteros.add(helicoptero);
   }

   public static void removeIni(Inimigos inimigo) {
      entidades.remove(inimigo);
   }

   public static void addExplosoes(AbstractExplosao explosao) {
      explosoes.add(explosao);
   }

   public static void removeExplosao(AbstractExplosao explosao) {
      explosoes.remove(explosao);
   }

   public static void addMunicao(Municao municao) {
      municoes.add(municao);
   }

   public static void removeMunicao(Municao municao) {
      municoes.remove(municao);
   }

   public static void addVida(MedKit vida) {
      medKits.add(vida);
   }

   public static void removeMedKit(MedKit medKit) {
      medKits.remove(medKit);
   }

   public static void addPontoSpawn(PontoDeSpawn pontoSpawn) {
      pontosSpawn.add(pontoSpawn);
   }

   public static AbstractPontos getPontosSpawn(int index) {
      return (AbstractPontos)pontosSpawn.get(index);
   }

   public static void addProjetil(AbstractProjetil projetil) {
      projeteis.add(projetil);
   }

   public static void removeProjetil(AbstractProjetil projetil) {
      projeteis.remove(projetil);
   }

   public void addTiles(Tiles tile) {
      tiles1.add(tile);
   }

   public static void addParede(Parede parede) {
      paredes.add(parede);
   }

   public static void addParedesParaJogador(ParedesLimite parede) {
      paredesParaJogador.add(parede);
   }

   public static void clearEntidades() {
      entidades.clear();
   }

   public static void clearProjeteis() {
      projeteis.clear();
   }

   public static void clearExplosoes() {
      explosoes.clear();
   }

   public static void clearVidas() {
      medKits.clear();
   }

   public static Jogador getJogador() {
      return jogador;
   }

   public static void setJogador(Jogador jogador) {
      Init.jogador = jogador;
   }

   public Municao getMunicao() {
      return this.municao;
   }

   public void setMunicao(Municao municao) {
      this.municao = municao;
   }

   public AbstractExplosao getExplosao() {
      return this.explosao;
   }

   public void setExplosao(AbstractExplosao explosao) {
      this.explosao = explosao;
   }

   public static Mapa getMapa() {
      return mapa;
   }

   public static void setMapa(Mapa mapa) {
      Init.mapa = mapa;
   }

   public IconMira getMira() {
      return this.mira;
   }

   public void setMira(IconMira mira) {
      this.mira = mira;
   }

   public Menu getMenu() {
      return this.menu;
   }

   public void setMenu(Menu menu) {
      this.menu = menu;
   }

   public Pause getPause() {
      return this.pause;
   }

   public void setPause(Pause pause) {
      this.pause = pause;
   }

   public static List<Municao> getMunicoes() {
      return municoes;
   }

   public static void setMunicoes(List<Municao> municoes) {
      Init.municoes = municoes;
   }

   public static List<MedKit> getVidas() {
      return medKits;
   }

   public static void setVidas(List<MedKit> vidas) {
      medKits = vidas;
   }

   public static List<AbstractProjetil> getProjeteis() {
      return projeteis;
   }

   public static void setProjeteis(List<AbstractProjetil> projeteis) {
      Init.projeteis = projeteis;
   }

   public static List<AbstractEntidade> getEntidades() {
      return entidades;
   }

   public static void setEntidades(List<AbstractEntidade> entidades) {
      Init.entidades = entidades;
   }

   public static List<Inimigos> getInimigos() {
      return inimigos;
   }

   public static void setInimigos(List<Inimigos> inimigos) {
      Init.inimigos = inimigos;
   }

   public static List<AbstractExplosao> getExplosoes() {
      return explosoes;
   }

   public static void setExplosoes(List<AbstractExplosao> explosoes) {
      Init.explosoes = explosoes;
   }

   public static List<AbstractPontos> getPontosSpawn() {
      return pontosSpawn;
   }

   public static void setPontosSpawn(List<AbstractPontos> pontosSpawn) {
      Init.pontosSpawn = pontosSpawn;
   }

   public static List<Tiles> getTiles1() {
      return tiles1;
   }

   public static void setTiles1(List<Tiles> tiles1) {
      Init.tiles1 = tiles1;
   }

   public static List<Parede> getParedes() {
      return paredes;
   }

   public static void setParedes(List<Parede> paredes) {
      Init.paredes = paredes;
   }

   public static List<ParedesLimite> getParedesParaJogador() {
      return paredesParaJogador;
   }

   public static void setParedesParaJogador(List<ParedesLimite> paredesParaJogador) {
      Init.paredesParaJogador = paredesParaJogador;
   }

   public static LinkedList<Float> getRays() {
      return rays;
   }

   public static void setRays(LinkedList<Float> rays) {
      Init.rays = rays;
   }

   public static LinkedList<Float> getLinesOnWalls() {
      return linesOnWalls;
   }

   public List<Tiles> getAllWalls() {
      return this.allWalls;
   }

   public static Camera getCam() {
      return cam;
   }

   public static void setCam(Camera cam) {
      Init.cam = cam;
   }

   public static PontosSpawn getSpawn() {
      return spawn;
   }

   public static void setSpawn(PontosSpawn spawn) {
      Init.spawn = spawn;
   }

   public static List<MedKit> getMedKits() {
      return medKits;
   }

   public static void setMedKits(List<MedKit> medKits) {
      Init.medKits = medKits;
   }

   public static Banco getBanco() {
      return banco;
   }

   public static Armas getArmas() {
      return armas;
   }

   public static void setArmas(Armas armas) {
      Init.armas = armas;
   }

   public static Explosivos getExplosivos() {
      return explosivos;
   }

   public static void setExplosivos(Explosivos explosivos) {
      Init.explosivos = explosivos;
   }

   public static Suportes getSuportes() {
      return suportes;
   }

   public static void setSuportes(Suportes suportes) {
      Init.suportes = suportes;
   }

   public static Sentinelas getSentinelas() {
      return sentinelas;
   }

   public static void setSentinelas(Sentinelas sentinelas) {
      Init.sentinelas = sentinelas;
   }

   public static void novoJogo() {
      clearEntidades();
      clearProjeteis();
      PontosSpawn.setContador(0);
      PontosSpawn.setRecord(0);
      PontosSpawn.setOnda(1);
      spawn.setMaxInis(20);
      spawn.setqInis(0);
      spawn.setqMiniBoss(0);
      clearExplosoes();
      clearVidas();
      jogador.setCargaAtual(30);
      banco.zeraCreditos();
   }

   public static void gameOver() {
      if (jogador.vida <= 0.0D) {
         clearEntidades();
         clearProjeteis();
         PontosSpawn.setContador(0);
         PontosSpawn.setRecord(0);
         PontosSpawn.setOnda(1);
         spawn.setMaxInis(20);
         spawn.setqInis(0);
         spawn.setqMiniBoss(0);
         Menu.setEstadoDoJogo("menu");
         clearExplosoes();
         clearVidas();
         jogador.setCargaAtual(30);
         banco.zeraCreditos();
      }

   }
}

package br.com.grafos;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Grafo {
	
	private int numeroArestas;
	private int numeroVertices;
	
	private boolean matrizAdjacencias[][];
	private Map<Integer, LinkedList<Integer>> listaAdjacencias;
	
	private double matrizPesos[][];
	
	private boolean temListaAdj;
	private boolean temMatrizAdj;
	
	/**
	 * Construtor que inicializa um grafo com matriz e lista de adjacencias
	 * @param numeroArestas
	 * 		  Numero de Arestas
	 */
	public Grafo(int numeroVertices){
		this.numeroVertices = numeroVertices;
		geraMatrizAdjacencias();
		geraListaAdjacencias();
		this.matrizPesos = new double[this.numeroVertices][this.numeroVertices];
		this.numeroArestas = 0;
	}

	/**
	 * 
	 * @param numeroArestas
	 * 			Numero de arestas
	 * @param matrizAdjacencias
	 * 			Parametro que determina se um grafo sera gerado 
	 * 			a partir de uma matriz de adjacencias ou lista de adjacencias.
	 * 			
	 * 			Se <code>matrizAdjacencias</code> for <code>true</code> 
	 * 			o grafo sera gerado a partir de uma matriz de ajacencias
	 * 			
	 * 			Se <code>matrizAdjacencias</code> for <code>false</code> 
	 * 			o grafo sera gerado a partir de uma lista de ajacencias
	 */
	public Grafo(int numeroVertices, boolean matrizAdjacencias){
		this.numeroVertices = numeroVertices;
		this.numeroArestas = 0;
		if(matrizAdjacencias){
			geraMatrizAdjacencias();
		} else {
			geraListaAdjacencias();
		}
		
	}
	
	/**
	 * Inicializa lista de adjacencias
	 */
	private void geraListaAdjacencias() {
		this.listaAdjacencias = new HashMap<Integer, LinkedList<Integer>>();
        for (int i = 0; i < this.numeroVertices; ++i) {
            this.listaAdjacencias.put(i, new LinkedList<Integer>());
        }
        this.temListaAdj = true;
	}
	
	
	/**
	 * Inicializa matriz de adjacencias
	 */
	private void geraMatrizAdjacencias() {
		this.matrizAdjacencias = new boolean[this.numeroVertices][this.numeroVertices];
		for (int row = 0; row < this.matrizAdjacencias.length; row++) {
	        for (int column = 0; column < this.matrizAdjacencias[row].length; column++) {
	            this.matrizAdjacencias[row][column] = false;
	        }
	    }
		this.temMatrizAdj = true;
	}

	/**
	 * @return Numero de arestas do grafo.
	 */
	public int getNumeroArestas() {
		return numeroArestas;
	}
	
	/**
	 * @return Matriz de adjacencias do grafo. 
	 */
	public boolean[][] getMatrizAdjacencias() {
		return matrizAdjacencias;
	}
	
	/**
	 * 
	 * @param vertice1
	 * @param vertice2
	 * 
	 * Adiciona aresta na lista e na matriz de adjacencias sem peso
	 * 	<code>aresta1</code> com <code>aresta2</code>
	 * 	<code>aresta2</code> com <code>aresta1</code>	
	 */
	public void addAresta(int vertice1, int vertice2){
		if(this.temMatrizAdj){
			this.matrizAdjacencias[vertice1-1][vertice2-1] = true ;
			this.matrizAdjacencias[vertice2-1][vertice1-1] = true ;
		}
		if(this.temListaAdj){
			this.listaAdjacencias.get(vertice1-1).add(vertice2-1);
			this.listaAdjacencias.get(vertice2-1).add(vertice1-1);
		}	
		this.numeroArestas += 1; //apos add incrementa o numero de arestas
	}
	
	/**
	 * @return Retorna o numero de vertices do grafo.
	 */
	public int getNumeroVertices() {
		return numeroVertices;
	}

	/**
	 * @return Grau Medio do grafo
	 */
	public float getGrauMedio() {
		float numeroArestas = 2*this.numeroArestas;
		
		return numeroArestas/this.numeroVertices;
	}

	/**
	 * @return Lista de Adjacencias do grafo
	 */
	public Map<Integer, LinkedList<Integer>> getListaAdjacencias() {
		return listaAdjacencias;
	}
	
	/**
	 *  @return Distribuição Empirica do Grafo
	 */
	public List<BigDecimal> getDistribuicaoEmpirica(){ 
		return GrafosUtil.getDistribuicaoEmpirica(this);
	}
	
	/**
	 * 
	 * @param vertice
	 * @return Grau de um vertice
	 */
	public int grauVertice(int vertice) {
		return this.listaAdjacencias.get(vertice).size();
	}
	
	/**
	 * 
	 * @return Lista com todos os graus de todos os vertices
	 */
	private List<Integer> getListaGraus(){
		return GrafosUtil.getListaGraus(this);
	}
	
	/**
	 * 
	 * @return Maior grau possível para um vertice do grafo
	 */
	public int getMaiorGrauPossivel(){
		return this.numeroVertices - 1;
	}
	
	/**
	 * 
	 * @return O maior grau do grafo
	 */
	public int getMaiorGrau() { 
		return Collections.max(getListaGraus());
	}
	
	/**
	 * 
	 * @return O menor grau do grafo
	 */
	public int getMenorGrau() {
		return Collections.min(getListaGraus());
	}
	
	/**
	 * 
	 * @param verticeInicial
	 * @return Lista de distancias de um vertice para todos os
	 * vertices do grafo
	 */
	public double[] getDistancia(int verticeInicial){
		return GrafosUtil.getDistancia(this, verticeInicial);
	}
	
	/**
	 * Dado uma lista de distancias de um vertice para todos os vertices
	 * do grafo, retorna qual é a maior distancia
	 * @param distancias
	 * 	  	  Lista de distancias
	 * @return Maior distancia
	 */
	public double getCaminhoMaisLongo(List<Double> distancias){
		return Collections.max(distancias);
	}
	
	/**
	 * 
	 * @param vertice
	 * @return Lista de vertices adjacentes a um veritce
	 */
	public LinkedList<Integer> getVerticesAdjacentes(int vertice) {
		return this.listaAdjacencias.get(vertice);
	}
	
	/**
	 * 
	 * @return Maior componente conexo do grafo
	 */
	public int getMaiorComponenteConexo(){
		Dfs componenteConexo = new Dfs(this);        
        return GrafosUtil.getMaiorComponenteConexo(this, componenteConexo);
	}
	
	
	public int getNumeroComponentesConexos(Dfs componenteConexo){
        return GrafosUtil.getNumeroComponentesConexos(componenteConexo);
	}
	
	/**
	 * Percorre o arquivo de grafo adicionando as arestas no grafo
	 * @param arquivo
	 */
	public void insereArestas(BufferedReader arquivo) {
		String linhaAtual;
		try {
			while ((linhaAtual = arquivo.readLine()) != null) {
				String[] arestas = linhaAtual.split(" ");
					addAresta(Integer.parseInt(arestas[0]),
							  Integer.parseInt(arestas[1]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double[][] getMatrizPesos() {
		return this.matrizPesos;
	}
	
	public double getPesoVertices(int vertice1, int vertice2){
		return this.matrizPesos[vertice1][vertice2];
	}
	
	
	public List<BigDecimal> getDistribuicaoEmpiricaDistancia() {
		return GrafosUtil.getDistribuicaoEmpiricaDistancia(this);
	}
}

package br.com.grafos;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class Bfs {
    private boolean[] visitado;
	private double[] distancia;
	private int[] arestas;
    
    public Bfs(Grafo grafo, int verticeInicial){
    	this.visitado = new boolean[grafo.getNumeroVertices()];
    	this.distancia = new double[grafo.getNumeroVertices()];
    	this.arestas = new int[grafo.getNumeroVertices()];
    	breadthFirstSearch(grafo, verticeInicial);
    }

	private void breadthFirstSearch(Grafo grafo, int verticeInicial){
//		long tempoInicial = System.currentTimeMillis();
		 
		Queue<Integer> q = new LinkedList<Integer>();
		this.distancia[verticeInicial] = 0;
		this.visitado[verticeInicial] = true;
		q.add(verticeInicial);
		while(!q.isEmpty()){
			int vertice = q.remove();
			for(int adj : grafo.getVerticesAdjacentes(vertice)){
				if(!this.visitado[adj]){
					this.arestas[adj] = vertice;
					this.distancia[adj] = this.distancia[vertice] + 1;
					this.visitado[adj]=true;
					q.add(adj);
				}
			}
		}
		//System.out.println("o bfs executou em " + (System.currentTimeMillis() - tempoInicial)+"ms");
	}
	
	/**
	 * 
	 * @param grafo
	 * @return Lista de distancias dos maiores caminhos minimos do grafo
	 */
	public double[] getDistancia() {
		return this.distancia;
	}
	
	/**
	 * 
	 * @param v
	 * @return Menor caminho entre o vertice inicial e um vertice <tt>v</tt>
	 */
	public List<Integer> getCaminho(int v) {
        if (!possuiCaminho(v)){
        	return null;
        } 
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; this.distancia[x] != 0; x = this.arestas[x]){
        	path.push(x);
        }
        path.push(x);
        return path;
    }
	
	
	/**
	 * 
	 * @param vertice
	 * @return Verifica se um <tt>vertice</tt> foi visitado
	 */
	public boolean possuiCaminho(int vertice) {
        return this.visitado[vertice];
    }
}

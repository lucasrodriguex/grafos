package br.com.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

	private final static double INFINITO = Double.POSITIVE_INFINITY;
	private double peso[];
	private Aresta arestas[];

	public Prim(GrafoPeso grafo, int source) {
		int numeroVertices = grafo.getNumeroVertices();

		if (!verificaPesoNegativo(grafo, numeroVertices)) {
			this.peso = new double[numeroVertices];
			this.arestas = new Aresta[numeroVertices];
			computePaths(grafo, source);
		}
	}

	public void computePaths(GrafoPeso grafo, int source) {
		this.peso[source] = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

		for (int v = 0; v < grafo.getNumeroVertices(); v++) {
			if (v != source) {
				this.peso[v] = INFINITO;
			}
			queue.add(v);
		}

		while (!queue.isEmpty()) {
			int u = queue.remove();

			for (Aresta aresta : grafo.getListaAdjacenciasComPeso().get(u)) {
				int v = aresta.getFrom();
				double peso = aresta.getPeso();
				if (this.peso[v] > peso) {
					this.peso[v] = peso;
					this.arestas[v] = aresta;
				}
			}
		}
	}

	private boolean verificaPesoNegativo(GrafoPeso grafo, int numeroVertices) {
		for (int v = 0; v < numeroVertices; v++) {
			for (Aresta aresta : grafo.getListaAdjacenciasComPeso().get(v)) {
				if (aresta.getPeso() < 0)
					throw new IllegalArgumentException("Aresta " + aresta
							+ " possui peso negativo");
				return true;
			}
		}
		return false;
	}

	public double[] getDistancias() {
		return peso;
	}
	
	public List<Aresta> getMST() {
		List<Aresta> mst = new ArrayList<Aresta>();
		for(int v = 0 ; v < this.arestas.length ; v++){
			Aresta aresta = this.arestas[v]; 
			if(aresta != null){
				mst.add(aresta);
			}
		}
		return mst;
	}
	
	public double getPesoMST() {
		double pesoTotal = 0.0;
        for (Aresta aresta : getMST())
            pesoTotal += aresta.getPeso();
        return pesoTotal;
	}

	public double getDistanciaByVertice(int verticeFinal) {
		double distancia = this.peso[verticeFinal];
		if (distancia < INFINITO) {
			return distancia;
		} else {
			throw new IllegalArgumentException("Caminho Inexistente");
		}
	}

}

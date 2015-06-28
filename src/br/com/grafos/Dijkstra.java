package br.com.grafos;

import java.util.PriorityQueue;

public class Dijkstra {

	private final static double INFINITO = Double.POSITIVE_INFINITY;
	private double distancia[];
	private double prev[];

	public Dijkstra(GrafoPeso grafo, int source) {
		int numeroVertices = grafo.getNumeroVertices();
		this.distancia = new double[numeroVertices];
		this.prev = new double[numeroVertices];
		if (!verificaPesoNegativo(grafo, numeroVertices)) {
			computePaths(grafo, source);
		}
	}

	public void computePaths(GrafoPeso grafo, int source) {
		this.distancia[source] = 0;
		
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (int v = 0; v < grafo.getNumeroVertices(); v++) {
			if (v != source) {
				this.distancia[v] = INFINITO;
			}
			queue.add(v);
		}

		while (!queue.isEmpty()) {
			int u = queue.remove();
			
			for (Aresta aresta : grafo.getListaAdjacenciasComPeso().get(u)) {
				int v = aresta.getTo(); 
				double weight = aresta.getPeso();
				double alt = this.distancia[u] + weight;
				if (alt < this.distancia[v]) {
					this.distancia[v] = alt;
				}
			}
		}
	}

	private boolean verificaPesoNegativo(GrafoPeso grafo, int numeroVertices) {
		for (int v = 0; v < numeroVertices; v++) {
			for (Aresta aresta : grafo.getListaAdjacenciasComPeso().get(v)) {
				if (aresta.getPeso() < 0) {
					throw new IllegalArgumentException("Aresta " + aresta
							+ " possui peso negativo");
				}
			}
		}
		return false;
	}

	public double[] getDistancias() {
		return distancia;
	}

	public double getDistanciaByVertice(int verticeFinal) {
		double distancia = this.distancia[verticeFinal];
		if (distancia < INFINITO) {
			return distancia;
		} else {
			throw new IllegalArgumentException("Caminho Inexistente");
		}
	}

	public double[] getPrev() {
		return prev;
	}
}

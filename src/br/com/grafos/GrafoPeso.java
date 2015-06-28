package br.com.grafos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GrafoPeso extends Grafo {
	
	private Map<Integer, LinkedList<Aresta>> listaAdjacenciasComPeso;
	private double pesoTotal;
	

	public GrafoPeso(final int numeroVertices, final boolean temMatrizAdjacencias) {
		super(numeroVertices, temMatrizAdjacencias);
		this.pesoTotal = 0;
		geraListaAdjacenciasComPeso();
	}
	
	private void geraListaAdjacenciasComPeso() {
		this.listaAdjacenciasComPeso = new HashMap<Integer, LinkedList<Aresta>>();
        for (int i = 0; i < getNumeroVertices(); ++i) {
            this.listaAdjacenciasComPeso.put(i, new LinkedList<Aresta>());
        }
	}
	
	@Override
	public void insereArestas(BufferedReader arquivo) {
		
		String linhaAtual;
		try {
			while ((linhaAtual = arquivo.readLine()) != null) {
				String[] arestas = linhaAtual.split(" ");
				addAresta(Integer.parseInt(arestas[0]), 
						  Integer.parseInt(arestas[1]), 
						  Double.parseDouble(arestas[2]));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addAresta(int from, int to, double peso) {
		this.listaAdjacenciasComPeso.get(from-1).add(new Aresta(from-1, to-1, peso));
		this.listaAdjacenciasComPeso.get(to-1).add(new Aresta(to-1, from-1, peso));
		this.pesoTotal += peso * 2;
		super.addAresta(from, to);
		
	}
	
	public double getDistanciaMediaComPeso() {
		return this.pesoTotal / getNumeroArestas();
	}
	
	public Map<Integer, LinkedList<Aresta>> getListaAdjacenciasComPeso() {
		return listaAdjacenciasComPeso;
	}
	
	public double getCaminhoMinimo(int verticeInicial, int verticeFinal) {
		Dijkstra dj = new Dijkstra(this, verticeInicial-1);
		return dj.getDistanciaByVertice(verticeFinal-1);
	}
	
}

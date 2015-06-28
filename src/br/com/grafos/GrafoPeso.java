package br.com.grafos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
		from = from - 1;
		to = to - 1;
		this.listaAdjacenciasComPeso.get(from).add(new Aresta(from, to, peso));
		this.listaAdjacenciasComPeso.get(to).add(new Aresta(to, from, peso));
		this.pesoTotal += peso * 2;
		
		from+=1;
		to+=1;
		super.addAresta(from, to);
	}
	
	public double getDistanciaMediaComPeso() {
		return this.pesoTotal / getNumeroArestas();
	}
	
	public Map<Integer, LinkedList<Aresta>> getListaAdjacenciasComPeso() {
		return listaAdjacenciasComPeso;
	}
	
	public double getCaminhoMinimo(int verticeInicial, int verticeFinal) {
		verticeInicial -= 1;
		verticeFinal -= 1;
		Dijkstra dj = new Dijkstra(this, verticeInicial);
		return dj.getDistanciaByVertice(verticeFinal);
	}
	
	public List<Aresta> getDistanciaMinima(int verticeInicial, int verticeFinal) {
		verticeInicial -= 1;
		verticeFinal -= 1;
		Dijkstra dj = new Dijkstra(this, verticeInicial);
		return dj.getArestasByVertice(verticeFinal);
	}
	
	public List<Aresta> getMst() {
		Prim mst = new Prim(this);
		return mst.getMST();
	}
	
	public double getPesoMst() {
		Prim mst = new Prim(this);
		return mst.getPesoMST();
	}
}

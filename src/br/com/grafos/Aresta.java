package br.com.grafos;

public class Aresta {
	
	private int from;
	private int to;
	private double peso;
	
	public Aresta(int from, int to, double peso) {
		this.from = from;
		this.to = to;
		this.peso = peso;
	}
	
	public int getFrom() {
		return this.from;
	}
	public int getTo() {
		return this.to;
	}
	public double getPeso() {
		return peso;
	}
	
	@Override
	public String toString() {
		return this.from+1 + " " + this.to+1 + " " + this.peso;
	}
}

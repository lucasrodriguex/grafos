package br.com.grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainT2 {
	
	public static void main(String[] args) {
		File f = new File("C:/desenv/grafos/grafo_4.txt");

		BufferedReader br = null;
//		String caminhoTxtSaida = "C:/Users/Lucas/Desktop/grafos/saidaAsGraph.txt";

		try {

			br = new BufferedReader(new FileReader(f));

			// A primeira linha do arquivo representa o numero de arestas
			int numeroVertices = Integer.parseInt(br.readLine());

			// cria um grafo com o numero de arestas que está no arquivo
			GrafoPeso grafo = new GrafoPeso(numeroVertices, false);
			grafo.insereArestas(br);
			
			if(grafo instanceof GrafoPeso){
				System.out.println("grafo com peso");
			} else {
				System.out.println("ruim");
			}
			System.out.println(grafo.getCaminhoMinimo(1, 10));
			System.out.println(grafo.getCaminhoMinimo(1, 100));
			System.out.println(grafo.getCaminhoMinimo(1, 1000));
			System.out.println(grafo.getCaminhoMinimo(1, 10000));
			System.out.println("Distancia Media: " + grafo.getDistanciaMediaComPeso());
//			System.out.println(grafo.getDistanciaMediaComPeso());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}

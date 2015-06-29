package br.com.grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TesteMain {
	public static void main(String[] args) {
		File f = new File("C:/desenv/grafos/grafo_3.txt");

		BufferedReader br = null;
		String caminhoTxtSaida = "C:/desenv/grafos/teste.txt";
		
		try {
			br = new BufferedReader(new FileReader(f));
			// A primeira linha do arquivo representa o numero de arestas
			int numeroVertices = Integer.parseInt(br.readLine());

			// cria um grafo com o numero de arestas que está no arquivo
			Grafo grafo = new Grafo(numeroVertices, false);
			grafo.insereArestas(br);
			
			GrafosUtil.geraTxtPesos(grafo, caminhoTxtSaida);
			
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

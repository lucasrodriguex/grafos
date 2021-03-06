package br.com.grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class TestesAs {

	public static void main(String[] args) {
		File f = new File(
				"C:/Users/Lucas/Dropbox/Faculdade/6º periodo/Grafos/as_graph.txt");

		BufferedReader br = null;
		String caminhoTxtSaida = "C:/Users/Lucas/Desktop/grafos/saidaAsGraph.txt";
		String caminhoDistribuicaoEmpirica = "C:/Users/Lucas/Desktop/grafos/distribuicaoEmpiricaAsGraph.txt";
		String caminhoArvoreDeBusca = "C:/Users/Lucas/Desktop/grafos/arvoreBuscaAsGraph.txt";
		String caminhosBfs = "C:/Users/Lucas/Desktop/grafos/caminhosBfsAsGraph.txt";

		try {
			
			int mb = 1024*1024;
	         
	        //Getting the runtime reference from system
	        Runtime runtime = Runtime.getRuntime();
	         
	 
			br = new BufferedReader(new FileReader(f));

			// A primeira linha do arquivo representa o numero de arestas
			int numeroVertices = Integer.parseInt(br.readLine());

			// cria um grafo com o numero de arestas que está no arquivo
			Grafo grafo = new Grafo(numeroVertices, false);
			grafo.insereArestas(br);
			
			Random rand = new Random();
		    int randomVertice = rand.nextInt((grafo.getNumeroVertices() - 1) + 1) + 0;

			GrafosUtil.geraTxtSaida(grafo, caminhoTxtSaida);
			GrafosUtil.getCaminhosBfs(grafo, caminhosBfs, randomVertice);
			GrafosUtil.geraDistribuicaoEmpirica(grafo, caminhoDistribuicaoEmpirica);
			GrafosUtil.geraArvoreDeBusca(grafo, caminhoArvoreDeBusca, randomVertice);
//			GrafosUtil.getDiametroGrafo(grafo);
			
			System.out.println("Memoria usada: "
		            + (runtime.totalMemory() - runtime.freeMemory()) / mb +"MB");
			
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

package br.com.grafos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainT2 {
	
	public static void main(String[] args) {
		File f = new File("C:/desenv/grafos/grafo_1.txt");
		BufferedReader br = null;
		String caminhoMST = "C:/desenv/grafos/resultados/MstGrafo1.txt";

		try {

			br = new BufferedReader(new FileReader(f));

			// A primeira linha do arquivo representa o numero de arestas
			int numeroVertices = Integer.parseInt(br.readLine());

			// cria um grafo com o numero de arestas que está no arquivo
			GrafoPeso grafo = new GrafoPeso(numeroVertices, false);
			grafo.insereArestas(br);
			
			grafo.getDistanciaMediaComPeso();
			
			GrafosUtil.geraTxtMST(grafo, caminhoMST);
			
//			Prim mst = new Prim(grafo);
//			System.out.println(mst.getMST());
//			System.out.println(mst.getPesoMST());
			
			System.out.println("done...");
			
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

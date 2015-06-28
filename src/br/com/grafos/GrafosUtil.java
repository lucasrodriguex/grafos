package br.com.grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GrafosUtil {

	/**
	 * Gera um arquivo com a distribuicao empirica do grafo
	 * 
	 * @param grafo
	 *            Grafo que sera gerada a distribuiçao empirica
	 * @param caminho
	 *            Caminho do arquivo a ser gerado
	 * @throws IOException
	 */
	public static void geraDistribuicaoEmpirica(Grafo grafo, String caminho)
			throws IOException {
		File f = new File(caminho);
		PrintWriter pw = new PrintWriter(f);
		int vertice = 0;
		List<BigDecimal> distribuicaoEmpirica = grafo.getDistribuicaoEmpirica();
		for (BigDecimal grau : distribuicaoEmpirica) {
			pw.println(vertice + " " + grau);
			vertice++;
		}
		pw.close();
	}

	/**
	 * Gera um arquivo com a distribuicao empirica da distancia do grafo
	 * 
	 * @param grafo
	 *            Grafo que sera gerada a distribuiçao empirica
	 * @param caminho
	 *            Caminho do arquivo a ser gerado
	 * @throws IOException
	 */
	public static void geraDistribuicaoEmpiricaDistancia(Grafo grafo,
			String caminho) throws IOException {
		File f = new File(caminho);
		PrintWriter pw = new PrintWriter(f);
		int vertice = 0;
		List<BigDecimal> distribuicaoEmpirica = grafo
				.getDistribuicaoEmpiricaDistancia();
		for (BigDecimal grau : distribuicaoEmpirica) {
			pw.println(vertice + " " + grau);
			vertice++;
		}
		pw.close();
	}

	/**
	 * Gera um arquivo txt com os caminhos percorridos pelo Bfs a partir de um
	 * vertice inicial
	 * 
	 * @param grafo
	 *            Grafo a ser utilizado pelo bfs
	 * @param caminho
	 *            Caminho do arquivo a ser gerado em disco
	 * @param verticeInicial
	 *            Vertice que sera inciada a busca pelo bfs
	 */
	public static void geraArvoreDeBusca(Grafo grafo, String caminho,
			int verticeInicial) {
		Bfs bfs = new Bfs(grafo, verticeInicial);
		File saida = new File(caminho);

		PrintWriter pw;
		try {
			pw = new PrintWriter(saida);
			pw.println("Arvore de busca do vertice " + verticeInicial);
			for (int i = 0; i < grafo.getNumeroVertices(); i++) {
				List<Integer> caminhos = bfs.getCaminho(i);
				if (caminhos != null) {
					pw.println(caminhos);
				} else {
					pw.println("[" + i + ", Sem Caminho]");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gera um arquivo txt com dados do grafo
	 * 
	 * @param grafo
	 */
	public static void geraTxtSaida(Grafo grafo, String caminho) {
		File saida = new File(caminho);
		PrintWriter pw;
		Dfs componentesConexos = new Dfs(grafo);
		int numeroComponentesConexos = getNumeroComponentesConexos(componentesConexos);
		int maiorComponente = getMaiorComponenteConexo(grafo,
				componentesConexos);

		try {
			pw = new PrintWriter(saida);
			pw.println("Numero de Vértices: " + grafo.getNumeroVertices());
			pw.println("Numero de Arestas: " + grafo.getNumeroArestas());
			pw.println("Grau medio: " + grafo.getGrauMedio());
			pw.println("Maior grau possivel: " + grafo.getMaiorGrauPossivel());
			pw.println("Maior grau: " + grafo.getMaiorGrau());
			pw.println("Menor grau: " + grafo.getMenorGrau());
			pw.println("Numero de componentes conexos: "
					+ numeroComponentesConexos);
			pw.println("Maior componente conexo: " + maiorComponente);

			int vertice = 1;
			for (BigDecimal distribuicao : grafo.getDistribuicaoEmpirica()) {
				pw.println(vertice + " " + distribuicao);
				vertice++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Distribuição Empirica do Grafo
	 */
	public static List<BigDecimal> getDistribuicaoEmpirica(Grafo grafo) {
		List<BigDecimal> distribuicaoEmpirica = new ArrayList<BigDecimal>();
		BigDecimal resultado;
		double divisao;
		for (int i = 0; i < grafo.getListaAdjacencias().size(); i++) {
			divisao = (double) grafo.grauVertice(i)
					/ (double) grafo.getNumeroVertices();
			resultado = new BigDecimal(divisao);
			distribuicaoEmpirica.add(resultado);
		}
		return distribuicaoEmpirica;
	}

	/**
	 * @return Distribuição Empirica do Grafo
	 */
	public static List<BigDecimal> getDistribuicaoEmpiricaDistancia(Grafo grafo) {
		List<BigDecimal> distribuicaoEmpirica = new ArrayList<BigDecimal>();
		BigDecimal resultado;
		double divisao;
		for (int i = 0; i < grafo.getListaAdjacencias().size(); i++) {
			divisao = (double) grafo.grauVertice(i)
					/ (double) grafo.getNumeroArestas();
			resultado = new BigDecimal(divisao);
			distribuicaoEmpirica.add(resultado);
		}
		return distribuicaoEmpirica;
	}

	/**
	 * Gera um arquivo txt que representa a lista de adjacencias de um grafo
	 * 
	 * @param lista
	 *            Lista de adjacencias a ser percorrida
	 * @param caminho
	 *            Caminho do arquivo que sera salvo em disco com a lista de
	 *            adjacencias
	 * @throws IOException
	 */
	public static void printList(Map<Integer, LinkedList<Integer>> lista,
			String caminho) throws IOException {
		File f = new File(caminho);
		PrintWriter pw = new PrintWriter(f);
		for (int indice = 0; indice < lista.size(); indice++) {
			if (lista.get(indice).isEmpty()) {
				pw.println(indice + 1 + " [VAZIO] ");
			} else
				pw.println(indice + 1 + " " + lista.get(indice));
		}
		pw.close();
		System.out
				.println("Lista de adjacencias gerada em disco com sucesso...");
	}

	/**
	 * Gera um arquivo txt que representa a matriz de adjacencias de um grafo
	 * 
	 * @param lista
	 *            Lista de adjacencias a ser percorrida
	 * @param caminho
	 *            Caminho do arquivo que sera salvo em disco com a lista de
	 *            adjacencias
	 * @throws IOException
	 */
	public static void printMatrix(boolean matrix[][], String caminho)
			throws IOException {
		File f = new File(caminho);
		PrintWriter pw = new PrintWriter(f);
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[row].length; column++) {
				if (matrix[row][column] == true) {
					pw.print(1 + " ");
				} else {
					pw.print(0 + " ");
				}
			}
			pw.println();
		}
		pw.close();
		System.out
				.println("Matriz de adjacencias gerada em disco com sucesso...");
	}

	public static List<Integer> getListaGraus(Grafo grafo) {
		List<Integer> listaGraus = new ArrayList<Integer>();
		for (int i = 0; i < grafo.getListaAdjacencias().size(); i++) {
			listaGraus.add(grafo.grauVertice(i));
		}
		return listaGraus;
	}

	/**
	 * 
	 * @param verticeInicial
	 * @return Lista de distancias de um vertice para todos os vertices do grafo
	 */
	public static double[] getDistancia(Object grafo, int verticeInicial) {
		if (grafo instanceof GrafoPeso) {
			Dijkstra dj = new Dijkstra((GrafoPeso) grafo, verticeInicial);
			return dj.getDistancias();

		} else if (grafo instanceof Grafo) {
			Bfs bfs = new Bfs((Grafo) grafo, verticeInicial);
			return bfs.getDistancia();

		} else {
			return null;
		}
	}

	/**
	 * 
	 * @return Maior componente conexo do grafo
	 */
	public static int getMaiorComponenteConexo(Grafo grafo, Dfs componenteConexo) {
		int numeroComponentesConectados = getNumeroComponentesConexos(componenteConexo);
		int maiorComponente = componenteConexo
				.size(numeroComponentesConectados);

		return maiorComponente;
	}

	public static int getNumeroComponentesConexos(Dfs componenteConexo) {
		return componenteConexo.count();
	}

	public static void getCaminhosBfs(Grafo grafo, String caminho, int vertice)
			throws IOException {
		File f = new File(caminho);
		PrintWriter pw = new PrintWriter(f);
		List<Double> distancias = new ArrayList<Double>();
		for (Double d : grafo.getDistancia(vertice))
			distancias.add(d);
		pw.println("Caminho mais longo para o vertice " + vertice + ": "
				+ grafo.getCaminhoMaisLongo(distancias));
		pw.println("Caminhos: " + distancias);

		pw.close();
	}

	public static void getDiametroGrafo(Grafo grafo) {
		System.out.println("Gerando diametro do grafo...");
		long tempoInicial = System.currentTimeMillis();
		List<Double> lista = new ArrayList<Double>();
		for (int vertice = 0; vertice < grafo.getNumeroVertices(); vertice++) {
			List<Double> distancias = new ArrayList<Double>();
			for (Double d : grafo.getDistancia(vertice))
				distancias.add(d);
			double caminhoLongo = grafo.getCaminhoMaisLongo(distancias);
			lista.add(caminhoLongo);
		}
		System.out.println("Diametro do grafo: "
				+ grafo.getCaminhoMaisLongo(lista));
		System.out.println("O diametro da internet foi calculado em: "
				+ (System.currentTimeMillis() - tempoInicial) / 1000 + "ms");
	}

	public static void geraTxtPesos(Grafo grafo, String caminho) {
		File saida = new File(caminho);
		PrintWriter pw;
		double matrix[][] = grafo.getMatrizPesos();
		try {
			pw = new PrintWriter(saida);
			pw.println("Numero de Vértices: " + grafo.getNumeroVertices());
			pw.println("Numero de Arestas: " + grafo.getNumeroArestas());
			for (int row = 0; row < matrix.length; row++) {
				for (int column = 0; column < matrix[row].length; column++) {
					pw.println(row + " " + column + " " + matrix[row][column]);
				}
				pw.println();
			}
			pw.close();
			System.out
					.println("Matriz de pesos gerada em disco com sucesso...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void geraTxtCaminhosDistancias(String caminhoArquivo,	GrafoPeso grafo) {
		File saida = new File(caminhoArquivo);
		PrintWriter pw;
		int numeroVertices = grafo.getNumeroVertices();
		
		try {
			pw = new PrintWriter(saida);
			pw.println(1 + "-" + 10 + " ("
					+ grafo.getCaminhoMinimo(1, 10)+ ")");
			
			pw.println(grafo.getDistanciaMinima(1, 10));
			
			pw.println();
			
			pw.println(1 + "-" + 100 + " ("
					+ grafo.getCaminhoMinimo(1, 100)+ ")");
			
			pw.println(grafo.getDistanciaMinima(1, 100));
			
			if(numeroVertices >= 1000){
				pw.println();
				pw.println(1 + "-" + 1000 + " ("
						+ grafo.getCaminhoMinimo(1, 1000)+ ")");
				
				pw.println(grafo.getDistanciaMinima(1, 1000));
				
				if(numeroVertices >= 10000){
					pw.println();
					pw.println(1 + "-" + 10000 + " ("
							+ grafo.getCaminhoMinimo(1, 10000)+ ")");
					
					pw.println(grafo.getDistanciaMinima(1, 10000));
				}
				
			}
			pw.close();
			System.out.println("Caminhos e distancias gerados...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void geraTxtDistanciaMedia(GrafoPeso grafo, String caminho) {
		File saida = new File(caminho);
		PrintWriter pw;
		try {
			pw = new PrintWriter(saida);
			pw.println("Distancia Media: " + grafo.getDistanciaMediaComPeso());
			pw.close();
			System.out.println("Distancia media gerada em disco com sucesso...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void geraTxtMST(GrafoPeso grafo, String caminho) {
		File saida = new File(caminho);
		PrintWriter pw;
		try {
			pw = new PrintWriter(saida);
			pw.println(grafo.getMst());
			pw.println("Peso MST: "+ grafo.getPesoMst());
			pw.close();
			System.out.println("MST e peso gerados em disco com sucesso...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

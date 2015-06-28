package br.com.grafos;


public class Dfs {
    private boolean[] marked;   // marked[v] = o vertice v foi marcado??
    private int[] id;           // id[v] = id do componente conexo contendo v
    private int[] size;         // size[id] = numero de vertices em uma componente
    private int count;          // numero de componentes conexos

    /**
     * Computa os componentes conexos de um grafo
     * @param grafo 
     *		  Grafo
     */
    public Dfs(Grafo grafo) {
        marked = new boolean[grafo.getNumeroVertices()];
        id = new int[grafo.getNumeroVertices()];
        size = new int[grafo.getNumeroVertices()];
        for (int v = 0; v < grafo.getNumeroVertices(); v++) {
            if (!marked[v]) {
                dfs(grafo, v);
                count++;
            }
        }
    }
    
    
    /**
     * Faz a chamada do dfs
     * @param grafo
     * @param vertice
     */
    private void dfs(Grafo grafo, int vertice) {
        marked[vertice] = true;
        id[vertice] = count;
        size[count]++;
        for (int w : grafo.getVerticesAdjacentes(vertice)) {
            if (!marked[w]) {
                dfs(grafo, w);
            }
        }
    }

    /**
     * Retorna o componente de uma componente conexa contendo o vertice <tt>v</tt> 
     * @param vertice 
     * 		  Vertice
     * @return o componente de uma componente conexa contendo o vertice <tt>v</tt>
     */
    public int id(int vertice) {
        return id[vertice];
    }

    /**
     * Retorna o numero de vertices em uma componente conexa contendo o vertice <tt>v</tt>.
     * @param vertice 
     * 		  Vertice
     * @return o numero de vertices em uma componente conexa contendo o vertice <tt>v</tt>.
     */
    public int size(int vertice) {
        return size[id[vertice]];
    }

    /**
     * 
     * @return Numero de componentes conexos
     */
    public int count() {
        return count;
    }

}
package dados;

import java.util.List;

public abstract class Midia {
    private List<Video> videos;
    private List<Musica> musicas;

    private int codigo;
    private String titulo;
    private int ano;

    private Categoria categoria;

    public Midia(int codigo, String titulo, int ano, Categoria categoria) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getAno() {
        return ano;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public abstract double calculaLocacao();

    public String toString() {
        return getCodigo() + "," + getTitulo() + "," + getAno() + "," + getCategoria().getNome();
    }

}

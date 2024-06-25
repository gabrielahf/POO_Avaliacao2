package dados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Midiateca implements Iterator {
    private List<Midia> midias;
    private int contador;

    public Midiateca() {
        midias = new ArrayList<>();
        this.contador = 0;
    }

    public boolean cadastraMidia(Midia jogo) {
        if ((consultaPorCodigo(jogo.getCodigo()) == null)) {
            midias.add(jogo);
            return true;
        } else
            return false;

    }

    public Midia consultaPorCodigo(int codigo) {
        for (Midia midia : midias) {
            if (midia.getCodigo() == codigo) {
                return midia;
            }
        }
        return null;
    }

    public List<Midia> consultaPorCategoria(Categoria categoria) {
        List<Midia> midias1 = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia.getCategoria().equals(categoria)) {
                midias1.add(midia);
            }
        }
        // if (midias1.isEmpty()) {
        // return null;
        // } else
        return midias1;
    }

    public boolean removeMidia(int codigo) {
        for (Midia midia : midias) {
            if (midia.getCodigo() == codigo) {
                midias.remove(midia);
                return true;
            }
        }
        return false;
    }

    // reinicia a iteraçao na coleção
    @Override
    public void reset() {
        contador = 0;
    }

    // retorna true se ainda ha elementos para a iteração
    @Override
    public boolean hasNext() {
        return contador < midias.size();
    }

    // retorna o proximo elemento da iteraçao
    @Override
    public Object next() {
        Midia midia = midias.get(contador);
        contador++;
        return midia;
    }

    public List<Midia> consultaPorQualidade(int qualidade) {
        List<Midia> videoComQualidade = new ArrayList<>();

        for (Midia midia : midias) {
            if (midia instanceof Video && ((Video) midia).getQualidade() == qualidade) {
                videoComQualidade.add(midia);
            }
        }
        return videoComQualidade;
    }

    public boolean consultaVideoQualidade(int qualidade) {
        for (Midia midia : midias) {
            if (midia instanceof Video && ((Video) midia).getQualidade() == qualidade) {
                return true;
            }
        }
        return false;
    }

    public Midia musicaDuracaoMaior() {
        Musica musicaMaiorDuracao = null;

        for (Midia midia : midias) {
            if (midia instanceof Musica) {
                Musica musica = (Musica) midia;
                if (musicaMaiorDuracao == null || musica.getDuracao() > musicaMaiorDuracao.getDuracao()) {
                    musicaMaiorDuracao = musica;
                }
            }
        }
        return musicaMaiorDuracao;
    }

    public double totalSomaLocacao() {
        double somatorio = 0.0;

        for (Midia midia : midias) {
            somatorio += midia.calculaLocacao();
        }

        double somaFormatada = Math.round(somatorio * 100.0) / 100.0;
        return somaFormatada;
    }

    public Midia midiaMaisRecente() {
        return midias.stream() // sequencia de elementos p/ suportar operaçoes de processamento de dados
                .filter(m -> m instanceof Musica || m instanceof Video) // apenas midias de instancias musica e video
                .max(Comparator.comparing(m -> {
                    if (m instanceof Musica) {
                        return ((Musica) m).getAno();
                    } else if (m instanceof Video) {
                        return ((Video) m).getAno();
                    }
                    return null;
                }))
                .orElse(null);
    }

}

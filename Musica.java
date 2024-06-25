package dados;

import java.text.DecimalFormat;

public class Musica extends Midia {
    private double duracao;

    public Musica(int codigo, String titulo, int ano, Categoria categoria, double duracao) {
        super(codigo, titulo, ano, categoria);
        this.duracao = duracao;
    }

    public double getDuracao() {
        return duracao;
    }

    @Override
    public double calculaLocacao() {
        switch (getCategoria()) {
            case ACA:
                return getDuracao() * 0.90;
            case DRA:
                return getDuracao() * 0.70;
            case FIC:
                return getDuracao() * 0.50;
            case ROM:
                return getDuracao() * 0.30;
            default:
                break;
        }
        return 0;
    }

    public String toString() {
        double valorFormatado = Math.round(calculaLocacao() * 100.0) / 100.0;
        DecimalFormat df = new DecimalFormat("#0.00");
        return super.toString() + "," + df.format(getDuracao()) + "," + valorFormatado;
    }

}

package dados;

public class Video extends Midia {
    private int qualidade;

    public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
        super(codigo, titulo, ano, categoria);
        this.qualidade = qualidade;
    }

    public int getQualidade() {
        return qualidade;
    }

    @Override
    public double calculaLocacao() {
        // se ano 2024 - valor da locação 20 reais
        if (getAno() == 2024)
            return 20.0;
        // se entre 2000 a 2023 - valor da locação 15 reais
        else if (getAno() >= 2000 && getAno() <= 2023) {
            return 15.0;
            // antes de 2000 - valor da locação 10 reais
        } else if (getAno() < 2000) {
            return 10.0;
        } else {
            return 0;
        }
    }

    public String toString() {
        double valorFormatado = Math.round(calculaLocacao() * 100.0) / 100.0;
        return super.toString() + "," + getQualidade() + "," + valorFormatado;
    }
}

package app;

import dados.Categoria;
import dados.Midia;
import dados.Midiateca;
import dados.Musica;
import dados.Video;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ACMEMidia {
    private static Midiateca midiateca;

    // Atributos para redirecionamento de E/S
    private static Scanner entrada = new Scanner(System.in); // Atributo para entrada de dados
    // private PrintStream saidaPadrao = System.out; // Guarda a saida padrao - tela
    // (console)
    private final String nomeArquivoEntrada = "entrada.txt"; // Nome do arquivo de entrada de dados
    private final String nomeArquivoSaida = "saida.txt"; // Nome do arquivo de saida de dados

    public ACMEMidia() {
        midiateca = new Midiateca();
        redirecionaES(); // Redireciona E/S para arquivos
    }

    public void executar() {
        cadastrarVideo(); // 1
        cadastrarMusica(); // 2
        MostrarDadosMidia(); // 3
        MostrarDadosMidiaCategoria(); // 4
        MostrarDadosVideoQualidade(); // 5
        MostrarDadosMusicaDuracao(); // 6
        RemoverMidia(); // 7
        MostrarSomatorioLocacaoMidia(); // 8
        // MostrarComparacaoValorMusicaLocacao(); //mostrar os dados da musica com valor
        // de locaçao mais próximo da media dos valores de locação
        MostrarDadosMidiaRecente(); // 10

    }

    /*
     * 1. Cadastrar vídeos: lê todos os dados de cada vídeo e, se o código não for
     * repetido
     * no sistema, cadastra-o no sistema. Se o código da vídeo for repetido mostra a
     * mensagem no formato: 1:Erro-video com codigo repetido: codigo
     * Para cada vídeo cadastrado com sucesso no sistema, mostra os dados da vídeo
     * no formato: 1:codigo,titulo,ano,categoria,qualidade
     */

    /*
     * No passo 1. Cadastrar vídeos: cada linha corresponde ao código, título, ano,
     * categoria e qualidade de um vídeo. Quando a linha lida for -1, não há mais
     * vídeos
     * a serem cadastrados
     */
    private void cadastrarVideo() {
        int codigo = 0;
        String titulo;
        int ano = 0;
        String cat;
        int qualidade;

        // System.out.println("Digite o codigo do video: ");
        codigo = entrada.nextInt();
        entrada.nextLine();

        while (codigo != -1) {
            // System.out.println("Digite o titulo do video: ");
            titulo = entrada.nextLine();

            // System.out.println("Digite o ano do video: ");
            ano = entrada.nextInt();
            entrada.nextLine();

            // System.out.println("Digite a categoria do video: ");
            cat = entrada.nextLine().toUpperCase();
            Categoria categoria = Categoria.valueOf(cat);

            // System.out.println("Digite a qualidade do video: ");
            qualidade = entrada.nextInt();
            entrada.nextLine();

            Video video = new Video(codigo, titulo, ano, categoria, qualidade);
            // videoList.add(video);
            if (midiateca.cadastraMidia(video)) {
                System.out.println(
                        "1:" + codigo + "," + titulo + "," + ano + "," + categoria.getNome() + "," + qualidade);
            } else {
                System.out.println("1:Erro-video com codigo repetido: " + codigo);
            }

            codigo = entrada.nextInt();
            entrada.nextLine();

        }

    }

    /*
     * : lê todos os dados de cada música e, se o código não for
     * repetido no sistema, cadastra-a no sistema. Se o código da música for
     * repetido
     * mostra a mensagem no formato: 2:Erro-musica com codigo repetido:
     * codigo. Para cada música cadastrada com sucesso no sistema, mostra os dados
     * da música
     * no formato: 2:codigo,titulo,ano,categoria,duração
     */

    /*
     * No passo 2. Cadastrar músicas: cada linha corresponde ao código, título, ano,
     * categoria e duração de uma música. Quando a linha lida for -1, não há mais
     * músicas a serem cadastradas
     */
    private void cadastrarMusica() {
        int codigo = 0;
        String titulo;
        int ano = 0;
        String cat;
        double duracao;

        // System.out.println("Digite o codigo do video: ");
        codigo = entrada.nextInt();
        entrada.nextLine();

        while (codigo != -1) {
            // System.out.println("Digite o titulo do video: ");
            titulo = entrada.nextLine();

            // System.out.println("Digite o ano do video: ");
            ano = entrada.nextInt();
            entrada.nextLine();

            // System.out.println("Digite a categoria do video: ");
            cat = entrada.nextLine().toUpperCase();
            Categoria categoria = Categoria.valueOf(cat);

            // System.out.println("Digite a qualidade do video: ");
            duracao = entrada.nextDouble();
            entrada.nextLine();

            Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);
            // videoList.add(video);
            if (midiateca.cadastraMidia(musica)) {
                System.out.println(
                        "2:" + codigo + "," + titulo + "," + ano + "," + categoria.getNome() + "," + duracao);
            } else {
                System.out.println("2:Erro-musica com codigo repetido: " + codigo);
            }

            codigo = entrada.nextInt();
            entrada.nextLine();

        }
    }

    /*
     * Mostrar os dados de uma determinada mídia: lê o código de uma mídia. Se não
     * existir uma mídia com o código indicado, mostra a mensagem de erro: 3:Codigo
     * inexistente
     */
    private void MostrarDadosMidia() {
        int codigo = 0;

        // System.out.println("Digite o codigo da midia: ");
        codigo = entrada.nextInt();
        entrada.nextLine();

        Midia midia = midiateca.consultaPorCodigo(codigo);

        if (midia == null) {
            System.out.println("3:Codigo inexistente.");
        } else {
            System.out.println("3:" + midia.toString());
        }
    }

    /*
     * Mostrar os dados de mídia(s) de uma determinada categoria: lê a categoria de
     * uma mídia. Se não existir uma mídia com a categoria indicada, mostra a
     * mensagem
     * de erro: 4:Nenhuma midia encontrada.
     */
    private void MostrarDadosMidiaCategoria() {
        String cat;

        cat = entrada.nextLine().toUpperCase();
        Categoria categoria;

        try {
            categoria = Categoria.valueOf(cat);
        } catch (IllegalArgumentException e) {
            System.out.println("4:Nenhuma midia encontrada.");
            return;
        }

        List<Midia> midiaCategoria = midiateca.consultaPorCategoria(categoria);

        if (midiaCategoria.isEmpty()) {
            System.out.println("4:Nenhuma midia encontrada.");
        } else {
            for (Midia midia : midiaCategoria) {
                System.out.println("4:" + midia.toString());

                List<Video> videos = midia.getVideos();
                if (videos != null && !videos.isEmpty()) {
                    for (Video video : videos) {
                        System.out.println("4:" + video.toString());
                    }
                }

                List<Musica> musicas = midia.getMusicas();
                if (musicas != null && !musicas.isEmpty()) {
                    for (Musica musica : musicas) {
                        System.out.println("4:" + musica.toString());
                    }
                }
            }
        }

    }

    /*
     * Mostrar os dados de vídeo(s) de uma determinada qualidade: lê a qualidade
     * de vídeo. Se não existir a qualidade indicada, mostra a mensagem de erro:
     * 5:Qualidade inexistente.
     */
    private void MostrarDadosVideoQualidade() {
        int qualidade;
        List<Midia> qualidades;

        // System.out.println("Digite a qualidade do video: ");
        qualidade = entrada.nextInt();
        entrada.nextLine();

        if (!midiateca.consultaVideoQualidade(qualidade)) {
            System.out.println("5:Qualidade inexistente.");
            return;
        }

        qualidades = midiateca.consultaPorQualidade(qualidade);

        for (Midia midia : qualidades) {
            System.out.println("5:" + midia.toString());
        }
    }

    /*
     * Mostrar os dados da música de maior duração: localiza a música cadastrada
     * com maior duração. Se não existir nenhuma música cadastrada, mostra a
     * mensagem de erro: 6:Nenhuma música encontrada.
     */
    private void MostrarDadosMusicaDuracao() {
        Musica musicaMaiorDuracao = (Musica) midiateca.musicaDuracaoMaior();
        if (musicaMaiorDuracao != null) {
            DecimalFormat df = new DecimalFormat("#0.00");
            System.out
                    .println("6:" + musicaMaiorDuracao.getTitulo() + "," + df.format(musicaMaiorDuracao.getDuracao()));
        } else {
            System.out.println("6:Nenhuma musica encontrada.");
        }

    }

    /*
     * Remover uma mídia: lê o código de uma mídia. Se não existir uma mídia com o
     * código indicado, mostra a mensagem de erro: 7:Codigo inexistente.
     * Se existir, mostra os dados da mídia no formato:
     * 7:atributo1,atributo2,atributo3,...,valor da locação e depois a
     * remove do sistema
     */
    private void RemoverMidia() {
        int codigo;

        // System.out.println("Digite o codigo da midia: ");
        codigo = entrada.nextInt();
        // entrada.nextLine();

        Midia midias = midiateca.consultaPorCodigo(codigo);

        if (midias != null) {
            System.out.println("7:" + midias.toString());
            midiateca.removeMidia(codigo);
        } else {
            System.out.println("7:Codigo inexistente.");
        }
    }

    /*
     * Mostrar o somatório de locações de todas as mídias: calcula o somatório do
     * valor de locação de todas as mídias do sistema. Se não existir mídia
     * cadastrada
     * no sistema, mostra a mensagem de erro: 8:Nenhuma midia encontrada.
     * Se existir, mostra a mensagem no formato: 8:valor do somatório
     */
    private void MostrarSomatorioLocacaoMidia() {
        double somaValorLocacao = midiateca.totalSomaLocacao();

        if (somaValorLocacao != 0.0) {
            System.out.println("8:" + somaValorLocacao);
        } else {
            System.out.println("8: Nenhuma midia encontrada.");
        }
    }

    /*
     * Mostrar os dados da mídia mais nova: mostra os dados da mídia mais nova. Se
     * não
     * existir nenhuma mídia cadastrada, mostra a mensagem de erro: 10:Nenhuma midia
     * encontrada.
     * Se existir, mostra os dados da mídia no formato: 10:codigo,titulo,ano
     */
    private void MostrarDadosMidiaRecente() {
        Midia midiaNova = midiateca.midiaMaisRecente();

        if (midiaNova != null) {
            System.out.println("10:" + midiaNova.getCodigo() + "," + midiaNova.getTitulo() + "," + midiaNova.getAno());
        } else {
            System.out.println("10:Nenhuma midia encontrada.");
        }
    }

    // Redireciona E/S para arquivos
    // Chame este metodo para redirecionar a leitura e escrita de dados para
    // arquivos
    private void redirecionaES() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            entrada = new Scanner(streamEntrada); // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida); // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH); // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH); // Ajusta para leitura para ponto decimal
    }

    // Restaura E/S padrao de tela(console)/teclado
    // Chame este metodo para retornar a leitura e escrita de dados para o padrao
    // private void restauraES() {
    // System.setOut(saidaPadrao);
    // entrada = new Scanner(System.in);
    // }

}

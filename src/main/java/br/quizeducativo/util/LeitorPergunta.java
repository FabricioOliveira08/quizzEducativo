package br.quizeducativo.util;

import br.quizeducativo.exception.ArquivoPerguntasException;
import br.quizeducativo.model.Pergunta;
import br.quizeducativo.model.PerguntaImagem;
import br.quizeducativo.model.PerguntaTexto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorPergunta {

    private static final String CAMINHO_ARQUIVO = "/data/perguntas.csv";

    public static List<Pergunta> carregarPerguntas() throws ArquivoPerguntasException {
        List<Pergunta> listaPerguntas = new ArrayList<>();

        InputStream is = LeitorPergunta.class.getResourceAsStream(CAMINHO_ARQUIVO);

        if(is == null){
                throw new ArquivoPerguntasException("Arquivo " + CAMINHO_ARQUIVO + " não foi encontrado na pasta resources.");
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))){
            String linha;
            boolean primeiraLinha = true;

            while((linha = br.readLine()) != null){
                if(primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                if(linha.trim().isEmpty()) {
                    continue;
                }
                String[] dados = linha.split(";");

                String tipo = dados[0].trim().toUpperCase();
                String enunciado = dados[1].trim();

                List<String> alternativas = Arrays.asList(dados[2].split("\\|"));

                int indiceCorreta = Integer.parseInt(dados[3].trim());

                if(tipo.equals("TEXTO")) {
                    listaPerguntas.add(new PerguntaTexto(enunciado, alternativas, indiceCorreta));
                }
                else if(tipo.equals("IMAGEM")) {
                    String caminhoImagem = dados[4].trim();
                    listaPerguntas.add(new PerguntaImagem(enunciado, alternativas, indiceCorreta,caminhoImagem));
                }
            }
        }catch (Exception e) {
            throw new ArquivoPerguntasException("Erro ao ler o arquivo de perguntas: " + e.getMessage());
        }
        return listaPerguntas;
    }

}

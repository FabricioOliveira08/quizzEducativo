package br.quizeducativo.util.dao;

import br.quizeducativo.model.Jogador;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class RankingDAO {
    private static final String CAMINHO_ARQUIVO = "ranking.csv";

    public void salvarResultado(Jogador jogador, int totalPerguntas) {
        try(FileWriter fw = new FileWriter(CAMINHO_ARQUIVO, true);
            PrintWriter pw = new PrintWriter(fw)) {
            String dataHoje = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm"));
            String linhaCSV = jogador.getNome() + ";" + jogador.getPontuacaoFinal() + ";" + totalPerguntas + ";" + dataHoje;
            pw.println(linhaCSV);
            System.out.println("Resultado salvo com sucesso no ranking.csv!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar no arquivo de ranking: " + e.getMessage());
        }
    }

    public List<String> obterTop5Ranking() {
        List<String[]> todasAsLinhas = new ArrayList<>();
        List<String> top5Formatado = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))){
            String linha;
            while ((linha = br.readLine()) != null){
                if(linha.trim().isEmpty() || !linha.contains(";")){
                    continue;
                }
                String[] dados = linha.split(";");

                if(dados.length >= 2) {
                    todasAsLinhas.add(dados);
                }
            }
        } catch (IOException e) {
            return top5Formatado;
        }

        todasAsLinhas.sort((jogadorA, jogadorB) -> Integer.compare(Integer.parseInt(jogadorB[1]), Integer.parseInt(jogadorA[1])));

        int limite = Math.min(5, todasAsLinhas.size());
        for (int i = 0; i < limite; i++) {
            String[] dados = todasAsLinhas.get(i);
            top5Formatado.add((i + 1) + "º Lugar: " + dados[0] + " - " + dados[1] + " pontos");
        }
        return top5Formatado;
    }
}

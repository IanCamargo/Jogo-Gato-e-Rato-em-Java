import java.util.*;

class Porta {
    private boolean temRato;

    public Porta() {
        this.temRato = false;
    }

    public void esconderRato() {
        this.temRato = true;
    }

    public boolean verificarRato() {
        return temRato;
    }
}

class Jogador {
    private String nome;
    private int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void adicionarPontos(int pontos) {
        this.pontuacao += pontos;
    }
}

public class JogoGatoERato {
    private List<Porta> portas;
    private Jogador jogador;
    private int pontuacaoInicial;

    public JogoGatoERato(String nomeJogador, int quantidadePortas) {
        this.jogador = new Jogador(nomeJogador);
        this.portas = new ArrayList<>();

        for (int i = 0; i < quantidadePortas; i++) {
            portas.add(new Porta());
        }

        // Esconde o rato em uma porta aleatoria
        Random rand = new Random();
        portas.get(rand.nextInt(quantidadePortas)).esconderRato();

        // Define a pontuacao base: Facil: 10 portas - 100 pontos / Intermediario: 30 portas - 300 pontos / Dificil: 50 portas - 500 pontos
        this.pontuacaoInicial = quantidadePortas * 10;
    }

    public int getPontuacaoRodada() {
        return jogador.getPontuacao();
    }

    // Se acertar de primeira ganha 100, 300 ou 500 depende do nível de dificuldade, colocou uma porta inválida ele avisa, acertou na ultima tentativa ganha 10 pontos
    public boolean verificarPorta(int indice, int tentativa) {
        if (indice < 0 || indice >= portas.size()) {
            System.out.println("Porta inválida. Tente novamente.");
            return false;
        }
        if (portas.get(indice).verificarRato()) {
            int pontos = pontuacaoInicial - ((tentativa - 1) * 10);

            if (tentativa == portas.size()) {
                pontos = 10;
            }
            jogador.adicionarPontos(pontos);
            return true;
        }
        return false;
    }

    // Estratégia manual: o usuário escolhe a porta até encontrar o rato.
    public int jogarManual() {
        Scanner scanner = new Scanner(System.in);
        boolean ratoEncontrado = false;
        int tentativas = 0;

        while (!ratoEncontrado) {
            System.out.println("Digite o número da porta que deseja abrir (1 a " + portas.size() + "):");
            int portaEscolhida = scanner.nextInt() - 1;
            tentativas++;
            if (verificarPorta(portaEscolhida, tentativas)) {
                System.out.println("Parabéns, " + jogador.getNome() + "! Você encontrou o rato na tentativa " + tentativas + "!");
                ratoEncontrado = true;
            } else {
                System.out.println("Rato não encontrado nessa porta. Tente novamente!");
            }
        }

        System.out.println("Fim de jogo! Você fez " + jogador.getPontuacao() + " pontos nesta rodada.");
        return jogador.getPontuacao();
    }

    // Estratégia automática: o sistema testa as portas de forma aleatória, com pausa de 1 segundo entre as tentativas.
    public int jogarAutomatico() {
        boolean ratoEncontrado = false;
        int tentativas = 0;
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < portas.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        for (int indice : indices) {
            tentativas++;
            System.out.println("Testando porta " + (indice + 1) + "...");
            if (verificarPorta(indice, tentativas)) {
                System.out.println("Parabéns, " + jogador.getNome() + "! O rato foi encontrado na porta " + (indice + 1) + " na tentativa " + tentativas + "!");
                ratoEncontrado = true;
                break;
            }
            try {
                Thread.sleep(1000); // pausa de 1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Fim de jogo! Você fez " + jogador.getPontuacao() + " pontos nesta rodada.");
        return jogador.getPontuacao();
    }

    // Metodo para configurar e iniciar uma rodada para um determinado jogador e dificuldade.
    public static int iniciarRodada(String nomeJogador, int quantidadePortas) {
        Scanner scanner = new Scanner(System.in);
        JogoGatoERato jogo = new JogoGatoERato(nomeJogador, quantidadePortas);

        System.out.println("Escolha sua estratégia:");
        System.out.println("1 - Procurar manualmente (uma porta por tentativa)");
        System.out.println("2 - Procurar automaticamente (testa as portas de forma aleatória)");
        int estrategia = scanner.nextInt();

        int pontosRodada = 0;
        if (estrategia == 1) {
            pontosRodada = jogo.jogarManual();
        } else if (estrategia == 2) {
            pontosRodada = jogo.jogarAutomatico();
        } else {
            System.out.println("Opção inválida. Iniciando busca manual por padrão.");
            pontosRodada = jogo.jogarManual();
        }
        return pontosRodada;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> placar = new HashMap<>();

        System.out.println("Bem-vindo ao Jogo Gato e Rato!");
        System.out.println("Digite a quantidade de rodadas da partida:");
        int quantidadeRodadas = scanner.nextInt();
        System.out.println("Digite a quantidade de jogadores:");
        int quantidadeJogadores = scanner.nextInt();
        scanner.nextLine();

        List<String> nomesJogadores = new ArrayList<>();
        for (int i = 0; i < quantidadeJogadores; i++) {
            System.out.println("Digite o nome do jogador " + (i + 1) + ":");
            String nome = scanner.nextLine();
            nomesJogadores.add(nome);
            placar.put(nome, 0);
        }

        System.out.println("Escolha o nível de dificuldade:");
        System.out.println("1 - Fácil (10 portas -> 100 pontos se acertar de primeira)");
        System.out.println("2 - Intermediário (30 portas -> 300 pontos se acertar de primeira)");
        System.out.println("3 - Difícil (50 portas -> 500 pontos se acertar de primeira)");
        int opcaoDificuldade = scanner.nextInt();
        int quantidadePortas;
        switch (opcaoDificuldade) {
            case 1:
                quantidadePortas = 10;
                break;
            case 2:
                quantidadePortas = 30;
                break;
            case 3:
                quantidadePortas = 50;
                break;
            default:
                System.out.println("Opção inválida. Nível Fácil selecionado por padrão.");
                quantidadePortas = 10;
        }

        for (int rodada = 1; rodada <= quantidadeRodadas; rodada++) {
            System.out.println("\n--- Rodada " + rodada + " ---");
            for (String nome : nomesJogadores) {
                System.out.println("\nVez de " + nome + " jogar:");
                int pontosRodada = iniciarRodada(nome, quantidadePortas);
                placar.put(nome, placar.get(nome) + pontosRodada);

                System.out.println("\n--- Placar após a Rodada " + rodada + " ---");
                exibirPlacarOrdenado(placar);
            }
        }

        System.out.println("\n--- Placar Final ---");
        exibirPlacarOrdenado(placar);

        // Vencedor
        String vencedor = "";
        int pontuacaoVencedor = -1;
        for (Map.Entry<String, Integer> entry : placar.entrySet()) {
            if (entry.getValue() > pontuacaoVencedor) {
                vencedor = entry.getKey();
                pontuacaoVencedor = entry.getValue();
            }
        }

        // Parabens
        System.out.println("\nParabéns, " + vencedor + "! Você venceu com " + pontuacaoVencedor + " pontos!");

        System.out.println("\nObrigado por jogar!");
    }

    // Metodo auxiliar para exibir o placar ordenado por pontuação
    public static void exibirPlacarOrdenado(Map<String, Integer> placar) {
        List<Map.Entry<String, Integer>> listaPlacar = new ArrayList<>(placar.entrySet());
        listaPlacar.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int posicao = 1;
        for (Map.Entry<String, Integer> entry : listaPlacar) {
            System.out.println(posicao + "° - " + entry.getKey() + ": " + entry.getValue() + " pontos");
            posicao++;
        }
    }
}
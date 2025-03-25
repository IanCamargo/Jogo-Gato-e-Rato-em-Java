# Jogo Gato e Rato em Java

Este é um jogo simples desenvolvido em Java que utiliza conceitos de Programação Orientada a Objetos. Nele, os jogadores tentam encontrar o rato escondido atrás de uma porta, escolhendo entre duas estratégias: **manual** ou **automática**.

## 💼 Informações sobre o Trabalho

**Faculdade de Tecnologia de Mogi Mirim**

**Curso:** Análise e Desenvolvimento de Sistemas (Noturno)

**Matérias:**

-   🖥️ Programação Orientada a Objetos

**Professor:**

-   👨🏻‍🏫 **Marcos Roberto de Moraes (Maromo)** - [@maromo71](https://github.com/maromo71)

**Autor:**

-   **Ian Camargo** - [@IanCamargo](https://github.com/IanCamargo)

---

## Descrição

No jogo, cada partida é composta por um número definido de rodadas e pode envolver vários jogadores. Antes do início, o usuário define:
- A quantidade de rodadas da partida.
- A quantidade de jogadores e os respectivos nomes.
- O nível de dificuldade da partida:
  - **Fácil:** 10 portas (100 pontos se acertar de primeira).
  - **Intermediário:** 30 portas (300 pontos se acertar de primeira).
  - **Difícil:** 50 portas (500 pontos se acertar de primeira).

A cada rodada, cada jogador tem sua vez de jogar. Durante a sua vez, o jogador pode escolher uma estratégia:
- **Manual:** O jogador escolhe qual porta abrir em cada tentativa. Se uma porta já foi escolhida, o sistema avisa e solicita outra escolha.
- **Automática:** O sistema testa as portas de forma aleatória, com uma pausa de 1 segundo entre as tentativas.

A pontuação é calculada com base na dificuldade escolhida. Se o jogador acertar na primeira tentativa, ele ganha a pontuação máxima (100, 300 ou 500 pontos, de acordo com o nível). A cada tentativa errada, o jogador perde 10 pontos, e se o acerto ocorrer na última tentativa, ele ganha 10 pontos.

Ao final de cada rodada, o placar geral é exibido ordenado por pontuação. Ao término da partida, o vencedor é anunciado com base na pontuação acumulada.

## Funcionalidades

- **Multiplayer:** Permite definir múltiplos jogadores para competir em uma partida.
- **Rodadas Múltiplas:** O número de rodadas é definido pelo usuário.
- **Escolha de Dificuldade:** Três níveis de dificuldade que alteram o número de portas e a pontuação máxima.
- **Duas Estratégias de Jogo:**
  - **Manual:** O jogador escolhe uma porta por tentativa, com verificação para evitar repetição de escolha.
  - **Automática:** As portas são testadas aleatoriamente com pausa entre as tentativas.
- **Placar:** Ao final de cada rodada, o placar é exibido com a posição dos jogadores, e ao final da partida, o vencedor é anunciado.

## Como Executar

### Pré-requisitos

- JDK (Java Development Kit) instalado (versão 8 ou superior).

### Compilação e Execução

1. Clone este repositório ou baixe o código.
2. Abra o terminal na pasta onde o arquivo `JogoGatoERato.java` está localizado.
3. Compile o código:

   ```bash
   javac JogoGatoERato.java
   ```

4. Execute o jogo:

   ```bash
   java JogoGatoERato
   ```

## Como Jogar

1. **Configuração Inicial:**
   - Informe a quantidade de rodadas.
   - Informe o número de jogadores e os respectivos nomes.
   - Escolha o nível de dificuldade (Fácil, Intermediário ou Difícil).

2. **Durante a Partida:**
   - Para cada rodada, cada jogador terá sua vez de jogar.
   - O jogador escolherá a estratégia (manual ou automática):
     - **Manual:** Escolha uma porta (entre 1 e o número total de portas). Se a porta já foi escolhida, será solicitado outro número.
     - **Automática:** O sistema testará as portas aleatoriamente até encontrar o rato.
   - A pontuação para a rodada é calculada de acordo com o número de tentativas.
   - Ao fim de cada rodada, o placar geral é exibido.

3. **Final da Partida:**
   - Após todas as rodadas, o placar final é mostrado e o vencedor é anunciado.

## Estrutura do Código

- **Classe `Porta`:** Representa uma porta onde o rato pode estar escondido.
- **Classe `Jogador`:** Representa um jogador e mantém seu nome e pontuação acumulada.
- **Classe `JogoGatoERato`:** Contém a lógica principal do jogo, gerenciamento de rodadas, estratégias (manual e automática) e cálculo de pontuação.

## Considerações

Este projeto foi desenvolvido para demonstrar os conceitos de Programação Orientada a Objetos em Java, incluindo encapsulamento, uso de coleções e controle de fluxo. Sinta-se à vontade para contribuir com melhorias ou adaptações.

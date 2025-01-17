    import java.util.Random;
    import java.util.Scanner;

    public class DesafioDaSorte {

        private static Scanner scanner = new Scanner(System.in);
        private static Random random = new Random(); // Cria um random para gerar números aleatórios
        private static final int MAX_TENTATIVAS = 7; // Define o máximo de tentativas

        public static void main(String[] args) {
            boolean jogarNovamente;
            do {
                jogarJogo(); // Chama o método para jogar o jogo
                jogarNovamente = desejaJogarNovamente(); // Pergunta se o usuário deseja jogar novamente
            } while (jogarNovamente); // Repete o jogo se o usuário quiser

            System.out.println("Obrigado por jogar!"); // Mensagem de despedida
            scanner.close(); // Fecha o scanner
        }

        private static void jogarJogo() {
            int numeroGerado = random.nextInt(100) + 1; // Gera um número aleatório entre 1 e 100
            int tentativas = MAX_TENTATIVAS;

            System.out.println("Bem-vindo ao Desafio da Sorte! Boa sorte.");
            System.out.println("Eu gerei um número entre 1 e 100. Você tem " + MAX_TENTATIVAS + " tentativas para adivinhar.");

            while (tentativas > 0) {
                int palpite = obterPalpiteDoUsuario(); // Obtém o palpite do usuário
                if (palpite == numeroGerado) { // Se o palpite estiver correto, sai do loop e imprime mensagem de sucesso
                    System.out.println("Parabéns! Você acertou na mosca! A decisão do lugar e dia será definido junto ao amor da sua vida");
                    return;
                }
                tentativas = processarPalpite(numeroGerado, palpite, tentativas); // Processa o palpite do usuário
            }

            if (tentativas == 0) {
                System.out.println("Você perdeu! O número era " + numeroGerado + "."); // Mensagem de derrota se acabarem as tentativas
            }
        }

        private static int obterPalpiteDoUsuario() {
            System.out.print("Digite seu palpite: ");
            return scanner.nextInt(); // Lê o palpite
        }

        private static int processarPalpite(int numeroGerado, int palpite, int tentativasRestantes) {
            switch (Integer.compare(palpite, numeroGerado)) { // Compara o palpite com o número gerado
                case -1:
                    System.out.println("O número gerado é maior.");
                    break;
                case 1:
                    System.out.println("O número gerado é menor.");
                    break;
            }

            tentativasRestantes--; // Diminui o número de tentativas restantes
            System.out.println("Você tem mais " + tentativasRestantes + " tentativas restantes.");
            return tentativasRestantes; // Retorna o número de tentativas restantes
        }

        private static boolean desejaJogarNovamente() {
            System.out.print("Você deseja jogar novamente? (s/n): "); // Pergunta se o usuário quer jogar novamente
            String resposta = scanner.next(); // Lê a resposta
            return resposta.equalsIgnoreCase("s"); // Retorna true se o usuário digitar 's'
        }
    }

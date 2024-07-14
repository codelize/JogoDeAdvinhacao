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

        while (tentativas > 0) {
            int palpite = obterPalpiteDoUsuario(numeroGerado); // Obtém o palpite do usuário, passando o número gerado para verificar o easter egg
            if (palpite == 1001) {
                continue; // Pula o restante do loop, não conta a tentativa
            }
            if (palpite == numeroGerado) { // Se o palpite estiver correto, sai do loop e imprime mensagem de sucesso
                if (tentativas == MAX_TENTATIVAS) {
                    System.out.println("Na mosca! Você acertou na primeira tentativa! Incrível!");
                } else if (tentativas == 1) {
                    System.out.println("Parabéns! Você acertou na última tentativa! Foi por pouco!");
                } else {
                    System.out.println("Parabéns! Você acertou na " + (MAX_TENTATIVAS - tentativas + 1) + "ª tentativa!");
                }
                return;
            }
            tentativas = processarPalpite(numeroGerado, palpite, tentativas); // Processa o palpite do usuário

            // Adicionada a linha para imprimir tentativas restantes
            if (tentativas > 0) {
                System.out.println("Você tem mais " + tentativas + " tentativas restantes.");
            }
        }

        // Adicionada a linha para imprimir a mensagem de tentativas esgotadas
        System.out.println("Oh não! Todas tentativas foram esgotadas. O número era " + numeroGerado + "."); // Mensagem de derrota se acabarem as tentativas
    }

    private static int obterPalpiteDoUsuario(int numeroGerado) {
        int palpite = 0;
        boolean palpiteValido = false;

        while (!palpiteValido) {
            System.out.print("Digite seu palpite (1 a 100): ");
            if (scanner.hasNextInt()) {
                palpite = scanner.nextInt();
                if (palpite == 1001) {
                    System.out.println("Easter Egg! O número gerado é " + numeroGerado + ".");
                    palpiteValido = true;
                } else if (palpite >= 1 && palpite <= 100) {
                    palpiteValido = true;
                } else {
                    System.out.println("Opção inválida. Digite um número entre 1 e 100.");
                }
            } else {
                System.out.println("Opção inválida. Digite um número entre 1 e 100.");
                scanner.next(); // Limpa a entrada inválida
            }
        }

        return palpite; // Lê o palpite
    }

    private static int processarPalpite(int numeroGerado, int palpite, int tentativasRestantes) {
        if (palpite != 1001) { // Apenas processa se o palpite não for 1001
            switch (Integer.compare(palpite, numeroGerado)) { // Compara o palpite com o número gerado
                case -1:
                    System.out.println("O número gerado é maior.");
                    break;
                case 1:
                    System.out.println("O número gerado é menor.");
                    break;
            }

            return --tentativasRestantes; // Decrementa e retorna o número de tentativas restantes
        }
        return tentativasRestantes; // Retorna o mesmo número de tentativas se for 1001
    }

    private static boolean desejaJogarNovamente() {
        String resposta;
        boolean respostaValida = false;

        while (!respostaValida) {
            System.out.print("Você deseja jogar novamente? (s/n): "); // Pergunta se o usuário quer jogar novamente
            resposta = scanner.next(); // Lê a resposta
            if (resposta.equalsIgnoreCase("s") || resposta.equalsIgnoreCase("n")) {
                respostaValida = true;
                if (resposta.equalsIgnoreCase("s")) {
                    System.out.println("Ótimo, você escolheu jogar novamente. Vamos começar outra vez!");
                    return true; // Retorna true se o usuário digitar 's'
                } else {
                    return false; // Retorna false se o usuário digitar 'n'
                }
            } else {
                System.out.println("Opção inválida. Digite 's' para sim ou 'n' para não.");
            }
        }
        return false; // Default return, should not reach here
    }
}

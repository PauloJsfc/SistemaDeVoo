package dcx.ufpb.br.paulo.ferreira;

import java.util.Scanner;

public class ProgramaVoo {
    public static void main(String [] args){
        SistemaGerenciaVoos sistema = new GerenciaVoos();
        Scanner read = new Scanner(System.in);

        while (true){
            System.out.println("Digite uma opção:\n1.Cadastrar Voo\n" + "2.Listar Voos\n" + "3.Atualizar Voo\n" + "4.Pesquisar Voo\n" + "5.Cancelar Voo\n" + "6.Sair\n");
            int opcao = read.nextInt();

            if (opcao == 1){
                String codigoVoo = Voo.CodigoDeVooAleatorio();

                System.out.print("Origem" + GerenciaVoos.FORMATO_LUGAR + ": ");
                OrigemVoo origem = new OrigemVoo(read.next(), read.next(), read.next(), read.next());

                System.out.print("Destino" + GerenciaVoos.FORMATO_LUGAR + ": ");
                DestinoVoo destino = new DestinoVoo(read.next(), read.next(), read.next(), read.next());

                System.out.print("Data do voo" + GerenciaVoos.FORMATO_DATA + ": ");
                DataSimples data = new DataSimples(read.next(), read.next());

                sistema.cadastraVoo(codigoVoo,origem,destino,data);

            }
            else if (opcao == 2) {
                sistema.listarVoos();
            }
            else if (opcao == 3){
                sistema.oqDesejaAlterarVoo();
            }
            else if (opcao == 4) {
                System.out.println("Qual o codigo do Voo que está procurando? ");
                System.out.println(sistema.pesquisaVoos(read.next()));
            }
            else if (opcao == 5) {
                System.out.println("Qual o codigo do Voo que deseja cancelar? ");
                sistema.cancelaVoo(read.next());
            }
            else if (opcao == 6) {
                System.out.println("Encerrando o sistema.");
                break;
            }
            else {
                System.out.println("Opção inválida.");
            }
        }

    }
}

package dcx.ufpb.br.paulo.ferreira;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgramaVoo {
    public static void main(String [] args){
        SistemaGerenciaVoos sistema = new GerenciaVoos();
        Scanner read = new Scanner(System.in);

        while (true){
            System.out.println("Digite uma opção:\n1.Cadastrar Voo\n" + "2.Listar Voos\n" + "3.Atualizar Voo\n" + "4.Pesquisar Voo\n" + "5.Cancelar Voo\n" + "6.Salvar Voos\n" + "7.Recuperar Voos\n" + "8.Sair\n");
            int opcao = read.nextInt();

            try {
                if (opcao == 1) {
                    try {
                        String codigoVoo = Voo.CodigoDeVooAleatorio();

                        System.out.print("Origem" + GerenciaVoos.FORMATO_LUGAR + ": ");
                        OrigemVoo origem = new OrigemVoo(read.next(), read.next(), read.next(), read.next());
                        read.nextLine();

                        System.out.print("Destino" + GerenciaVoos.FORMATO_LUGAR + ": ");
                        DestinoVoo destino = new DestinoVoo(read.next(), read.next(), read.next(), read.next());
                        read.nextLine();

                        System.out.print("Data do voo" + GerenciaVoos.FORMATO_DATA + ": ");
                        DataSimples data = new DataSimples(read.next(), read.next());
                        read.nextLine();

                        sistema.cadastraVoo(codigoVoo, origem, destino, data);
                    } catch (InputMismatchException e){
                        System.out.println("Verifique se os dados foram inseridos de maneira correta.");
                    }
                } else if (opcao == 2) {
                    sistema.listarVoos();
                } else if (opcao == 3) {
                    sistema.oqDesejaAlterarVoo();
                } else if (opcao == 4) {
                    sistema.buscarVoos();
                } else if (opcao == 5) {
                    sistema.cancelaVoo();
                } else if (opcao == 6){
                    try {
                        sistema.gravarVoos();
                        System.out.println("Dados salvos com sucesso");
                    } catch(IOException e){
                        System.out.println("Problema ao salvar dados. Tente mais tarde" + e.getMessage());
                    }
                } else if (opcao == 7) {
                    try {
                        sistema.recuperarVoos();
                        System.out.println("Dados recuperados com sucesso");
                    } catch (IOException e){
                        System.out.println("Problema ao recuperar dados. Tente mais tarde" + e.getMessage());
                    }
                } else if (opcao == 8) {
                    System.out.println("Encerrando o sistema.");
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (VooInvalidoException e){
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}

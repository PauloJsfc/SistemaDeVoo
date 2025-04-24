package dcx.ufpb.br.paulo.ferreira;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgramaVoo {
    public static void main(String[] args) {
        SistemaGerenciaVoos sistema = new GerenciaVoos();
        Scanner read = new Scanner(System.in);

        while (true) {
            System.out.println("\nDigite uma opção:");
            System.out.println("1.Cadastrar Voo");
            System.out.println("2.Listar Voos");
            System.out.println("3.Atualizar Voo");
            System.out.println("4.Pesquisar Voo");
            System.out.println("5.Cancelar Voo");
            System.out.println("6.Salvar Voos");
            System.out.println("7.Recuperar Voos");
            System.out.println("8.Sair");

            int opcao;
            try {
                opcao = read.nextInt();
                read.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                read.nextLine();
                continue;
            }

            try {
                if (opcao == 1) {
                    try {
                        String codigoVoo = Voo.CodigoDeVooAleatorio();

                        System.out.print("Origem" + GerenciaVoos.FORMATO_LUGAR + ": ");
                        String origemInput = read.nextLine().trim();
                        String[] origemPartes = origemInput.split(",");
                        if (origemPartes.length < 4) {
                            throw new VooInvalidoException("Formato de origem inválido");
                        }
                        OrigemVoo origem = new OrigemVoo(
                                origemPartes[0].trim(),
                                origemPartes[1].trim(),
                                origemPartes[2].trim(),
                                origemPartes[3].trim()
                        );

                        System.out.print("Destino" + GerenciaVoos.FORMATO_LUGAR + ": ");
                        String destinoInput = read.nextLine().trim();
                        String[] destinoPartes = destinoInput.split(",");
                        if (destinoPartes.length < 4) {
                            throw new VooInvalidoException("Formato de destino inválido");
                        }
                        DestinoVoo destino = new DestinoVoo(
                                destinoPartes[0].trim(),
                                destinoPartes[1].trim(),
                                destinoPartes[2].trim(),
                                destinoPartes[3].trim()
                        );

                        System.out.print("Data do voo" + GerenciaVoos.FORMATO_DATA + ": ");
                        String dataInput = read.nextLine().trim();
                        String[] dataPartes = dataInput.split(" ");
                        if (dataPartes.length < 2) {
                            throw new VooInvalidoException("Formato de data inválido");
                        }
                        DataSimples data = new DataSimples(dataPartes[0], dataPartes[1]);

                        sistema.cadastrarVoo(codigoVoo, origem, destino, data);
                    } catch (CodigoInvalidoException | OrigemInvalidaException | DestinoInvalidoException | DataInvalidaException e) {
                        System.out.println("Erro: " + e.getMessage());
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
                } else if (opcao == 6) {
                    try {
                        sistema.gravarVoos();
                        System.out.println("Dados salvos com sucesso");
                    } catch (IOException e) {
                        System.out.println("Problema ao salvar dados. Tente mais tarde: " + e.getMessage());
                    }
                } else if (opcao == 7) {
                    try {
                        sistema.recuperarVoos();
                        System.out.println("Dados recuperados com sucesso");
                    } catch (IOException e) {
                        System.out.println("Problema ao recuperar dados. Tente mais tarde: " + e.getMessage());
                    }
                } else if (opcao == 8) {
                    System.out.println("Encerrando o sistema.");
                    break;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (VooInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        read.close();
    }
}
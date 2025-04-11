package dcx.ufpb.br.paulo.ferreira;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciaVoos implements SistemaGerenciaVoos{
    public static final String FORMATO_LUGAR = "(Cep,Pais,Estado,Cidade)";
    public static final String FORMATO_DATA = "(xx/xx/xx yy:yy)";


    private List<Voo> voos;

    public GerenciaVoos(){
        this.voos = new ArrayList<>();
    }

    public boolean cadastraVoo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data){

        //DONE: separei as condicionais para casos específicos e adicionei excesões
        if (codigoVoo == null || codigoVoo.isEmpty()) {
            throw new VooInvalidoException.CodigoInvalidoException("ERRO: Código não encontrado");

        }else if (origem == null){
            throw new VooInvalidoException.OrigemInvalidaException("ERRO: Origem não encontrada");

        } else if (destino == null) {
            throw new VooInvalidoException.DestinoInvalidoException("ERRO: origem não encontrada");

        } else if (data == null) {
            throw new VooInvalidoException.DataInvalidaException("ERRO: data inválida");

        }

        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                return false;
            }
        }
        Voo novoVoo = new Voo(codigoVoo, origem, destino, data);
        this.voos.add(novoVoo);
        return true;
    }

    public void listarVoos(){
        if (this.voos.isEmpty()){
            //DONE: verificação de Voo
            throw new VooInvalidoException("ERRO: voo inválido");
        } else {
            for (Voo v : this.voos) {
                System.out.println(v);
            }
        }
    }

    public void oqDesejaAlterarVoo(){
        Scanner read = new Scanner(System.in);
        try {
            System.out.print("Qual o codigo do Voo que deseja atualizar? ");
            String codigoVoo = read.next();

            Voo voo = pesquisaVoos(codigoVoo);

            if (voo == null) {
                throw new VooInvalidoException.CodigoInvalidoException("O código:" + codigoVoo + " não encontrado");
            }

            System.out.print("Qual tipo de alteração (Origem, Destino, Data): ");
            String tipoAlteracao = read.next();
            if (tipoAlteracao.equalsIgnoreCase("Origem")) {
                System.out.print("Insira a nova Origem" + FORMATO_LUGAR + ": ");
                voo.setOrigem(new OrigemVoo(read.next(), read.next(), read.next(), read.next()));

            } else if (tipoAlteracao.equalsIgnoreCase("Destino")) {
                System.out.print("Insira o novo Destino" + FORMATO_LUGAR + ": ");
                voo.setDestino(new DestinoVoo(read.next(), read.next(), read.next(), read.next()));

            } else if (tipoAlteracao.equalsIgnoreCase("Data")) {
                System.out.print("Insira a nova Data" + FORMATO_DATA + ": ");
                voo.setData(new DataSimples(read.next(), read.next()));

            } else {
                throw new VooInvalidoException("ERRO: Voo inválido");
            }

        } catch (VooInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Voo pesquisaVoos(String codigoVoo){
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                return v;
            }
        }
        throw new VooInvalidoException.CodigoInvalidoException("Voo com código " + codigoVoo + " não encontrado.");
    }

    public boolean cancelaVoo(String codigoVoo){
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                this.voos.remove(v);
                return true;
            }
        }
        throw new VooInvalidoException.CodigoInvalidoException("Voo com código " + codigoVoo + " não encontrado para cancelamento.");
    }
}

package dcx.ufpb.br.paulo.ferreira;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GerenciaVoos implements SistemaGerenciaVoos{
    public static final String FORMATO_LUGAR = "(Cep,Pais,Estado,Cidade)";
    public static final String FORMATO_DATA = "(xx/xx/xx yy:yy)";


    private List<Voo> voos;

    public GerenciaVoos(){
        this.voos = new ArrayList<>();
    }

    public boolean cadastraVoo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data){

        //altereia aqui pra gente fazer os Exception em casos específicos
        if (codigoVoo == null || codigoVoo.isEmpty()) {
            throw new SistemaVooException("Código do voo inválido.");
        }
        if (origem == null ) {
            throw new SistemaVooException("Origem do voo não informada.");
        }
        if (destino == null) {
            throw new SistemaVooException("Destino do voo não informado.");
        }
        if (data == null) {
            throw new SistemaVooException("Data do voo não informada.");
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
            throw new SistemaVooException("ERRO: nenhum voo encontrado");
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
                throw new SistemaVooException("ERRO: código informado inválido");
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
                throw new SistemaVooException("Alguma infomação incorreta");
            }
        } catch (SistemaVooException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Voo pesquisaVoos(String codigoVoo){
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                return v;
            }
        }
        throw new NoSuchElementException("Voo com código " + codigoVoo + " não encontrado.");
    }

    public boolean cancelaVoo(String codigoVoo){
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                this.voos.remove(v);
                return true;
            }
        }
        throw new NoSuchElementException("Voo com código " + codigoVoo + " não encontrado para cancelamento.");
    }
}

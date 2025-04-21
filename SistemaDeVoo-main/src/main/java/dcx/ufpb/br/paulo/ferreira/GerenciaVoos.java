package dcx.ufpb.br.paulo.ferreira;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciaVoos implements SistemaGerenciaVoos{
    public static final String FORMATO_LUGAR = "(Cep,Pais,Estado,Cidade)";
    public static final String FORMATO_DATA = "(xx/xx/xx yy:yy)";


    private List<Voo> voos;
    private GravadorDeVoo gravador;

    public GerenciaVoos(){
        this.voos = new ArrayList<>();
        this.gravador = new GravadorDeVoo();
    }

    public void cadastraVoo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data){

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
                String novoCodigoVoo = Voo.CodigoDeVooAleatorio();
                codigoVoo = novoCodigoVoo;
                break;
            }
        }
        Voo novoVoo = new Voo(codigoVoo, origem, destino, data);
        this.voos.add(novoVoo);
        System.out.println("Voo com codigo: " + novoVoo.getCodigoVoo() + ", cadastrado com sucesso.");
    }

    public void listarVoos(){
        if (this.voos.isEmpty()){
            throw new VooInvalidoException("ERRO: Não há Voos cadastrados");
        } else {
            for (Voo v : this.voos) {
                System.out.println(v);
            }
        }
    }

    public void oqDesejaAlterarVoo(){
        Scanner read = new Scanner(System.in);
        try {
            String codigoVoo = solicitarCodigoVoo(read);

            Voo voo = buscarVooPorCodigo(codigoVoo);

            String tipoAlteracao = solicitarTipoAlteracao(read);

            processarAlteracao(voo, tipoAlteracao, read);

        } catch (VooInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private String solicitarCodigoVoo(Scanner read) {
        System.out.print("Qual o codigo do Voo? ");
        return read.next();
    }

    private String solicitarTipoAlteracao(Scanner read) {
        System.out.print("Qual tipo de alteração (Origem, Destino, Data): ");
        return read.next();
    }

    private void processarAlteracao(Voo voo, String tipoAlteracao, Scanner read) {
        if (tipoAlteracao.equalsIgnoreCase("origem")) {
            alterarOrigem(voo, read);
        } else if (tipoAlteracao.equalsIgnoreCase("destino")) {
            alterarDestino(voo, read);
        } else if (tipoAlteracao.equalsIgnoreCase("data")) {
            alterarData(voo, read);
        } else {
            throw new VooInvalidoException("ERRO: Tipo de alteração de Voo inválido");
        }
    }

    private void alterarOrigem(Voo voo, Scanner read) {
        System.out.print("Insira a nova Origem" + FORMATO_LUGAR + ": ");
        voo.setOrigem(new OrigemVoo(read.next(), read.next(), read.next(), read.next()));
        System.out.println("Origem do voo " + voo.getCodigoVoo() + " atualizada com sucesso.");
    }

    private void alterarDestino(Voo voo, Scanner read) {
        System.out.print("Insira o novo Destino" + FORMATO_LUGAR + ": ");
        voo.setDestino(new DestinoVoo(read.next(), read.next(), read.next(), read.next()));
        System.out.println("Destino do voo " + voo.getCodigoVoo() + " atualizado com sucesso.");
    }

    private void alterarData(Voo voo, Scanner read) {
        System.out.print("Insira a nova Data" + FORMATO_DATA + ": ");
        voo.setData(new DataSimples(read.next(), read.next()));
        System.out.println("Data do voo " + voo.getCodigoVoo() + " atualizada com sucesso.");
    }

    private Voo buscarVooPorCodigo(String codigoVoo){
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                return v;
            }
        }
        throw new VooInvalidoException.CodigoInvalidoException("Voo com código " + codigoVoo + " não encontrado.");
    }

    public void buscarVoos(){
        List<Voo> listaVoos = new ArrayList<>();
        Scanner read = new Scanner(System.in);

        if (this.voos.isEmpty()){
            throw new VooInvalidoException("ERRO: Não há Voos cadastrados");
        }

        try {
            String tipoBusca = solicitarTipoBusca(read);

            List<Voo> voosBuscados = processarBusca(tipoBusca, read);

            listaVoos.addAll(voosBuscados);

        } catch (VooInvalidoException e) {
            System.out.println(e.getMessage());
        }

        for (Voo v : listaVoos){
            System.out.println(v.toString());
        }
    }

    private String solicitarTipoBusca(Scanner read){
        System.out.println("Qual o tipo de busca (Codigo, Origem, Destino, Data): ");
        return read.nextLine();
    }

    private List<Voo> processarBusca(String tipoBusca, Scanner read){
        if (tipoBusca.equalsIgnoreCase("Codigo")){
            List<Voo> v = new ArrayList<>();
            String codigo = solicitarCodigoVoo(read);
            v.add(buscarVooPorCodigo(codigo));
            return v;
        } else if (tipoBusca.equalsIgnoreCase("Origem")){
            return buscaPorOrigem(read);
        } else if (tipoBusca.equalsIgnoreCase("Destino")) {
            return buscaPorDestino(read);
        } else if (tipoBusca.equalsIgnoreCase("Data")) {
            return buscaPorData(read);
        } else {
            throw new VooInvalidoException("ERRO: Tipo de busca de Voo inválido");
        }
    }

    private String solicitarAtributoOrigem(Scanner read){
        System.out.print("Insira o Atributo de Origem" + FORMATO_LUGAR + ": ");
        return read.next();
    }

    private String solicitarAtributoDestino(Scanner read){
        System.out.print("Insira o Atributo de Destino" + FORMATO_LUGAR + ": ");
        return read.next();
    }

    private String solicitarData(Scanner read){
        System.out.print("Insira a Data" + FORMATO_DATA + ": ");
        return read.next();
    }

    private String solicitarAtributo(Scanner read){
        System.out.println("Qual atributo deseja buscar (Cidade, Estado, Pais, Cep): ");
        return read.next();
    }

    private List<Voo> buscaPorOrigem(Scanner read){
        String atributoOrigem = solicitarAtributo(read);
        String dadoParaBusca = solicitarAtributoOrigem(read);
        List<Voo> encontrados = new ArrayList<>();
        for (Voo v : this.voos) {
            if (atributoOrigem.equalsIgnoreCase("Cidade") &&
                    v.getOrigem().getOrigemCidade().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            } else if (atributoOrigem.equalsIgnoreCase("Estado") &&
                    v.getOrigem().getOrigemEstado().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            } else if (atributoOrigem.equalsIgnoreCase("Pais") &&
                    v.getOrigem().getOrigemPais().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            } else if (atributoOrigem.equalsIgnoreCase("Cep") &&
                    v.getOrigem().getOrigemCep().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            }
        }
        if (encontrados.isEmpty()) {
            throw new VooInvalidoException("ERRO: Nenhum voo encontrado para a origem informada");
        }
        return encontrados;
    }

    private List<Voo> buscaPorDestino(Scanner read) {
        String atributoDestino = solicitarAtributo(read);
        String dadoParaBusca = solicitarAtributoDestino(read);
        List<Voo> encontrados = new ArrayList<>();
        for (Voo v : this.voos) {
            if (atributoDestino.equalsIgnoreCase("Cidade") &&
                    v.getDestino().getDestinoCidade().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            } else if (atributoDestino.equalsIgnoreCase("Estado") &&
                    v.getDestino().getDestinoEstado().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            } else if (atributoDestino.equalsIgnoreCase("Pais") &&
                    v.getDestino().getDestinoPais().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            } else if (atributoDestino.equalsIgnoreCase("Cep") &&
                    v.getDestino().getDestinoCep().equalsIgnoreCase(dadoParaBusca)) {
                encontrados.add(v);
            }
        }
        if (encontrados.isEmpty()) {
            throw new VooInvalidoException("ERRO: Nenhum voo encontrado para o destino informado");
        }
        return encontrados;
    }

    private List<Voo> buscaPorData(Scanner read){
        String dadoParaBusca = solicitarData(read);
        List<Voo> encontrados = new ArrayList<>();
        for (Voo v : this.voos){
            if (v.getData().getData().toLowerCase().contains(dadoParaBusca.toLowerCase())){
                encontrados.add(v);
            }
        }
        if (encontrados.isEmpty()) {
            throw new VooInvalidoException("ERRO: Nenhum voo encontrado para a data informada");
        }
        return encontrados;
    }

    public boolean cancelaVoo(){
        Scanner read = new Scanner(System.in);

        if (this.voos.isEmpty()){
            throw new VooInvalidoException("ERRO: Não há Voos cadastrados");
        }

        String codigoVoo = solicitarCodigoVoo(read);
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                this.voos.remove(v);
                return true;
            }
        }
        throw new VooInvalidoException.CodigoInvalidoException("Voo com código " + codigoVoo + " não encontrado para cancelamento.");
    }
    public void gravarVoos() throws IOException {
        this.gravador.gravarVoo(this.voos);
    }

    public void recuperarVoos() throws IOException{
        List<Voo> voosRecuperados = this.gravador.recuperarVoos();
        for (Voo v : voosRecuperados){
            this.cadastraVoo(v.getCodigoVoo(),v.getOrigem(),v.getDestino(),v.getData());
        }
    }

}

package dcx.ufpb.br.paulo.ferreira;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

    public void cadastrarVoo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data) throws CodigoInvalidoException, OrigemInvalidaException, DestinoInvalidoException, DataInvalidaException {

        if (codigoVoo == null || codigoVoo.isEmpty()) {
            throw new CodigoInvalidoException("Código não encontrado");

        }else if (origem == null){
            throw new OrigemInvalidaException("Origem não encontrada");

        } else if (destino == null) {
            throw new DestinoInvalidoException("Destino não encontrado");

        } else if (data == null) {
            throw new DataInvalidaException("data inválida");

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

    public void listarVoos() throws VooInvalidoException{
        if (this.voos.isEmpty()){
            throw new VooInvalidoException("Não há Voos cadastrados");
        } else {
            for (Voo v : this.voos) {
                System.out.println(v);
            }
        }
    }

    public void oqDesejaAlterarVoo() throws VooInvalidoException {
        Scanner read = new Scanner(System.in);

        String codigoVoo = solicitarCodigoVoo(read);

        Voo voo = buscarVooPorCodigo(codigoVoo);

        String tipoAlteracao = solicitarTipoAlteracao(read);


        processarAlteracao(voo, tipoAlteracao, read);

    }

    private String solicitarCodigoVoo(Scanner read) {
        System.out.print("Qual o codigo do Voo? ");
        return read.nextLine();
    }

    private String solicitarTipoAlteracao(Scanner read) {
        System.out.print("Qual tipo de alteração (Origem, Destino, Data): ");
        return read.nextLine();
    }

    private void processarAlteracao(Voo voo, String tipoAlteracao, Scanner read) throws VooInvalidoException{
        if (tipoAlteracao.equalsIgnoreCase("origem")) {
            alterarOrigem(voo, read);
        } else if (tipoAlteracao.equalsIgnoreCase("destino")) {
            alterarDestino(voo, read);
        } else if (tipoAlteracao.equalsIgnoreCase("data")) {
            alterarData(voo, read);
        } else {
            throw new VooInvalidoException("Tipo de alteração de Voo inválido");
        }
    }

    private void alterarOrigem(Voo voo, Scanner read) {
        System.out.print("Insira a nova Origem" + FORMATO_LUGAR + ": ");
        String origemInput = read.nextLine().trim();
        String[] origemPartes = origemInput.split(",");
        if (origemPartes.length < 4) {
            throw new InputMismatchException("Formato de origem inválido");
        }
        OrigemVoo origem = new OrigemVoo(
                origemPartes[0].trim(),
                origemPartes[1].trim(),
                origemPartes[2].trim(),
                origemPartes[3].trim()
        );
        voo.setOrigem(origem);
        System.out.println("Origem do voo " + voo.getCodigoVoo() + " atualizada com sucesso.");
    }

    private void alterarDestino(Voo voo, Scanner read) {
        System.out.print("Insira o novo Destino" + FORMATO_LUGAR + ": ");
        String destinoInput = read.nextLine().trim();
        String[] destinoPartes = destinoInput.split(",");
        if (destinoPartes.length < 4) {
            throw new InputMismatchException("Formato de destino inválido");
        }
        DestinoVoo destino = new DestinoVoo(
                destinoPartes[0].trim(),
                destinoPartes[1].trim(),
                destinoPartes[2].trim(),
                destinoPartes[3].trim()
        );
        voo.setDestino(destino);
        System.out.println("Destino do voo " + voo.getCodigoVoo() + " atualizado com sucesso.");
    }

    private void alterarData(Voo voo, Scanner read) {
        System.out.print("Insira a nova Data" + FORMATO_DATA + ": ");
        String dataInput = read.nextLine().trim();
        String[] dataPartes = dataInput.split(" ");
        if (dataPartes.length < 2) {
            throw new InputMismatchException("Formato de data inválido");
        }
        DataSimples data = new DataSimples(dataPartes[0], dataPartes[1]);
        voo.setData(data);
        System.out.println("Data do voo " + voo.getCodigoVoo() + " atualizada com sucesso.");
    }

    private Voo buscarVooPorCodigo(String codigoVoo) throws VooInvalidoException{
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                return v;
            }
        }
        throw new VooInvalidoException("Voo com código " + codigoVoo + " não encontrado.");
    }

    public void buscarVoos() throws VooInvalidoException{
        List<Voo> listaVoos = new ArrayList<>();
        Scanner read = new Scanner(System.in);

        if (this.voos.isEmpty()){
            throw new VooInvalidoException("Não há Voos cadastrados");
        }
            String tipoBusca = solicitarTipoBusca(read);

            List<Voo> voosBuscados = processarBusca(tipoBusca, read);

            listaVoos.addAll(voosBuscados);

        for (Voo v : listaVoos){
            System.out.println(v.toString());
        }
    }

    private String solicitarTipoBusca(Scanner read){
        System.out.println("Qual o tipo de busca (Codigo, Origem, Destino, Data): ");
        return read.nextLine();
    }

    private List<Voo> processarBusca(String tipoBusca, Scanner read) throws VooInvalidoException{
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
            throw new VooInvalidoException("Tipo de busca de Voo inválido");
        }
    }

    private String solicitarAtributoOrigem(Scanner read){
        System.out.print("Insira o Atributo de Origem" + FORMATO_LUGAR + ": ");
        return read.nextLine();
    }

    private String solicitarAtributoDestino(Scanner read){
        System.out.print("Insira o Atributo de Destino" + FORMATO_LUGAR + ": ");
        return read.nextLine();
    }

    private String solicitarData(Scanner read){
        System.out.print("Insira a Data" + FORMATO_DATA + ": ");
        return read.nextLine();
    }

    private String solicitarAtributo(Scanner read){
        System.out.println("Qual atributo deseja buscar (Cidade, Estado, Pais, Cep): ");
        return read.nextLine();
    }

    private List<Voo> buscaPorOrigem(Scanner read) throws VooInvalidoException{
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
            throw new VooInvalidoException("Nenhum voo encontrado para a origem informada");
        }
        return encontrados;
    }

    private List<Voo> buscaPorDestino(Scanner read) throws VooInvalidoException{
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
            throw new VooInvalidoException("Nenhum voo encontrado para o destino informado");
        }
        return encontrados;
    }

    private List<Voo> buscaPorData(Scanner read) throws VooInvalidoException{
        String dadoParaBusca = solicitarData(read);
        List<Voo> encontrados = new ArrayList<>();
        for (Voo v : this.voos){
            if (v.getData().getData().toLowerCase().contains(dadoParaBusca.toLowerCase())){
                encontrados.add(v);
            }
        }
        if (encontrados.isEmpty()) {
            throw new VooInvalidoException("Nenhum voo encontrado para a data informada");
        }
        return encontrados;
    }

    public boolean cancelaVoo() throws VooInvalidoException{
        Scanner read = new Scanner(System.in);

        if (this.voos.isEmpty()){
            throw new VooInvalidoException("Não há Voos cadastrados");
        }

        String codigoVoo = solicitarCodigoVoo(read);
        for (Voo v : this.voos){
            if (v.getCodigoVoo().equalsIgnoreCase(codigoVoo)){
                this.voos.remove(v);
                return true;
            }
        }
        throw new VooInvalidoException("Voo com código " + codigoVoo + " não encontrado para cancelamento.");
    }
    public void gravarVoos() throws IOException {
        this.gravador.gravarVoo(this.voos);
    }

    public void recuperarVoos() throws IOException, VooInvalidoException {
        List<Voo> voosRecuperados = this.gravador.recuperarVoos();
        for (Voo v : voosRecuperados){
            try {
                this.cadastrarVoo(v.getCodigoVoo(),v.getOrigem(),v.getDestino(),v.getData());
            } catch (CodigoInvalidoException e) {
                throw new RuntimeException(e);
            } catch (OrigemInvalidaException e) {
                throw new RuntimeException(e);
            } catch (DestinoInvalidoException e) {
                throw new RuntimeException(e);
            } catch (DataInvalidaException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

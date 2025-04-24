package dcx.ufpb.br.paulo.ferreira;

import java.io.IOException;

public interface SistemaGerenciaVoos {
    void cadastrarVoo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data) throws CodigoInvalidoException,OrigemInvalidaException,DestinoInvalidoException,DataInvalidaException;
    void listarVoos() throws VooInvalidoException;
    void oqDesejaAlterarVoo() throws VooInvalidoException;
    void buscarVoos() throws VooInvalidoException;
    boolean cancelaVoo() throws VooInvalidoException;
    void gravarVoos() throws IOException;
    void recuperarVoos() throws IOException, VooInvalidoException;

}

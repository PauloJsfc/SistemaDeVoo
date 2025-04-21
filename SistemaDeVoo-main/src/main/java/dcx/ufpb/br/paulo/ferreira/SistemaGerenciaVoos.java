package dcx.ufpb.br.paulo.ferreira;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public interface SistemaGerenciaVoos {
    void cadastraVoo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data) throws VooInvalidoException;
    void listarVoos() throws VooInvalidoException;
    void oqDesejaAlterarVoo() throws VooInvalidoException;
    void buscarVoos() throws VooInvalidoException;
    boolean cancelaVoo() throws VooInvalidoException;
    void gravarVoos() throws IOException;
    void recuperarVoos() throws IOException;

}

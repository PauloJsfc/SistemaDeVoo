package dcx.ufpb.br.paulo.ferreira;

public interface SistemaGerenciaVoos {
    boolean cadastraVoo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data); // TODO: Criar Exceptions
    void listarVoos();
    void oqDesejaAlterarVoo();
    Voo pesquisaVoos(String codigoVoo);
    boolean cancelaVoo(String codigoVoo);

}

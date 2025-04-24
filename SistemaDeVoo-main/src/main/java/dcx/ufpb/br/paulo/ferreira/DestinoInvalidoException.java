package dcx.ufpb.br.paulo.ferreira;

public class DestinoInvalidoException extends Exception{
    public DestinoInvalidoException(String message) {
        super(message);
    }

    public DestinoInvalidoException(){
        this("Destino não existe");
    }
}

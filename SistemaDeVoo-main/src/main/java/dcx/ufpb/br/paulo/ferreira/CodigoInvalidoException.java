package dcx.ufpb.br.paulo.ferreira;

public class CodigoInvalidoException extends Exception{
    public CodigoInvalidoException(String message) {
        super(message);
    }

    public CodigoInvalidoException(){
        this("Codigo não existe");
    }
}

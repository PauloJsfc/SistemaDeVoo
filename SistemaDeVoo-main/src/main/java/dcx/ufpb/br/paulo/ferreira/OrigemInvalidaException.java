package dcx.ufpb.br.paulo.ferreira;

public class OrigemInvalidaException extends Exception{
    public OrigemInvalidaException(String message) {
        super(message);
    }

    public OrigemInvalidaException(){
        this("Origem não existe");
    }
}

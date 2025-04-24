package dcx.ufpb.br.paulo.ferreira;

public class DataInvalidaException extends Exception{
    public DataInvalidaException(String message) {
        super(message);
    }

    public DataInvalidaException(){
        this("Data não existe");
    }
}

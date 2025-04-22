package dcx.ufpb.br.paulo.ferreira;

public class VooInvalidoException extends Exception {
    public VooInvalidoException(String message) {
        super(message);
    }

    public VooInvalidoException(){
        this("Voo não existe");
    }
}

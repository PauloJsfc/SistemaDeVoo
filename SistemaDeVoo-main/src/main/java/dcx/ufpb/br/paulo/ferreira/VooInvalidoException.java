package dcx.ufpb.br.paulo.ferreira;

public class VooInvalidoException extends RuntimeException {
    public VooInvalidoException(String message) {
        super(message);
    }
    public static class CodigoInvalidoException extends VooInvalidoException{
        public CodigoInvalidoException(String mensagem){
            super(mensagem);
        }
    }
    public static class OrigemInvalidaException extends VooInvalidoException {
        public OrigemInvalidaException(String mensagem) {
            super(mensagem);
        }
    }

    public static class DestinoInvalidoException extends VooInvalidoException {
        public DestinoInvalidoException(String mensagem) {
            super(mensagem);
        }
    }

    public static class DataInvalidaException extends VooInvalidoException {
        public DataInvalidaException(String mensagem) {
            super(mensagem);
        }
    }
}

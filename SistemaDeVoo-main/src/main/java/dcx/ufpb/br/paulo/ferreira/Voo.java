package dcx.ufpb.br.paulo.ferreira;

import java.util.Objects;
import java.util.Random;

public class Voo {
    private String codigoVoo;
    private OrigemVoo origem;
    private DestinoVoo destino;
    private DataSimples data;

    public Voo(String codigoVoo, OrigemVoo origem, DestinoVoo destino, DataSimples data){
        this.codigoVoo = (codigoVoo == null || codigoVoo.isEmpty()) ?
                CodigoDeVooAleatorio() : codigoVoo;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
    }

    public Voo(){
        this(null,null,null,null);
    }

    public static String CodigoDeVooAleatorio(){
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String nums = "0123456789";
        Random random = new Random();
        StringBuilder codigo = new StringBuilder();

        for(int k = 0; k < 2; k++){
            int indiceAleatorio = random.nextInt(letras.length());
            codigo.append(letras.charAt(indiceAleatorio));
        }

        for(int k = 0; k < 3; k++){
            int indiceAleatorio = random.nextInt(nums.length());
            codigo.append(nums.charAt(indiceAleatorio));
        }

        return codigo.toString();
    }

    public String toString(){
        return "Voo de codigo: " + this.codigoVoo + ", Saindo De " + this.origem + " para " + this.destino + " na Data: " + this.data;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Voo voo = (Voo) o;
        return Objects.equals(codigoVoo, voo.codigoVoo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigoVoo);
    }

    public String getCodigoVoo(){
        return codigoVoo;
    }

    public OrigemVoo getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemVoo origem) {
        this.origem = origem;
    }

    public DestinoVoo getDestino() {
        return destino;
    }

    public void setDestino(DestinoVoo destino) {
        this.destino = destino;
    }

    public DataSimples getData() {
        return data;
    }

    public void setData(DataSimples data) {
        this.data = data;
    }
}

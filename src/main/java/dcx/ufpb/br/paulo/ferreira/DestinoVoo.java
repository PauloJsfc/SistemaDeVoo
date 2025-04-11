package dcx.ufpb.br.paulo.ferreira;

public class DestinoVoo {
    private String destinoCep;
    private String destinoPais;
    private String destinoEstado;
    private String destinoCidade;

    public DestinoVoo(String destinoCep, String destinoPais, String destinoEstado, String destinoCidade){
        this.destinoCep = destinoCep;
        this.destinoPais = destinoPais;
        this.destinoEstado = destinoEstado;
        this.destinoCidade = destinoCidade;
    }

    public DestinoVoo(){
        this("Not Found","Not Found","Not Found","Not Found");
    }

    public String toString(){
        return this.destinoCep + ", " + this.destinoPais + ", " + this.destinoEstado + ", " + this.destinoCidade;
    }

    public String getDestinoCep() {
        return destinoCep;
    }

    public void setDestinoCep(String destinoCep) {
        this.destinoCep = destinoCep;
    }

    public String getDestinoPais() {
        return destinoPais;
    }

    public void setDestinoPais(String destinoPais) {
        this.destinoPais = destinoPais;
    }

    public String getDestinoEstado() {
        return destinoEstado;
    }

    public void setDestinoEstado(String destinoEstado) {
        this.destinoEstado = destinoEstado;
    }

    public String getDestinoCidade() {
        return destinoCidade;
    }

    public void setDestinoCidade(String destinoCidade) {
        this.destinoCidade = destinoCidade;
    }
}

package dcx.ufpb.br.paulo.ferreira;

public class OrigemVoo {
    private String origemCep;
    private String origemPais;
    private String origemEstado;
    private String origemCidade;

    public OrigemVoo(String cep, String pais, String estado, String cidade){
        this.origemCep = cep;
        this.origemPais = pais;
        this.origemEstado = estado;
        this.origemCidade = cidade;
    }

    public OrigemVoo(){
        this("Not Found","Not Found","Not Found","Not Found");
    }

    public String toString(){
        return this.origemCep + ", " + this.origemPais + ", " + this.origemEstado + ", " + this.origemCidade;
    }

    public String getOrigemCep() {
        return origemCep;
    }

    public void setOrigemCep(String origemCep) {
        this.origemCep = origemCep;
    }

    public String getOrigemPais() {
        return origemPais;
    }

    public void setOrigemPais(String origemPais) {
        this.origemPais = origemPais;
    }

    public String getOrigemEstado() {
        return origemEstado;
    }

    public void setOrigemEstado(String origemEstado) {
        this.origemEstado = origemEstado;
    }

    public String getOrigemCidade() {
        return origemCidade;
    }

    public void setOrigemCidade(String origemCidade) {
        this.origemCidade = origemCidade;
    }
}


package dcx.ufpb.br.paulo.ferreira;

public class DataSimples {
    private String data;
    private String hora;

    public DataSimples(String data, String hora){
        this.data = data;
        this.hora = hora;
    }

    public DataSimples(){
        this("Not Found", "Not Found");
    }

    public String toString(){
        return this.data + " " + this.hora;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getHora(){
        return hora;
    }

    public void setHora(String hora){
        this.hora = hora;
    }
}

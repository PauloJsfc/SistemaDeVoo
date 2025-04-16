package dcx.ufpb.br.paulo.ferreira;

import java.util.List;
import java.io.*;
public class GravadorDeVoo{
    private String NomeArquivoVoo;

    public GravadorDeVoo(String nomeArquivoVoo){
        this.NomeArquivoVoo = nomeArquivoVoo;
    }
    public GravadorDeVoo(){
        this("Voo.txt");
    }
    public void gravarVoo(List<Voo> voos) throws IOException {
        //Criando o escritor
        BufferedWriter escritor = null;

        try {
            escritor = new BufferedWriter(new FileWriter(this.NomeArquivoVoo));
            //For para salvar todos os voos
            for (Voo v : voos){
                escritor.write(v.getCodigoVoo()+"#"+v.getData()+
                        "##"+v.getDestino()+"###"+v.getOrigem()+"\n");
            }
          //Testando se o escritor está vazio
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
    }
    public List<Voo> recuperarVoo() throws IOException{
        //TODO: completar classe recuperar voo
    }
}

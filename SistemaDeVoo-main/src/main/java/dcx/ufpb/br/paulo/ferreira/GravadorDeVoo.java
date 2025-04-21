package dcx.ufpb.br.paulo.ferreira;

import java.util.ArrayList;
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
    public List<Voo> recuperarVoos() throws IOException {
        List<Voo> voos = new ArrayList<>();
        BufferedReader leitor = null;

        try {
            File arquivo = new File(this.NomeArquivoVoo);
            if (!arquivo.exists()) {
                return voos; // Retorna lista vazia se o arquivo não existir
            }

            leitor = new BufferedReader(new FileReader(this.NomeArquivoVoo));
            String linha;

            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split("#", 4);
                if (partes.length == 4) {
                    String codigoVoo = partes[0];
                    String data = partes[1].replace("#", "");
                    String destino = partes[2].replace("#", "");
                    String origem = partes[3].replace("#", "");

                    // Separar data e hora
                    String[] dataHora = data.split(" ");
                    DataSimples dataSimples = new DataSimples(dataHora[0], dataHora.length > 1 ? dataHora[1] : "00:00");

                    // Separar informações de origem
                    String[] origemInfo = origem.split(", ");
                    OrigemVoo origemVoo = new OrigemVoo(
                            origemInfo.length > 0 ? origemInfo[0] : "Not Found",
                            origemInfo.length > 1 ? origemInfo[1] : "Not Found",
                            origemInfo.length > 2 ? origemInfo[2] : "Not Found",
                            origemInfo.length > 3 ? origemInfo[3] : "Not Found"
                    );

                    // Separar informações de destino
                    String[] destinoInfo = destino.split(", ");
                    DestinoVoo destinoVoo = new DestinoVoo(
                            destinoInfo.length > 0 ? destinoInfo[0] : "Not Found",
                            destinoInfo.length > 1 ? destinoInfo[1] : "Not Found",
                            destinoInfo.length > 2 ? destinoInfo[2] : "Not Found",
                            destinoInfo.length > 3 ? destinoInfo[3] : "Not Found"
                    );

                    Voo voo = new Voo(codigoVoo, origemVoo, destinoVoo, dataSimples);
                    voos.add(voo);
                }
            }
        } finally {
            if (leitor != null) {
                leitor.close();
            }
        }

        return voos;
    }
}

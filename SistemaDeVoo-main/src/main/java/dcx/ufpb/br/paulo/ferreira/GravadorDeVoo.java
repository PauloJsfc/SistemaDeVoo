package dcx.ufpb.br.paulo.ferreira;

import java.util.LinkedList;
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
                escritor.write(v.getCodigoVoo()+"###"+v.getData()+
                        "###"+v.getDestino()+"###"+v.getOrigem()+"\n");
            }
            //Testando se o escritor está vazio
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
    }
    public List<Voo> recuperarVoos() throws IOException {
        BufferedReader leitor = null;
        List<Voo> voosLidos = new LinkedList<>();
        try {
            leitor = new BufferedReader(new FileReader(this.NomeArquivoVoo));
            String linhaLida;
            while ((linhaLida = leitor.readLine()) != null){
                String [] dadosVoo = linhaLida.split("###");
                if (dadosVoo.length < 4) {
                    continue;
                }

                String codigoVoo = dadosVoo[0];


                DataSimples dataVoo = new DataSimples();
                String[] partesData = dadosVoo[1].split(" ");
                if (partesData.length >= 2) {
                    dataVoo.setData(partesData[0]);
                    dataVoo.setHora(partesData[1]);
                }

                // Extrair destino
                String[] dadosDestino = dadosVoo[2].split(", ");
                if (dadosDestino.length < 4) continue;
                DestinoVoo destino = new DestinoVoo(
                        dadosDestino[0], // cep
                        dadosDestino[1], // pais
                        dadosDestino[2], // estado
                        dadosDestino[3]  // cidade
                );

                // Extrair origem
                String[] dadosOrigem = dadosVoo[3].split(", ");
                if (dadosOrigem.length < 4) continue;
                OrigemVoo origem = new OrigemVoo(
                        dadosOrigem[0], // cep
                        dadosOrigem[1], // pais
                        dadosOrigem[2], // estado
                        dadosOrigem[3]  // cidade
                );

                Voo v = new Voo(codigoVoo, origem, destino, dataVoo);
                voosLidos.add(v);
            }
            return voosLidos;
        } finally {
            if (leitor!=null){
                leitor.close();
            }
        }
    }
}

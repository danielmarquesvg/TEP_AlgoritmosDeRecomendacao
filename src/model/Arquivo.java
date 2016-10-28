package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Arquivo {
	
	//static Aleatoria aleatoria = new Aleatoria();
	
	static FileReader fileReaderArquivoBase;
	static FileReader fileReaderArquivoTeste;
	
	static ArrayList<Registro> arquivoBase = new ArrayList<Registro>();
	static ArrayList<Registro> arquivoTeste = new ArrayList<Registro>();
	static ArrayList<Registro> arquivoPredicao = new ArrayList<Registro>();
	
	public static void carregarArquivos(FileReader fileReaderBase, FileReader fileReaderTeste){
		fileReaderArquivoBase = fileReaderBase;
		fileReaderArquivoTeste = fileReaderTeste;
		JOptionPane.showMessageDialog(null, "Uhuuuu, arquivo carregado!");
	}
	
	public static void lerArquivo(){
		
		try {
			BufferedReader bufferedReaderArquivoBase = new BufferedReader(fileReaderArquivoBase);
			BufferedReader bufferedReaderArquivoTeste = new BufferedReader(fileReaderArquivoTeste);
			
			String linhaArquivoBase = bufferedReaderArquivoBase.readLine();
			String linhaArquivoTeste = bufferedReaderArquivoTeste.readLine();
			
			File arquivo = new File( "c:/Users/jaquelline/Desktop/predicao.txt" );
			FileWriter fw = new FileWriter( arquivo );
			BufferedWriter bw = new BufferedWriter( fw );
			//ao invés de ser substituído (append)
			fw = new FileWriter( arquivo, true );

			boolean existe = arquivo.exists();
			//cria um arquivo (vazio)
			arquivo.createNewFile();
			 
			//cria um diretório
			arquivo.mkdir();			

			
			while(linhaArquivoBase != null){
				String [] arrayLinha = linhaArquivoBase.split("\\s+");
				
				String userId = arrayLinha[0];
				String itemId = arrayLinha[1];
				String rating = arrayLinha[2];
				String timeStamp = arrayLinha[3];
				
				Registro registro = new Registro(userId, itemId, rating, timeStamp);
				arquivoBase.add(registro);
				
				linhaArquivoBase = bufferedReaderArquivoBase.readLine();
			}
			
			while(linhaArquivoTeste != null){
				String [] arrayLinha = linhaArquivoTeste.split("\\s+");
				
				String userId = arrayLinha[0];
				String itemId = arrayLinha[1];
				String rating = arrayLinha[2];
				String timeStamp = arrayLinha[3];
				
				Registro registro = new Registro(userId, itemId, rating, timeStamp);
				arquivoTeste.add(registro);
				
				//Registro registro2 = new Registro(userId, itemId, gera_aleatorio(), timeStamp);
				//arquivoPredicao.add(registro2);
				
				//escrve no arquivo
				//bw.write( userId + "\t" + itemId+ "\t" + gera_aleatorio()+ "\t" + timeStamp);
				
				//quebra de linha
				bw.newLine();
				linhaArquivoTeste = bufferedReaderArquivoTeste.readLine();
			}
			
			//erro_rmse();
		
			//fecha os recursos
			//bw.close();
			//fw.close();
						
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		SistemaColaborativo p = new SistemaColaborativo();
		Integer aux [][] = criaArray();
		//p.print(aux);
		p.preparaArray(aux);
		
		System.out.print(arquivoTeste.get(0).userId+" "+arquivoTeste.get(0).itemId+" "+arquivoTeste.get(0).rating+" "+arquivoTeste.get(0).timeStamp+"\n");
		System.out.print(arquivoTeste.get(1).userId+" "+arquivoTeste.get(1).itemId+" "+arquivoTeste.get(1).rating+" "+arquivoTeste.get(1).timeStamp+"\n");
		System.out.print(arquivoTeste.get(2).userId+" "+arquivoTeste.get(2).itemId+" "+arquivoTeste.get(2).rating+" "+arquivoTeste.get(2).timeStamp+"\n");

		
	}

	public static Integer[][] criaArray(){
		
		int maior_id_usuario = 4 ; //quantidade de usuarios
		int busca_maior = 0; //maior id item
		
		for (int i = 0; i < arquivoBase.size(); i++) {
			
			//busca o maior número de item
			int maior_id_item = Integer.parseInt(arquivoBase.get(i).itemId);			
			if (maior_id_item > busca_maior ){
				busca_maior = maior_id_item;
			}
			
		}
		
		//Matriz refente a tabela associativa de usuario e produto
		Integer matriz[][] = new Integer [maior_id_usuario+1][busca_maior+1];
		
		for (int i =0; i < maior_id_usuario; i++) {
			for (int j = 0; j < busca_maior; j++) {
				//inicializando a matriz com zeros, para os produtos que não for avaliado ser idetificados
				matriz[i][j] = 0;
			}
		}
		
		// preenchimento da matriz com as informações obtidas pelo u1.base, u2.base, ...
		try {
			int k=0;
			for (int i = 0; i < maior_id_usuario; i++) {
				for (int j = 0; j < busca_maior-1; j++) {
										
					if((j+1) == Integer.parseInt(arquivoBase.get(k).itemId)){ //comparação para verificar se o item foi avaliado pelo usuario
						matriz[i+1][j+1] = Integer.parseInt(arquivoBase.get(k).rating); //atribuição da nota na posição referente ao usuario e o item
						System.out.println("Matriz ["+(i+1)+"]["+(j+1)+"] = "+matriz[i+1][j+1]);
						k++;
					}else{
						System.out.println("Matriz ["+(i+1)+"]["+(j+1)+"] = "+matriz[i+1][j+1]);
					}
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Carregamento concluido");
		}
		
		return matriz;
	}

	//metodo para calcular erro medio RMSE
	public static void erro_rmse (){
		//System.out.println(arquivoBase.get(1).rating);
		//System.out.println(arquivoTeste.get(1).rating);
		
		
		int n = arquivoTeste.size();
		System.out.println(n);
		double somatorio=0;
		
		for (int i = 0; i < n; i++) {
			
			int ratingTeste = Integer.parseInt(arquivoTeste.get(i).rating);
			
			//System.out.println(arquivoTeste.get(i).rating +"\t+ " +arquivoPredicao.get(i).rating);
			
			int ratingPredicao = Integer.parseInt(arquivoPredicao.get(i).rating);
			double subtracao = ratingTeste - ratingPredicao;
			somatorio = somatorio + Math.pow(subtracao, 2.0);
			
		}

		double rmse= Math.sqrt(somatorio/(double)n);
		System.out.println("EMSE = " + rmse);
	}
	

}

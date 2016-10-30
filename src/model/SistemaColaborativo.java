package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SistemaColaborativo {

	Arquivo arquivo = new Arquivo();
	
	int maior_item = arquivo.maiorIdDoItem;
	int maior_usuario = 3;
	double array_similaridade [] = new double [maior_item];
	
	public void print(Integer matriz [][][]){
		//Integer matriz_sc[][] = arquivo.criaArray();
		Integer matriz_sc[][][] = matriz;
		
		try {
			for (int i = 0; i < maior_usuario; i++) {
				for (int j = 0; j < maior_item; j++) {
					
					System.out.println("MatrizSC ["+(i+1)+"]["+(j+1)+"] = "+matriz_sc[i+1][j+1][0]+" Flag = " + matriz_sc[i+1][j+1][1]);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Imprimido com sucesso.");
		}
	}
	
	//Prepara o array Analise(o que se pretente buscar uma predição) e array Compara (os outros usuarios)
	public void preparaArray(Integer matriz [][][]){
		
		int array_usuarioAnalise [] = new int [maior_item]; //Notas do usuario para realizar predição
		int array_usuarioCompara []	= new int [maior_item]; //Notas do usuario para buscar vizinho mais proximo
		
		
		int usuario = 1;
		int proximoUsuario = 1;
		
		try {
			//salvar arquivo para testar se dados estão corretos;
			File arquivo = new File( "c:/Users/jaquelline/Desktop/dados.txt" );
			FileWriter fw = new FileWriter( arquivo );
			BufferedWriter bw = new BufferedWriter( fw );
			//ao invés de ser substituído (append)
			fw = new FileWriter( arquivo, true );

			boolean existe = arquivo.exists();
			//cria um arquivo (vazio)
			arquivo.createNewFile();
			 
			//cria um diretório
			arquivo.mkdir();			
					
			while(usuario <= maior_usuario){
				
				for (int j = 0; j < array_usuarioAnalise.length; j++) {
					array_usuarioAnalise [j] = matriz [usuario][j+1][0]; //Atribuir notas do usuario 1				
				}
				
				for (int i = 0; i < array_usuarioAnalise.length; i++) {
					//System.out.print("array_usuarioAnalise ["+i+"]= " +array_usuarioAnalise [i]);
					bw.write( "Usuario "+usuario+" | array_usuarioAnalise ["+i+"]= " +array_usuarioAnalise [i]);
					bw.newLine();
				}
				System.out.println();
				while(proximoUsuario <= maior_usuario){
					
					if(usuario == proximoUsuario){ //Verifica proximo usuario, para não comparar com  ele proprio
						proximoUsuario++;	//Se for igual, incrementa para buscar o proximo usuario
					}else{
						try {
							for (int j = 0; j < array_usuarioAnalise.length; j++) {
								array_usuarioCompara [j] = matriz [proximoUsuario][j+1][0]; ////Atribuir notas do usuario 2
								//System.out.println("array_usuarioCompara = " +array_usuarioCompara [j]);
							}
						} catch (Exception e) {
							System.out.println("Problemas");
						}
						
						for (int i = 0; i < array_usuarioCompara.length; i++) {
							//System.out.print("array_usuarioCompara ["+i+"]= " +array_usuarioCompara [i]);
							bw.write( "Proximo "+proximoUsuario+" | array_usuarioCompara ["+i+"]= " +array_usuarioCompara [i]);
							bw.newLine();
						}
						System.out.println();
						//calcular a similaridade entre o usuario analise e o que está comparando
						calculaSimilaridade(array_usuarioAnalise, array_usuarioCompara, proximoUsuario);
						proximoUsuario++; //incrementa para buscar novo usuario, para ser comparado
					}
					
					
				}
								
				// Verifica se o usuario a ser analizado ainda possui algum item com Falg 0, sem predição de rating diferente de 0
				for (int i = 0; i < array_usuarioCompara.length; i++) {
					if((matriz [usuario][i+1][1] == 0) && (matriz [usuario][i+1][0] == 0)){ 
						System.out.println("Usuário ainda não possui todas as predições");
					}else{
						proximoUsuario = 1;
						usuario++;
					}
				}
									
			}		
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void calculaSimilaridade(int array_usuarioAnalise[], int array_usuarioCompara[], int usuario){
		
		double mult_array = 0; //multiplicação dos itens que ambos avaliaram
		double somatorioAnalise = 0;
		double somatorioCompara = 0;
		double similaridade = 0;
		int contador_avaliacao=0;
		
		
		for (int i = 0; i < array_usuarioCompara.length; i++) {
						 
			if((array_usuarioAnalise[i] != 0) && (array_usuarioCompara[i] != 0)){ //verifica se alguma posição é 0, se não, ambos avaliaram o mesmo produto
				mult_array += array_usuarioAnalise[i]*array_usuarioCompara[i];    // então, realiza-se o somatorio da multicação das notas
				somatorioAnalise += Math.pow(array_usuarioAnalise[i], 2.0); //somatorio da potencia das notas avalidada pelo usuario 1
				somatorioCompara += Math.pow(array_usuarioCompara[i], 2.0);//somatorio da potencia das notas avalidada pelo usuario 2
				contador_avaliacao++;
			}
		}
		
		if(contador_avaliacao >= 2){ //verifica se houve mais de uma avaliação em comum entre os usuarios
			similaridade = mult_array / (Math.sqrt(somatorioAnalise) * Math.sqrt(somatorioCompara)); //Calculo da formula da similaridade
			//System.out.println("Similarida = " + similaridade);
			array_similaridade [usuario] = similaridade; //guarda resultados da porcentagem de todos os usuarios
		}else{
			array_similaridade [usuario] = 0;
			//tem que criar uma flag pra esse caso para o metodo prepara array não entrar em loop;
		}
		verificaVizinhoMaisProximo(2);		
	}
	
	public void verificaVizinhoMaisProximo(int qntVizinhos){
		
		double vizinhos_porcentagem [] = new double [qntVizinhos];
		int vizinhos_posicao [] = new int[qntVizinhos];
		double ordenado [] = new double [array_similaridade.length];
		int dec = 0;
		int maior = 0;
		ordenado = bubble(array_similaridade); 
		
		
		for (int i = 0; i < vizinhos_porcentagem.length; i++) {
			vizinhos_porcentagem[i] = ordenado [array_similaridade.length - dec]; //array com as K porcentagens maiores
			dec++;
		}
		
		int i=0;
		while (i<array_similaridade.length) {
			if(array_similaridade[i] == vizinhos_porcentagem[maior]){ // busca no array original qual os K vizinhos que obteve maior porcentagem
				vizinhos_posicao [maior] = i;
				maior++;
				i=0;
			}	
			i++;
		}
	}
	
	public static double[] bubble (double numeros[]){
		double aux; boolean trocou;
		int tam = numeros.length; 
		do{
			tam--;
			trocou = false;
			for (int i=0; i<tam; i++){
				if(numeros[i]>numeros[i+1]){
					aux = numeros[i];
					numeros[i]= numeros[i+1];
					numeros[i+1]= aux;
					trocou= true;
				}
			}
		}while (trocou);
		//print (numeros);
		return numeros;
	}
}

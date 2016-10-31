package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SistemaColaborativo {

	Arquivo arquivo = new Arquivo();
	
	int maior_item = arquivo.maiorIdDoItem;
	int maior_usuario = arquivo.maiorIdDoUsuario;;
	double array_similaridade [] = new double [maior_item];
	int usuario_rating=0;
	int item_rating=0;
	int media_final=0;
	

	
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
			File arquivo = new File( "c:/Users/jaquelline/Desktop/predicao_u5.txt" );
			FileWriter fw = new FileWriter(arquivo);
			
			//ao invés de ser substituído (append)
			fw = new FileWriter( arquivo, true );
			BufferedWriter bw = new BufferedWriter( fw );
			boolean existe = arquivo.exists();
			//cria um arquivo (vazio)
			arquivo.createNewFile();
			 
			//cria um diretório
			arquivo.mkdir();	
					
				
			while(usuario <= maior_usuario){
				
					boolean flag=false;	
					for (int j = 0; j < array_usuarioAnalise.length; j++) {
						array_usuarioAnalise [j] = matriz [usuario][j+1][0]; //Atribuir notas do usuario 1			
						
						if((flag==false)&&(matriz [usuario][j+1][1]==0)&& (matriz [usuario][j+1][0] == 0) ){
							flag=true;
							usuario_rating = usuario; 
							item_rating = j+1; //guarda o item que receberá a predição
						}
					}
					//for (int i = 0; i < array_usuarioAnalise.length; i++) {
						//System.out.print("array_usuarioAnalise ["+i+"]= " +array_usuarioAnalise [i]);
						//bw.write( "Usuario "+usuario+" | array_usuarioAnalise ["+i+"]= " +array_usuarioAnalise [i]);
						//bw.newLine();
					//}
				//	System.out.println(flag);
					if (flag == false){
						usuario++;
						for (int i = 0; i < maior_usuario; i++) {
							for (int j = 0; j < maior_item; j++) {
								if(matriz [i+1][j+1][0] == -1){
									matriz [i+1][j+1][0] = 0;
								}
							}
						}
					}else{
						
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
								
								//for (int i = 0; i < array_usuarioCompara.length; i++) {
									//System.out.print("array_usuarioCompara ["+i+"]= " +array_usuarioCompara [i]);
									//bw.write( "Proximo "+proximoUsuario+" | array_usuarioCompara ["+i+"]= " +array_usuarioCompara [i]);
									//bw.newLine();
								//}
						//		System.out.println("Preparar calcular similaridade");
								//calcular a similaridade entre o usuario analise e o que está comparando
								calculaSimilaridade(array_usuarioAnalise, array_usuarioCompara, proximoUsuario, matriz);
					//			System.out.println("calculou");
								proximoUsuario++; //incrementa para buscar novo usuario, para ser comparado
							}
							
							
						}
						//printarray(array_similaridade);
						matriz = verificaVizinhoMaisProximo(5, matriz);
						
						System.out.println(usuario_rating+ "\t" +item_rating+"\t"+media_final);
						
						bw.write(usuario_rating+ "\t" +item_rating+"\t"+media_final );
						bw.newLine();
						
						//proximoUsuario = 1;
						//usuario++;
						// Verifica se o usuario a ser analizado ainda possui algum item com Falg 0, sem predição de rating diferente de 0
						
					}
	//				System.out.println("verifica matriz for");
					proximoUsuario = 1;
					//usuario++;
					/*for (int i = 0; i < maior_item; i++) {
						if((matriz [usuario_rating][i+1][1] == 0) && (matriz [usuario][i+1][0] == 0)){ 
							System.out.println("Usuário ainda não possui todas as predições");
						}else{
							//proximoUsuario = 1;
							System.out.println("Não era para entrar");
							//usuario++;
						}
					}*/
										
			}	
			bw.close();
			System.out.println("Fim");
		} catch (Exception e) {
			System.out.println("Finalizou");
		
		}
		
		
		
	}
	
	public void calculaSimilaridade(int array_usuarioAnalise[], int array_usuarioCompara[], int usuario, Integer matriz[][][]){
		
		//print(matriz);
		Integer matriz_aux[][][];
		double mult_array = 0; //multiplicação dos itens que ambos avaliaram
		double somatorioAnalise = 0;
		double somatorioCompara = 0;
		double similaridade = 0;
		int contador_avaliacao=0;
		
		//System.out.println("Fora: " +array_usuarioCompara[item_rating]);
		if(array_usuarioCompara[item_rating-1]!=0){ //verifica se o usuário comparou o item em questão
		//	System.out.println("Usuario ["+usuario+"] Item [" +item_rating+"] ="+array_usuarioCompara[item_rating-1]);
			for (int i = 0; i < array_usuarioCompara.length; i++) {
				 
				if((array_usuarioAnalise[i] != 0) && (array_usuarioCompara[i] != 0)){ //verifica se alguma posição é 0, se não, ambos avaliaram o mesmo produto
					mult_array += array_usuarioAnalise[i]*array_usuarioCompara[i];    // então, realiza-se o somatorio da multicação das notas
					somatorioAnalise += Math.pow(array_usuarioAnalise[i], 2.0); //somatorio da potencia das notas avalidada pelo usuario 1
					somatorioCompara += Math.pow(array_usuarioCompara[i], 2.0);//somatorio da potencia das notas avalidada pelo usuario 2
					contador_avaliacao++;
				}
			}
			
			if(contador_avaliacao > 0){ //verifica se houve mais de uma avaliação em comum entre os usuarios
				similaridade = mult_array / (Math.sqrt(somatorioAnalise) * Math.sqrt(somatorioCompara)); //Calculo da formula da similaridade
	//			System.out.println("usuario"+ usuario+ "Similarida = " + similaridade);
				array_similaridade [usuario] = similaridade; //guarda resultados da porcentagem de todos os usuarios
			}else{
				array_similaridade [usuario] = 0;
				//tem que criar uma flag pra esse caso para o metodo prepara array não entrar em loop;
			}
		}
		
		//printarray(array_similaridade);
		//matriz = verificaVizinhoMaisProximo(2, matriz);	
		//print(matriz);
		//return matriz;
	}
	
	public void printarray(int array[]){
		for (int i = 0; i < array.length; i++) {
			System.out.println("Vizinhos array ["+i+"]= "+array[i] );
		}
	}
	public void printarray(double array[]){
		for (int i = 0; i < array.length; i++) {
			System.out.println("array ["+i+"]= "+array[i] );
		}
	}
	public void printarray(Integer array[]){
		for (int i = 0; i < array.length; i++) {
			System.out.println("array ["+i+"]= "+array[i] );
		}
	}
	public Integer [][][] verificaVizinhoMaisProximo(int qntVizinhos, Integer matriz[][][]){
		
	//	printarray(array_similaridade);
		//System.out.println("Qnt vizinho = "+qntVizinhos);
		Integer rating [] = new Integer[qntVizinhos];
		double vizinhos_porcentagem [] = new double [qntVizinhos];
		int vizinhos_posicao [] = new int[qntVizinhos];
		double ordenado [] = new double [array_similaridade.length];
		double ordenado2 [] = new double [array_similaridade.length];
		int dec = 1;
		int maior = 0;
		
		ordenado2 = array_similaridade.clone() ;//para não sobrescrever o conteudo 
		ordenado = bubble(ordenado2); 
		
	//	System.out.println("vamos buscar as melhores porcentagem ");
		for (int g = 0; g < qntVizinhos; g++) {
			int tamanho = ordenado.length;
	//		System.out.println("tamanho = "+ tamanho);
			tamanho = tamanho - dec;
			vizinhos_porcentagem[g] = ordenado [tamanho]; //array com as K porcentagens maiores
			dec++;
		}
		
		//printarray(vizinhos_porcentagem);
	//	printarray(array_similaridade);
		int t=0;
		while ((t<array_similaridade.length) && (maior<vizinhos_porcentagem.length)) {
	
			if(array_similaridade[t] == vizinhos_porcentagem[maior]){ // busca no array original qual os K vizinhos que obteve maior porcentagem
			//	System.out.println("Entrou");
				vizinhos_posicao [maior] = t;
				maior++;
				t=0;
			}	
			t++;
				
		}
//		System.out.println("Agora vai");
		
		
		for (int j = 0; j < vizinhos_posicao.length; j++) {
			rating [j] = matriz[vizinhos_posicao[j]][item_rating][0];
			//System.out.println("usuario:" +vizinhos_posicao[j]+"item: "+item_rating);
			//System.out.println("rating = " + matriz[vizinhos_posicao[j]][item_rating][0]);
		}
	//	printarray(vizinhos_porcentagem);
	//	printarray(vizinhos_posicao );
	//	printarray(rating);
	//	System.out.println("chegou aqui.");
		int media = calculaMediaPonderada(rating, vizinhos_porcentagem);
		//System.out.println("Media = " + media);
		//System.out.println("Usuario_rating = "+ usuario_rating);
	//	System.out.println("Item_rating = "+ item_rating);
		if(media==0){
			matriz[usuario_rating][item_rating][0] = -1; //recebe -1, por que não existe nenhum usuario que tbm avaliou esse produto
		}else{
			matriz[usuario_rating][item_rating][0] = media;
		}
		
		//print(matriz);
		return matriz;
	}
	
	public int calculaMediaPonderada(Integer rating[], double vizinhos_porcentagem []){
		
		double media_ponderada = 0;
		double contador = 0;
		
		for (int i = 0; i < vizinhos_porcentagem.length; i++) {
			media_ponderada += rating[i]*vizinhos_porcentagem[i];
			contador++;
		}
		
		media_ponderada = media_ponderada/contador;
		media_final = (int) Math.abs(media_ponderada);
		return media_final;
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

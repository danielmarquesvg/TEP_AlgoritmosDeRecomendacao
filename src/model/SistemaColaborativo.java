package model;

import javax.naming.ldap.SortControl;

public class SistemaColaborativo {

	Arquivo arquivo = new Arquivo();
	double array_similaridade [] = new double [4];
	
	public void print(Integer matriz [][]){
		//Integer matriz_sc[][] = arquivo.criaArray();
		Integer matriz_sc[][] = matriz;
		
		try {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 1681; j++) {
					System.out.println();
					System.out.println("MatrizSC ["+(i+1)+"]["+(j+1)+"] = "+matriz_sc[i+1][j+1]);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Imprimido com sucesso.");
		}
	}
	
	public void preparaArray(Integer matriz [][]) {
		
		int array_usuarioAnalise [] = new int [1681]; //Notas do usuario para realizar predição
		int array_usuarioCompara []	= new int [1681]; //Notas do usuario para buscar vizinho mais proximo
		
		
		int usuario = 1;
		int proximoUsuario = 1;
				
		while(usuario < 4){
			
			for (int j = 0; j < array_usuarioAnalise.length; j++) {
				array_usuarioAnalise [j] = matriz [usuario][j+1]; //Atribuir notas do usuario 1
				System.out.println("array_usuarioAnalise = " +array_usuarioAnalise [j]);
			}
			
			while(proximoUsuario < 1681){
				
				if(usuario == proximoUsuario){ //Verifica proximo usuario, para não comparar com  ele proprio
					proximoUsuario++;	//Se for igual, incrementa para buscar o proximo usuario
				}else{
					for (int j = 0; j < array_usuarioAnalise.length; j++) {
						array_usuarioCompara [j] = matriz [proximoUsuario][j+1]; ////Atribuir notas do usuario 2
						System.out.println("array_usuarioCompara = " +array_usuarioCompara [j]);
					}
					calculaSimilaridade(array_usuarioAnalise, array_usuarioCompara, proximoUsuario);
					proximoUsuario++; //incrementa para buscar novo usuario, para ser comparado
				}

			}
			proximoUsuario = 1;
			
		}		
		
	}
	
	public void calculaSimilaridade(int array_usuarioAnalise[], int array_usuarioCompara[], int usuario){
		
		double mult_array = 0; //multiplicação dos itens que ambos avaliaram
		double somatorioAnalise = 0;
		double somatorioCompara = 0;
		double similaridade = 0;
		
		for (int i = 0; i < array_usuarioCompara.length; i++) {
						 
			if((array_usuarioAnalise[i] != 0) && (array_usuarioCompara[i] != 0)){ //verifica se alguma posição é 0, se não, ambos avaliaram o mesmo produto
				mult_array += array_usuarioAnalise[i]*array_usuarioCompara[i];    // então, realiza-se o somatorio da multicação das notas
				somatorioAnalise += Math.pow(array_usuarioAnalise[i], 2.0); //somatorio da potencia das notas avalidada pelo usuario 1
				somatorioCompara += Math.pow(array_usuarioCompara[i], 2.0);//somatorio da potencia das notas avalidada pelo usuario 2

			}
		}
		
		similaridade = mult_array / (Math.sqrt(somatorioAnalise) * Math.sqrt(somatorioCompara)); //Calculo da formula da similaridade
		
		array_similaridade [usuario] = similaridade; //guarda resultados da porcentagem de todos os usuarios
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

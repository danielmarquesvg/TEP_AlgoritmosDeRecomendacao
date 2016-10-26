package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Arquivo {
	
	static FileReader fileReaderArquivoBase;
	static FileReader fileReaderArquivoTeste;
	static ArrayList<Registro> arquivoBase = new ArrayList<Registro>();
	static ArrayList<Registro> arquivoTeste = new ArrayList<Registro>();
	
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
				arquivoBase.add(registro);
				
				linhaArquivoTeste = bufferedReaderArquivoTeste.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.print(arquivoBase.get(0).userId+" "+arquivoBase.get(0).itemId+" "+arquivoBase.get(0).rating+" "+arquivoBase.get(0).timeStamp+"\n");
		System.out.print(arquivoBase.get(1).userId+" "+arquivoBase.get(1).itemId+" "+arquivoBase.get(1).rating+" "+arquivoBase.get(1).timeStamp+"\n");
		System.out.print(arquivoBase.get(2).userId+" "+arquivoBase.get(2).itemId+" "+arquivoBase.get(2).rating+" "+arquivoBase.get(2).timeStamp+"\n");
		System.out.print(arquivoBase.get(3).userId+" "+arquivoBase.get(3).itemId+" "+arquivoBase.get(3).rating+" "+arquivoBase.get(3).timeStamp+"\n");
		System.out.print(arquivoBase.get(4).userId+" "+arquivoBase.get(4).itemId+" "+arquivoBase.get(4).rating+" "+arquivoBase.get(4).timeStamp+"\n");
		
		
	}

}

package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController{
	public String[] linha_de_retorno;
	
	


	@Override
	public void verificaDirTemp() throws IOException {
		File dir = new File("c:\\TEMP");
		if(!dir.exists()) {
			dir.mkdir();
		}
	}

	@Override
	public boolean verificaRegistro(String caminho_arquivo, int codigo) throws IOException {
		File file = new File(caminho_arquivo);
		String codigoString = String.valueOf(codigo);
		
		if(file.isFile()) {
			FileInputStream fluxo = new FileInputStream(file);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			
			while(linha != null) {
				String[] linhaSeparada = linha.split(","); 
				if(linhaSeparada[0].equals(codigoString)) {
					linha_de_retorno = linhaSeparada;
					return true;
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}
		linha_de_retorno = null;
		return false;
	}

	@Override
	public void imprimeCadastro(String caminho_arquivo, int codigo) throws IOException {
		if(verificaRegistro(caminho_arquivo, codigo)) {
			System.out.println("Código: "+ linha_de_retorno[0]);
			System.out.println("Nome: "+ linha_de_retorno[1]);
			System.out.println("Email: "+ linha_de_retorno[2]);
		}else {
			throw new IOException("Código não encontrado");
		}
		
	}

	@Override
	public void insereCadastro(String caminho_arquivo, int codigo, String nome, String email) throws IOException {
		File arq = new File(caminho_arquivo);
		
		if(!(arq.exists() && arq.isFile())) {
			insereCabecalho(arq);
		}
		
		if(!verificaRegistro(caminho_arquivo, codigo)) {	
			FileWriter fileWrite = new FileWriter(arq, arq.exists());
			PrintWriter print = new PrintWriter(fileWrite);
			print.write("\r\n" + codigo + "," + nome + "," + email + ",");
			print.flush();
			print.close();
			fileWrite.close();
		}else {
			throw new IOException("Código já está cadastrado");
		}
		
	}
	
	private void insereCabecalho(File arq) throws IOException {
		FileWriter fileWrite = new FileWriter(arq);
		PrintWriter print = new PrintWriter(fileWrite);
		print.write("Codigo,Nome,Email,");
		print.flush();
		print.close();
		fileWrite.close();
		
	}
	
	
}

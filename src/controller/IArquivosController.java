package controller;

import java.io.IOException;

public interface IArquivosController {
	
		
	public void verificaDirTemp() throws IOException;
	
	public boolean verificaRegistro(String caminho_arquivo, int codigo) throws IOException;
	
	public void imprimeCadastro(String caminho_arquivo, int codigo) throws IOException;
	
	public void insereCadastro(String caminho_arquivo, int codigo, String nome, String email) throws IOException;
	
}

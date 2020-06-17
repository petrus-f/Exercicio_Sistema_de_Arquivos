package view;

import java.io.IOException;

import javax.swing.JOptionPane;

import controller.ArquivosController;

public class Principal {

	public static void main(String[] args) {
		
		ArquivosController arq = new ArquivosController();
		
		String caminho_arquivo = "C:\\TEMP\\arquivo.csv";
		
		
		int op=0;
		int numero;
		do {
			op = Integer.parseInt(JOptionPane.showInputDialog("1- Inserir Cadastro\n2- Imprimir Cadastro"));
			switch(op) {
			case 1:
				numero = Integer.parseInt(JOptionPane.showInputDialog("Numero"));			
				String nome = JOptionPane.showInputDialog("Nome:");
				String email = JOptionPane.showInputDialog("Email:");
				try {
					arq.insereCadastro(caminho_arquivo, numero, nome, email);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				numero = Integer.parseInt(JOptionPane.showInputDialog("Numero:"));
				try {
					arq.imprimeCadastro(caminho_arquivo, numero);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}while (op != 9);
		
		
		
//		try {
//			arq.verificaDirTemp();
//			System.out.println(arq.verificaRegistro(caminho_arquivo, 3));
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}

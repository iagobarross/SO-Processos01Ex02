/*EXERCÍCIO 2

Fazer, em java, uma aplicação que liste os processos ativos, permita ao usuário entrar com o
nome ou o PID do processo e o mate.
A aplicação deverá funcionar, minimamente em Windows e Linux (Alunos com Mac podem fazer
para os 3 SO).
É notório que cada SO tem comandos diferentes para as ações supracitadas, portanto:
Criar em Eclipse, um novo Java Project com uma classe chamada KillController.java no package
controller e uma classe Main.java no package view.

A classe KillController.java deve ter 4 métodos.
1) O primeiro, chamado os, que identifica e retorna o nome do Sistema Operacional (Fazê-lo
privado)
2) O segundo, chamado listaProcessos, que verifica o SO e, de acordo com SO, selecione o
comando para listar os processos ativos.
O método deve receber todas as linhas de saída do processo de listagem e exibi-las em console
3) O terceiro, chamado mataPid, que recebe um PID como parâmetro de entrada, verifica o SO
e, de acordo com SO, selecione o comando para matar o processo e o finalize
4) O quarto, chamado mataNome, que recebe um nome de processo como parâmetro de
entrada, verifica o SO e, de acordo com SO, selecione o comando para matar o processo e o
finalize*/
package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

	@SuppressWarnings("deprecation")
	public void listaProcessos() {
		String os = os();
		if (os.contains("Windows")) {
			String cmd = "TASKLIST /FO TABLE";
			try {
				Process p = Runtime.getRuntime().exec(cmd);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();

				while (linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
			} catch (Exception e) {
				String msg = e.getMessage();
				System.err.println(msg);
			}
		} else if(os.contains("Linux")) {
			String cmd = "ps -ef";
			try {
				Process p = Runtime.getRuntime().exec(cmd);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();

				while (linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
				
			} catch (Exception e) {
				String msg = e.getMessage();
				System.err.println(msg);
			}
		}
	}
	
	private void callProcess(String proc) {
		try {
			Runtime.getRuntime().exec(proc);
		} catch (Exception e) {
			String msg = e.getMessage();
			System.err.println(msg);
		}
	}
	
	public void mataPid(String valor) {
		int pid = Integer.parseInt(valor);
		String os = os();
		StringBuffer buffer = new StringBuffer();
		if(os.contains("Windows")) {
			try {
			String cmd = "TASKKILL /PID ";
			buffer.append(cmd);
			buffer.append(pid);
			
			} catch(Exception e) {
				String msg = e.getMessage();
				System.err.println(msg);
			}
			String kill = buffer.toString();
			callProcess(kill);
		} else if (os.contains("Linux")) {
			try {
				String cmd = "kill -9 ";
				buffer.append(cmd);
				buffer.append(pid);
				
				} catch(Exception e) {
					String msg = e.getMessage();
					System.err.println(msg);
				}
				String kill = buffer.toString();
				callProcess(kill);
			}
		}
	
	public void mataNome(String valor) {
		String os = os();
		StringBuffer buffer = new StringBuffer();
		
		if(os.contains("Windows")) {
			try {
				String cmd = "TASKKILL /IM ";
				buffer.append(cmd);
				buffer.append(valor);
			} catch (Exception e) {
				String msg = e.getMessage();
				if(msg.contains("740")) {
					mataPid(valor);
				}
			}
			String kill = buffer.toString();
			callProcess(kill);
		} else if(os.contains("Linux")) {
			try {
				String cmd = "pkill -f ";
				buffer.append(cmd);
				buffer.append(valor);
				
				} catch(Exception e) {
					String msg = e.getMessage();
					System.err.println(msg);
				}
				String kill = buffer.toString();
				callProcess(kill);
			}
			
		}
	}
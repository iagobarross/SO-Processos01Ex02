package view;

import controller.KillController;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		KillController kCont = new KillController();
		int opc = 0;

		while (opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("\tMenu Processos\n\n1- Listar Processos\n2-Matar processos por PID\n3- Matar processos por nome\n9- Finalizar Programa"));
			switch (opc) {
			case 1:
				kCont.listaProcessos();
				break;
			
			case 2:
				String pid = JOptionPane.showInputDialog("Digite o PID do processo:");
				kCont.mataPid(pid);
				break;
			
			case 3:
				String nome = JOptionPane.showInputDialog("Digite o nome do processo:");
				kCont.mataNome(nome);
				break;
			
			case 9:
				JOptionPane.showMessageDialog(null, "Programa Finalizado");
				System.exit(0);
				break;
				
			default:
				JOptionPane.showMessageDialog(null, "Opcão Inválida");
			}
		}

	}
}

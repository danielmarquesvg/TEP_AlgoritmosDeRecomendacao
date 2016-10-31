package interfaceGrafica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FramePrincipal extends JFrame {

	public static JPanel contentPane;
	public static PanelGerarArquivoDePredicao panelGerarArquivoDePredicao = new PanelGerarArquivoDePredicao();
	public static PanelPrincipal panelPrincipal = new PanelPrincipal();
	public static PanelCalculoDoErro panelCalculoDoErro = new PanelCalculoDoErro();
	public static String escolha = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);
		setTitle("Projeto Algoritmos de Recomendação");
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		panelPrincipal.setBounds(0, 0, 800, 600);
		contentPane.add(panelPrincipal);
		setVisible(true);
		
	}
	
	public static String escolha(){
		return escolha;
	}
	
	public static void modificarEscolha(String opcaoEscolhida){
		escolha = opcaoEscolhida;
	}
}

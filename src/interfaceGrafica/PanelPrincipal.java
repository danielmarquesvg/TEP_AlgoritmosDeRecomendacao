package interfaceGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
	
	File fileArquivoBase;
	FileReader fileReaderArquivoBase;
	File fileArquivoTeste;
	FileReader fileReaderArquivoTeste;
	
	/**
	 * Create the panel.
	 */
	public PanelPrincipal() {
		
		setBackground(new Color(0, 102, 102));
		setSize(800, 600);
		setLayout(null);
		
		JLabel labelProjetoAlgoritmosDePredicao = new JLabel("Projeto Algoritmos de Recomendação");
		labelProjetoAlgoritmosDePredicao.setFont(new Font("Segoe UI Light", Font.PLAIN, 42));
		labelProjetoAlgoritmosDePredicao.setForeground(new Color(255, 255, 255));
		labelProjetoAlgoritmosDePredicao.setBounds(29, 11, 722, 63);
		add(labelProjetoAlgoritmosDePredicao);
		
		JPanel panelArquivoBase = new JPanel();
		panelArquivoBase.setBackground(new Color(0, 153, 153));
		panelArquivoBase.setBounds(29, 85, 722, 176);
		add(panelArquivoBase);
		panelArquivoBase.setLayout(null);
		
		JLabel labelGeracaoDeArquivosDePredicao = new JLabel("Geração de arquivos de predição");
		labelGeracaoDeArquivosDePredicao.setForeground(new Color(255, 255, 255));
		labelGeracaoDeArquivosDePredicao.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		labelGeracaoDeArquivosDePredicao.setBounds(10, 11, 676, 62);
		panelArquivoBase.add(labelGeracaoDeArquivosDePredicao);
		
		JButton botaoIrParaGeracaoDeArquivos = new JButton("Ir para a geração de arquivos");
		botaoIrParaGeracaoDeArquivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PanelGerarArquivoDePredicao panelGerarArquivoDePredicao = new PanelGerarArquivoDePredicao();
				
				FramePrincipal.modificarEscolha("Gerar Arquivo de Predicao");
				FramePrincipal.contentPane.removeAll();
				FramePrincipal.contentPane.add(panelGerarArquivoDePredicao);
				FramePrincipal.contentPane.validate();
				FramePrincipal.contentPane.repaint();
			}
		});
		botaoIrParaGeracaoDeArquivos.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoIrParaGeracaoDeArquivos.setBackground(new Color(255, 255, 255));
		botaoIrParaGeracaoDeArquivos.setBounds(10, 84, 255, 32);
		panelArquivoBase.add(botaoIrParaGeracaoDeArquivos);
		
		JPanel panelArquivoTeste = new JPanel();
		panelArquivoTeste.setBackground(new Color(0, 153, 153));
		panelArquivoTeste.setBounds(29, 272, 722, 176);
		add(panelArquivoTeste);
		panelArquivoTeste.setLayout(null);
		
		JLabel labelCalculoDoErro = new JLabel("Cálculo do Erro");
		labelCalculoDoErro.setForeground(Color.WHITE);
		labelCalculoDoErro.setFont(new Font("Segoe UI Light", Font.PLAIN, 28));
		labelCalculoDoErro.setBounds(10, 11, 519, 32);
		panelArquivoTeste.add(labelCalculoDoErro);
		
		JButton botaoIrParaCalculoDoErro = new JButton("Ir para o cálculo do erro");
		botaoIrParaCalculoDoErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PanelCalculoDoErro panelCalculoDoErro = new PanelCalculoDoErro();
				
				FramePrincipal.modificarEscolha("Calcular Erro");
				FramePrincipal.contentPane.removeAll();
				FramePrincipal.contentPane.add(panelCalculoDoErro);
				FramePrincipal.contentPane.validate();
				FramePrincipal.contentPane.repaint();
			}
		});
		botaoIrParaCalculoDoErro.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoIrParaCalculoDoErro.setBackground(Color.WHITE);
		botaoIrParaCalculoDoErro.setBounds(10, 59, 256, 32);
		panelArquivoTeste.add(botaoIrParaCalculoDoErro);

	}

}

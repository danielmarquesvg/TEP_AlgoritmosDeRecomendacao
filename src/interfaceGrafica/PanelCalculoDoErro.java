package interfaceGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Arquivo;


public class PanelCalculoDoErro extends JPanel {

	JLabel labelDiretorioDoArquivoDeTesteInicial;
	JLabel labelCaminhoDoDiretorioDoArquivoDeTesteInicial;
	File fileArquivoBase;
	FileReader fileReaderArquivoBase;
	
	JLabel labelDiretorioDoArquivoTesteDePredicao;
	JLabel labelCaminhoDoDiretorioDoArquivoTesteDePredicao;
	File fileArquivoTeste;
	FileReader fileReaderArquivoTeste;
	
	static JLabel labelErro;
	static JLabel labelErroRMSE;
	public static String erro = "";
	
	/**
	 * Create the panel.
	 */
	public PanelCalculoDoErro() {
		
		setBackground(new Color(0, 102, 102));
		setSize(800, 600);
		setLayout(null);
		
		JLabel labelCalculoDoErro = new JLabel("Cálculo do Erro");
		labelCalculoDoErro.setFont(new Font("Segoe UI Light", Font.PLAIN, 42));
		labelCalculoDoErro.setForeground(new Color(255, 255, 255));
		labelCalculoDoErro.setBounds(29, 11, 627, 63);
		add(labelCalculoDoErro);
		
		JPanel panelArquivoBase = new JPanel();
		panelArquivoBase.setBackground(new Color(0, 153, 153));
		panelArquivoBase.setBounds(29, 85, 722, 176);
		add(panelArquivoBase);
		panelArquivoBase.setLayout(null);
		
		JLabel labelSelecionarArquivoDeTesteInicial = new JLabel("Selecionar o arquivo de teste inicial");
		labelSelecionarArquivoDeTesteInicial.setForeground(new Color(255, 255, 255));
		labelSelecionarArquivoDeTesteInicial.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelSelecionarArquivoDeTesteInicial.setBounds(10, 11, 318, 26);
		panelArquivoBase.add(labelSelecionarArquivoDeTesteInicial);
		
		JButton botaoAbrirArquivoTesteInicial = new JButton("Abrir arquivo de teste inicial");
		botaoAbrirArquivoTesteInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					// Instanciacao de fileChooser e alteracao do diretorio para
					// buscar a imagem
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("src/arquivos"));

					// Verificacao do fileChooser
					if (fileChooser.showOpenDialog(botaoAbrirArquivoTesteInicial) == JFileChooser.APPROVE_OPTION) {

						// Cria um file onde eh armazenada a imagem
						fileArquivoBase = fileChooser.getSelectedFile();
						
						labelCaminhoDoDiretorioDoArquivoDeTesteInicial.setText(fileArquivoBase.getPath());
						labelDiretorioDoArquivoDeTesteInicial.setVisible(true);
						labelCaminhoDoDiretorioDoArquivoDeTesteInicial.setVisible(true);

						repaint();
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,"Não foi possivel carregar a imagem.");
				}				
			}
		});
		botaoAbrirArquivoTesteInicial.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoAbrirArquivoTesteInicial.setBackground(new Color(255, 255, 255));
		botaoAbrirArquivoTesteInicial.setBounds(10, 48, 286, 32);
		panelArquivoBase.add(botaoAbrirArquivoTesteInicial);
		
		JPanel panelArquivoTeste = new JPanel();
		panelArquivoTeste.setBackground(new Color(0, 153, 153));
		panelArquivoTeste.setBounds(29, 272, 722, 176);
		add(panelArquivoTeste);
		panelArquivoTeste.setLayout(null);
		
		JLabel labelArquivoTesteDePredicao = new JLabel("Selecionar o arquivo teste de predição");
		labelArquivoTesteDePredicao.setForeground(Color.WHITE);
		labelArquivoTesteDePredicao.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelArquivoTesteDePredicao.setBounds(10, 11, 318, 26);
		panelArquivoTeste.add(labelArquivoTesteDePredicao);
		
		JButton botaoAbrirArquivoTesteDePredicao = new JButton("Abrir arquivo teste de predição");
		botaoAbrirArquivoTesteDePredicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Instanciacao de fileChooser e alteracao do diretorio para
					// buscar a imagem
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("src/arquivos"));

					// Verificacao do fileChooser
					if (fileChooser.showOpenDialog(botaoAbrirArquivoTesteDePredicao) == JFileChooser.APPROVE_OPTION) {

						// Cria um file onde eh armazenada a imagem
						fileArquivoTeste = fileChooser.getSelectedFile();
						
						labelCaminhoDoDiretorioDoArquivoTesteDePredicao.setText(fileArquivoTeste.getPath());
						labelDiretorioDoArquivoTesteDePredicao.setVisible(true);
						labelCaminhoDoDiretorioDoArquivoTesteDePredicao.setVisible(true);

						repaint();
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,"Não foi possivel carregar a imagem.");
				}		
			}
		});
		botaoAbrirArquivoTesteDePredicao.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoAbrirArquivoTesteDePredicao.setBackground(Color.WHITE);
		botaoAbrirArquivoTesteDePredicao.setBounds(10, 48, 290, 32);
		panelArquivoTeste.add(botaoAbrirArquivoTesteDePredicao);
		
		labelDiretorioDoArquivoDeTesteInicial = new JLabel("Diretório do Arquivo de Teste Inicial");
		labelDiretorioDoArquivoDeTesteInicial.setBounds(10, 102, 318, 26);
		labelDiretorioDoArquivoDeTesteInicial.setForeground(Color.WHITE);
		labelDiretorioDoArquivoDeTesteInicial.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelDiretorioDoArquivoDeTesteInicial.setVisible(false);
		panelArquivoBase.add(labelDiretorioDoArquivoDeTesteInicial);
		
		labelCaminhoDoDiretorioDoArquivoDeTesteInicial = new JLabel();
		labelCaminhoDoDiretorioDoArquivoDeTesteInicial.setForeground(Color.WHITE);
		labelCaminhoDoDiretorioDoArquivoDeTesteInicial.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		labelCaminhoDoDiretorioDoArquivoDeTesteInicial.setBounds(10, 124, 672, 26);
		labelCaminhoDoDiretorioDoArquivoDeTesteInicial.setVisible(false);
		panelArquivoBase.add(labelCaminhoDoDiretorioDoArquivoDeTesteInicial);
		
		labelDiretorioDoArquivoTesteDePredicao = new JLabel("Diretório do Arquivo Teste");
		labelDiretorioDoArquivoTesteDePredicao.setBounds(10, 102, 318, 26);
		labelDiretorioDoArquivoTesteDePredicao.setForeground(Color.WHITE);
		labelDiretorioDoArquivoTesteDePredicao.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelDiretorioDoArquivoTesteDePredicao.setVisible(false);
		panelArquivoTeste.add(labelDiretorioDoArquivoTesteDePredicao);
		
		labelCaminhoDoDiretorioDoArquivoTesteDePredicao = new JLabel("");
		labelCaminhoDoDiretorioDoArquivoTesteDePredicao.setBounds(10, 124, 666, 26);
		labelCaminhoDoDiretorioDoArquivoTesteDePredicao.setForeground(Color.WHITE);
		labelCaminhoDoDiretorioDoArquivoTesteDePredicao.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		labelCaminhoDoDiretorioDoArquivoTesteDePredicao.setVisible(false);
		panelArquivoTeste.add(labelCaminhoDoDiretorioDoArquivoTesteDePredicao);
		
		JLabel labelCalcularErro = new JLabel("Calcular o erro");
		labelCalcularErro.setForeground(Color.WHITE);
		labelCalcularErro.setFont(new Font("Segoe UI Light", Font.PLAIN, 38));
		labelCalcularErro.setBounds(39, 459, 249, 51);
		add(labelCalcularErro);
		
		JButton botaoCalcularErro = new JButton("Iniciar");
		botaoCalcularErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					fileReaderArquivoBase = new FileReader(fileArquivoBase);
					fileReaderArquivoTeste = new FileReader(fileArquivoTeste);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Arquivo.carregarArquivos(fileReaderArquivoBase, fileReaderArquivoTeste);
				Arquivo.lerArquivo();
			}
		});
		botaoCalcularErro.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoCalcularErro.setBackground(Color.WHITE);
		botaoCalcularErro.setBounds(39, 513, 238, 32);
		add(botaoCalcularErro);
		
		labelErro = new JLabel("");
		labelErro.setForeground(Color.WHITE);
		labelErro.setFont(new Font("Segoe UI Light", Font.PLAIN, 24));
		labelErro.setBounds(385, 513, 366, 32);
		labelErro.setVisible(false);
		add(labelErro);
		
		labelErroRMSE = new JLabel("Erro RMSE");
		labelErroRMSE.setForeground(Color.WHITE);
		labelErroRMSE.setFont(new Font("Segoe UI Light", Font.PLAIN, 38));
		labelErroRMSE.setBounds(385, 459, 249, 51);
		labelErroRMSE.setVisible(false);
		add(labelErroRMSE);
		
		JButton botaoPaginaInicial = new JButton("");
		botaoPaginaInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PanelPrincipal panelPrincipal = new PanelPrincipal();
				
				FramePrincipal.modificarEscolha("");
				FramePrincipal.contentPane.removeAll();
				FramePrincipal.contentPane.add(panelPrincipal);
				FramePrincipal.contentPane.validate();
				FramePrincipal.contentPane.repaint();
				
			}
		});
		botaoPaginaInicial.setBounds(679, 11, 63, 63);
		add(botaoPaginaInicial);
		
		ImageIcon imageIconPaginaInicial = new ImageIcon(getClass().getResource("/imagens/homeIcon.png"));
		botaoPaginaInicial.setIcon(new ImageIcon(imageIconPaginaInicial.getImage().getScaledInstance(
				botaoPaginaInicial.getWidth(), botaoPaginaInicial.getHeight(), java.awt.Image.SCALE_SMOOTH)));
		botaoPaginaInicial.setOpaque(false);
		botaoPaginaInicial.setContentAreaFilled(false);
		botaoPaginaInicial.setBorderPainted(true);

	}
	
	public static String getErro(){
		return erro;
	}
	
	public static void modificarErro(String error){
		erro = error;
		
		//exibir na interface grafica
		labelErro.setText(erro);
		labelErro.setVisible(true);
		labelErroRMSE.setVisible(true);
	}
}

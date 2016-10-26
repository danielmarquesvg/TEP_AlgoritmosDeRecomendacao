package interfaceGrafica;

import javax.swing.JPanel;

import model.Arquivo;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.awt.event.ActionEvent;


public class PanelPrincipal extends JPanel {
	
	JLabel labelDiretorioDoArquivoBase;
	JLabel labelCaminhoDoDiretorioDoArquivoBase;
	File fileArquivoBase;
	FileReader fileReaderArquivoBase;
	
	JLabel labelDiretorioDoArquivoTeste;
	JLabel labelCaminhoDoDiretorioDoArquivoTeste;
	File fileArquivoTeste;
	FileReader fileReaderArquivoTeste;
	
	/**
	 * Create the panel.
	 */
	public PanelPrincipal() {
		
		setBackground(new Color(0, 102, 102));
		setSize(800, 600);
		setLayout(null);
		
		JLabel labelCarregandoArquivos = new JLabel("Carregando os arquivos ... ...");
		labelCarregandoArquivos.setFont(new Font("Segoe UI Light", Font.PLAIN, 42));
		labelCarregandoArquivos.setForeground(new Color(255, 255, 255));
		labelCarregandoArquivos.setBounds(29, 11, 722, 63);
		add(labelCarregandoArquivos);
		
		JPanel panelArquivoBase = new JPanel();
		panelArquivoBase.setBackground(new Color(0, 153, 153));
		panelArquivoBase.setBounds(29, 85, 722, 176);
		add(panelArquivoBase);
		panelArquivoBase.setLayout(null);
		
		JLabel labelSelecionarArquivoBase = new JLabel("Selecionar o arquivo base");
		labelSelecionarArquivoBase.setForeground(new Color(255, 255, 255));
		labelSelecionarArquivoBase.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelSelecionarArquivoBase.setBounds(10, 11, 318, 26);
		panelArquivoBase.add(labelSelecionarArquivoBase);
		
		JButton botaoAbrirArquivoBase = new JButton("Abrir arquivo base");
		botaoAbrirArquivoBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					// Instanciacao de fileChooser e alteracao do diretorio para
					// buscar a imagem
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("src"));

					// Verificacao do fileChooser
					if (fileChooser.showOpenDialog(botaoAbrirArquivoBase) == JFileChooser.APPROVE_OPTION) {

						// Cria um file onde eh armazenada a imagem
						fileArquivoBase = fileChooser.getSelectedFile();
						
						labelCaminhoDoDiretorioDoArquivoBase.setText(fileArquivoBase.getPath());
						labelDiretorioDoArquivoBase.setVisible(true);
						labelCaminhoDoDiretorioDoArquivoBase.setVisible(true);

						repaint();
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,"Não foi possivel carregar a imagem.");
				}				
			}
		});
		botaoAbrirArquivoBase.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoAbrirArquivoBase.setBackground(new Color(255, 255, 255));
		botaoAbrirArquivoBase.setBounds(10, 48, 203, 32);
		panelArquivoBase.add(botaoAbrirArquivoBase);
		
		JPanel panelArquivoTeste = new JPanel();
		panelArquivoTeste.setBackground(new Color(0, 153, 153));
		panelArquivoTeste.setBounds(29, 272, 722, 176);
		add(panelArquivoTeste);
		panelArquivoTeste.setLayout(null);
		
		JLabel labelArquivoTeste = new JLabel("Selecionar o arquivo teste");
		labelArquivoTeste.setForeground(Color.WHITE);
		labelArquivoTeste.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelArquivoTeste.setBounds(10, 11, 318, 26);
		panelArquivoTeste.add(labelArquivoTeste);
		
		JButton botaoAbrirArquivoTeste = new JButton("Abrir arquivo teste");
		botaoAbrirArquivoTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Instanciacao de fileChooser e alteracao do diretorio para
					// buscar a imagem
					final JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File("src"));

					// Verificacao do fileChooser
					if (fileChooser.showOpenDialog(botaoAbrirArquivoBase) == JFileChooser.APPROVE_OPTION) {

						// Cria um file onde eh armazenada a imagem
						fileArquivoTeste = fileChooser.getSelectedFile();
						
						labelCaminhoDoDiretorioDoArquivoTeste.setText(fileArquivoTeste.getPath());
						labelDiretorioDoArquivoTeste.setVisible(true);
						labelCaminhoDoDiretorioDoArquivoTeste.setVisible(true);

						repaint();
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,"Não foi possivel carregar a imagem.");
				}		
			}
		});
		botaoAbrirArquivoTeste.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoAbrirArquivoTeste.setBackground(Color.WHITE);
		botaoAbrirArquivoTeste.setBounds(10, 48, 203, 32);
		panelArquivoTeste.add(botaoAbrirArquivoTeste);
		
		labelDiretorioDoArquivoBase = new JLabel("Diretório do Arquivo Base");
		labelDiretorioDoArquivoBase.setBounds(10, 102, 318, 26);
		labelDiretorioDoArquivoBase.setForeground(Color.WHITE);
		labelDiretorioDoArquivoBase.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelDiretorioDoArquivoBase.setVisible(false);
		panelArquivoBase.add(labelDiretorioDoArquivoBase);
		
		labelCaminhoDoDiretorioDoArquivoBase = new JLabel();
		labelCaminhoDoDiretorioDoArquivoBase.setForeground(Color.WHITE);
		labelCaminhoDoDiretorioDoArquivoBase.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		labelCaminhoDoDiretorioDoArquivoBase.setBounds(10, 124, 672, 26);
		labelCaminhoDoDiretorioDoArquivoBase.setVisible(false);
		panelArquivoBase.add(labelCaminhoDoDiretorioDoArquivoBase);
		
		labelDiretorioDoArquivoTeste = new JLabel("Diretório do Arquivo Teste");
		labelDiretorioDoArquivoTeste.setBounds(10, 102, 318, 26);
		labelDiretorioDoArquivoTeste.setForeground(Color.WHITE);
		labelDiretorioDoArquivoTeste.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		labelDiretorioDoArquivoTeste.setVisible(false);
		panelArquivoTeste.add(labelDiretorioDoArquivoTeste);
		
		labelCaminhoDoDiretorioDoArquivoTeste = new JLabel("");
		labelCaminhoDoDiretorioDoArquivoTeste.setBounds(10, 124, 666, 26);
		labelCaminhoDoDiretorioDoArquivoTeste.setForeground(Color.WHITE);
		labelCaminhoDoDiretorioDoArquivoTeste.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		labelCaminhoDoDiretorioDoArquivoTeste.setVisible(false);
		panelArquivoTeste.add(labelCaminhoDoDiretorioDoArquivoTeste);
		
		JLabel labelVoceEstaPronto = new JLabel("Você está pronto?");
		labelVoceEstaPronto.setForeground(Color.WHITE);
		labelVoceEstaPronto.setFont(new Font("Segoe UI Light", Font.PLAIN, 38));
		labelVoceEstaPronto.setBounds(29, 459, 722, 41);
		add(labelVoceEstaPronto);
		
		JButton botaoIniciar = new JButton("Iniciar");
		botaoIniciar.addActionListener(new ActionListener() {
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
		botaoIniciar.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		botaoIniciar.setBackground(Color.WHITE);
		botaoIniciar.setBounds(39, 513, 207, 32);
		add(botaoIniciar);

	}
}

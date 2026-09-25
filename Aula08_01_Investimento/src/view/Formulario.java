package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import business.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Formulario 
{
	private JFrame frame;
	private JLabel lblCapital, lblTempo, lblMontante, lblTitulo, lblTipos, lblGanho;
	private JTextField ftxtCapital, ftxtTempo;
	private JButton btnSimular;
	private float capital, taxa;
	private int tempo;
	private JComboBox<String> comboInvestimentos;
	String opcoes[] = {"Poupança", "CDI", "Tesouro Direto"};
	private Aplicacao aplicacao = null;
	String opcao;
	
	public Formulario()
	{
		InicializaComponentes();
	}
	
	private void atualizaResultado()
	{
		lblMontante.setText("MONTANTE: R$" + String.format("%.2f", aplicacao.getMontante()));	
		lblGanho.setText("GANHO: R$" + String.format("%.2f", aplicacao.getGanho()));	
	}
	
	private void InicializaComponentes()
	{
		frame = new JFrame("Investimento JFrame");
		frame.setBounds(50, 50, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		
		Container painelDeConteudo = frame.getContentPane();
		
		lblTitulo = new JLabel("Investimento JFrame");
		lblTitulo.setBounds(50, 50, 200, 25);
		lblTitulo.setFont(new Font("Consolas", Font.BOLD, 16));
		lblTitulo.setForeground(Color.blue);
		painelDeConteudo.add(lblTitulo);
		
		lblCapital = new JLabel("Capital Inicial");
		lblCapital.setBounds(50, 50, 200, 125);
		lblCapital.setFont(new Font("Consolas", Font.ITALIC, 16));
		painelDeConteudo.add(lblCapital);
		
		ftxtCapital = new JTextField();
		ftxtCapital.setBounds(215, 100, 200, 25);
		ftxtCapital.setFont(new Font("Consolas", Font.ITALIC, 16));
		ftxtCapital.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();

		        if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
		            e.consume();
		        }
		        
		        if (c == '.' && ftxtCapital.getText().contains(".")) {
		            e.consume();
		        }
		    }
		});
		painelDeConteudo.add(ftxtCapital);
		
		lblTempo = new JLabel("Tempo (em meses)");
		lblTempo.setBounds(50, 100, 200, 125);
		lblTempo.setFont(new Font("Consolas", Font.ITALIC, 16));
		painelDeConteudo.add(lblTempo);
		
		ftxtTempo = new JTextField();
		ftxtTempo.setBounds(215, 150, 100, 25);
		ftxtTempo.setFont(new Font("Consolas", Font.ITALIC, 16));
		ftxtTempo.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();

		        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
		            e.consume();
		        }
		    }
		});
		painelDeConteudo.add(ftxtTempo);
		
		lblTipos = new JLabel("Tipos de investimento");
		lblTipos.setBounds(50, 150, 200, 125);
		lblTipos.setFont(new Font("Consolas", Font.ITALIC, 16));
		painelDeConteudo.add(lblTipos);
		
		comboInvestimentos = new JComboBox<>(opcoes);
		comboInvestimentos.setBounds(250, 200, 200, 25);
		painelDeConteudo.add(comboInvestimentos);
		
		btnSimular = new JButton("SIMULAR");
		btnSimular.setForeground(Color.blue);
		btnSimular.setBounds(50, 250, 400, 25);
		btnSimular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				capital = Float.parseFloat(ftxtCapital.getText());
				tempo = Integer.parseInt(ftxtTempo.getText());
				opcao = (String) comboInvestimentos.getSelectedItem();
				switch(opcao) {
					case "Poupança":
						taxa = (float) 0.0038;
						break;
					case "CDI":
						taxa = (float) 0.0053;
						break;
					case "Tesouro Direto":
						taxa = (float) 0.0065;
						break;
				}
				aplicacao = new Aplicacao();
				aplicacao.calcularRendimento(capital, tempo, taxa);
				atualizaResultado();
			}
		});
		painelDeConteudo.add(btnSimular);
		
		lblMontante = new JLabel("MONTANTE: ");
		lblMontante.setBounds(50, 300, 200, 125);
		lblMontante.setFont(new Font("Consolas", Font.BOLD, 16));
		painelDeConteudo.add(lblMontante);
		
		lblGanho = new JLabel("GANHO: ");
		lblGanho.setBounds(50, 350, 200, 125);
		lblGanho.setFont(new Font("Consolas", Font.BOLD, 16));
		painelDeConteudo.add(lblGanho);
		
		frame.setVisible(true);
	}
}

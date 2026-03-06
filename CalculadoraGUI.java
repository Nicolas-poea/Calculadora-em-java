import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;

public class CalculadoraGUI {

    private static JTextField campoAtivo;
    private static JLabel labelOperacao;

    public static void main(String[] args) {

        JFrame janela = new JFrame("Calculadora");
        janela.setSize(400, 600);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);

        // --- Campos de Texto ---
        JTextField n1 = new JTextField();
        n1.setBounds(50, 20, 280, 35);
        campoAtivo = n1;

        labelOperacao = new JLabel("", SwingConstants.CENTER);
        labelOperacao.setBounds(50, 60, 280, 25);
        labelOperacao.setFont(new Font("Arial", Font.BOLD, 20));

        JTextField n2 = new JTextField();
        n2.setBounds(50, 90, 280, 35);

        // Listeners de foco
        n1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) { campoAtivo = n1; }
        });
        n2.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) { campoAtivo = n2; }
        });

        JLabel resultado = new JLabel("Resultado: ");
        resultado.setBounds(50, 500, 280, 40);

        // --- Botões Numéricos (0-9) ---
        int xBase = 100, yBase = 260; 
        for (int i = 0; i <= 9; i++) {
            final String num = String.valueOf(i);
            JButton btn = new JButton(num);
            int col = (i == 0) ? 1 : (i - 1) % 3;
            int lin = (i == 0) ? 3 : (i - 1) / 3;

            if (i == 0) btn.setBounds(xBase + 60, yBase + 120, 60, 45);
            else btn.setBounds(xBase + (col * 60), yBase + (lin * 40), 60, 40);

            btn.addActionListener(e -> {
                if (campoAtivo != null) {
                    campoAtivo.setText(campoAtivo.getText() + num);
                }
            });
            janela.add(btn);
        }

        // --- Botões de Operação ---
        JButton soma = new JButton("+");
        soma.setBounds(50, 140, 55, 40);
        soma.addActionListener(e -> { labelOperacao.setText("+"); n2.requestFocus(); });

        JButton sub = new JButton("-");
        sub.setBounds(115, 140, 55, 40);
        sub.addActionListener(e -> { labelOperacao.setText("-"); n2.requestFocus(); });

        JButton mult = new JButton("*");
        mult.setBounds(180, 140, 55, 40);
        mult.addActionListener(e -> { labelOperacao.setText("*"); n2.requestFocus(); });

        JButton div = new JButton("/");
        div.setBounds(245, 140, 55, 40);
        div.addActionListener(e -> { labelOperacao.setText("/"); n2.requestFocus(); });

        // --- Botão Igual (=) ---
        JButton btnIgual = new JButton("=");
        btnIgual.setBounds(50, 200, 120, 40);
        btnIgual.addActionListener(e -> {
            try {
                double a = Double.parseDouble(n1.getText());
                double b = Double.parseDouble(n2.getText());
                String op = labelOperacao.getText();
                double res = 0;

                if (op.equals("+")) res = a + b;
                else if (op.equals("-")) res = a - b;
                else if (op.equals("*")) res = a * b;
                else if (op.equals("/")) {
                    if (b == 0) { resultado.setText("Erro: Divisão por 0"); return; }
                    res = a / b;
                }
                resultado.setText("Resultado: " + res);
            } catch (Exception err) {
                resultado.setText("Erro: Use números válidos");
            }
        });

        // --- Botão Limpar (C) ---
        JButton limpar = new JButton("C");
        limpar.setBounds(180, 200, 120, 40);
        limpar.addActionListener(e -> {
            n1.setText(""); n2.setText(""); labelOperacao.setText("");
            resultado.setText("Resultado:"); n1.requestFocus();
        });

        // Adicionar e mostrar
        janela.add(n1); janela.add(n2); janela.add(labelOperacao);
        janela.add(soma); janela.add(sub); janela.add(mult); janela.add(div);
        janela.add(btnIgual); janela.add(limpar); janela.add(resultado);
        janela.setVisible(true);

    } 
} 
package com.project.application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextField;
import java.awt.List;
import java.awt.Color;

import com.project.dto.GuessResult;
import com.project.dto.Hint;
import com.project.model.MasterMindBreaker;
import com.project.model.NumberGenerator;
import com.project.model.UserGuess;

import javax.swing.JButton;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
/**
 * MasterMind Oyunu
 * @author toshiba
 *
 */
public class MasterMindApplication {

	private JFrame frame;
	private NumberGenerator generatedNumber = new NumberGenerator();
	private MasterMindBreaker breaker = null;
	private Hint winnerHint = new Hint(4,0);
	private final static int DIGIT_COUNT = 4;
	private String breakerLastGuess = "";
	private final static String ERROR_MESSAGE = "Hatalı Numara Girişi";
	private final static String CHEAT_MESSAGE = "Beni kandırmaya çalışmışsın baştan başlayalım.";
	private final static String BREAKER_BUTTON_START_TEXT = "Başla";
	private final static String BREAKER_BUTTON_GUESS_TEXT = "Tahmin";
	private final static String BREAKER_HINT_ERROR = "İpucunu eksik girdin";
	private final static String USER_WIN_MESSAGE =" Hamlede Bildiniz!";
	private final static String BREAKER_WIN_MESSAGE =" Hamlede Bildim!";
	public static int userGuessCount = 1;
	public static int breakerGuessCount = 1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterMindApplication window = new MasterMindApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MasterMindApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		breaker = new MasterMindBreaker();
		
		TextField txtUserGuess = new TextField();
		txtUserGuess.setBounds(29, 157, 139, 19);
		txtUserGuess.addKeyListener(new KeyAdapter() {//text fielda sadece numeric değer girilmesi
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (!Character.isDigit(vChar) && (vChar != KeyEvent.VK_BACK_SPACE)
						&& (vChar != KeyEvent.VK_DELETE)) {
					e.consume();
				}
				
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(txtUserGuess);
		
		List listUserGuesses = new List();
		listUserGuesses.setBounds(29, 10, 139, 139);
		frame.getContentPane().add(listUserGuesses);
		
		JButton btnUserGuess = new JButton("Tahmin");
		btnUserGuess.setBounds(63, 205, 90, 25);
		frame.getContentPane().add(btnUserGuess);
		
		Label lblPlusses = new Label("");
		lblPlusses.setBounds(64, 182, 23, 21);
		lblPlusses.setForeground(Color.GREEN);
		frame.getContentPane().add(lblPlusses);
		
		Label lblMinusses = new Label("");
		lblMinusses.setBounds(106, 182, 23, 21);
		lblMinusses.setForeground(Color.YELLOW);
		frame.getContentPane().add(lblMinusses);
		
		Label lblError = new Label("");
		lblError.setBounds(29, 240, 145, 21);
		lblError.setForeground(Color.RED);
		frame.getContentPane().add(lblError);
		
		List listBreakerGuesses = new List();
		listBreakerGuesses.setBounds(244, 10, 139, 139);
		frame.getContentPane().add(listBreakerGuesses);
		
		JLabel lblHintPlusses = new JLabel("+");
		lblHintPlusses.setBounds(244, 154, 23, 15);
		lblHintPlusses.setFont(new Font("Dialog", Font.BOLD, 15));
		lblHintPlusses.setForeground(Color.GREEN);
		frame.getContentPane().add(lblHintPlusses);
		
		JLabel lblHintMinusses = new JLabel("-");
		lblHintMinusses.setBounds(244, 179, 23, 15);
		lblHintMinusses.setFont(new Font("Dialog", Font.BOLD, 18));
		lblHintMinusses.setForeground(Color.YELLOW);
		frame.getContentPane().add(lblHintMinusses);
		
		JComboBox comboPlusses = new JComboBox();
		comboPlusses.setBounds(274, 152, 90, 24);
		comboPlusses.setModel(new DefaultComboBoxModel(new String[] {"-", "0", "1", "2", "3", "4"}));
		frame.getContentPane().add(comboPlusses);
		
		JComboBox comboMinusses = new JComboBox();
		comboMinusses.setBounds(274, 179, 90, 24);
		comboMinusses.setModel(new DefaultComboBoxModel(new String[] {"-", "0", "1", "2", "3", "4"}));
		frame.getContentPane().add(comboMinusses);
		
		JButton btnBreaker = new JButton("GUESS");
		btnBreaker.setBounds(274, 205, 90, 25);
		frame.getContentPane().add(btnBreaker);
		btnBreaker.setText(BREAKER_BUTTON_START_TEXT);
		
		Label lblBreakerGuess = new Label("");
		lblBreakerGuess.setForeground(Color.WHITE);
		lblBreakerGuess.setBounds(372, 205, 68, 21);
		frame.getContentPane().add(lblBreakerGuess);
		
		Label lblBreakerError = new Label("");
		lblBreakerError.setFont(new Font("Dialog", Font.PLAIN, 8));
		lblBreakerError.setForeground(Color.RED);
		lblBreakerError.setBounds(209, 240, 231, 21);
		frame.getContentPane().add(lblBreakerError);
		
		btnUserGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userButtonOnClickFunction(txtUserGuess,lblPlusses,lblMinusses,listUserGuesses,lblError);
			}
		});
		
		btnBreaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				breakerButtonOnClickFunction( breaker, btnBreaker, comboPlusses , comboMinusses ,  listBreakerGuesses , lblBreakerGuess , lblBreakerError);
			}
		});

	}
	/**
	 * Programın kullanıcının aklından tuttuğu sayıyı tahmin etmeye çalışır
	 * @param breaker
	 * @param btnBreaker
	 * @param comboPlusses
	 * @param comboMinusses
	 * @param listBreakerGuesses
	 * @param lblBreakerGuess
	 * @param lblBreakerError
	 */
	private void breakerButtonOnClickFunction(MasterMindBreaker breaker,JButton btnBreaker, JComboBox comboPlusses , JComboBox comboMinusses , List listBreakerGuesses , Label lblBreakerGuess , Label lblBreakerError ) {
		
		lblBreakerError.setText("");
		
		if (breakerLastGuess.equals("")) {
			breakerLastGuess = breaker.startGuessing();
			lblBreakerGuess.setText(breakerLastGuess);
			btnBreaker.setText(BREAKER_BUTTON_GUESS_TEXT);
		} else {
		
		int plusses = Integer.parseInt(comboPlusses.getSelectedItem().toString());
		int minusses = Integer.parseInt(comboMinusses.getSelectedItem().toString());
		Hint breakerHint = new Hint(plusses, minusses);//kazanıp kazanmama durumu kontrol ediliyor
		if (breakerHint.equals(winnerHint)) {
			JOptionPane.showMessageDialog(frame, breakerGuessCount + BREAKER_WIN_MESSAGE);
		}else if (validateBreakerHints(comboPlusses,comboMinusses,lblBreakerError)) {

				GuessResult result = breaker.getHintAndGuessNewNumber(breakerHint);
				if (result.getErrorCode() == 0) {
					listBreakerGuesses.add(
							breakerGuessCount++ + ". " + breakerLastGuess + "      " + breakerHint.getHintString());
					lblBreakerGuess.setText(result.getBreakerGuess());
					breakerLastGuess = result.getBreakerGuess();
					comboPlusses.setSelectedItem("-");
					comboMinusses.setSelectedItem("-");
				} else {
					lblBreakerError.setText(CHEAT_MESSAGE);
					listBreakerGuesses.clear();
					breakerGuessCount = 1;
					breaker = new MasterMindBreaker();
					comboPlusses.setSelectedItem("-");
					comboMinusses.setSelectedItem("-");
					btnBreaker.setText(BREAKER_BUTTON_START_TEXT);
					breakerLastGuess = "";
				}
			}
		}

	}
	/**
	 * program için ipucu inputları kontrol ediliyor
	 * input girilmeden yeni tahmin yapılmıyor
	 * @param comboPlusses
	 * @param comboMinusses
	 * @param lblBreakerError
	 * @return
	 */
	private boolean validateBreakerHints(JComboBox comboPlusses , JComboBox comboMinusses ,Label lblBreakerError) {
		
		if (comboPlusses.getSelectedItem().toString().equals("-") || comboMinusses.getSelectedItem().toString().equals("-")) {
			lblBreakerError.setText(BREAKER_HINT_ERROR);
			return false;
		}
		return true;
	}
	
	/**
	 * Kullanıcının programın ürettiği sayıyı tahmin etmesi için gerekli ipuçlarını verir
	 * @param txtUserGuess
	 * @param lblPlusses
	 * @param lblMinusses
	 * @param listUserGuesses
	 * @param lblError
	 */
	private void userButtonOnClickFunction(TextField txtUserGuess , Label lblPlusses, Label lblMinusses , List listUserGuesses, Label lblError ) {
		
		lblError.setText("");
		
		if (validateUserTxtField(txtUserGuess.getText(),lblError)) {
			int enteredGuess = Integer.parseInt(txtUserGuess.getText());
			UserGuess userGuess = new UserGuess(enteredGuess);
			Hint hint = userGuess.checkUserGuess(generatedNumber.getGeneratedNumber());
			if (hint.equals(winnerHint)) {
				JOptionPane.showMessageDialog(frame, userGuessCount + USER_WIN_MESSAGE);
			}
			txtUserGuess.setText("");
			lblPlusses.setText("+" + hint.getPlusCount());
			lblMinusses.setText("-" + hint.getMinusCount());
			listUserGuesses.add(userGuessCount++ + ". " + enteredGuess + "      " + hint.getHintString());
		}
	}
	/**
	 * kullanıcının girdiği tahmin formata uygun olup olmadığı kontrol ediliyor
	 * 4 basamaklı rakamları birbirinden farklı
	 * @param guess
	 * @param lblError
	 * @return
	 */
	private boolean validateUserTxtField(String guess, Label lblError ) {

		if (guess.equals("")) {
			lblError.setText(ERROR_MESSAGE);
			return false;
		}
		
		if (guess.length() != DIGIT_COUNT) {
			lblError.setText(ERROR_MESSAGE);
			return false;
		}
		
		if (occuranceChecker(guess)) {
			lblError.setText(ERROR_MESSAGE);
			return false;
		}
		return true;

	}
	
	/**
	 * sayının basamakları farklı olup olmadığını kontrol eder
	 * @param guess
	 * @return
	 */
	private boolean occuranceChecker(String guess) {
		
		char[] digitArray = guess.toCharArray();
		
		for (int i = 0; i < DIGIT_COUNT; i++) {
			int occurance = countOccurences(guess, digitArray[i], 0);
			if (occurance > 1) {
				return true;
			}
		}

		return false;
	}
	/**
	 * aynı rakam birden fazla kullanılmışsa
	 * kullanıldığı kadar değer döner
	 * @param guess
	 * @param digit
	 * @param index
	 * @return
	 */
	private int countOccurences(String guess, char digit, int index) {
			    if (index >= guess.length()) {
			        return 0;
			    }
			     
			    int count = guess.charAt(index) == digit ? 1 : 0;
			    return count + countOccurences(guess, digit, index + 1);
			}
}

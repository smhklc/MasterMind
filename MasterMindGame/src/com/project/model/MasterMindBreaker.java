package com.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.project.dto.FourDigitNumber;
import com.project.dto.GuessResult;
import com.project.dto.Hint;

/**
 * programın tahmini bulma sınıfı
 * @author toshiba
 *
 */
public class MasterMindBreaker {
	private static final int NUMARALS_COUNT = 10;
	private static final int DIGIT_COUNT = 4;
	private List<String> numaralList = new ArrayList<String>();
	private List<String> possibleGuesses = new ArrayList<String>();
	private String breakerGuess = "";

	public MasterMindBreaker() {
		setNumaralList();
		setAllPossibleGuesses();
		guessNewNumber();
	}
	/**
	 * rakam listesini oluşturur
	 */
	private void setNumaralList() {
		for (int i = 0; i < NUMARALS_COUNT; i++) {
			numaralList.add(Integer.toString(i));
		}
	}
	/**
	 * olası tüm tahminleri bir listeye doldurur
	 * yapılacak tahminleri bu liste içerisinden seçer
	 */
	private void setAllPossibleGuesses() {
		
		for (int digit1 = 1; digit1 < NUMARALS_COUNT - 1; digit1++) {
			
			for (int digit2 = 0; digit2 < NUMARALS_COUNT; digit2++) {
				
				for (int digit3 = 0; digit3 < NUMARALS_COUNT; digit3++) {
					
					for (int digit4 = 0; digit4 < NUMARALS_COUNT; digit4++) {
						
						if (digitCompare(digit1,digit2,digit3,digit4)) {
							String possibleGuess = numaralList.get(digit1) + numaralList.get(digit2) +
									numaralList.get(digit3) + numaralList.get(digit4);
							
							possibleGuesses.add(possibleGuess);
						}
						//String differentDigitNumber = 
					}//end of forth digit
				}//end of third digit
			}//end of second digit
		}//end of first digit

	}

	/**
	 * tahminin rakamları birbirinden farklı olması için
	 * tüm basamaklar birbiriyle kontrol eder
	 * @param digit1
	 * @param digit2
	 * @param digit3
	 * @param digit4
	 * @return
	 */
	private boolean digitCompare(int digit1 , int digit2 , int digit3 , int digit4) {
		
		if (digit1 != digit2 && digit1 != digit3 && digit1 != digit4) {
			if (digit2 != digit3 && digit2 != digit4)  {
				if (digit3 != digit4) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * son tahmini doğru tahmin olarak kabul eder
	 * son tahminin asıl ipucuyla aynı olan olası tahminleri listede tutar
	 * diğerlerini listeden çıkarır
	 * böylece tahmin listesi sürekli kısalır
	 * @param hint
	 */
	private void collapsePossibleGuesses (Hint hint) {
		
		for (int i = 0; i < possibleGuesses.size(); i++) {
                if (!comparePossibleGuesses(possibleGuesses.get(i)).equals(hint)) {
                    possibleGuesses.remove(i--);
                }
		}

	}
	/**
	 * son yapılan tahmini doğru tahmin kabul ederek
	 * girilen tahminin ipucunu bulur
	 * @param possibleGuess
	 * @return
	 */
	private Hint comparePossibleGuesses(String possibleGuess) {
		UserGuess compareGuess = new UserGuess(Integer.parseInt(possibleGuess));
		return compareGuess.checkUserGuess(convertStringToFourDigitNumber(breakerGuess));
	}
	private FourDigitNumber convertStringToFourDigitNumber(String value) {
		int fullNumber = Integer.parseInt(value);
		int digits [] = new int[DIGIT_COUNT];
		
		for (int i = 0; i < digits.length; i++) {
			digits[i] = Integer.parseInt(value.substring(i, i+1));
		}
		return new FourDigitNumber(fullNumber,digits);
	}

	/**
	 * olası tahminlerden yeni tahmin seçme
	 */
	private void guessNewNumber() {
		
		Random randomNumber = new Random(); 
		int indexOfRandomGuess = randomNumber.nextInt(possibleGuesses.size());
		
		breakerGuess = possibleGuesses.get(indexOfRandomGuess);
	}
	
	/**
	 * ilk yapılan tahmin getirir
	 * @return
	 */
	public String startGuessing() {
		return this.breakerGuess;
	} 
	
	/**
	 * 
	 * @param hint
	 * @return
	 */
	public GuessResult getHintAndGuessNewNumber(Hint hint) {
		
		GuessResult result = null; 
		
		collapsePossibleGuesses(hint);
		try {
			guessNewNumber();
			result = new GuessResult(0, this.breakerGuess);
		} catch (Exception e) {
			result = new GuessResult(-1 , "");			
		}
		
		return result;
	}
}

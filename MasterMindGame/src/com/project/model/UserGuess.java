package com.project.model;

import java.util.Arrays;

import com.project.dto.FourDigitNumber;
import com.project.dto.Hint;

/**
 * kullanıcı tahminini kontrol eden sınıf
 * @author toshiba
 *
 */
public class UserGuess {
	private FourDigitNumber userGuess;
	private static final int DIGIT_COUNT = 4;
	
	public UserGuess(int userGuess) {
		this.userGuess = new FourDigitNumber(userGuess,prepareDigits(userGuess));
	}
	
	/**
	 * kullanıcının tahminine göre ipucu üreten metoddur
	 * @param generatedNumber
	 * @return
	 */
	public Hint checkUserGuess(FourDigitNumber generatedNumber) {
		
		int pluses = getPluses(generatedNumber);
		int minuses = getMinuses(generatedNumber, pluses);
		
		Hint hint = new Hint(pluses, minuses);
		
		return hint;
	}
	
	/**
	 * tahminin doğru basamak ve doğru değerlerini bulur (+ lar)
	 * @param generatedNumber
	 * @return
	 */
	private int getPluses(FourDigitNumber generatedNumber) {
		int pluses = 0;
		
		for (int i = 0; i < DIGIT_COUNT; i++) {
			int user = this.userGuess.getDigits()[i];
			int generated = generatedNumber.getDigits()[i];
			if (user == generated) {
				pluses++;
			}
		}
		return pluses;
	}
	
	/**
	 * tahminin doğru değerlerini bulur (- ler)
	 * @param generatedNumber
	 * @param pluses
	 * @return
	 */
	private int getMinuses(FourDigitNumber generatedNumber , int pluses) {
		int minuses = 0;
		
		for (int i = 0; i < DIGIT_COUNT; i++) {
			int userGuessDigit = this.userGuess.getDigits()[i];
			if (Arrays.stream(generatedNumber.getDigits()).anyMatch(q -> q == userGuessDigit)) {
				minuses++;
			}
		}
		
		return minuses- pluses;
	}
	
	/**
	 * tahmini basamaklarına ayırarak diziye atama işlemi yapar
	 * @param userGuess
	 * @return
	 */
	private int[] prepareDigits(int userGuess) {
		String userGuessBuffer = Integer.toString(userGuess);
		
		int digits[] = new int[DIGIT_COUNT];
		
		for (int i = 0; i < userGuessBuffer.length(); i++) {
			digits[i] = Integer.parseInt(userGuessBuffer.substring(i,i+1)); 
		}
		
		return digits;
	}	
}

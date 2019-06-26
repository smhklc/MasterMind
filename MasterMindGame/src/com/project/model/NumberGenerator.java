package com.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.project.dto.FourDigitNumber;
/**
 * rakamları farklı sayı üreten sınıf
 * @author toshiba
 *
 */
public class NumberGenerator {

	private FourDigitNumber generatedNumber;
	private static final int DIGIT_COUNT = 4;
	
	public NumberGenerator() {
		super();
		generateNumber();
	}
	
	private void generateNumber() {
		
		int digits[] = new int[DIGIT_COUNT];
		List<Integer> numaralList = new ArrayList<Integer>(); 
		fillNumaralListWithoutZero(numaralList);
		digits[0] = getUniqueNumaral(numaralList);
		addNumaralListZero(numaralList);
		for (int i = 1; i < DIGIT_COUNT; i++) {	
			digits[i] = getUniqueNumaral(numaralList);
		}
		
		this.generatedNumber = new FourDigitNumber(prepareExactNumber(digits), digits);
		
	}
	/**
	 * her bir elemanı rastgele üretilecek sayının basamağı olan diziyi
	 * sayıya çevirir
	 * @param digits
	 * @return
	 */
	private int prepareExactNumber(int digits[]) {
		
		String exactNumberBuffer = "";
		for (int i = 0; i < digits.length; i++) {
			exactNumberBuffer = exactNumberBuffer + digits[i];
		}
		
		int exactNumber = Integer.parseInt(exactNumberBuffer);
		
		return exactNumber;
		
	}
	/**
	 * rakam listesinden rastgele bir rakam seçip 
	 * seçilen rakamı listeden çıkarır
	 * @param numaralList
	 * @return
	 */
	private int getUniqueNumaral(List<Integer> numaralList) {
		Random randomNumber = new Random();
		int numaralListCount = numaralList.size();
		int indexOfNumaralList = randomNumber.nextInt(numaralListCount);
		int uniqueNumaral = numaralList.get(indexOfNumaralList);
		numaralList.remove(indexOfNumaralList);
		return uniqueNumaral;
	}
	/**
	 * rakam listesini oluşturan metod
	 * ilk basamak 0 olamayacağı için 1 den başlıyor
	 * @param numaralList
	 */
	private void fillNumaralListWithoutZero(List<Integer> numaralList) {
		for (int i = 1; i < 10; i++) {
			numaralList.add(i);
		}
	}
	/**
	 * rakam listesine 0 ekler
	 * @param numaralList
	 */
	private void addNumaralListZero(List<Integer> numaralList) {
		numaralList.add(0);
	}

	public FourDigitNumber getGeneratedNumber() {
		return generatedNumber;
	}
}

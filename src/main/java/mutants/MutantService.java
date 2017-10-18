package mutants;
import java.util.ArrayList;
import java.util.List;

public class MutantService {

	private List<String> dictionary;

	public MutantService() {
		dictionary = new ArrayList<String>();
		dictionary.add("AAAA");
		dictionary.add("CCCC");
		dictionary.add("TTTT");
		dictionary.add("GGGG");
	}

	boolean isMutant(String[] dna) {

		if (dna == null) {
			throw new NullPointerException("DNA sequence cannot be null.");
		}

		char[][] wordSearch = new char[6][6];

		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna[i].length(); j++) {
				wordSearch[i][j] = dna[i].charAt(j);
			}
		}

		List<String> validWords = findSequence(wordSearch);
		
		for(String word: validWords) {
			System.out.println(word);
		}
		
		return validWords.size() > 2;
	}

	private List<String> findSequence(char[][] m) {
		List<String> validWords = new ArrayList<String>();

		for (String word : dictionary) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if (isWordInWordSearchInner(m, word, i, j, null)) {
						validWords.add(word);
					}
				}
			}
		}
		return validWords;
	}

	private static boolean isWordInWordSearchInner(char[][] wordSearch, String word, int x, int y,
			DirectionEnum direction) {
		if (word.isEmpty()) {
			return true;
		}

		if (notPossibleMatch(word.length(), x, y, direction)) {
			return false;
		}

		else {
			boolean firstLetter = matches(wordSearch, x, y, word.charAt(0));
			if (firstLetter) {
				if (direction == null) {
					boolean left = isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y, DirectionEnum.LEFT);
					boolean right = isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y,
							DirectionEnum.RIGHT);
					boolean bottom = isWordInWordSearchInner(wordSearch, word.substring(1), x, y + 1,
							DirectionEnum.BOTTOM);
					boolean top = isWordInWordSearchInner(wordSearch, word.substring(1), x, y - 1, DirectionEnum.TOP);
					boolean topLeft = isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y - 1,
							DirectionEnum.TOP_LEFT);
					boolean topRight = isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y - 1,
							DirectionEnum.TOP_RIGHT);
					boolean bottomLeft = isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y + 1,
							DirectionEnum.BOTTOM_LEFT);
					boolean bottomRight = isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y + 1,
							DirectionEnum.BOTTOM_RIGHT);

					return left || right || bottom || top || topLeft || topRight || bottomLeft || bottomRight;
				}
				if (direction == DirectionEnum.LEFT) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y, DirectionEnum.LEFT);
				}
				if (direction == DirectionEnum.RIGHT) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y, DirectionEnum.RIGHT);
				}
				if (direction == DirectionEnum.BOTTOM) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x, y + 1, DirectionEnum.BOTTOM);
				}
				if (direction == DirectionEnum.TOP) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x, y - 1, DirectionEnum.TOP);
				}
				if (direction == DirectionEnum.BOTTOM_LEFT) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y + 1,
							DirectionEnum.BOTTOM_LEFT);
				}
				if (direction == DirectionEnum.BOTTOM_RIGHT) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y + 1,
							DirectionEnum.BOTTOM_RIGHT);
				}
				if (direction == DirectionEnum.TOP_LEFT) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x - 1, y - 1, DirectionEnum.TOP_LEFT);
				}
				if (direction == DirectionEnum.TOP_RIGHT) {
					return isWordInWordSearchInner(wordSearch, word.substring(1), x + 1, y - 1,
							DirectionEnum.TOP_RIGHT);
				}
			}
			return false;
		}

	}

	private static boolean notPossibleMatch(int wordLength, int x, int y, DirectionEnum direction) {
		if (direction == null) {
			return false;
		}
		if (direction == DirectionEnum.LEFT) {
			return wordLength-1 > x;
		}
		if (direction == DirectionEnum.RIGHT) {
			return wordLength-1 > (6 - x);
		}
		if (direction == DirectionEnum.BOTTOM) {
			return wordLength-1 > (6 - y);
		}
		if (direction == DirectionEnum.TOP) {
			return wordLength-1 > y;
		}
		if (direction == DirectionEnum.BOTTOM_LEFT) {
			return wordLength-1 > (6 - y) && wordLength > x;
		}
		if (direction == DirectionEnum.BOTTOM_RIGHT) {
			return wordLength-1 > (6 - y) && wordLength > (6 - x);
		}
		if (direction == DirectionEnum.TOP_LEFT) {
			return wordLength-1 > y && wordLength > x;
		}
		if (direction == DirectionEnum.TOP_RIGHT) {
			return wordLength-1 > y && wordLength > (6 - x);
		}
		return true;
	}

	private static boolean matches(char[][] wordSearch, int x, int y, char c) {
		if (x < 0 || x >= wordSearch.length || y < 0 || y >= wordSearch[x].length) {
			return false;
		} else {
			return wordSearch[x][y] == c;
		}
	}
}
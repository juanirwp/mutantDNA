package mutants;
public class Main {

	public static void main(String[] args) {
		
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		String[] dna2 = {"ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
		String[] dna3 = {"AAAAAA","AAACAA","AAATAA","AGAAAA","AACAAA","AAATAA"};
		
		MutantService ws = new MutantService();

		System.out.println(ws.isMutant(dna));
		System.out.println(ws.isMutant(dna2));
		System.out.println(ws.isMutant(dna3));
		
	}

}

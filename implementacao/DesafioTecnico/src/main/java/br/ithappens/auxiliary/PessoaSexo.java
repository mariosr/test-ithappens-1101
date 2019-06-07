package br.ithappens.auxiliary;

public enum PessoaSexo {
	   MASCULINO("masculino","M"),
	   FEMININO("feminino", "F"),
	   INDEFINIDO("indefinido","I");

	   private String sexIndex;
	   private String initialSex;

	   private PessoaSexo(String sexIndex, String initialSex) {
		   this.sexIndex = sexIndex; 
		   this.initialSex = initialSex;
	   }

	   public static PessoaSexo getSex(String sexIndex) {
	      for (PessoaSexo l : PessoaSexo.values()) {
	          if (l.sexIndex.equalsIgnoreCase(sexIndex) || l.initialSex.equalsIgnoreCase(sexIndex)) return l;
	      }
	      return PessoaSexo.INDEFINIDO;
	   }
}

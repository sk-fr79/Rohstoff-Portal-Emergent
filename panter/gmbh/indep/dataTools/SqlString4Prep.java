package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;

import panter.gmbh.indep.exceptions.myException;

public class SqlString4Prep {
	private String    cSQL_plain = null;
	private Object[]  arrayPlaceHolders = null;   //allowed ObjectTypes:  String, Long, Integer, BigDecimal
	
	public SqlString4Prep(String cSQL_plain, Object[] arrayPlaceHolders) throws myException {
		super();
		this.cSQL_plain = cSQL_plain;
		this.arrayPlaceHolders = arrayPlaceHolders;
		
		//jetzt zaehlen, ob alle platzhalter auch eine entsprechung haben
		int i=0;
		for (char c: this.cSQL_plain.toCharArray()) {
			if (c=='?') {
				i++;
			}
		}
		
		for (Object ob: this.arrayPlaceHolders) {
			if (!(ob instanceof String || ob instanceof Long || ob instanceof Integer || ob instanceof BigDecimal)) {
				throw new myException(this,"allowed ObjectTypes:  String, Long, Integer, BigDecimal !!");
			}
		}
		
		
		if (i!=this.arrayPlaceHolders.length) {
			throw new myException(this,"Number of parameters must refer to placeholders !!");
		}
		
	}

	public String get_SQL_plain() {
		return cSQL_plain;
	}

	public Object[] get_arrayPlaceHolders() {
		return arrayPlaceHolders;
	}
	
	
}

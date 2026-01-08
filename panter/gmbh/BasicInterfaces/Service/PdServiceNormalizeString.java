package panter.gmbh.BasicInterfaces.Service;

import java.text.Normalizer;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class PdServiceNormalizeString {
	public static String normalisierung(String str_to_normalize) throws myException{
		
		if(S.isFull(str_to_normalize)) {
			str_to_normalize = str_to_normalize.toUpperCase();
			str_to_normalize = str_to_normalize.replaceAll("'", " ");
			str_to_normalize = str_to_normalize.replaceAll("-", " ");
			String str_normalize = Normalizer.normalize(str_to_normalize, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			return str_normalize;
		}
		return "";
	}
}

/**
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.Comparator;
import java.util.HashMap;

import panter.gmbh.indep.S;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class PdServiceCleanNormalizeAndSeparateStrings {

	private HashMap<String, String>  hmReplacers = new HashMap<>();


	//alle input-string ersetzen
	public VEK<String>  getReplacedStrings(VEK<String> originals) {
		VEK<String> v = new VEK<String>();

		for (String o : originals) {
			String o2 = o;
			for (String r: this.hmReplacers.keySet()) {
				o2=this.replaceText(o2, r, hmReplacers.get(r));
			}
			v.add(o2);
		}

		return v;
	}


	//alle input-strings in grossbuchstaben umwandeln
	public VEK<String>  getToUpperStrings(VEK<String> originals) {
		VEK<String> v = new VEK<String>();

		for (String o : originals) {
			v.add(o.toUpperCase());
		}

		return v;
	}


	//alle teilstrings in den originals separieren
	public VEK<String>  getSeparatedStrings(VEK<String> originals) {
		VEK<String> v = new VEK<String>();

		for (String o : originals) {
			String[] seps = o.split(" ");
			if (seps!=null) {
				for (String s: seps) {
					if (S.isFull(s.trim())) {
						v._addIfNotIn(s.trim());
					}
				}
			}
		}
		return v;
	}



	/**
	 * zuerst saeubern / ersetzen / gross / separieren
	 * @param originals
	 * @return
	 */
	public VEK<String>  getCompleteCycle(VEK<String> originals) {
		return this.getSeparatedStrings(this.getToUpperStrings(this.getReplacedStrings(originals)));
	}

	/**
	 * zuerst saeubern / ersetzen / gross / separieren,
	 * dann die numberOfString zurueckgeben, die laenger sind als minLen
	 * @param originals
	 * @param numberOfString
	 * @param minLen
	 * @return
	 */
	public VEK<String>  getCompleteCycleLongestN(VEK<String> originals, int numberOfString, int minLen) {
		VEK<String> v= this.getSeparatedStrings(this.getToUpperStrings(this.getReplacedStrings(originals)));
		return this.getLongestN(numberOfString, v, minLen);
	}





	public PdServiceCleanNormalizeAndSeparateStrings _addReplacePair(String toReplace, String isInserted) {
		this.hmReplacers.put(toReplace, isInserted);
		return this;
	}


	public PdServiceCleanNormalizeAndSeparateStrings _addStdReplacers() {
		this
		._addReplacePair(".", "")
		._addReplacePair("/", " ")
		._addReplacePair("-", " ")
		._addReplacePair(",", " ")
		._addReplacePair("'", " ")
		._addReplacePair(":", "")
		._addReplacePair("ä", "ae")
		._addReplacePair("ö", "oe")
		._addReplacePair("ü", "ue")
		._addReplacePair("Ä", "AE")
		._addReplacePair("Ö", "OE")
		._addReplacePair("Ü", "UE")
		._addReplacePair("ß", "ss")

		._addReplacePair("é", "e")
		._addReplacePair("è", "e")
		._addReplacePair("ê", "e")
		._addReplacePair("à", "a")
		._addReplacePair("ï", "I")
		._addReplacePair("É", "E")
		._addReplacePair("È", "E")
		._addReplacePair("Ê", "E")
		._addReplacePair("À", "A")
		._addReplacePair("Ï", "I")
		._addReplacePair("ç", "c")
		._addReplacePair("Ç", "C")
		;

		return this;
	}


	private String replaceText(String orig, String find, String replace) {
		String s = orig;

		while (s.contains(find)) {
			s = s.replace(find, replace);
		}

		return s;
	}

	public VEK<String> getLongest(VEK<String> completeStrings, int iMinLenOfStrings) {
		VEK<String> v = new VEK<String>();
		VEK<String> v1 = new VEK<String>()._a();
		v1.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return new Long(S.NN(o2).length()).compareTo(new Long(S.NN(o1).length()));
			}
		});

		for (int i=0;i<completeStrings.size(); i++) {
			if(i<v1.size()) {
				if (S.isFull(v1.get(i)) && v1.get(i).length()>=iMinLenOfStrings) {
					v._a(v1.get(i));
				}
			}
		}
		return v;
	}


	/**
	 * gibt die num - laengsten String aus dem Vector zurueck (falls einer fehlt, wird mit dem letzten aufgefuellt)
	 * @param num
	 * @param completeStrings
	 * @param iMinLenOfStrings  minimale laenge, die die return-Strings haben muessen
	 * @return
	 */
	public VEK<String> getLongestN(int num,  VEK<String> completeStrings, int iMinLenOfStrings) {
		VEK<String> v = new VEK<String>();
		VEK<String> v1 = new VEK<String>()._a(completeStrings);

		v1.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return new Long(S.NN(o2).length()).compareTo(new Long(S.NN(o1).length()));
			}
		});

		for (int i=0;i<num; i++) {
			if(i<v1.size()) {
				if (S.isFull(v1.get(i)) && v1.get(i).length()>=iMinLenOfStrings) {
					v._a(v1.get(i));
				}
			}
		}

		if (v.size()==0) {
			return v;
		} else if (v.size()<num ) {
			String fuellString = v.get(v.size()-1);
			int size = v.size();

			for (int i=0;i<(num-size); i++) {
				v.add(fuellString);
			}
		}

		return v;

	}


}

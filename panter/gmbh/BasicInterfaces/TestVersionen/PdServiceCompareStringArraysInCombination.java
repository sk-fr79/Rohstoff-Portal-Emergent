/**
 * 
 */
package panter.gmbh.BasicInterfaces.TestVersionen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.LongStream;

import panter.gmbh.BasicInterfaces.Service.PdServiceNormalizeString;
import panter.gmbh.BasicInterfaces.Service.PdServicePermutater;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * es werden zwei string-bloecke mit einander verbunden, wobei der linke String (verketteter linker lock) folgendermassen gegen die einzelnen rechten geprueft wird:
 * linkerString.contains(jedeKombinationVonRechts) 
 */
public class PdServiceCompareStringArraysInCombination {

	
	private VEK<String>  leftSide = new VEK<String>();
	private VEK<String>  rightSide = new VEK<String>();
	
	private boolean      normalize = false;
	private int    		 minimalePrueflaenge = 4;   //damit wird verhindert, dass zb. sowas geprueft wird wie: "HANSMEIERHUBER".contains("MEI")
	
	private boolean      matchFound = false;
	
	/**
	 * 
	 */
	public PdServiceCompareStringArraysInCombination() {
		super();
	}
	
	public PdServiceCompareStringArraysInCombination _setNormalize(boolean normalize) {
		this.normalize = normalize;
		return this;
	}

	public PdServiceCompareStringArraysInCombination _setMinimalePrueflaengeRechts(int minimalePrueflaengeRechts) {
		this.minimalePrueflaenge = minimalePrueflaengeRechts;
		return this;
	}
	
	
	public PdServiceCompareStringArraysInCombination _addLeftSide(String left) throws myException {
		
		ArrayList<String> teile = S.Separat(left, ' ');
		
		for (String s: teile) {
			if (this.normalize) {
				s=PdServiceNormalizeString.normalisierung(s);
			}
			this.leftSide._a(s);
		}
		
		return this;
	}
	
	
	public PdServiceCompareStringArraysInCombination _addRightSide(String right) throws myException {
		
		ArrayList<String> teile = S.Separat(right, ' ');
		
		for (String s: teile) {
			if (this.normalize) {
				s=PdServiceNormalizeString.normalisierung(s);
			}
			this.rightSide._a(s);
		}
		
		return this;
	}
	
	
	

	public boolean isMatching() {
		boolean ret = false;
		
		//den linken String zusammenfassen
		StringBuffer leftBuffer = new StringBuffer();
		for (String s: this.leftSide) {
			leftBuffer.append(s);
		}
		String left = leftBuffer.toString(); 
		
		if (left.length()>this.minimalePrueflaenge) {
			ret = this.compareRecursive(left, new VEK<String>()._a(this.rightSide), 0);
		}
		return ret;
	}

	
	private boolean compareRecursive(String leftSide, VEK<String> vRightSide, int startPoint) {
		boolean ret = false;

		LongStream.range(0, PdServicePermutater.factorial(vRightSide.size())).forEachOrdered(i -> {
	        VEK<String> vPermut = new VEK<String>()._a(PdServicePermutater.permutation(i, vRightSide));
	        this.compare(leftSide, vPermut);
	    });
		
		
		
//		if ( ! ret) {
//			for (int i=startPoint; i<vRightSide.size(); i++) {
//				DEBUG._print(vRightSide," / ");
//
//				Collections.swap(vRightSide, startPoint, i);
//				
////				ret = this.compare(leftSide, vRightSide,startPoint);   //unvertauscht vergleichen
//	
//				//weitersuchen, naechste ebene
//				//if (startPoint!=i) {
//				  ret = compareRecursive(leftSide, new VEK<String>()._a(vRightSide), startPoint+1);
//				//}
//
//				  
//				  
//				//zurueckstauschen
//				Collections.swap(vRightSide, i,startPoint);
//				
//				if (ret) {
//					break;
//				}
//			}
//		}
		
		
		
		
		return ret;
	}
	
	
	private boolean compare(String leftSide, VEK<String> vRightSide) {
		boolean ret = false;
		
		StringBuffer sb = new StringBuffer();
		for (String s: vRightSide) {
			sb.append(s);
		}
		//jede teilkombination wird geprueft
		DEBUG._print("->  "+leftSide+".contains("+sb.toString()+")");
		
		if (sb.toString().length()>this.minimalePrueflaenge && leftSide.contains(sb.toString())) {
			ret = true;
		}
		return ret;
	}
	
	
	
}

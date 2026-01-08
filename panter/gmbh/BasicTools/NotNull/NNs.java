/**
 * 
 */
package panter.gmbh.BasicTools.NotNull;

/**
 * @author martin
 *
 */
public class NNs implements IF_NN<String>{

	private NNs() {
		super();
	}

	public static String nn(String s_orig, String s_default) {
		return new NNs().notNull(s_orig, s_default);
	}
	
	
}

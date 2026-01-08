/**
 * 
 */
package panter.gmbh.BasicTools.NotNull;

/**
 * @author martin
 *
 */
public class NNint implements IF_NN<Integer>{

	private NNint() {
		super();
	}

	public static Integer nn(Integer s_orig, Integer s_default) {
		return new NNint().notNull(s_orig, s_default);
	}
	
	
}

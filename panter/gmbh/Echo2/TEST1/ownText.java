/**
 * 
 */
package panter.gmbh.Echo2.TEST1;

/**
 * @author martin
 *
 */
public class ownText implements IF_ownText{

	private String s = null;

	
	/**
	 * @param p_s
	 */
	public ownText(String p_s) {
		super();
		this.s = p_s;
	}




	@Override
	public String s() {
		return s;
	}

}

/**
 * panter.gmbh.Echo2.RB.TOOLS
 * @author martin
 * @date 26.11.2018
 * 
 */
package panter.gmbh.Echo2.RB.TOOLS;

/**
 * @author martin
 * @date 26.11.2018
 *
 */
public class Gl {

	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @param span
	 * @param insets
	 * @return
	 */
	public static RB_gld lt(int span, int insets) {
		return new RB_gld()._left_top()._span(span)._ins(insets);
	}
	
	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @param span
	 * @param insets
	 * @return
	 */
	public static RB_gld lm(int span, int insets) {
		return new RB_gld()._left_mid()._span(span)._ins(insets);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @param span
	 * @param insets
	 * @return
	 */
	public static RB_gld lb(int span, int insets) {
		return new RB_gld()._left_bottom()._span(span)._ins(insets);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @return GridLayoutData left_top_span1_ins(1,1,1,1)
	 */
	public static RB_gld lt() {
		return new RB_gld()._left_top()._span(1)._ins(1);
	}


	
	/**
	 * 
	 * @author martin
	 * @date 09.01.2019
	 *
	 * @param span
	 * @param insLeft
	 * @param insTop
	 * @param insRight
	 * @param insBottom
	 * @return
	 */
	public static RB_gld lt(int span, int insLeft, int insTop, int insRight, int insBottom) {
		return new RB_gld()._left_top()._span(span)._ins(insLeft,insTop,insRight,insBottom);
	}

	/**
	 * 
	 * @author martin
	 * @date 09.01.2019
	 *
	 * @param span
	 * @param insLeft
	 * @param insTop
	 * @param insRight
	 * @param insBottom
	 * @return
	 */
	public static RB_gld lm(int span, int insLeft, int insTop, int insRight, int insBottom) {
		return new RB_gld()._left_mid()._span(span)._ins(insLeft,insTop,insRight,insBottom);
	}

	/**
	 * 
	 * @author martin
	 * @date 09.01.2019
	 *
	 * @param span
	 * @param insLeft
	 * @param insTop
	 * @param insRight
	 * @param insBottom
	 * @return
	 */
	public static RB_gld lb(int span, int insLeft, int insTop, int insRight, int insBottom) {
		return new RB_gld()._left_bottom()._span(span)._ins(insLeft,insTop,insRight,insBottom);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @return GridLayoutData left_mid_span1_ins(1,1,1,1)
	 */
	public static RB_gld lm() {
		return new RB_gld()._left_mid()._span(1)._ins(1);
	}

	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @return GridLayoutData left_bottom_span1_ins(1,1,1,1)
	 */
	public static RB_gld lb() {
		return new RB_gld()._left_bottom()._span(1)._ins(1);
	}

	
}

/**
 * panter.gmbh.Echo2.RB.TOOLS
 * @author martin
 * @date 23.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;

/**
 * @author martin
 * @date 23.05.2019
 *
 */
public class GR extends E2_Grid {
  
	private RB_gld layoutStdTxt = new RB_gld()._left_bottom()._ins(0, 0, 0, 0);
	private RB_gld layoutStdComp = new RB_gld()._left_mid()._ins(0, 0, 0, 0);
	
	private GR() {
		super();
	}

	public static GR n(int ... spalten) {
		GR g = new GR();
		if (spalten!=null) {
			g = g.cW(spalten);
		}
		return g;
	}

	
	/**
	 * layoutMid
	 * @author martin
	 * @date 23.05.2019
	 *
	 * @return
	 */
	public GR lMid() {
		layoutStdTxt = new RB_gld()._center_bottom()._ins(0, 0, 0, 0);
		layoutStdComp = new RB_gld()._center_mid()._ins(0, 0, 0, 0);

		return this;
	}
	
	
	/**
	 * columnWidth
	 * @author martin
	 * @date 23.05.2019
	 *
	 * @param spalten
	 * @return
	 */
	public GR cW(int ... spalten) {
		this._setSize(spalten);
		MyLong l = new MyLong(0);
		for (int i=0; i<spalten.length;i++) {
			l.add(spalten[i]);
		}
		this._w(l.getInt());
		return this;
	}
	
	
	
	/**
	 * addSmall
	 * @author martin
	 * @date 23.05.2019
	 *
	 * @param as
	 * @return
	 */
	public GR aS(String ... as) {
		for (String s: as) {
			this._a(new RB_lab(s)._fsa(-2)._fo_italic()._col_fore_dgrey(), layoutStdTxt);
		}
		return this;
	}
	
	/**
	 * addSmallTranslated
	 * @author martin
	 * @date 23.05.2019
	 *
	 * @param as
	 * @return
	 */
	public GR aST(String ... as) {
		for (String s: as) {
			this._a(new RB_lab(S.ms(s))._fsa(-2)._fo_italic()._col_fore_black(), layoutStdTxt);
		}
		return this;
	}
	
	/**
	 * addSmall
	 * @author martin
	 * @date 23.05.2019
	 *
	 * @param as
	 * @return
	 */
	public GR aS(MyE2_String ... as) {
		for (MyE2_String s: as) {
			this._a(new RB_lab(s)._fsa(-2)._fo_italic()._col_fore_dgrey(), layoutStdTxt);
		}
		return this;
	}
	
	
	/**
	 * addComponents
	 * @author martin
	 * @date 23.05.2019
	 *
	 * @param ac
	 * @return
	 */
	public GR aC(Component ... ac) {
		for (Component c: ac) {
			this._a(c, layoutStdComp);
		}
		return this;
	}
	
}

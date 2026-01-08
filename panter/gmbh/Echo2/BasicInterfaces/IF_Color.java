package panter.gmbh.Echo2.BasicInterfaces;



import java.lang.reflect.Method;

import nextapp.echo2.app.Color;
import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;

@SuppressWarnings("unchecked")

public interface IF_Color<T> extends IF__Reflections{
	
	/**
	 * sets BackgroundColor 
	 * @param backcolor
	 * @return
	 */
	public default T _col_back(Color backcolor) {
		Method m =this.CheckMethod(this.getClass().getMethods(), "setBackground");

		if (m!=null) {
			try {
				m.invoke(this, backcolor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}
	
	/**
	 * set Background E2_ColorDark
	 * @return
	 */
	public default T _col_back_d() {
		return this._col_back(new E2_ColorDark());
	}
	
	/**
	 * set Background E2_ColorDDark
	 * @return
	 */
	public default T _col_back_dd() {
		return this._col_back(new E2_ColorDDark());
	}
	
	/**
	 * set Background E2_ColorDDDark
	 * @return
	 */
	public default T _col_back_ddd() {
		return this._col_back(new E2_ColorDDDark());
	}


	
	/**
	 * set Background E2_ColorDark
	 * @return
	 */
	public default T _col_back_l() {
		return this._col_back(new E2_ColorLight());
	}
	
	/**
	 * set Background E2_ColorDDark
	 * @return
	 */
	public default T _col_back_ll() {
		return this._col_back(new E2_ColorLLight());
	}
	
	/**
	 * set Background E2_ColorDDDark
	 * @return
	 */
	public default T _col_back_lll() {
		return this._col_back(new E2_ColorLLLight());
	}

	
	
	
	/**
	 * set Background E2_ColorBase
	 * @return
	 */
	public default T _col_back_base() {
		return this._col_back(new E2_ColorBase());
	}
	

	/**
	 * set Background E2_ColorHelpBackground
	 * @return
	 */
	public default T _col_back_help() {
		return this._col_back(new E2_ColorHelpBackground());
	}

	
	/**
	 * set Background E2_ColorAlarm
	 * @return
	 */
	public default T _col_back_alarm() {
		return this._col_back(new E2_ColorAlarm());
	}

	/**
	 * set Background Color.WHITE
	 * @return
	 */
	public default T _col_back_white() {
		return this._col_back(Color.WHITE);
	}

	/**
	 * set Background Color.GREEN
	 * @return
	 */
	public default T _col_back_green() {
		return this._col_back(Color.GREEN);
	}

	/**
	 * set Background Color.ORANGE
	 * @return
	 */
	public default T _col_back_orange() {
		return this._col_back(Color.ORANGE);
	}

	/**
	 * set Background Color.YELLOW
	 * @return
	 */
	public default T _col_back_yellow() {
		return this._col_back(Color.YELLOW);
	}

	/**
	 * set Background Color.BLUE
	 * @return
	 */
	public default T _col_back_blue() {
		return this._col_back(Color.BLUE);
	}
		
	/**
	 * set Background Color.RED
	 * @return
	 */
	public default T _col_back_red() {
		return this._col_back(Color.RED);
	}
	
	
	/**
	 * sets ForegroundColor 
	 * @param forecolor
	 * @return
	 */
	public default T _col_fore(Color forecolor) {

		Method m =this.CheckMethod(this.getClass().getMethods(), "setForeground");

		if (m!=null) {
			try {
				m.invoke(this, forecolor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return (T)this;
	}

	
	/**
	 * set ForegroundColor Color.DARKGRAY
	 * @return
	 */
	public default T _col_fore_dgrey() {
		return this._col_fore(Color.DARKGRAY);
	}
	
	/**
	 * set ForegroundColor Color.LIGHTGRAY
	 * @return
	 */
	public default T _col_fore_lgrey() {
		return this._col_fore(Color.LIGHTGRAY);
	}
	
	/**
	 * set ForegroundColor Color.BLACK
	 * @return
	 */
	public default T _col_fore_black() {
		return this._col_fore(Color.BLACK);
	}
	
	
	/**
	 * set Background E2_ColorDark
	 * @return
	 */
	public default T _colD() {
		return this._col_back(new E2_ColorDark());
	}
	
	/**
	 * set Background E2_ColorDDark
	 * @return
	 */
	public default T _colDD() {
		return this._col_back(new E2_ColorDDark());
	}
	
	/**
	 * set Background E2_ColorDDDark
	 * @return
	 */
	public default T _colDDD() {
		return this._col_back(new E2_ColorDDDark());
	}

	/**
	 * set Background E2_ColorLight
	 * @return
	 */
	public default T _colL() {
		return this._col_back(new E2_ColorLight());
	}
	
	/**
	 * set Background E2_ColorLLight
	 * @return
	 */
	public default T _colLL() {
		return this._col_back(new E2_ColorLLight());
	}
	
	/**
	 * set Background E2_ColorLLLight
	 * @return
	 */
	public default T _colLLL() {
		return this._col_back(new E2_ColorLLLight());
	}

	
}
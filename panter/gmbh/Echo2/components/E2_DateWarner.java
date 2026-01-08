/**
 * panter.gmbh.Echo2.components
 * @author martin
 * @date 01.03.2021
 * 
 */
package panter.gmbh.Echo2.components;

import java.time.DayOfWeek;
import java.util.Date;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 01.03.2021
 *
 */
public class E2_DateWarner extends E2_Grid {
	
	
	public static interface ToolTipProvider  { public abstract String genToolTips(E2_DateWarner warner); }
	
	public static enum StatusAmpel {
		RED
		,YELLOW
		,GREEN
		,DARKRED
	}
	

	//tage-grenzen, wann die farbe wechsels
	//bsp: gelbeGrenze=10, gruene grenze = 5
	
	// wenn das plandatum noch 11 tage entfernt ist, dann gruen
	// ab 10-Tage bis 5 tage, dann gelb,
	// wenn < 5 tage oder schon ueberschritten, dann rot
	
	
	private Long gelbeGrenze = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_GELB.getCheckedValue());
	
	private Long roteGrenze = Long.parseLong(ENUM_MANDANT_CONFIG.TAGE_WARNGRENZE_FAKTFRIST_ROT.getCheckedValue());
	private int width = 30;
	private int heigth = 30;
	private Color  colorOK = Color.GREEN;
	private Color  colorGelbeGrenze = new E2_ColorHelpBackground();
	private Color  colorRoteGrenze = new E2_ColorAlarm();
	private Font   font = new E2_FontPlain(2);
	
	private Color  fontColor = Color.BLACK;
	
	private boolean okClosed = false;
	
	private boolean forceRed = false;
	private boolean forceYellow = false;
	private boolean forceGreen = false;
	
	
	
	private StatusAmpel statusAmpelAktuell = null;
	private Long 		dateDiff = null;

	private RB_lab      label = null;
	
	private boolean     isRendered = false;
	
	public E2_DateWarner() {
		super();
		this._setSize(40)._a("@empty");
	}
	
	
	public E2_DateWarner _calc(Date dateToCheck) {
		dateDiff = MyDate.get_DayDifferenceDate2MINUSDate1(new MyDate(new Date()), new MyDate(dateToCheck));

		return this;
	}
	
	
	public E2_DateWarner _calc(Date dateToCheck, boolean ignoreSaturdaySunday) {
		if (!ignoreSaturdaySunday) {
			dateDiff = MyDate.get_DayDifferenceDate2MINUSDate1(new MyDate(new Date()), new MyDate(dateToCheck));
		} else {
			dateDiff = myDateHelper.getDifferenceBitweenDates(new Date(), dateToCheck, new VEK<DayOfWeek>()
												._a(DayOfWeek.MONDAY)
												._a(DayOfWeek.TUESDAY)
												._a(DayOfWeek.WEDNESDAY)
												._a(DayOfWeek.THURSDAY)
												._a(DayOfWeek.FRIDAY));	
		}
		
		
		return this;
	}
	
	
	
	
	
	public E2_DateWarner _renderWith( String text, ToolTipProvider toolTipProvider) {
		this._clear()._setSize(width)._setRowHight(0,heigth);
		this.label=new RB_lab()._t(text)._f(this.font)._col_fore(fontColor);
		
		RB_gld layout = new RB_gld()._ins(1)._center_mid();
		
		if (dateDiff>gelbeGrenze || okClosed || forceGreen) {
			layout._col(colorOK);
			statusAmpelAktuell=StatusAmpel.GREEN;
		} else if ((dateDiff<=gelbeGrenze && dateDiff>roteGrenze) || forceYellow) {
			layout._col(colorGelbeGrenze);
			statusAmpelAktuell=StatusAmpel.YELLOW;
		} else if (dateDiff<0 || forceRed){
			//ueberschritten
			layout._col(colorRoteGrenze);
			statusAmpelAktuell=StatusAmpel.DARKRED;
		} else {
			//wenn unterhalb der roten grenze  dann rot
			layout._col(colorRoteGrenze);
			statusAmpelAktuell=StatusAmpel.RED;
		}
		
		if (toolTipProvider!=null) {
			this.label.setToolTipText(toolTipProvider.genToolTips(this));  
		}
		
		this._a(label,layout);
		
		this.isRendered = true;
		
		return this;
	}
	
	
	
	/**
	 * @return the gelbeGrenze
	 */
	public Long getGelbeGrenze() {
		return gelbeGrenze;
	}

	/**
	 * @param gelbeGrenze the gelbeGrenze to set
	 */
	public E2_DateWarner _setGelbeGrenze(Long gelbeGrenze) {
		this.gelbeGrenze = gelbeGrenze;
		return this;
	}

	/**
	 * @return the roteGrenze
	 */
	public Long getRoteGrenze() {
		return roteGrenze;
	}

	/**
	 * @param roteGrenze the roteGrenze to set
	 */
	public E2_DateWarner _setRoteGrenze(Long roteGrenze) {
		this.roteGrenze = roteGrenze;
		return this;
	}

	/**
	 * @return the width
	 */
	public int getWarnWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public E2_DateWarner _setWarnWidth(int width) {
		this.width = width;
		return this;
	}

	/**
	 * @return the heigth
	 */
	public int getWarnHeigth() {
		return heigth;
	}

	/**
	 * @param heigth the heigth to set
	 */
	public E2_DateWarner _setWarnHeigth(int heigth) {
		this.heigth = heigth;
		return this;
	}

	/**
	 * @return the colorOK
	 */
	public Color getColorOK() {
		return colorOK;
	}

	/**
	 * @param colorOK the colorOK to set
	 */
	public E2_DateWarner _setColorOK(Color colorOK) {
		this.colorOK = colorOK;
		return this;
	}

	/**
	 * @return the colorGelbeGrenze
	 */
	public Color getColorGelbeGrenze() {
		return colorGelbeGrenze;
	}

	/**
	 * @param colorGelbeGrenze the colorGelbeGrenze to set
	 */
	public E2_DateWarner _setColorGelbeGrenze(Color colorGelbeGrenze) {
		this.colorGelbeGrenze = colorGelbeGrenze;
		return this;
	}

	/**
	 * @return the colorRoteGrenze
	 */
	public Color getColorRoteGrenze() {
		return colorRoteGrenze;
	}

	/**
	 * @param colorRoteGrenze the colorRoteGrenze to set
	 */
	public E2_DateWarner _setColorRoteGrenze(Color colorRoteGrenze) {
		this.colorRoteGrenze = colorRoteGrenze;
		return this;
	}

	/**
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * @param font the font to set
	 */
	public E2_DateWarner _setFont(Font font) {
		this.font = font;
		return this;
	}

	/**
	 * @return the okClosed
	 */
	public boolean isOkClosed() {
		return okClosed;
	}

	/**
	 * @param okClosed the okClosed to set
	 */
	public E2_DateWarner _setOkClosed(boolean okClosed) {
		this.okClosed = okClosed;
		return this;
	}

	/**
	 * @return the statusAmpelAktuell
	 */
	public StatusAmpel getStatusAmpelAktuell() {
		return statusAmpelAktuell;
	}

	/**
	 * @return the dateDiff
	 */
	public Long getDateDiff() {
		return dateDiff;
	}

	/**
	 * @return the label
	 */
	public RB_lab getLabel() {
		return label;
	}


	/**
	 * @return the forceRed
	 */
	public boolean isForceRed() {
		return forceRed;
	}


	/**
	 * @param forceRed the forceRed to set
	 */
	public E2_DateWarner _setForceRed(boolean forceRed) {
		this.forceRed = forceRed;
		return this;
	}


	/**
	 * @return the forceYellow
	 */
	public boolean isForceYellow() {
		return forceYellow;
	}


	/**
	 * @param forceYellow the forceYellow to set
	 */
	public E2_DateWarner _setForceYellow(boolean forceYellow) {
		this.forceYellow = forceYellow;
		return this;
	}


	/**
	 * @return the forceGreen
	 */
	public boolean isForceGreen() {
		return forceGreen;
	}


	/**
	 * @param forceGreen the forceGreen to set
	 */
	public E2_DateWarner _setForceGreen(boolean forceGreen) {
		this.forceGreen = forceGreen;
		return this;
	}


	/**
	 * @return the isRendered
	 */
	public boolean isRendered() {
		return isRendered;
	}


	/**
	 * @return the fontColor
	 */
	public Color getFontColor() {
		return fontColor;
	}


	/**
	 * @param fontColor the fontColor to set
	 */
	public E2_DateWarner _setFontColor(Color fontColor) {
		this.fontColor = fontColor;
		return this;
	}

	
	
	
	
}

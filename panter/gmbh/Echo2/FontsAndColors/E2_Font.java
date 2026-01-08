package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.indep.bibALL;

public class E2_Font extends Font implements IF_PdFonts
{
    public E2_Font(int FontAttribute,int iDiffSizeToStandard)
    {
        super(	Font.ARIAL, 
        		FontAttribute, 
        		new Extent(bibALL.get_FONT_SIZE()+iDiffSizeToStandard,Extent.PT));
    }
    
    
    /**
     * 2016-08-16
     * @param FontAttribute
     */
    public E2_Font(int FontAttribute)   {
        super(	Font.ARIAL, FontAttribute, new Extent(12,Extent.PT));
    }

    

    /**
     * 
     * 2016-08-16
     * @param type
     * @param FontAttribute
     * @param size
     */
    public E2_Font(Typeface type, int FontAttribute, Extent size)   {
        super(	type, FontAttribute, size);
    }

 
    

	/**
	 * 
	 * @param iSizeAdd  (increase or decrease fontsize)
	 * @return
	 */
	public E2_Font _fsa(int iSizeAdd) {
		Font f = this;
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		return new E2_Font(f.getTypeface(),style, new Extent(f.getSize().getValue()+iSizeAdd,Extent.PT));
	}


	/**
	 * set fontsize
	 * @param iSize
	 * @return
	 */
	public E2_Font _fs(int iSize) {
		Font f = this;
		int style = 0;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}

		return new E2_Font(f.getTypeface(),style, new Extent(iSize,Extent.PT));
	}


	/**
	 * set font bold
	 * @return
	 */
	public E2_Font _b() {
		Font f = this;
		int style = Font.BOLD;   //entspricht plain
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		return new E2_Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
	}

	/**
	 * set font italic
	 * @return
	 */
	public E2_Font _i() {
		Font f = this;
		int style = Font.ITALIC;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}
		if (f.isUnderline()) {style+=Font.UNDERLINE;}
		return new E2_Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
	}


	/**
	 * set font bold-italic
	 * @return
	 */
	public E2_Font _bi() {
		return this._b()._i();
	}
	
	
	/**
	 * set font underlined
	 * @return
	 */
	public E2_Font _ul() {
		Font f = this;
		int style = Font.UNDERLINE;   //entspricht plain
		if (f.isBold()) {style+=Font.BOLD;}
		if (f.isItalic()) {style+=Font.ITALIC;}
		if (f.isLineThrough()) {style+=Font.LINE_THROUGH;}
		if (f.isOverline()) {style+=Font.OVERLINE;}

		return new E2_Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
	}

    
    
}

/**
 * 
 */
package panter.gmbh.Echo2.RB.TOOLS;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;

/**
 * @author martin
 *
 */
public class RB_labelStyle extends MutableStyle {
	
	public RB_labelStyle() {
		super();
		this._font(new E2_FontPlain());   //standardFont
	}

	public RB_labelStyle _font(Font f) {
		this.setProperty(Label.PROPERTY_FONT, f);
		return this;
	}

	public RB_labelStyle _foreCol(Color c) {
		this.setProperty(Label.PROPERTY_FOREGROUND, c);
		return this;
	}

	
	public RB_labelStyle _plain() {
		int style = Font.PLAIN; 
		if (this.getProperty(Label.PROPERTY_FONT)!=null && this.getProperty(Label.PROPERTY_FONT) instanceof Font) {
			Font f= (Font) this.getProperty(Label.PROPERTY_FONT);
			Font f2= new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
			this._font(f2);
		}
		return this;
	}

	
	public RB_labelStyle _italic() {
		int style = Font.ITALIC; 
		if (this.getProperty(Label.PROPERTY_FONT)!=null && this.getProperty(Label.PROPERTY_FONT) instanceof Font) {
			Font f= (Font) this.getProperty(Label.PROPERTY_FONT);
			if (f.isBold()) {		style+=Font.BOLD;}
			//if (f.isItalic()) 		{style+=Font.ITALIC;}
			if (f.isLineThrough()) 	{style+=Font.LINE_THROUGH;}
			if (f.isOverline()) 	{style+=Font.OVERLINE;}
			if (f.isUnderline()) 	{style+=Font.UNDERLINE;}
			
			Font f2= new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
			this._font(f2);
		}
		return this;
	}

	public RB_labelStyle _bold() {
		int style = Font.BOLD; 
		if (this.getProperty(Label.PROPERTY_FONT)!=null && this.getProperty(Label.PROPERTY_FONT) instanceof Font) {
			Font f= (Font) this.getProperty(Label.PROPERTY_FONT);
			//if (f.isBold()) {		style+=Font.BOLD;}
			if (f.isItalic()) 		{style+=Font.ITALIC;}
			if (f.isLineThrough()) 	{style+=Font.LINE_THROUGH;}
			if (f.isOverline()) 	{style+=Font.OVERLINE;}
			if (f.isUnderline()) 	{style+=Font.UNDERLINE;}
			
			Font f2= new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
			this._font(f2);
		}
		return this;
	}
	
	public RB_labelStyle _lineThrough() {
		int style = Font.LINE_THROUGH; 
		if (this.getProperty(Label.PROPERTY_FONT)!=null && this.getProperty(Label.PROPERTY_FONT) instanceof Font) {
			Font f= (Font) this.getProperty(Label.PROPERTY_FONT);
			if (f.isBold()) {		style+=Font.BOLD;}
			if (f.isItalic()) 		{style+=Font.ITALIC;}
			//if (f.isLineThrough()) 	{style+=Font.LINE_THROUGH;}
			if (f.isOverline()) 	{style+=Font.OVERLINE;}
			if (f.isUnderline()) 	{style+=Font.UNDERLINE;}
			
			Font f2= new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
			this._font(f2);
		}
		return this;
	}
	
	public RB_labelStyle _overLine() {
		int style = Font.OVERLINE; 
		if (this.getProperty(Label.PROPERTY_FONT)!=null && this.getProperty(Label.PROPERTY_FONT) instanceof Font) {
			Font f= (Font) this.getProperty(Label.PROPERTY_FONT);
			if (f.isBold()) {		style+=Font.BOLD;}
			if (f.isItalic()) 		{style+=Font.ITALIC;}
			if (f.isLineThrough()) 	{style+=Font.LINE_THROUGH;}
			//if (f.isOverline()) 	{style+=Font.OVERLINE;}
			if (f.isUnderline()) 	{style+=Font.UNDERLINE;}
			
			Font f2= new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
			this._font(f2);
		}
		return this;
	}
	
	public RB_labelStyle _underLine() {
		int style = Font.UNDERLINE; 
		if (this.getProperty(Label.PROPERTY_FONT)!=null && this.getProperty(Label.PROPERTY_FONT) instanceof Font) {
			Font f= (Font) this.getProperty(Label.PROPERTY_FONT);
			if (f.isBold()) {		style+=Font.BOLD;}
			if (f.isItalic()) 		{style+=Font.ITALIC;}
			if (f.isLineThrough()) 	{style+=Font.LINE_THROUGH;}
			if (f.isOverline()) 	{style+=Font.OVERLINE;}
			//if (f.isUnderline()) 	{style+=Font.UNDERLINE;}
			
			Font f2= new Font(f.getTypeface(),style, new Extent(f.getSize().getValue(),Extent.PT));
			this._font(f2);
		}
		return this;
	}
	

	
}

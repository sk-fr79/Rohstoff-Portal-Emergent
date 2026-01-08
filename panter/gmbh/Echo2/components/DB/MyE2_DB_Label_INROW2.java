package panter.gmbh.Echo2.components.DB;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.bibFONT;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * neue klasse mit ueberschriebenem delete-anzeige-mechanismus,
 * ist im standard lineWrapped
 * @author martin
 *
 */
public class MyE2_DB_Label_INROW2 extends MyE2_DB_Label_INROW {

	private Font 	f_font = null;
	private Integer f_colWidth = null;
	private Boolean f_lineWrap = true;
	
	public MyE2_DB_Label_INROW2(SQLField osqlField)	throws myException {
		super(osqlField);
		this.init(null,null,true);
	}

	
	
	public void init(Font p_font, Integer p_colWidth, Boolean p_lineWrap) {
		this.f_font = p_font;
		this.f_colWidth = p_colWidth;
		this.f_lineWrap = p_lineWrap;
		
		if (this.f_font!=null) {
			this.setFont(this.f_font);
		}
		
		if (this.f_colWidth!=null) {
			this.EXT().set_oColExtent(new Extent(this.f_colWidth));
		}
		if (this.f_lineWrap!=null) {
			this.setLineWrap(this.f_lineWrap);
		}
	}
	
	
	@Override
	public void make_Look_Deleted(boolean bIsDeleted)
	{
		bibFONT.change_fontToLineThrough(this, bIsDeleted);
		//kann bei der super - initialisierung zu laufzeitfehler fuehren, weil dort der ersatzbutton noch nicht initialisiert ist
		if (this.get_oErsatzButton() !=null) {
			bibFONT.change_fontToLineThrough(this.get_oErsatzButton(), bIsDeleted);
		}
	}

	
	@Override
	public void setFontActive(Font oFont)
	{
		Font oFont4Mark = oFont==null?new E2_FontPlain():oFont;
		
		this.get_oErsatzButton().setFont(bibFONT.equal_LineThrough_status(oFont4Mark, this.get_oErsatzButton()));
	}

	

	public Object get_Copy(Object ob) throws myExceptionCopy {
		MyE2_DB_Label_INROW2 oLabCopy = null;
		try
		{
			oLabCopy = new MyE2_DB_Label_INROW2(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Label:get_Copy:copy-error!");
		}
		
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		
		if (this.getIcon() != null)
			oLabCopy.setIcon(this.getIcon());
		
		if (this.get_oTextObject() != null)
			oLabCopy.set_Text(this.get_oTextObject());
		
		oLabCopy.set_cErsatzFuerLeeranzeige(this.get_cErsatzFuerLeeranzeige());
		
		oLabCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oLabCopy));
		
		if (this.getStyle() != null)
			oLabCopy.setStyle(this.getStyle());

		oLabCopy.setLayoutData(this.getLayoutData());

		oLabCopy.init(this.f_font,this.f_colWidth,this.f_lineWrap);
		
		return oLabCopy;

		
	}

	
	
	
}

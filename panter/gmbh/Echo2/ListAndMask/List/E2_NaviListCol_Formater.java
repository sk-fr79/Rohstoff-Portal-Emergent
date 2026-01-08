package panter.gmbh.Echo2.ListAndMask.List;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.components.MyE2IF__Component;

/*
 * 2013-11-07: hilfsklasse zur formatierung einer Spalte in eine Navilist, Erzeugt zwei Layout-Datas fuer Titel und Inhalt
 */
public class E2_NaviListCol_Formater {
	

	private GridLayoutData oLayoutTitle = null;
	private GridLayoutData oLayoutList = null;
	private Extent         oColExtent = null;
	private boolean       bTitelLineWrap = true;
	
	




	public E2_NaviListCol_Formater(		Insets 		I_Head, 
										Insets 		I_Body, 
										Alignment 	AL_Head, 
										Alignment 	AL_Body, 
										Color 		COL_Head, 
										Color 		COL_Body,
										Extent      COL_EXT,
										boolean    b_TitelLineWrap) {
		super();
		
		oLayoutTitle = new GridLayoutData();
		oLayoutTitle.setAlignment(AL_Head);
		oLayoutTitle.setBackground(COL_Head);
		oLayoutTitle.setInsets(I_Head);

		oLayoutList = new GridLayoutData();
		oLayoutList.setAlignment(AL_Body);
		oLayoutList.setBackground(COL_Body);
		oLayoutList.setInsets(I_Body);
		
		this.oColExtent  = COL_EXT;
		this.bTitelLineWrap = b_TitelLineWrap;
		
	}

	
	public void Format(MyE2IF__Component  oComp) {
		oComp.EXT().set_oLayout_ListElement(this.oLayoutList);
		oComp.EXT().set_oLayout_ListTitelElement(this.oLayoutTitle);
		oComp.EXT().set_bLineWrapListHeader(this.bTitelLineWrap);
		
		if (this.oColExtent!=null) {
			oComp.EXT().set_oColExtent(this.oColExtent);
		}
	}
	
	
	public GridLayoutData get_oLayoutTitle() {
		return oLayoutTitle;
	}


	public GridLayoutData get_oLayoutList() {
		return oLayoutList;
	}

	public Extent get_oColExtent() {
		return oColExtent;
	}

	
	public boolean get_bTitelLineWrap() {
		return bTitelLineWrap;
	}

	
	public static E2_NaviListCol_Formater STANDARD_LEFT(int iColWidth) {
		return new E2_NaviListCol_Formater(	E2_INSETS.I(2,1,2,1),
												E2_INSETS.I(2,1,2,1),
												new Alignment(Alignment.LEFT, Alignment.TOP), 
												new Alignment(Alignment.LEFT, Alignment.CENTER),
												new E2_ColorDark(),new E2_ColorBase(),
												new Extent(iColWidth),
												true);	
		
	}
	

	public static E2_NaviListCol_Formater STANDARD_CENTER(int iColWidth) {
		return new E2_NaviListCol_Formater(	E2_INSETS.I(2,1,2,1),
												E2_INSETS.I(2,1,2,1),
												new Alignment(Alignment.CENTER, Alignment.TOP), 
												new Alignment(Alignment.CENTER, Alignment.CENTER),
												new E2_ColorDark(),new E2_ColorBase(),
												new Extent(iColWidth),
												true);	
	}

	
	
	public static E2_NaviListCol_Formater STANDARD_RIGHT(int iColWidth) {
		return new E2_NaviListCol_Formater(	E2_INSETS.I(2,1,2,1),
												E2_INSETS.I(2,1,2,1),
												new Alignment(Alignment.RIGHT, Alignment.TOP), 
												new Alignment(Alignment.RIGHT, Alignment.CENTER),
												new E2_ColorDark(),new E2_ColorBase(),
												new Extent(iColWidth),
												true);	
	}
	
	
}

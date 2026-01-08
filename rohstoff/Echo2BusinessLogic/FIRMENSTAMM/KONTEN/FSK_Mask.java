/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.staticStyles.Style_Grid_Normal;
import panter.gmbh.indep.exceptions.myException;

public class FSK_Mask extends MyE2_Column implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public FSK_Mask(	FSK_MASK_ComponentMAP 		oE2_MAP_KONTO) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		MyE2_Grid		oGridPage1	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_TabbedPane oTabbed = new MyE2_TabbedPane(null);
		oTabbed.set_bAutoHeight(true);

		
		oTabbed.add_Tabb(new MyE2_String("Kontenangaben"),oGridPage1);

		this.add(oTabbed);

		oGridPage1.setColumnWidth(0,new Extent(25,Extent.PERCENT));

		E2_MaskFiller oFiller = new E2_MaskFiller(oE2_MAP_KONTO,null,null);
		
		oGridPage1.setSize(2);
		oFiller.add_Line(oGridPage1,"#ID-Konto",1,"ID_KONTO|# ",1);
		oFiller.add_Line(oGridPage1,"#IBAN-NR",1,"IBAN_NR|# ",1);
		oFiller.add_Line(oGridPage1,"#Konto-Nr",1,"KONTONUMMER|# ",1);
//		oFiller.add_Line(oGridPage1,"#BIC-NR",1,"BIC_NR|# ",1);
		oFiller.add_Line(oGridPage1,"#Bank",1,"ID_BANKENSTAMM|# ",1);
		oFiller.add_Line(oGridPage1,"# ",1,FSK__CONST.FSK_SONDERFELDER.BANKINFO.name()+"|# ",1);
//		oFiller.add_Line(oGridPage1,"#Konto-Typ",1,"KONTOTYP|# ",1);
//		oFiller.add_Line(oGridPage1,"#Kreditkarte-Nummer",1,"KREDITKARTENNUMMER|# ",1);
//		oFiller.add_Line(oGridPage1,"#Kreditkarte-Zusatz",1,"KREDITKARTENVV|# ",1);
//		oFiller.add_Line(oGridPage1,"#Ablauf-Monat",1,"KREDITKARTENABLAUFMM|# ",1);
//		oFiller.add_Line(oGridPage1,"#Ablauf-Jahr",1,"KREDITKARTENABLAUFYYYY|# ",1);
//		oFiller.add_Line(oGridPage1,"#Name auf KK",1,"KK_NAME|# ",1);
//		oFiller.add_Line(oGridPage1,"#Strasse auf KK",1,"KK_STRASSE|# ",1);
//		oFiller.add_Line(oGridPage1,"#PLZ auf KK",1,"KK_PLZ|# ",1);
//		oFiller.add_Line(oGridPage1,"#Ort auf KK",1,"KK_ORT|# ",1);
//		oFiller.add_Line(oGridPage1,"#Land auf KK",1,"KK_LAND|# ",1);
//		oFiller.add_Line(oGridPage1,"#Kreditkartentyp",1,"KREDITKARTENTYP|# ",1);
		
		this.vMaskGrids.add(oGridPage1);
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
}
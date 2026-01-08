/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

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
import panter.gmbh.basics4project.DB_ENUMS.MAT_SPEZ;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class FSMS_Mask extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public FSMS_Mask(	FSMS_MASK_ComponentMAP 		oE2_MAP_MAT_SPEZ) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		MyE2_Grid		oGridPage1	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_TabbedPane oTabbed = new MyE2_TabbedPane(null);
		oTabbed.set_bAutoHeight(true);

		
		oTabbed.add_Tabb(new MyE2_String("Materialspezifikation"),oGridPage1);

		this.add(oTabbed);

		oGridPage1.setColumnWidth(0,new Extent(25,Extent.PERCENT));

		E2_MaskFiller oFiller = new E2_MaskFiller(oE2_MAP_MAT_SPEZ,null,null);

		
		oGridPage1.setSize(2);
		oFiller.add_Line(oGridPage1,"#ID(Mat-Spez)",1,"ID_MAT_SPEZ",1);
		oFiller.add_Line(oGridPage1,"#Soll/Ist",1,_DB.MAT_SPEZ$SOLL_IST_STATUS,1);
		oGridPage1.add(new Separator(),2,new Insets(2));
		oFiller.add_Line(oGridPage1,"#Sorte",1,"ID_ARTIKEL",1);
		oFiller.add_Line(oGridPage1,"#Sortenbezeichnung",1,MAT_SPEZ.id_artikel_bez.fn(),1);
		oFiller.add_Line(oGridPage1,"#Lieferant",1,"IST_LIEFERANT",1);
		oFiller.add_Line(oGridPage1,"#Abnehmer",1,"IST_ABNEHMER",1);
		oFiller.add_Line(oGridPage1,"#Bezeichnung",1,"BEZEICHNUNG",1);
		oFiller.add_Line(oGridPage1,"#Bemerkungen",1,"BEMERKUNGEN",1);
		oFiller.add_Line(oGridPage1,"#Datum/Zeit",1,_DB.MAT_SPEZ$DATUM_ERFASSUNG+"|#|"+_DB.MAT_SPEZ$ZEIT_ERFASSUNG+"|# |"+FSMS_Const.FSMS_MASK_KEY_SETZE_DATUM_UHRZEIT,1);
		oGridPage1.add(new Separator(),2,new Insets(2));
		oGridPage1.add(oE2_MAP_MAT_SPEZ.get_Comp("DAUGHTER_ANTEILE"),2,new Insets(2));
		
		
		this.vMaskGrids.add(oGridPage1);
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
}
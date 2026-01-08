package rohstoff.Echo2BusinessLogic.BAM_IMPORT;


import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.MAIL_INBOX.MAIL_INBOX_Const;

public class BAM_IMPORT_MASK extends MyE2_Grid 
{

	
	public BAM_IMPORT_MASK(BAM_IMPORT_MASK_ComponentMAP oMap) throws myException
	{
		super(4, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String("Import-ID:"), 1, "ID_BAM_IMPORT|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Übertragene Belegnummer:"), 1, "BELEGNUMMER|#  |",3);
		oFiller.add_Line(this, new MyE2_String("Angelegt im Device am: "), 1, "BAM_ANGELEGT_AM|# Gesendet am: |BAM_GESENDET_AM|# |",3);
		oFiller.add_Separator ( this, E2_INSETS.I_0_5_0_5 );
		oFiller.add_Line(this, new MyE2_String("Zuordnung der Abzugseinträge zu Wiegekarte und Fuhre durchführen: "), 4);
		oFiller.add_Line(this, new MyE2_String(""),4);
		oFiller.add_Line(this, new MyE2_String("Automatische Zuordnung:"),1,"|"+ BAM_IMPORT_Const.BUTTON_AUTO_CONNECT +"|",3);
		oFiller.add_Line(this, new MyE2_String(""),4);
		oFiller.add_Line(this, new MyE2_String("Zugeordnete Wiegekarte:"), 1, "ID_WIEGEKARTE|" + BAM_IMPORT_Const.BUTTON_CONNECT_WIEGEKARTE+"|"+ BAM_IMPORT_Const.BUTTON_DISCONNECT_WIEGEKARTE+"|" ,3);
		oFiller.add_Line(this, new MyE2_String(""),4);
		oFiller.add_Line(this, new MyE2_String("Zugeordnete Fuhre:"), 1, BAM_IMPORT_Const.SEARCHFIELD_FUHRE + "|#  |",3);
//		oFiller.add_Line(this, new MyE2_String("ID Fuhre:"), 1, "ID_VPOS_TPA_FUHRE|#  |"+ BAM_IMPORT_Const.BUTTON_DISCONNECT_FUHRE+"|",3);
//		oFiller.add_Line(this, new MyE2_String("ID Fuhren-Ort:"), 1, "ID_VPOS_TPA_FUHRE_ORT|#  |",3);
		oFiller.add_Separator(this, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(this, new MyE2_String("Abgeschlossen:"), 1, "ABGESCHLOSSEN|#  |",3);


	}

	
	
}

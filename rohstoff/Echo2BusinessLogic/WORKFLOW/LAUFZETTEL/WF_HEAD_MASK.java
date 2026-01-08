package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;



public class WF_HEAD_MASK extends MyE2_Grid 
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2661167717482778360L;

	public WF_HEAD_MASK(WF_HEAD_MASK_ComponentMAP oMap) throws myException
	{
		super(8, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
		Alignment align_lc = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		//hier kommen die Felder	
		oFiller.add_Line(this, new MyE2_String(""),1,"PRIVAT|#Laufzettel ist Privat", 5, new MyE2_String("Laufzettel"),1, "ID_LAUFZETTEL",1,align_lc);
		this.add(new Separator(),8,E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Laufzettel von:"), 1, "ID_USER_BESITZER",1, new MyE2_String("am:") ,1, "ANGELEGT_AM",3,new MyE2_String("Letzte Änderung:"),1, WF_HEAD_CONST.HASH_SONDERFELD_GEANDERT_VON ,1,align_lc);
		oFiller.add_Line(this, new MyE2_String("Supervisor:"), 1, "ID_USER_SUPERVISOR",6, WF_HEAD_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG,1,align_lc);
		this.add(new Separator(), 8, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Beschreibung:"), 1, "TEXT",7);
		this.add(new Separator(), 8, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Status: "),1, 
				"ID_LAUFZETTEL_STATUS",1, 
				"|# |#" + new MyE2_String("Abgeschlossen von:") +	"|ID_USER_ABGESCHLOSSEN_VON|# " + new MyE2_String("am:").toString() + "|ABGESCHLOSSEN_AM" ,6,align_lc);



	}

	
	
}

package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class WF_MASK extends MyE2_Grid 
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -263408720945670246L;

	
	public WF_MASK(WF_MASK_ComponentMAP oMap) throws myException
	{
		super(8, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		
				
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);
		
		Alignment align_lc = new Alignment(Alignment.LEFT, Alignment.CENTER);
		
		this.add(new Separator(),8,E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Laufzettel:"),1,WF_CONST.HASH_LAUFZETTEL_BESCHREIBUNG,7);
		
		// Supervisor
		oFiller.add_Line(this, new MyE2_String("Supervisor:"), 1, WF_CONST.HASH_LAUFZETTEL_SUPERVISOR,7);
		
		
		this.add(new Separator(),8,E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String(""),1,"PRIVAT|#Eintrag ist Privat",2, new MyE2_String("LZ/Eintrag"),1, "ID_LAUFZETTEL|#/|ID_LAUFZETTEL_EINTRAG",1,align_lc);
		this.add(new Separator(),8,E2_INSETS.I_2_2_2_2);

		oFiller.add_Line(this, 	new MyE2_String("Eintrag von:"), 1, 
								"ID_USER_BESITZER",1, 
								(new MyE2_String("#am: ")).toString() + "|ANGELEGT_AM"  ,1, 
								new MyE2_String("Letzte Änderung:"),1,  WF_CONST.HASH_SONDERFELD_GEANDERT_VON + "|#/|" + WF_CONST.HASH_SONDERFELD_LETZTE_AENDERUNG ,1 ,align_lc );
		
		
		oFiller.add_Line(this, new MyE2_String("Zu erledigen von:"), 1, WF_CONST.HASH_LAUFZETTEL_BENUTZER_AUSWAHL_CONTAINER+"|"+WF_CONST.HASH_BUTTON_SET_WF_BEARBEITER_SELF,3,align_lc);
		oFiller.add_Line(this,	new MyE2_String("Zu erledigen bis:"),1,"FAELLIG_AM|SEND_NACHRICHT|#",2,"#Aufgabenerneuerung in",1,"KADENZ_NACH_ABSCHLUSS|#Tagen|",1,align_lc);
//		oFiller.add_Line(this,	new MyE2_String(""),5, WF_CONST.HASH_CHECKBOX_KADENZ_NACH_ABSCHLUSS,1,align_lc);

		oFiller.add_Line(this, new MyE2_String(""),1,WF_CONST.HASH_BUTTON_CREATE_REMINDER+"|#",1,"|#",2,LAUFZETTEL_EINTRAG.kadenz_nach_faelligkeit.fn() + "|#|"+ WF_CONST.HASH_CHECKBOX_KADENZ_NACH_ABSCHLUSS,1,align_lc);
		oFiller.add_Line(this, new MyE2_String(""),1,WF_CONST.HASH_BUTTON_CREATE_MESSAGE_TO_GROUP,1,"|#",6);
		
		
//		oFiller.add_Line(this, new MyE2_String(""),1,WF_CONST.HASH_BUTTON_CREATE_REMINDER+"|#",1,"|#",6);
//		oFiller.add_Line(this, new MyE2_String(""),1,WF_CONST.HASH_BUTTON_CREATE_MESSAGE_TO_GROUP,1,"|#",6);
		

		
		this.add(new Separator(),8,E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Priorität:"), 1, "ID_LAUFZETTEL_PRIO",7,align_lc );
		oFiller.add_Line(this, new MyE2_String("Aufgabe:"), 1, "AUFGABE",7);
		
		oFiller.add_Line(this, new MyE2_String(""),1,WF_CONST.HASH_LIST_CONNECTOR,7);
		
		oFiller.add_Line(this, new MyE2_String("Tätigkeitsbericht:"), 1, "BERICHT",7);
		this.add(new Separator(),8,E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Status: "),1, 
								"ID_LAUFZETTEL_STATUS",1, 
								"|# |#" + new MyE2_String("Abgeschlossen von:") +	"|ID_USER_ABGESCHLOSSEN_VON|# " + new MyE2_String("am:").toString() + "|ABGESCHLOSSEN_AM" ,6,align_lc);
		
		
	}

	
}

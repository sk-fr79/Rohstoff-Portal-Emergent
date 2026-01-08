package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MODUL_LINK_Object_Factory {

	
	
	public MODUL_LINK_Button_Base getLinkButton (
			RECORD_MODUL_CONNECT rec, 
			Vector<E2_BasicModuleContainer> ContainerToClose) throws myException
	{
		 return getLinkButton(rec,ContainerToClose,true,true);
	}
	
	
	public MODUL_LINK_Button_Base getLinkButton (
			RECORD_MODUL_CONNECT rec, 
			Vector<E2_BasicModuleContainer> ContainerToClose,
			boolean bShowTextInButton, 
			boolean bShowLabel) throws myException{
		
		MODUL_LINK_Button_Base oButton = null;
		
		int id = rec.get_ID_TYP_lValue(new Long(0)).intValue();
		
		try {
		
			switch (id) {
			case 1:	// LINK_to MODUL (LISTE)
				if (rec.get_ZIEL_cUF().equalsIgnoreCase(E2_MODULNAMES.NAME_MODUL_MASCHINENSTAMM_LISTE)){
					oButton = new MODUL_LINK_Button_Goto_Modul_Maschinenaufgabe(rec, ContainerToClose,bShowTextInButton,bShowLabel);
				}else if (
						rec.get_ZIEL_cUF().equalsIgnoreCase(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE)){
					oButton = new MODUL_LINK_Button_Goto_Modul_Laufzettel(rec, ContainerToClose,bShowTextInButton,bShowLabel);
				}else{
					// default-Button
					oButton = new MODUL_LINK_Button_Goto_Modul(rec,ContainerToClose,bShowTextInButton, bShowLabel);
				}
				break;
				
			case 2: // LINK to MASK	
				// Sprung in die Firmenmaske und dort in die Zusaztinfos
				if (rec.get_ZIEL_cUF().equalsIgnoreCase(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK_INFOLISTE)){
					oButton = new MODUL_LINK_Button_Goto_Mask_Adressen_Tab_Info(rec, ContainerToClose,bShowTextInButton,bShowLabel);
					// Maskenanzeige des Firmenstammes + generische Tab-Anzeige	
				} else if (rec.get_ZIEL_cUF().equalsIgnoreCase(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK)){
					oButton = new MODUL_LINK_Button_Goto_Mask_Adressen_Tab(rec, ContainerToClose,bShowTextInButton,bShowLabel);
					// Maskenanzeige der Fuhre	
				} else if (rec.get_ZIEL_cUF().equalsIgnoreCase(E2_MODULNAMES.NAME_MODUL_FUHRENMASKE)){
					oButton = new MODUL_LINK_Button_Goto_Mask_FUHRE(rec, ContainerToClose,bShowTextInButton,bShowLabel);
				}else {
					// AKTUELLE keine generische Maskenanzeige 
					bibMSG.add_MESSAGE(new MyE2_Warning_Message("Keine generische Maskenanzeige implementiert!"));
				}
				
			default:
				break;
			}
			
		} catch (Exception e) {
			// Button konnte nicht erzeugt werden, vermutlich weil das verlinkte Objekt gelöscht wurde.
			// greift nicht bei allen Buttons....nur bei denen, bei denen ein lookup auf den Datensatz beim erzeugen des Buttons gemacht wird.
			oButton = new MODUL_LINK_Button_ERROR("Der verknüpfte Datensatz konnte nicht gefunden werden");
		}
			
	
		
		return oButton;
	}
	

	public MODUL_LINK_Button_Base getLinkButton (Vector<RECORD_MODUL_CONNECT> v_rec,Vector<E2_BasicModuleContainer> ContainerToClose,boolean bShowTextInButton, boolean bShowLabel) throws myException{
		MODUL_LINK_Button_Base oButton = null;
		
		if (v_rec.size() > 0){
			int id = v_rec.get(0).get_ID_TYP_lValue(new Long(0)).intValue();
		
			switch (id) {
			case 1:	// LINK_MODUL
				oButton = new MODUL_LINK_Button_Goto_Modul(v_rec,ContainerToClose,bShowTextInButton,bShowLabel);
				break;
				
			default:
				break;
			}
			
		}

		return oButton;
	}

	
}

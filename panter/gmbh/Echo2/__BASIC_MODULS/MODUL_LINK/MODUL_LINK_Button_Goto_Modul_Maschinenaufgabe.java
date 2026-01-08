package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MODUL_LINK_Button_Goto_Modul_Maschinenaufgabe extends MODUL_LINK_Button_Goto_Modul {

	
	
	
	public MODUL_LINK_Button_Goto_Modul_Maschinenaufgabe(	RECORD_MODUL_CONNECT oRec,	Vector<E2_BasicModuleContainer> ContainerToClose)
			throws myException {
		this(oRec,ContainerToClose,true,true);
	}

	public MODUL_LINK_Button_Goto_Modul_Maschinenaufgabe(	
			RECORD_MODUL_CONNECT oRec,	
			Vector<E2_BasicModuleContainer> ContainerToClose,
			boolean bShowTextInButton, 
			boolean bShowLabel)
			throws myException {
		super(oRec, ContainerToClose,bShowTextInButton,bShowLabel);
	}
	
	public MODUL_LINK_Button_Goto_Modul_Maschinenaufgabe(
			Vector<RECORD_MODUL_CONNECT> vRec,
			Vector<E2_BasicModuleContainer> ContainerToClose)
			throws myException {
		super(vRec, ContainerToClose);
	}

	

	@Override
	protected MyE2_Label createLabel() throws myException {
		MyE2_Label o = null;
//		if (is_DisplayLabel()){
//			String sText = v_rec.get(0).get_BESCHREIBUNG_cUF();
//			if (v_rec.size() == 1){
//				RECORD_MASCHINEN oRecGoto = null;
//				oRecGoto = new RECORD_MASCHINEN(v_rec.get(0).get_ID_ZIEL_cUF());
//				if (oRecGoto != null){
//					sText += " (" + oRecGoto.get_KFZKENNZEICHEN_cUF() + ")";
//				}
//				o = new MyE2_Label(sText);
//			} else if (v_rec.size() > 1){
//				o = new MyE2_Label( sText + " (" + Integer.toString(v_rec.size()) + ")");
//			}
//		}
		
		return o;
	}
	
	@Override
	protected MyE2_Button createButton() throws myException {
		MyE2_Button o = null;

		if (v_rec.size() >= 1){
			String sText = v_rec.get(0).get_BESCHREIBUNG_cUF();
			o = new MyE2_Button(E2_ResourceIcon.get_RI("kompass.png"),E2_ResourceIcon.get_RI("leer.png"));
			
			// den 1. Record der Maschine lesen
//			RECORD_MASCHINEN oRecGoto = null;
//			oRecGoto = new RECORD_MASCHINEN(v_rec.get(0).get_ID_ZIEL_cUF());
			
			// prüfen, ob ein Text in den Button gesetzt werden muss
			if (is_showTextInButton() )
			{
//				o.setText( sText + " (" + oRecGoto.get_KFZKENNZEICHEN_cUF_NN("-")   + ")" );
				o.setText( sText );
				o.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			}
			
			if (is_DisplayTooltip()) {
				
//				if(v_rec.size() == 1){
//					if (oRecGoto != null){
//						sText = oRecGoto.get_BESCHREIBUNG_cUF_NN("")  + System.getProperty("line.separator");
//						sText += oRecGoto.get_KFZKENNZEICHEN_cUF_NN("") + System.getProperty("line.separator");
//						sText += oRecGoto.get_BEMERKUNG_cUF_NN("") ;
//					}
//					o.setToolTipText(sText );
//				} else if (v_rec.size() > 1){
//					o.setToolTipText(v_rec.get(0).get_BESCHREIBUNG_cUF()+ " (" + Integer.toString(v_rec.size()) + ")");
//				}
				
				o.setToolTipText(v_rec.get(0).get_BESCHREIBUNG_cUF()+ " (" + Integer.toString(v_rec.size()) + ")");

			}
		}
		return o;
	}
	
	
}

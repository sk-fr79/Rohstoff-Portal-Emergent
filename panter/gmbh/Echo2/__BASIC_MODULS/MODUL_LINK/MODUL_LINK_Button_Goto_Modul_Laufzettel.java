package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class MODUL_LINK_Button_Goto_Modul_Laufzettel extends MODUL_LINK_Button_Goto_Modul {

	
	public MODUL_LINK_Button_Goto_Modul_Laufzettel(	RECORD_MODUL_CONNECT oRec,	Vector<E2_BasicModuleContainer> ContainerToClose)
			throws myException {
		this(oRec,ContainerToClose,false,false);
	}

	
	public MODUL_LINK_Button_Goto_Modul_Laufzettel(	
			RECORD_MODUL_CONNECT oRec,	
			Vector<E2_BasicModuleContainer> ContainerToClose,
			boolean bShowTextInButton, 
			boolean bShowLabel)
			throws myException {
		super(oRec, ContainerToClose,bShowTextInButton,bShowLabel);
	}


	public MODUL_LINK_Button_Goto_Modul_Laufzettel(
			Vector<RECORD_MODUL_CONNECT> vRec,
			Vector<E2_BasicModuleContainer> ContainerToClose)
			throws myException {
		super(vRec, ContainerToClose);
	}

	

	@Override
	protected MyE2_Label createLabel() throws myException {
		MyE2_Label o = null;
		String sText = v_rec.get(0).get_BESCHREIBUNG_cUF();
		if (v_rec.size() == 1){
//			RECORD_LAUFZETTEL oRecGoto = null;
//			try {
//				oRecGoto = new RECORD_LAUFZETTEL(v_rec.get(0).get_ID_ZIEL_cUF());
//				if (oRecGoto != null){
//					sText += "(" + oRecGoto.get_TEXT_cUF() + ")";
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				sText = new MyE2_String("Kein Objekt gefunden: Laufzettel-ID: ").CTrans() + v_rec.get(0).get_ID_ZIEL_cUF();	
//			}
			o = new MyE2_Label(sText);
		} else if (v_rec.size() > 1){
			o = new MyE2_Label( sText + " (" + Integer.toString(v_rec.size()) + ")");
		}
		return o;
	}
	
	
	@Override
	protected MyE2_Button createButton() throws myException {
		MyE2_Button o = null;

		if (v_rec.size() >= 1){
			String sText = v_rec.get(0).get_BESCHREIBUNG_cUF();
			o = new MyE2_Button(E2_ResourceIcon.get_RI("kompass.png"),E2_ResourceIcon.get_RI("leer.png"));
			
			// den 1. Record lesen
//			RECORD_LAUFZETTEL oRecGoto = null;
//			oRecGoto = new RECORD_LAUFZETTEL(v_rec.get(0).get_ID_ZIEL_cUF());
//			
//			// prüfen, ob ein Text in den Button gesetzt werden muss
//			if (is_showTextInButton() &&  !bibALL.isEmpty(oRecGoto.get_TEXT_cUF() ) )
//			{
//				String sText2 = oRecGoto.get_TEXT_cUF_NN("");
//				int nLen = sText2.length();
//				if (nLen > 50){
//					sText2 = sText2.substring(0, 50) +"..."; 
//				}
//				
//				o.setText( sText + " (" + sText2 + ")");
//				o.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
//			}
			o.setText( sText );
			o.setStyle(MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
			
			
//			if (is_DisplayTooltip() && oRecGoto != null) {
//				if(v_rec.size() == 1){
//					sText = oRecGoto.get_TEXT_cUF() ;
//				} else if (v_rec.size() > 1){
//					sText = v_rec.get(0).get_BESCHREIBUNG_cUF()+ " (" + Integer.toString(v_rec.size()) + ")";
//				}
//				
//				o.setToolTipText(sText );
//			}
			
			if (is_DisplayTooltip() ) {
				sText = v_rec.get(0).get_BESCHREIBUNG_cUF()+ " (" + Integer.toString(v_rec.size()) + ")";
				o.setToolTipText(sText );
			}

		}
		return o;
	}
	
	
}

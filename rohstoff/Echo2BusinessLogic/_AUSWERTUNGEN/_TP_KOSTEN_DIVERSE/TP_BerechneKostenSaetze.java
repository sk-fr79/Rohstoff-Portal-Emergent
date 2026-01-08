package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE;

import java.util.Vector;

import nextapp.echo2.app.Extent;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN.BL_Kostenberechnung;


/**
 * neuaufbau der kostensaetze aller bewegungsatom/vectoren der einzelnen betroffenen adressen
 * @author martin
 *
 */
public class TP_BerechneKostenSaetze
{
	private Vector<String>  vID_ADRESSE= new Vector<String>();
	private MyE2_Label      lbZaehler = new MyE2_Label("0");

	/**
	 * 
	 * @param vID_ADRESSE_ZuBearbeiten  (wenn null, dann werden alle bearbeitet)
	 */
	public TP_BerechneKostenSaetze(Vector<String> vID_ADRESSE_ZuBearbeiten) {
		super();

		this.vID_ADRESSE.removeAllElements();
		
		//beschaffen aller adressen, die einen kosteneintrag fuer die transportkosten beinhalten
		if (vID_ADRESSE_ZuBearbeiten==null) {
			this.vID_ADRESSE.addAll(
					bibVECTOR.get_VectorFromArray(
							bibDB.EinzelAbfrageInArray("SELECT "+_DB.FIRMENINFO$ID_ADRESSE+" FROM "+bibE2.cTO()+"."+_DB.FIRMENINFO)));
		} else {
			this.vID_ADRESSE.addAll(vID_ADRESSE_ZuBearbeiten);
		}

		MyE2_Grid oGridAnzeige = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		oGridAnzeige.add(new MyE2_Label(new MyE2_String("Ich baue die Kostensätze neu auf ...")),3, E2_INSETS.I(5,3,5,3));
		oGridAnzeige.add(this.lbZaehler,MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,3,5,3)));
		oGridAnzeige.add(new MyE2_Label("von"),MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,3,5,3)));
		oGridAnzeige.add(new MyE2_Label(""+this.vID_ADRESSE.size()),MyE2_Grid.LAYOUT_CENTER_CENTER(E2_INSETS.I(5,3,5,3)));
		
		if (this.vID_ADRESSE.size()>=30) {
		
			new E2_ServerPushMessageContainer_STD (new Extent(350), new Extent(200), new MyE2_String("Neuberechnung der Kosten"),true,true,5000,oGridAnzeige,E2_INSETS.I(10,10,10,10)) {
	
				@Override
				public void Run_Loop() throws myException {
					TP_BerechneKostenSaetze oThis = TP_BerechneKostenSaetze.this;
					BL_Kostenberechnung  oKostenRech = new BL_Kostenberechnung();
					int iCount = 0;
					
					for (String cID_ADRESSE: oThis.vID_ADRESSE) {
			
						oKostenRech.ErzeugeSQL_Kostensaetze_Fuer_Adresse_Und_Speichere(cID_ADRESSE);
						oKostenRech.clearStatements();
						
						oThis.lbZaehler.setText(""+(iCount++));
					}

				}
	
				@Override
				public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
				}
				
			};
		} else {
			
			BL_Kostenberechnung  oKostenRech = new BL_Kostenberechnung();
			for (String cID_ADRESSE: this.vID_ADRESSE) {
				oKostenRech.ErzeugeSQL_Kostensaetze_Fuer_Adresse_Und_Speichere(cID_ADRESSE);
				oKostenRech.clearStatements();
			}
			if (bibMSG.get_bIsOK()) {
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Aktualisierung: OK")));
			}
		}
		
		
	}
	
}

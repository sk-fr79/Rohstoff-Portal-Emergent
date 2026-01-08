package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

/*
 * methode wird nach den suchvorgaengen geladen und prueft, ob das gewaehlte lager einen fremdwaren-tag traegt.
 * Das uebergebene Grid muss ein Teil der E2_Componentmap der maske sein
 */
public abstract class FU_ZeigeAufMaske_FremdwarenLager {
	
	private MyE2_Grid  oGridZurAnzeige = null;

	public FU_ZeigeAufMaske_FremdwarenLager(MyE2_Grid ogridZurAnzeige, boolean bOnlyClearAnzeige) throws myException {
		super();
		this.oGridZurAnzeige = ogridZurAnzeige;
		this.oGridZurAnzeige.removeAll();
		
		if ((!bOnlyClearAnzeige) && this.get_bLadestationIstVonMandant()){
		
			MyLong lID_Adresse = new MyLong(this.get_cID_LAGER_AUS_MASKE()); 
			if (lID_Adresse.get_oLong()==null) {
				return;
			}
			
			RECORD_ADRESSE recAdresseLager = new RECORD_ADRESSE(lID_Adresse.get_lValue());
			
			if (recAdresseLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get_vKeyValues().size()==0) {
				//dann ist es hauptadresse und es kann keines mit fremdware definierte eigene lager sein
			} else {
				if (recAdresseLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().
						get(0).get_UP_RECORD_ADRESSE_id_adresse_basis().get_ID_ADRESSE_cUF_NN("").
						equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("--"))) {
					//nur dann kann es eine fremdwarenlager sein
					if (S.isFull(recAdresseLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_ID_ADRESSE_FREMDWARE_cUF())) {
						MyE2_Label  oLabelFremd = new MyE2_Label(new MyE2_String("FL"), new E2_FontBold(2));
						GridLayoutData oGL = new GridLayoutData();
						oGL.setBackground(new E2_ColorHelpBackground());
						oGL.setInsets(E2_INSETS.I_1_1_1_1);
						this.oGridZurAnzeige.add(oLabelFremd, oGL);
						MyLong lID_Fremd = new MyLong(recAdresseLager.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_ID_ADRESSE_FREMDWARE_cUF());
						if (lID_Fremd.get_oLong()!=null) {
							RECORD_ADRESSE recAdresseFremdbesitz = new RECORD_ADRESSE(lID_Fremd.get_lValue());
							oLabelFremd.setToolTipText(new MyE2_String("Fremdwarenlager der Firma: ",true,recAdresseFremdbesitz.get___KETTE(
									bibVECTOR.get_Vector(_DB.ADRESSE$NAME1, _DB.ADRESSE$NAME2, _DB.ADRESSE$ORT)),false).CTrans());
						}
					}
				}
			}
		}
	}

	
	public abstract String get_cID_LAGER_AUS_MASKE() throws myException;
	public abstract boolean get_bLadestationIstVonMandant() throws myException;
	
	
}

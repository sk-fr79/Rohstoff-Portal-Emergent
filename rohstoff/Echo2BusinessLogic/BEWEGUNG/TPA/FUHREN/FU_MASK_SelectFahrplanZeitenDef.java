/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 07.01.2021
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldV2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.FAHRPLAN_ZEITANGABE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 07.01.2021
 *
 */
public class FU_MASK_SelectFahrplanZeitenDef extends MyE2_DB_SelectFieldV2  {

	/**
	 * @author martin
	 * @date 07.01.2021
	 *
	 * @param osqlField
	 * @throws myException
	 */
	public FU_MASK_SelectFahrplanZeitenDef(SQLField osqlField) throws myException {
		super(osqlField, new Extent(100));
		this._populateWith(new RecList22(_TAB.fahrplan_zeitangabe)._fillWithAllFromMandant(), FAHRPLAN_ZEITANGABE.id_fahrplan_zeitangabe,FAHRPLAN_ZEITANGABE.zeitangabe, (r)-> {
			return r.getFs(FAHRPLAN_ZEITANGABE.zeitangabe,"","");
		});
		this.setWidth(new Extent(265));
		this.setFont(new E2_FontBoldItalic(-2));
		this.setBackground(new E2_ColorBase());
		
		this._aaa(()-> {
			try {
				MyE2_DB_TextArea tfZeitangabeText = (MyE2_DB_TextArea) EXT().get_oComponentMAP().get__Comp(VPOS_TPA_FUHRE.zeitangabe);
				
				MyE2_DB_TextField tfZeitVon     = (MyE2_DB_TextField) EXT().get_oComponentMAP().get__Comp(VPOS_TPA_FUHRE.fahrt_anfang_fp);
				MyE2_DB_TextField tfZeitBis     = (MyE2_DB_TextField) EXT().get_oComponentMAP().get__Comp(VPOS_TPA_FUHRE.fahrt_ende_fp);
				
				String idZeitDef = S.NN(get_ActualWert(),"").replace(".", "");
				Rec22 zeitDef = null;
				
				if (S.isFull(idZeitDef)) {
					zeitDef = new Rec22(_TAB.fahrplan_zeitangabe)._fill_id(idZeitDef);
					
					if (S.isFull(zeitDef.getFs(FAHRPLAN_ZEITANGABE.von))) {
						tfZeitVon.setText(zeitDef.getFs(FAHRPLAN_ZEITANGABE.von));
					} 
					if (S.isFull(zeitDef.getFs(FAHRPLAN_ZEITANGABE.bis))) {
						tfZeitBis.setText(zeitDef.getFs(FAHRPLAN_ZEITANGABE.bis));
					}
					if (S.isFull(zeitDef.getFs(FAHRPLAN_ZEITANGABE.zeitangabe_text))) {
						tfZeitangabeText.setText(zeitDef.getFs(FAHRPLAN_ZEITANGABE.zeitangabe_text));
					}
				}
			} catch (Exception e) {
				bibMSG.MV()._addAlarmUT("Error< 6ee73c0e-5103-11eb-ae93-0242ac130002>: "+e.getLocalizedMessage());
			}
			
		});
	}


}

package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class FSK_DD_Artikel extends MyE2_DB_SelectField {

	public FSK_DD_Artikel(SQLField osqlField) throws myException {
		super(osqlField);
		this.setFont(new E2_FontPlain(-2));
		this.setWidth(new Extent(170));
	}

	
	/**
	 * refresh muss jedesmal beim aufruf der adress-maske ausgefuehrt werden
	 * @throws myException
	 */
	public void refreshDD(String cID_ADRESSE_UF) throws myException {
		
		String[][] cSorten = null; 
		
		String cSQL = "SELECT NVL("+_DB.Z_ARTIKEL$ANR1+",CSCONVERT('<ANR1>','NCHAR_CS'))||'-'||NVL("+_DB.Z_ARTIKEL$ARTBEZ1+",CSCONVERT('<ARTBEZ1>','NCHAR_CS')), "+
						_DB.Z_ARTIKEL$ID_ARTIKEL+" FROM "+
						bibE2.cTO()+"."+_DB.ARTIKEL ;
		
		if (cID_ADRESSE_UF.equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""))) {
			
			cSorten = new E2_DropDownSettings(cSQL+ " ORDER By 1", cSQL, true).getDD();		
			this.set_ListenInhalt(cSorten, false);
			
		} else {
			
			String cSQL_Restrict = cSQL+" WHERE "+_DB.Z_ARTIKEL$ID_ARTIKEL+
											" IN (SELECT A.ID_ARTIKEL FROM JT_ARTIKELBEZ_LIEF ABL " +
											" INNER JOIN JT_ARTIKEL_BEZ AB ON (ABL.ID_ARTIKEL_BEZ=AB.ID_ARTIKEL_BEZ) "+
											" INNER JOIN JT_ARTIKEL     A  ON (AB.ID_ARTIKEL=A.ID_ARTIKEL)" +
											" WHERE ABL.ID_ADRESSE="+ cID_ADRESSE_UF+" ) ";
			
			E2_DropDownSettings oDD = new E2_DropDownSettings(cSQL_Restrict+ " ORDER By 1", cSQL, true);
			dataToView  oDD_Real = new dataToView(oDD.getDD(), false, bibE2.get_CurrSession());
			dataToView  oDD_All = new dataToView(oDD.get_DD_Shadow(), false, bibE2.get_CurrSession());

			dataToView  oDD_Shadows = new dataToView(false,bibE2.get_CurrSession());
			//jetzt alle aus dem shadow-Bereich rausnehmen, die im realen sind
			for (dataToView.zuOrdnung oZO: oDD_All) {
				if (!oDD_Real.get_bHasData(oZO.get_cData())) {
					oDD_Shadows.add(oZO);
				}
			}
			this.set_oDataToView(oDD_Real);
			this.set_odataToViewShadow(oDD_Shadows);
		}
	}
	
}

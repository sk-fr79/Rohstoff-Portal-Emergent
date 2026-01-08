
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.LAGER.LAG_LIST_CONST;


public class BOR_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT {

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException {

	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException {
		
		
        // Artikel der Bordercrossing-Zusatztabelle anzeigen
        String idBorderCrossing = oUsedResultMAP.get_cUNFormatedROW_ID();
        String sArtikel = "";
        
        SqlStringExtended sqlExt =new SqlStringExtended(
        		new SEL(_TAB.bordercrossing_artikel)
        		.ADDFIELD(ARTIKEL.anr1.tnfn(),ARTIKEL.anr1.fn())
        		.ADDFIELD(ARTIKEL.artbez1.tnfn(),ARTIKEL.artbez1.fn())
        		.FROM(_TAB.bordercrossing_artikel)
        		.INNERJOIN(_TAB.artikel, BORDERCROSSING_ARTIKEL.id_artikel, ARTIKEL.id_artikel)
        		.AND(new vglParam(BORDERCROSSING_ARTIKEL.id_bordercrossing))
        		.ORDERUP(ARTIKEL.anr1).s());
     
        sqlExt	._addParameters(new VEK<ParamDataObject>()
        		._a( new Param_Long(Long.parseLong(idBorderCrossing))) );
        
        RecList21 rl = new RecList21(_TAB.bordercrossing_artikel)._fill(sqlExt);

        MyE2_Column colArtikel = (MyE2_Column)oMAP.get__Comp(BOR_LIST_BasicModuleContainer.NAME_OF_ARTIKEL_COL_IN_LIST);
        colArtikel.setInsets(E2_INSETS.I_0_0_0_0);
        colArtikel.removeAll();
		
        for (Rec21 rec: rl){
        	sArtikel =  rec.getDbValueUnFormated(ARTIKEL.anr1.fn()) + " - " + rec.getDbValueUnFormated(ARTIKEL.artbez1.fn()) ;
        	colArtikel.add(new MyE2_Label(sArtikel),E2_INSETS.I_0_0_0_0);
        }
	}
}

package rohstoff.Echo2BusinessLogic._TAX.RULES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class TR__LIST_Selector_SingleOrMulti extends E2_ListSelektorMultiselektionStatusFeld_STD
{

	public TR__LIST_Selector_SingleOrMulti() {
	
		super(1,false,new MyE2_String("Zeige: "),new Extent(95));
		
		try {
			VEK<IF_Field> fields = new VEK<IF_Field>()
					._a(HANDELSDEF.id_land_quelle_jur)
					._a(HANDELSDEF.id_land_quelle_geo)
					._a(HANDELSDEF.id_land_ziel_jur)
					._a(HANDELSDEF.id_land_ziel_geo)
					._a(HANDELSDEF.quelle_ist_mandant)
					._a(HANDELSDEF.ziel_ist_mandant)
					._a(HANDELSDEF.tp_verantwortung)
					._a(HANDELSDEF.ust_teilnehmer_quelle)
					._a(HANDELSDEF.ust_teilnehmer_ziel)
					._a(HANDELSDEF.sorte_rc_quelle)
					._a(HANDELSDEF.sorte_rc_ziel)
					._a(HANDELSDEF.sorte_produkt_quelle)
					._a(HANDELSDEF.sorte_produkt_ziel)
					._a(HANDELSDEF.sorte_dienstleist_quelle)
					._a(HANDELSDEF.sorte_dienstleist_ziel)
					._a(HANDELSDEF.sorte_eow_quelle)
					._a(HANDELSDEF.sorte_eow_ziel)
					;


			And and = new And();
			
			for (IF_Field f: fields) {
				and.add(new vgl("H", f, _TAB.handelsdef.fullTableName(), f));
			}
			
			SEL sel = new SEL("count("+HANDELSDEF.id_handelsdef.fn("H")+")").FROM(_TAB.handelsdef,"H").WHERE(and);
			
			this.ADD_STATUS_TO_Selector(true,	"("+sel.s()+")<=1",	new MyE2_String("Einfach"),   new MyE2_String("Zeige singuläre Handelsdefinitionen an"));
			this.ADD_STATUS_TO_Selector(true,	"("+sel.s()+")>1",	new MyE2_String("Mehrfach"),   new MyE2_String("Zeige Handelsdefinitionen an, mehrfach angelegt sind"));		
			this.set_cConditionWhenAllIsSelected("");

		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._add(e.get_ErrorMessage());
		}	
		
		
	}

}

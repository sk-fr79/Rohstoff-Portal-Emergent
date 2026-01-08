package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST.SELEKTOR;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;

public class FZ_LIST_SEL_RechGutPos extends E2_ListSelektorMultiselektionStatusFeld_STD	{
	
	private static int[] CheckBoxSelektorSpalten = {85,85};

	public FZ_LIST_SEL_RechGutPos() throws myException
	{
		super(FZ_LIST_SEL_RechGutPos.CheckBoxSelektorSpalten,true,null,new Extent(20));
		
//		String queryRech = bibALL.ReplaceTeilString(
//					FZ__CONST.FIELDS.ANZAHL_RECHNUNGEN.querydef(),"#EIGENE_ADRESS_ID#",bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());
//		String queryGut = bibALL.ReplaceTeilString(
//					FZ__CONST.FIELDS.ANZAHL_GUTSCHRIFT.querydef(),"#EIGENE_ADRESS_ID#",bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF());
//		
		
		this.ADD_STATUS_TO_Selector(true,	new And(new TermLMR(BEWEGUNG_VEKTOR.zahl_rechpos,_TermCONST.COMP.GT.s(),"0")).
												and(new TermLMR(BEWEGUNG_VEKTOR.zahl_gutpos,_TermCONST.COMP.EQ.s(),"0")).s(),	
											new MyE2_String("Rech."),   new MyE2_String("Zeige Datensätze mit Rechnungspositionen"));
		
		this.ADD_STATUS_TO_Selector(true,	new And(new TermLMR(BEWEGUNG_VEKTOR.zahl_gutpos,_TermCONST.COMP.GT.s(),"0")).
												and(new TermLMR(BEWEGUNG_VEKTOR.zahl_rechpos,_TermCONST.COMP.EQ.s(),"0")).s(),	
											new MyE2_String("Gut."),   new MyE2_String("Zeige Datensätze mit Gutschriftspositionen"));	

		this.ADD_STATUS_TO_Selector(true,	new And(new TermLMR(BEWEGUNG_VEKTOR.zahl_gutpos,_TermCONST.COMP.GT.s(),"0")).
												and(new TermLMR(BEWEGUNG_VEKTOR.zahl_rechpos,_TermCONST.COMP.GT.s(),"0")).s(),	
											new MyE2_String("R.&G."),   new MyE2_String("Zeige Datensätze mit Rechnungs- und Gutschriftspositionen"));	
		
		this.ADD_STATUS_TO_Selector(true,	new And(new TermLMR(BEWEGUNG_VEKTOR.zahl_gutpos,_TermCONST.COMP.EQ.s(),"0")).
												and(new TermLMR(BEWEGUNG_VEKTOR.zahl_rechpos,_TermCONST.COMP.EQ.s(),"0")).s(),	
											new MyE2_String("Ohne"),   new MyE2_String("Zeige Datensätze ohne Abrechnungspositionen"));	
		
		
		this.set_cConditionWhenAllIsSelected("1=1");
	}
}
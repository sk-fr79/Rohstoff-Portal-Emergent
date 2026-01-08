package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_SelectField_POSITIONSTYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;

public class BSA_P_LIST__ComponentMAP extends E2_ComponentMAP {

	
	public BSA_P_LIST__ComponentMAP() throws myException 
	{
		super(new BSA_P_LIST_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("POSITIONSNUMMER")),		new MyE2_String("Pos"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANR1_ANR2")),				new MyE2_String("Sorte"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1"), MyE2_Label.STYLE_SMALL_PLAIN()),				new MyE2_String("Artikelbezeichnung 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANZAHL")),					new MyE2_String("Menge"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINHEITKURZ")),			new MyE2_String("EH"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINZELPREIS_FW")),			new MyE2_String("E-Preis"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WAEHRUNG_PRO_EINHEIT")),	new MyE2_String("-"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS_FW")),			new MyE2_String("G-Preis"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("WAEHRUNGSSYMBOL")),		new MyE2_String("W"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUMSBEREICH")),			new MyE2_String("Gültigkeit"));
		
		//ab hier standardmaessig ausgeblendet
		MyE2_DB_MultiComponentColumn	 	oColArtbez_Liefbed = new MyE2_DB_MultiComponentColumn();
		oColArtbez_Liefbed.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LIEFERBEDINGUNGEN"), MyE2_Label.STYLE_SMALL_PLAIN()),		new MyE2_String("Lieferbed."),null,false);
		oColArtbez_Liefbed.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ZAHLUNGSBEDINGUNGEN"), MyE2_Label.STYLE_SMALL_PLAIN()),	new MyE2_String("Zahlungsbed."),null,false);
		this.add_Component("GRUPPENFELD_1",oColArtbez_Liefbed,new MyE2_String("Zahlungs-/Lieferbed."),false);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STEUERSATZ")),	new MyE2_String("MWST."),false,false);
		this.add_Component(new BS_SelectField_POSITIONSTYP(oFM.get_("POSITION_TYP"),oFM,true),	new MyE2_String("Pos.Typ"),false,false);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("MENGENDIVISOR")),	new MyE2_String("Mengediv."),false,false);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_STD")),new MyE2_String("ID-VPos"),false,true);
		
		this.set_oSubQueryAgent(new ownMapFormatingAgent());
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());

		
	}

	
	
	private class ownMapFormatingAgent extends XX_ComponentMAP_SubqueryAGENT
	{

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP omap)		throws myException
		{
		}

		@Override
		public void fillComponents(E2_ComponentMAP omap, SQLResultMAP usedResultMAP) throws myException
		{
			if (S.NN(usedResultMAP.get_UnFormatedValue("DELETED")).equals("Y"))
			{
				omap.set_AllComponentsAsDeleted();
			}
		}
		
	}
	
	
}

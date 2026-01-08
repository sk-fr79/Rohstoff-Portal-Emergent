package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import nextapp.echo2.app.layout.ColumnLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SubQueryAgentShowDelGrundOncheckBox;

public class BST_P_LIST__ComponentMAP extends E2_ComponentMAP {

	public BST_P_LIST__ComponentMAP() throws myException 
	{
		super(new BST_P_LIST_SQLFieldMAP());
		
		ColumnLayoutData oLayoutRechts = MyE2_Column.LAYOUT_RIGHT(E2_INSETS.I_2_2_2_2);
		ColumnLayoutData oLayoutLinks = MyE2_Column.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2);
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		E2_CheckBoxForList oCB = new E2_CheckBoxForList();
		oCB.setFocusTraversalParticipant(false);
		
		this.add_Component("CHECK_BOX",oCB,new MyE2_String("?"));
		this.add_Component("MARKER",new E2_ButtonListMarker(),new MyE2_String("?"));

		this.add_Component(BST__CONST.HASH_KEY_POSITION_STORNO_INFO,new BST__ButtonStornoInfo(),new MyE2_String("?"));
		
		MyE2_DB_MultiComponentColumn	 	oColPos = new MyE2_DB_MultiComponentColumn();
		oColPos.add_Component(new MyE2_DB_Label_INROW(oFM.get_("POSITIONSNUMMER")),	new MyE2_String("Pos"),null);
		
		
		MyE2_DB_MultiComponentColumn	 	oColBezeichnung = new MyE2_DB_MultiComponentColumn();
		oColBezeichnung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ARTBEZ1")),new MyE2_String("TPA-Artikel"),null);
		oColBezeichnung.add_Component(new MyE2_DB_Label_INROW(oFM.get_("F_TRANSPORT_ARTIKEL")),new MyE2_String("Gefahrene Sorte"),null);

		MyE2_DB_MultiComponentColumn	 	oColVonBis = new MyE2_DB_MultiComponentColumn();
		oColVonBis.add_Component(new MyE2_DB_Label_INROW(oFM.get_("F_L_NAME_ORT")),new MyE2_String("Lieferanten-Name"),null);
		oColVonBis.add_Component(new MyE2_DB_Label_INROW(oFM.get_("F_A_NAME_ORT")),new MyE2_String("Abnehmer-Name"),null);

		MyE2_DB_MultiComponentColumn	 	oColMenge = new MyE2_DB_MultiComponentColumn();
		oColMenge.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ANZAHL")),					new MyE2_String("Anz. TPA"),  null);
		oColMenge.add_Component(new MyE2_DB_Label_INROW(oFM.get_("F_MENGE_VORGABE_KO")),		new MyE2_String("Plan-M."),  null);
		
		MyE2_DB_MultiComponentColumn	 	oColEPreis = new MyE2_DB_MultiComponentColumn();
		oColEPreis.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EINZELPREIS")),	new MyE2_String("E-Preis "+bibE2.get_cBASISWAEHRUNG_SYMBOL()), null);
		
		MyE2_DB_MultiComponentColumn	 	oColGPreis = new MyE2_DB_MultiComponentColumn();
		oColGPreis.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GESAMTPREIS")),	new MyE2_String("G-Preis "+bibE2.get_cBASISWAEHRUNG_SYMBOL()), null);

		MyE2_DB_MultiComponentColumn	 	oColIDs = new MyE2_DB_MultiComponentColumn();
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_VPOS_TPA")),new MyE2_String("ID-VPOS"),null);
		oColIDs.add_Component(new MyE2_DB_Label_INROW(oFM.get_("F_ID_VPOS_TPA_FUHRE")),new MyE2_String("ID-Transport-Position"),null);

	
		this.add_Component("GRUPPENFELD_0",oColPos,new MyE2_String("Positionsnummer"));
		
		this.add_Component("GRUPPENFELD_1",oColMenge,new MyE2_String("Mengen"));
		this.add_Component("GRUPPENFELD_2",oColBezeichnung,new MyE2_String("Bezeichnungen"));
		this.add_Component("GRUPPENFELD_3",oColVonBis,new MyE2_String("Fahrt von-bis"));
		this.add_Component("GRUPPENFELD_4",oColEPreis,new MyE2_String("Einzelpreis"));
		this.add_Component("GRUPPENFELD_5",oColGPreis,new MyE2_String("Gesamtpreis"));
		this.add_Component("GRUPPENFELD_6",oColIDs,new MyE2_String("IDs"));
	
		
		
		// sichtbarkeit
		this.get__Comp("GRUPPENFELD_6").EXT().set_bIsVisibleInList(false);
		
	
		this.get_hmRealComponents().get("POSITIONSNUMMER").EXT().set_oLayout_ListElement_AND_Titel(oLayoutRechts);
		this.get_hmRealComponents().get("ANZAHL").EXT().set_oLayout_ListElement_AND_Titel(oLayoutRechts);
		this.get_hmRealComponents().get("F_MENGE_VORGABE_KO").EXT().set_oLayout_ListElement_AND_Titel(oLayoutRechts);
		this.get_hmRealComponents().get("EINZELPREIS").EXT().set_oLayout_ListElement_AND_Titel(oLayoutRechts);
		this.get_hmRealComponents().get("GESAMTPREIS").EXT().set_oLayout_ListElement_AND_Titel(oLayoutRechts);

		this.get_hmRealComponents().get("ARTBEZ1").EXT().set_oLayout_ListElement_AND_Titel(oLayoutLinks);
		this.get_hmRealComponents().get("F_TRANSPORT_ARTIKEL").EXT().set_oLayout_ListElement_AND_Titel(oLayoutLinks);
		this.get_hmRealComponents().get("F_L_NAME_ORT").EXT().set_oLayout_ListElement_AND_Titel(oLayoutLinks);
		this.get_hmRealComponents().get("F_A_NAME_ORT").EXT().set_oLayout_ListElement_AND_Titel(oLayoutLinks);
		
		
		this.set_oSubQueryAgent(new BST_P_LIST_MarkerSubQueryAgent());
		this.add_oSubQueryAgent(new BS__SubQueryAgentShowDelGrundOncheckBox());

		
	}

	
	
	
	
}

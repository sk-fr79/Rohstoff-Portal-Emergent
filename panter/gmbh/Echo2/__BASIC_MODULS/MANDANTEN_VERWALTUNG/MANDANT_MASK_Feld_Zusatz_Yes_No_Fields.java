package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ_FELDNAMEN;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

/**
 * maskenfeld fuer die mandantenmaske zur darstellung der schalterfelder (MANDANT_ZUSATZ_FELDNAMEN$FIELD_TYPE="YES_NO")
 * @author martin
 *
 */
public class MANDANT_MASK_Feld_Zusatz_Yes_No_Fields extends MyE2_Grid implements MyE2IF__DB_Component{
	
	
	private Vector<ownCheckBox>   			vCB_Values = new Vector<ownCheckBox>();
	private MyE2EXT__DB_Component 			oEXTDB=new MyE2EXT__DB_Component(this);

	
	
	public MANDANT_MASK_Feld_Zusatz_Yes_No_Fields(SQLFieldForPrimaryKey 	osqlField) throws myException
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL_W100());
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);

	}

	
	


	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.removeAll();
	}



	@Override
	public String get_cActualMaskValue() throws myException {
		return null;
	}



	@Override
	public String get_cActualDBValueFormated() throws myException	{
		return null;
	}



	@Override
	public void set_cActualMaskValue(String cText) throws myException	{
	}



	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException 	{

		//zuerst alle zusatz_feldname rausziehen, die den typ yes-no tragen
		RECLIST_MANDANT_ZUSATZ_FELDNAMEN rlZusatz_Feld_Namen = new RECLIST_MANDANT_ZUSATZ_FELDNAMEN("SELECT "+_DB.MANDANT_ZUSATZ_FELDNAMEN+".* FROM "+bibE2.cTO()+"." +
				_DB.MANDANT_ZUSATZ_FELDNAMEN+" WHERE "+_DB.MANDANT_ZUSATZ_FELDNAMEN$FIELD_TYPE+"='"+MANDANT_CONST.ALLOWED_VALUES_ZUSATZFELD_TYPEN.YES_NO+"' ORDER BY "+_DB.MANDANT_ZUSATZ_FELDNAMEN$MASK_TEXT);

		String ID_MANDANT_UF = oResultMAP.get_cUNFormatedROW_ID();
		
		this.vCB_Values.removeAllElements();
		
		for (int i=0;i<rlZusatz_Feld_Namen.get_vKeyValues().size();i++) {
			RECORD_MANDANT_ZUSATZ_FELDNAMEN  recZusatzFieldName = rlZusatz_Feld_Namen.get(i);
			
			RECLIST_MANDANT_ZUSATZ  rlZusatz = new RECLIST_MANDANT_ZUSATZ(	"SELECT * FROM "+bibE2.cTO()+"."+_DB.MANDANT_ZUSATZ+" WHERE "+_DB.MANDANT_ZUSATZ$ID_MANDANT_ZUSATZ_FELDNAMEN+"="+recZusatzFieldName.get_ID_MANDANT_ZUSATZ_FELDNAMEN_VALUE_FOR_SQLSTATEMENT()+" AND "+
																			_DB.MANDANT_ZUSATZ$ID_MANDANT+"="+ID_MANDANT_UF);
			
			RECORD_MANDANT_ZUSATZ recMandantZusatz = null; 
			
			if (rlZusatz.get_vKeyValues().size()>0) {
				recMandantZusatz = rlZusatz.get(0);
			}
			
			this.vCB_Values.add(new ownCheckBox(recZusatzFieldName,recMandantZusatz,ID_MANDANT_UF));
		}

		
		this.setSize(30);   //10 zeilen in jeder spalte
		
		
		//jetzt das grid fuellen
		this.removeAll();
		
		for (int i=0;i<this.vCB_Values.size();i++) {
			this.add(this.vCB_Values.get(i), E2_INSETS.I(2, 2, 2, 2));
		}
		
	}


	@Override
	public void set_bIsComplexObject(boolean bisComplex){
	}


	@Override
	public boolean get_bIsComplexObject()	{
		return true;
	}


	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException	{
		//komponente wird nur beim update angezeigt
		return new Vector<String>();
	}



	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException	{
		Vector<String> vRueck = new Vector<String>();
		for (ownCheckBox oCB: this.vCB_Values) {
			String cSQL=oCB.get_SQL_Statement();
			if (S.isFull(cSQL)) {
				vRueck.add(cSQL);
			}
		}
		return vRueck;
	}



	@Override
	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}



	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component eXT_DB)
	{
		this.oEXTDB=eXT_DB;
	}
	
	
	
	
	
	
	
	
	
	
	private class ownCheckBox extends MyE2_CheckBox {

		private RECORD_MANDANT_ZUSATZ_FELDNAMEN  	recZusatzFeldNamen = null;
		private RECORD_MANDANT_ZUSATZ  				recMandantZusatz = null;
		private String  							cID_MANDANT_UF = null;
		
		/*
		 * wenn old-value=null heisst: der wert ist nicht besetzt
		 */
		private String  							cOLD_Value = null;
		
		public ownCheckBox(RECORD_MANDANT_ZUSATZ_FELDNAMEN  RecZusatzFeldNamen, RECORD_MANDANT_ZUSATZ RecMandantZusatz, String ID_MANDANT_UF) throws myException
		{
			super(new MyE2_String(RecZusatzFeldNamen.get_MASK_TEXT_cUF_NN(RecZusatzFeldNamen.get_FIELDNAME_cUF_NN("<Fehler>"))));
			this.recZusatzFeldNamen = RecZusatzFeldNamen;
			this.recMandantZusatz = RecMandantZusatz;
			this.cID_MANDANT_UF = ID_MANDANT_UF;
			
			
			//jetzt die anzeigeeinstellung definieren (fuer Y genuegt entweder YES_NO oder TEXT = Y
			if (this.recMandantZusatz != null) {
				if (this.recMandantZusatz.get_YES_NO_VALUE_cUF_NN("").equals("Y") || this.recMandantZusatz.get_TEXT_VALUE_cUF_NN("").equals("Y")) {
					this.setSelected(true);
				} else {
					this.setSelected(false);
				}
				this.cOLD_Value = this.isSelected()?"Y":"N";
			} else {
				if (this.recZusatzFeldNamen.get_DEFAULT_YES_NO_VALUE_cUF_NN("").equals("Y") || this.recZusatzFeldNamen.get_DEFAULT_TEXT_VALUE_cUF_NN("").equals("Y")) {
					this.setSelected(true);
				}
			}
			
			//this.add_oActionAgent(new ownActionAgent());
			this.setToolTipText(this.recZusatzFeldNamen.get_RELATION_INFO_cUF_NN("")+" <Key: "+this.recZusatzFeldNamen.get_FIELDNAME_cF_NN("")+">");
			
			//2015-10-27: lineWrap
			this.setLineWrap(true);
		}
		
		
		public void set_recMandantZusatz(RECORD_MANDANT_ZUSATZ rec_MandantZusatz) {
			this.recMandantZusatz = rec_MandantZusatz;
		}
		
		
		public String get_SQL_Statement() throws myException {
			String cRueck = "";
			
			if (this.cOLD_Value==null) {
				// Manfred: 2015-11-02
				// Speichern wenn der Wert sich vom Default unterscheidet, sonst wird ein Wert der von Y->N gesetzt wird nicht in die Tabelle eingefügt.
				String sDefaultYN = recZusatzFeldNamen.get_DEFAULT_YES_NO_VALUE_cUF_NN("*");
				String sYN = ( this.isSelected()) ?"Y":"N"; 
				
				if (!sYN.equals(sDefaultYN)){
					RECORDNEW_MANDANT_ZUSATZ  rnMandantenZusatz = new RECORDNEW_MANDANT_ZUSATZ();
					
					String cID_MANDANT_ZUSATZ_NEXT=bibDB.get_NextSequenceValueOfTable("JD_MANDANT_ZUSATZ");

					rnMandantenZusatz.set_NEW_VALUE_ID_MANDANT_ZUSATZ(cID_MANDANT_ZUSATZ_NEXT);
					rnMandantenZusatz.set_NEW_VALUE_ID_MANDANT(this.cID_MANDANT_UF);
					rnMandantenZusatz.set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(this.recZusatzFeldNamen.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF());
					rnMandantenZusatz.set_NEW_VALUE_FIELDNAME(this.recZusatzFeldNamen.get_FIELDNAME_cUF());
				
					rnMandantenZusatz.set_NEW_VALUE_YES_NO_VALUE(sYN);
					rnMandantenZusatz.set_NEW_VALUE_TEXT_VALUE(sYN);
					
					MySqlStatementBuilder  oStatementBuilder = rnMandantenZusatz.get_StatementBuilder(false);
					
					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$GEAENDERT_VON);
					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$LETZTE_AENDERUNG);
					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$ERZEUGT_VON);
					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$ERZEUGT_AM);
					
					cRueck =  oStatementBuilder.get_CompleteInsertString(_DB.MANDANT_ZUSATZ);
					DEBUG.System_println(cRueck, null);
				}
			} else {
					
					String cYES_NO_WERT = this.isSelected()?"Y":"N";
					if (!S.NN(this.cOLD_Value).equals(cYES_NO_WERT)) {
						this.recMandantZusatz.set_NEW_VALUE_YES_NO_VALUE(cYES_NO_WERT);
						this.recMandantZusatz.set_NEW_VALUE_TEXT_VALUE(cYES_NO_WERT);

						Vector<String> vFieldNotUpdate = bibVECTOR.get_Vector(	_DB.MANDANT_ZUSATZ$ID_MANDANT_ZUSATZ, 
																			_DB.MANDANT_ZUSATZ$GEAENDERT_VON, 
																			_DB.MANDANT_ZUSATZ$LETZTE_AENDERUNG);
					
						cRueck	=	this.recMandantZusatz.get_SQL_UPDATE_STATEMENT(vFieldNotUpdate,true);
						DEBUG.System_println(cRueck, null);
					}
			}
				
			return cRueck;
		}
		
		
		
//		private class ownActionAgent extends XX_ActionAgent {
//			@Override
//			public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
//				ownCheckBox 							oThis = 	ownCheckBox.this;
//				
//				String YesNoWert = oThis.isSelected()?"Y":"N";
//
//				MyE2_MessageVector oMV = new MyE2_MessageVector();
//				
//				RECORD_MANDANT_ZUSATZ  recZusatzNew = null;
//
//				MyDBToolBox  oDBT = bibALL.get_myDBToolBox();
//				oDBT.setZusatzFelder(bibALL.get_DB_ZusatzFelder(false, true, true, "", bibALL.get_KUERZEL()));
//				
//				if (oThis.recMandantZusatz == null) {
//					RECORDNEW_MANDANT_ZUSATZ  rnMandantenZusatz = new RECORDNEW_MANDANT_ZUSATZ();
//					
//					String cID_MANDANT_ZUSATZ_NEXT=bibDB.get_NextSequenceValueOfTable("JD_MANDANT_ZUSATZ");
//
//					rnMandantenZusatz.set_NEW_VALUE_ID_MANDANT_ZUSATZ(cID_MANDANT_ZUSATZ_NEXT);
//					rnMandantenZusatz.set_NEW_VALUE_ID_MANDANT(oThis.cID_MANDANT_UF);
//					rnMandantenZusatz.set_NEW_VALUE_ID_MANDANT_ZUSATZ_FELDNAMEN(oThis.recZusatzFeldNamen.get_ID_MANDANT_ZUSATZ_FELDNAMEN_cUF());
//					rnMandantenZusatz.set_NEW_VALUE_FIELDNAME(oThis.recZusatzFeldNamen.get_FIELDNAME_cUF());
//				
//					rnMandantenZusatz.set_NEW_VALUE_YES_NO_VALUE(YesNoWert);
//					rnMandantenZusatz.set_NEW_VALUE_TEXT_VALUE(YesNoWert);
//					
//					MySqlStatementBuilder  oStatementBuilder = rnMandantenZusatz.get_StatementBuilder(false);
//					
//					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$GEAENDERT_VON);
//					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$LETZTE_AENDERUNG);
//					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$ERZEUGT_VON);
//					oStatementBuilder.remove(_DB.MANDANT_ZUSATZ$ERZEUGT_AM);
//					
//					String cSQL=  oStatementBuilder.get_CompleteInsertString(_DB.MANDANT_ZUSATZ);
//					DEBUG.System_println(cSQL, null);
//					
//					oMV.add_MESSAGE(oDBT.ExecMultiSQLVector(bibVECTOR.get_Vector(cSQL),true));
//					if (!oMV.get_bHasAlarms()) {
//						recZusatzNew = new RECORD_MANDANT_ZUSATZ(cID_MANDANT_ZUSATZ_NEXT);
//					}
//					
//				} else {
//					
//					oThis.recMandantZusatz.set_NEW_VALUE_YES_NO_VALUE(YesNoWert);
//					oThis.recMandantZusatz.set_NEW_VALUE_TEXT_VALUE(YesNoWert);
//
//					Vector<String> vFieldNotUpdate = bibVECTOR.get_Vector(	_DB.MANDANT_ZUSATZ$ID_MANDANT_ZUSATZ, 
//																			_DB.MANDANT_ZUSATZ$GEAENDERT_VON, 
//																			_DB.MANDANT_ZUSATZ$LETZTE_AENDERUNG);
//					
//					String cSQL	=	oThis.recMandantZusatz.get_SQL_UPDATE_STATEMENT(vFieldNotUpdate,true);
//					DEBUG.System_println(cSQL, null);
//					
//					oMV.add_MESSAGE(oDBT.ExecMultiSQLVector(bibVECTOR.get_Vector(cSQL),true));
//					
//					recZusatzNew = new RECORD_MANDANT_ZUSATZ(oThis.recMandantZusatz.get_ID_MANDANT_ZUSATZ_cUF());
//				}
//				
//				//den neuen status an die komponente uebergeben (fuer den naechten klick)
//				oThis.set_recMandantZusatz(recZusatzNew);
//				
//				if (oMV.get_bHasAlarms()) {
//					bibMSG.add_MESSAGE(oMV);
//				} else {
//					MyE2_String cStringInfo = new MyE2_String("Der Schalter ",true,oThis.recZusatzFeldNamen.get_FIELDNAME_cUF(),false," wurde gesetzt und gespeichert: Neuer Wert: ",true,YesNoWert,false);
//					bibMSG.add_MESSAGE(new MyE2_Info_Message(cStringInfo));
//				}
//			}
//		}
	}


	
	
	
	
	
	
}

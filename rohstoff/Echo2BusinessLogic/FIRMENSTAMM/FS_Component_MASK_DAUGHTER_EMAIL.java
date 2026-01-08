package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.QUALIFIER.Q_DB_CheckBoxGrid;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_DAUGHTER_EMAIL extends MyE2_DBC_DaughterTable
{

	//hash fuer das qualifier-datenelement innerhalb der liste
	public static String   HASH_4_MAIL_QUALIFIER = "HASH_4_MAIL_QUALIFIER";
	
	private boolean  add_Singular_RE_GUT_SEND = false; 
	
	/**
	 * 
	 * @param fieldMAPMotherTable
	 * @param ocomponentMAP_from_Mother
	 * @param bMitTyp
	 * @param bAddTypeSingularRE_GUT_SEND   (2014-12-12: rechnung und gutschrift singulaere mailadresse zum versenden von originalen)
	 * @throws myException
	 */
	public FS_Component_MASK_DAUGHTER_EMAIL(	SQLFieldMAP 		fieldMAPMotherTable, 
												E2_ComponentMAP		ocomponentMAP_from_Mother,
												boolean 			bMitTyp,
												boolean     		bAddTypeSingularRE_GUT_SEND) throws myException
	{
		super();
		
		this.add_Singular_RE_GUT_SEND = bAddTypeSingularRE_GUT_SEND;
		
		SQLFieldMAP oSQLFieldMapEMAIL = new SQLFieldMAP("JT_EMAIL",bibE2.get_CurrSession());
		oSQLFieldMapEMAIL.addCompleteTable_FIELDLIST("JT_EMAIL",":ID_EMAIL:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMapEMAIL.add_SQLField(new SQLFieldForPrimaryKey("JT_EMAIL","ID_EMAIL","ID_EMAIL",new MyE2_String("ID-EMAIL"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_EMAIL.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMapEMAIL.add_SQLField(new SQLFieldJoinOutside("JT_EMAIL","ID_ADRESSE","ID_ADRESSE",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);

		oSQLFieldMapEMAIL.get_("EMAIL").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		oSQLFieldMapEMAIL.initFields();
		
		
		E2_ComponentMAP 			oMapEmail = new E2_ComponentMAP(oSQLFieldMapEMAIL);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_MailAdresse = 		new MyE2_DB_TextField(oSQLFieldMapEMAIL.get_SQLField("EMAIL"),true,245,0,false);
		MyE2_DB_TextField			oTF_Beschreibung = 		new MyE2_DB_TextField(oSQLFieldMapEMAIL.get_SQLField("BESCHREIBUNG"),true,245,0,false);
		MyE2_DB_SelectField			oSelectTyp	=			new MyE2_DB_SelectField(oSQLFieldMapEMAIL.get_("TYPE"),myCONST.EMAIL_TYPE_SELECTOR_ARRAY,true);
		
		oSelectTyp.setFont(new E2_FontPlain(-2));
		
		oSelectTyp.setWidth(new Extent(120));

		oTF_MailAdresse.EXT().set_oColExtent(new Extent(250));
		oTF_Beschreibung.EXT().set_oColExtent(new Extent(250));
		oSelectTyp.EXT().set_oColExtent(new Extent(120));
		
		//2012-02-15: der alte typ-selektor wird inaktiv, nur noch zur info mitgenommen
		oSelectTyp.EXT().set_bDisabledFromBasic(true);
		
		
		oTF_MailAdresse.EXT_DB().set_bIsSortable(false);
		oTF_Beschreibung.EXT_DB().set_bIsSortable(false);
		oSelectTyp.EXT_DB().set_bIsSortable(false);
		
		oMapEmail.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapEmail.add_Component(oTF_MailAdresse,new MyE2_String("Mailadresse"));
		oMapEmail.add_Component(oTF_Beschreibung,new MyE2_String("Beschreibung"));
		if (bMitTyp)
		{
			oMapEmail.add_Component(oSelectTyp,new MyE2_String("Typ"));
			
			if (bAddTypeSingularRE_GUT_SEND) {
				oMapEmail.add_Component(FS_Component_MASK_DAUGHTER_EMAIL.HASH_4_MAIL_QUALIFIER, 
					new FS__QUALIFIER_GRID_4_EMAIL_MAIN_ADRESS(
							oSQLFieldMapEMAIL.get_SQLField(RECORD_EMAIL.FIELD__ID_EMAIL), 
							"JT_EMAIL"),new MyE2_String("Mail-Verwendung"));
			} else {
				oMapEmail.add_Component(FS_Component_MASK_DAUGHTER_EMAIL.HASH_4_MAIL_QUALIFIER, 
						new FS__QUALIFIER_GRID_4_EMAIL(
								oSQLFieldMapEMAIL.get_SQLField(RECORD_EMAIL.FIELD__ID_EMAIL), 
								"JT_EMAIL"),new MyE2_String("Mail-Verwendung"));
			}
		}
			
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);

		//this.set_oContainerExScrollHeight(new Extent(300));
		this.set_to_100_percent();
		this.set_oContainerExScrollHeight(new Extent(40,Extent.PERCENT));

		
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapEmail,
							null);
		
		//2014-12-15: validierer fuer die singularitaet der RE-GS-Adresse
		this.EXT().add_FieldSetters_AND_Validator__AfterReadInputMAP(new ownValid_IF_RE_GUT_MAIL_is_Singular());
		
	}

	
	
	
	//2014-12-15: validierer zur pruefung, ob mehr als ein schalter bei eindeutiger RE/GS - eMail gesetzt ist
	private class ownValid_IF_RE_GUT_MAIL_is_Singular extends XX_FieldSetter_AND_Validator {

		@Override
		public MyE2_MessageVector isValid(String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException {
			
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (FS_Component_MASK_DAUGHTER_EMAIL.this.add_Singular_RE_GUT_SEND) {
			
				FS_Component_MASK_DAUGHTER_EMAIL oThis = FS_Component_MASK_DAUGHTER_EMAIL.this;
				
				int iAnzahlCheckSingularMail = 0;
				
				for (E2_ComponentMAP oMAP: oThis.get_oNavigationList().get_vComponentMAPS()) {
					Q_DB_CheckBoxGrid oGridCB = (Q_DB_CheckBoxGrid)oMAP.get__Comp(FS_Component_MASK_DAUGHTER_EMAIL.HASH_4_MAIL_QUALIFIER);
				
					if (!oThis.get_bMapIsMarkedToDelete(oMAP)) {
						if (oGridCB.get_hmQ_DEF_CheckBoxen().get(myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT).isSelected()) {iAnzahlCheckSingularMail++;}
					}
				}
				
				for (E2_ComponentMAP oMAP: oThis.get_oNavigationList().get_vComponentMAPS_NEW()) {
					Q_DB_CheckBoxGrid oGridCB = (Q_DB_CheckBoxGrid)oMAP.get__Comp(FS_Component_MASK_DAUGHTER_EMAIL.HASH_4_MAIL_QUALIFIER);
				
					if (!oThis.get_bMapIsMarkedToDelete(oMAP)) {
						if (oGridCB.get_hmQ_DEF_CheckBoxen().get(myCONST.EMAIL_TYPE_VALUE_BUCHHALTUNG_RE_GUT).isSelected()) {iAnzahlCheckSingularMail++;}
					}
				}
				
				if (iAnzahlCheckSingularMail>1) {
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Eine eMail-Adresse für die Versendung von Rechnung/Gutschrifts-Originalen darf es nur einmal geben ! ")));
				}
				
			}
			return oMV;
		}
		
	}
	

	
	
}

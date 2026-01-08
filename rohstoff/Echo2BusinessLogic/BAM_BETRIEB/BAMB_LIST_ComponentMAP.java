package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Icon;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class BAMB_LIST_ComponentMAP extends E2_ComponentMAP
{

	
	public BAMB_LIST_ComponentMAP(SQLFieldMAP sqlfieldMAP) throws myException
	{
		super(sqlfieldMAP);
		
		MyE2_DB_MultiComponentColumn oColumnBuchung = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColumnGrund = new MyE2_DB_MultiComponentColumn(); 
		MyE2_DB_MultiComponentColumn oColumnFeststellung = new MyE2_DB_MultiComponentColumn();
		
		/*
		 * eine icon-ampel, die den status des abschlusses einer BAM anzeigt
		 */
		/*
		 * spezielle icons zum anzeigen des status von: BAMs, Buchungen usw
		 */
		Vector<E2_ResourceIcon> vIcons = new Vector<E2_ResourceIcon>();
		Vector<String> vDBValues = new Vector<String>();
		vIcons.add(E2_ResourceIcon.get_RI("listlabel_trans.png"));  	vDBValues.add("--");
		vIcons.add(E2_ResourceIcon.get_RI("listlabel_red.png"));		vDBValues.add("NN");
		vIcons.add(E2_ResourceIcon.get_RI("listlabel_yellow.png"));	vDBValues.add("NY");
		vIcons.add(E2_ResourceIcon.get_RI("listlabel_yellow.png"));	vDBValues.add("YN");
		vIcons.add(E2_ResourceIcon.get_RI("listlabel_green.png"));		vDBValues.add("YY");
		MyE2_DB_Icon  oFieldIcon = new MyE2_DB_Icon( (SQLField)sqlfieldMAP.get(BAMB_LIST_ModulContainer.NAME_OF_SQL_INFOFIELD),
													E2_ResourceIcon.get_RI("listlabel_trans.png"),
													vIcons,
													vDBValues);
		
		oColumnBuchung.add_Component( new  MyE2_DB_Label_INROW((SQLField)sqlfieldMAP.get("BUCHUNGSNUMMER")),new MyE2_String("Buchungsnummer"),null);
		oColumnBuchung.add_Component( new  MyE2_DB_Label_INROW((SQLField)sqlfieldMAP.get("BAM_DATUM")),new MyE2_String("Datum BAM"),null);
		
		oColumnGrund.add_Component(new  MyE2_DB_Label_INROW((SQLField)sqlfieldMAP.get("BAM_GRUND")),new MyE2_String("Grund der BAM"),null);
		oColumnGrund.add_Component(new  MyE2_DB_Label_INROW((SQLField)sqlfieldMAP.get("FEHLERURSACHE")),new MyE2_String("Fehlerursache"),null);
		
		oColumnFeststellung.add_Component(new  MyE2_DB_Label_INROW((SQLField)sqlfieldMAP.get("FESTSTELLUNG_BAM")),new MyE2_String("Feststellung bei"),null);
		oColumnFeststellung.add_Component(new  MyE2_DB_Label_INROW((SQLField)sqlfieldMAP.get("BETREFF_BAM")),new MyE2_String("Betrifft"),null);
		
		
		this.add_Component(BAMB_LIST_ModulContainer.NAME_OF_CHECKBOX_IN_LIST, new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(BAMB_LIST_ModulContainer.NAME_OF_LISTMARKER_IN_LIST, new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(oFieldIcon,new MyE2_String("OK"));
		this.add_Component(BAMB_LIST_ModulContainer.HASH_LABEL_COLUMN_BUCHUNG, oColumnBuchung,new MyE2_String("Buchung Nummer/Datum"));
		this.add_Component(BAMB_LIST_ModulContainer.HASH_LABEL_COLUMN_GRUND,oColumnGrund,new MyE2_String("Grund/Ursache"));
		this.add_Component(BAMB_LIST_ModulContainer.HASH_LABEL_COLUMN_FESTSTELLUNG,oColumnFeststellung,new MyE2_String("Feststellung/Betreff"));
		
		this.add_Component(new DB_Component_USER_DROPDOWN((SQLField)sqlfieldMAP.get("ID_USER_AUSSTELLUNG")),new MyE2_String("Ausgestellt von"));
		this.add_Component(new MyE2_DB_Label((SQLField)sqlfieldMAP.get("ID_FBAM")),new MyE2_String("ID"));

		
		oColumnBuchung.set_all_labels_EmtpyValue("--");
		oColumnGrund.set_all_labels_EmtpyValue("--");
		
		this.get__Comp("ID_USER_AUSSTELLUNG").EXT().set_bIsVisibleInList(false);
		
	}

}

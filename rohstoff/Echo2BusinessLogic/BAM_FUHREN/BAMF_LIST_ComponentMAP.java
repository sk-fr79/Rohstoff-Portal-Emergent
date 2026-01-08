package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Icon;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class BAMF_LIST_ComponentMAP extends E2_ComponentMAP_V2
{
	BAMF_LIST_ModulContainer oBAMF_ListModuleContainer = null;
	
	public BAMF_LIST_ComponentMAP(SQLFieldMAP sqlfieldMAP, BAMF_LIST_ModulContainer BAMF_ListModuleContainer) throws myException
	{
		super(sqlfieldMAP);
		
		this.oBAMF_ListModuleContainer = BAMF_ListModuleContainer;
		
		MyE2_DB_MultiComponentColumn oColumnBuchung = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColumnGrund = new MyE2_DB_MultiComponentColumn(); 
		MyE2_DB_MultiComponentColumn oColumnFuhre = new MyE2_DB_MultiComponentColumn(); 
		MyE2_DB_MultiComponentColumn oColumnSorte = new MyE2_DB_MultiComponentColumn(); 
		MyE2_DB_MultiComponentColumn oColumnFeststellung = new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn oColumnIDs = new MyE2_DB_MultiComponentColumn();
		
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
		MyE2_DB_Icon  oFieldIcon = new MyE2_DB_Icon( (SQLField)sqlfieldMAP.get(BAMF_LIST_ModulContainer.NAME_OF_SQL_INFOFIELD),
													E2_ResourceIcon.get_RI("listlabel_trans.png"),
													vIcons,
													vDBValues);
		
		oColumnBuchung.add_Component( new  MyE2_DB_Label_INROW(sqlfieldMAP.get("BUCHUNGSNUMMER")),new MyE2_String("Buch.Nr"),null);
//		oColumnBuchung.add_Component( new  MyE2_DB_Label_INROW(sqlfieldMAP.get("BAM_DATUM")),new MyE2_String("Datum BAM"),null);
		
		oColumnGrund.add_Component(new  MyE2_DB_Label_INROW(sqlfieldMAP.get("BAM_GRUND")),new MyE2_String("Grund der BAM"),null);
		oColumnGrund.add_Component(new  MyE2_DB_Label_INROW(sqlfieldMAP.get("FEHLERURSACHE")),new MyE2_String("Fehlerursache"),null);

		oColumnIDs.add_Component(new MyE2_DB_Label_INROW(sqlfieldMAP.get("ID_FBAM")),new MyE2_String("ID-FBAM"),null);

		oColumnIDs.add_Component(new BAMF_LIST_BT_OpenFuhre(sqlfieldMAP.get("ID_VPOS_TPA_FUHRE"),this.oBAMF_ListModuleContainer),new MyE2_String("ID-Fuhre"),null);
		
		oColumnFeststellung.add_Component(new  MyE2_DB_Label_INROW(sqlfieldMAP.get("FESTSTELLUNG_BAM")),new MyE2_String("Feststellung bei"),null);
		oColumnFeststellung.add_Component(new  MyE2_DB_Label_INROW(sqlfieldMAP.get("BETREFF_BAM")),new MyE2_String("Betrifft"),null);
		
		oColumnSorte.add_Component(new  MyE2_DB_Label_INROW(sqlfieldMAP.get(BAMF_LIST_ModulContainer.NAME_OF_SORTE)),new MyE2_String("Sorte"),null);
		
		this.add_Component(BAMF_LIST_ModulContainer.NAME_OF_CHECKBOX_IN_LIST, new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(BAMF_LIST_ModulContainer.NAME_OF_LISTMARKER_IN_LIST, new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component(oFieldIcon,new MyE2_String("OK"));
		
		//2012-10-16: sprung in die fuhrenzentrale 
		this.add_Component(BAMF_LIST_ModulContainer.NAME_OF_JUMPBUTTON_TO_FUHRE,new BAMF_LIST_BT_JUMP_to_FUHRE(),new MyE2_String("FU"),true,new MyE2_String("Sprung in die Fuhrenzentrale"),null,null);
		
		
		//2013-04-22: lieferant und abnehmer
		oColumnFuhre.add_Component(new  MyE2_DB_Label_INROW(sqlfieldMAP.get(BAMF_LIST_ModulContainer.NAME_LISTFELD_LIEFERANT)),new MyE2_String("Lieferant"),null);
		oColumnFuhre.add_Component(new  MyE2_DB_Label_INROW(sqlfieldMAP.get(BAMF_LIST_ModulContainer.NAME_LISTFELD_ABNEHMER)),new MyE2_String("Abnehmer"),null);
		
		this.add_Component(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_BUCHUNG, oColumnBuchung,new MyE2_String("Buchung Nummer/Datum"));
		this.add_Component(new BAMF_ListCompShowDateFields());
		this.add_Component(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_GRUND,oColumnGrund,new MyE2_String("Grund/Ursache"));
		this.add_Component(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_SORTE,oColumnSorte,new MyE2_String("Sorte"));
		this.add_Component(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_FUHRE,oColumnFuhre,new MyE2_String("Lieferant/Abnehmer"));
		this.add_Component(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_FESTSTELLUNG,oColumnFeststellung,new MyE2_String("Feststellung/Betreff"));
		this.add_Component(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_IDS,oColumnIDs,new MyE2_String("ID FBAM/ID Transport-Position"));
		
		oColumnIDs.EXT().set_bIsVisibleInList(false);
		
		oColumnBuchung.set_all_labels_EmtpyValue("--");
		oColumnGrund.set_all_labels_EmtpyValue("--");
		oColumnFuhre.set_all_labels_EmtpyValue("--");
		oColumnSorte.set_all_labels_EmtpyValue("--");
		
		//2012-08-07: etwas formatieren
		this.get__Comp(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_GRUND).EXT().set_oColExtent(new Extent(200));
		this.get__Comp(BAMF_LIST_ModulContainer.HASH_LABEL_COLUMN_FESTSTELLUNG).EXT().set_oColExtent(new Extent(200));
		
		
		
	}

}

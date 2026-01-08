package rohstoff.businesslogic.bewegung2.lager_saldo;

import java.util.ArrayList;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BG_STATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO.ATOM_SALDO_LIST_CONST;
import rohstoff.businesslogic.bewegung2.global.saldo.B2_LAG_SALDO_Ermittlung;
import rohstoff.businesslogic.bewegung2.lager.vertragsmengen.B2_LAG_KontraktmengenErmittlung;

public class B2_LAG_SALDO_LIST_ComponentMap extends E2_ComponentMAP  {

	private RB_TransportHashMap   m_tpHashMap = null;

	private B2_LAG_KontraktmengenErmittlung m_oVertragsMengen = null;
	
	// die Component-Map bekommt eine Referenz auf den Saldo-Ermittler
	private  B2_LAG_SALDO_Ermittlung		m_oSaldoErmittlungen = null;
	
	public B2_LAG_SALDO_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap, B2_LAG_KontraktmengenErmittlung oVertragsmengenErmittlung, B2_LAG_SALDO_Ermittlung oSaldoErmittlung, ArrayList<B2_LAG_SALDO_Ermittlung> alSaldoErmittlungAdditional) throws myException  {

		super(new B2_LAG_SALDO_LIST_SqlFieldMAP(p_tpHashMap));

		this.m_tpHashMap = p_tpHashMap;        

		this.m_oVertragsMengen = oVertragsmengenErmittlung;
		this.m_oSaldoErmittlungen = oSaldoErmittlung;
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();

		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),S.ms("?"));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),S.ms("?"));
		//hier optionale spalten fuer direktes loeschen/edit/view
		//        this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.DIRECT_DEL.db_val(),    	new B2_LAG_SALDO_LIST_bt_DeleteInListRow(this.m_tpHashMap)
		//        																			._setGridLayout4List(new RB_gld()._ins(4))
		//        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
		//        																S.ms("?"));
		
		//        this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.DIRECT_EDIT.db_val(),   	new B2_LAG_SALDO_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
		//        																			._setGridLayout4List(new RB_gld()._ins(4))
		//        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
		//        																S.ms("?"));
		//        this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.DIRECT_VIEW.db_val(),   	new B2_LAG_SALDO_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
		//																					._setGridLayout4List(new RB_gld()._ins(4))
		//																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
		//																		S.ms("?"));
		//        this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
		//																					._addGlobalValidator(B2_LAG_SALDO_VALIDATORS.ATTACHMENT.getValidator())
		//        																			._setGridLayout4List(new RB_gld()._ins(4))
		//        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
		//        																			, S.ms("?"));

		//hier kommen die Felder  
//		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("ID_BASE"),true),  S.ms("Id_Base"));
		
//		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("SUBADRESSE"),true),  S.ms("Id_Adresse"));
//		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("SUBARTIKEL"),true),    S.ms("ID_sorte"));

		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ID_BASE.db_val()),true),		S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ID_BASE.user_text()));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUBADRESSE.db_val()),true),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUBADRESSE.user_text()));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUBARTIKEL.db_val()),true),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUBARTIKEL.user_text()));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ADRESSE_INFO.db_val()),true),S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ADRESSE_INFO.user_text()));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ART_INFO.db_val()),true),    S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ART_INFO.user_text()));

		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_MIT_INVENTUR.db_val()	,new MyE2_Label(),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_MIT_INVENTUR.user_text()));
		
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.EINHEITKURZ.db_val()),true),    S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.EINHEITKURZ.user_text()));

		
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_1.db_val()			,new MyE2_Label(),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_1.user_text()));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_2.db_val()			,new MyE2_Label(),	S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_2.user_text()));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DELTA.db_val()			,new MyE2_Label(),	S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DELTA.user_text()));
		
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURMENGE.db_val(), 		new MyE2_Label(),	S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURMENGE.user_text()));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURDATUM.db_val(), 		new MyE2_Label(),	S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURDATUM.user_text()));
		
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_KONTRAKT.db_val()		,new MyE2_Label(),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_KONTRAKT.user_text()));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_EK_KONTRAKT.db_val()		,new MyE2_Label(),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_EK_KONTRAKT.user_text()));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_EK_KONTRAKT.db_val()	,new MyE2_Label(),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_EK_KONTRAKT.user_text()));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_VK_KONTRAKT.db_val()		,new MyE2_Label(),	S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_VK_KONTRAKT.user_text()));
		this.add_Component(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_VK_KONTRAKT.db_val()	,new MyE2_Label(),  S.ms(B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_VK_KONTRAKT.user_text()));

		this._setLineWrapListHeader(true 
				,"SUBADRESSE"
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ADRESSE_INFO.db_val()
				
				);

		RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
		this._setLayoutTitles(gldTitelCenter
				);
		
		RB_gld gldTitelLeft = 	new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark());
		this._setLayoutTitles(gldTitelLeft
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.EINHEITKURZ.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ART_INFO.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ADRESSE_INFO.db_val()
				);
		
		RB_gld gldTitelRight = 	new RB_gld()._right_top()._ins(1,2,1,1)._col(new E2_ColorDark());
		this._setLayoutTitles(gldTitelRight
				,"SUBADRESSE"
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_MIT_INVENTUR.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_1.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_2.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DELTA.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_KONTRAKT.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_EK_KONTRAKT.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_EK_KONTRAKT.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_VK_KONTRAKT.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_VK_KONTRAKT.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURDATUM.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURMENGE.db_val()	
				);

		RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElements(gldElementCenter
				);
		

		RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElements(gldElementLeft
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.EINHEITKURZ.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ART_INFO.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.ADRESSE_INFO.db_val()
				);
		

		
		RB_gld gldElementRight = 	new RB_gld()._right_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElements(gldElementRight
				,"SUBADRESSE"
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_MIT_INVENTUR.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_1.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DYN_2.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_DELTA.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SALDO_KONTRAKT.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_EK_KONTRAKT.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_EK_KONTRAKT.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.SUMME_VK_KONTRAKT.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.RESTMGE_VK_KONTRAKT.db_val()
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURDATUM.db_val()	
				,B2_LAG_SALDO_CONST.B2_LAG_SALDO_NAMES.INVENTURMENGE.db_val()	
				);
		


		this.set_oSubQueryAgent(
				new B2_LAG_SALDO_LIST_FORMATING_Agent(this.m_tpHashMap, this.m_oVertragsMengen, this.m_oSaldoErmittlungen)._set_additionnal_saldo(alSaldoErmittlungAdditional));

//		this.set_Factory4Records(new factory4Records());
	}
	
	
	private class factory4Records extends E2_ComponentMAP_Factory4Records {
		@Override
		public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
			return new RECORD_BG_ATOM(cID_MAINTABLE);
		}
	}



}



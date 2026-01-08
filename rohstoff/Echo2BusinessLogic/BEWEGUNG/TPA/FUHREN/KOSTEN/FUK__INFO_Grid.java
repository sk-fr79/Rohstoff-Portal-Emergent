package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN;

import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class FUK__INFO_Grid extends MyE2_Grid_InLIST implements MyE2IF__DB_Component
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	private static int[] iBreite = {40,80,14,15};
	
	
	public FUK__INFO_Grid( SQLField osqlField, MutableStyle Style) throws myException
	{
		super(FUK__INFO_Grid.iBreite, Style);
		
		if (osqlField == null)
			throw new myException("FUK__INFO_Grid:Constructor:null-SQLField not allowed !");
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
		this.oEXTDB.set_bIsSortable(false);
	}
	
	
	public abstract String get_cID_VPOS_TPA_FUHRE_UF() throws myException;


	
	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.removeAll();
	}

	@Override
	public String get_cActualMaskValue() throws myException
	{
		return null;
	}

	@Override
	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException
	{
		//wird hier nur formal verwendet, um das info-grid zu fuellen
		this.removeAll();

		//nur wenn der umschliessende container sichtbar ist
		if (this.EXT().get_oColumnComponentContainerThisBelongsTo()!=null)
		{
			if (this.EXT().get_oColumnComponentContainerThisBelongsTo() instanceof MyE2IF__Component)
			{
				if (!((MyE2IF__Component)this.EXT().get_oColumnComponentContainerThisBelongsTo()).EXT().get_bIsVisibleInList())
				{
					return;
				}
			}
		}
		
		String cID_VPOS_TPA_FUHRE = this.get_cID_VPOS_TPA_FUHRE_UF();

		if (S.isFull(cID_VPOS_TPA_FUHRE))
		{
			RECORD_VPOS_TPA_FUHRE  recFuhre = new RECORD_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE);
			
			RECLIST_VPOS_TPA_FUHRE_KOSTEN  recListKosten = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_KOSTEN_id_vpos_tpa_fuhre("NVL(JT_VPOS_TPA_FUHRE_KOSTEN.DELETED,'N')='N'","NVL(TO_CHAR(JT_VPOS_TPA_FUHRE_KOSTEN.DATUM_BELEG,'YYYY-MM-DD'),'0000-00-00')",true);
			
			for (int i=0;i<recListKosten.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE_KOSTEN recKosten = recListKosten.get(i);
				
				String cTyp = "-";
				MyE2_String cToolTips = new MyE2_String("Undefinierter Kostentyp");
				
				if (recKosten.get_UP_RECORD_FUHREN_KOSTEN_TYP_id_fuhren_kosten_typ()!=null)
				{
					cTyp = recKosten.get_UP_RECORD_FUHREN_KOSTEN_TYP_id_fuhren_kosten_typ().get_KURZTEXT_UEBERSICHT_cF_NN("-");
					cToolTips = new MyE2_String("Kostentyp: "+recKosten.get_UP_RECORD_FUHREN_KOSTEN_TYP_id_fuhren_kosten_typ().get_TEXT4BENUTZER_cF_NN("??"));
				}
				
				MyE2_Label oLabInfo = new MyE2_Label(cTyp,recKosten.is_BELEG_VORHANDEN_YES()?MyE2_Label.STYLE_NORMAL_ITALLIC():MyE2_Label.STYLE_NORMAL_ITALIC_GREY());
				oLabInfo.setToolTipText(cToolTips.CTrans());
				this.add(oLabInfo, LayoutDataFactory.get_GridLayoutGridLeftTOP(E2_INSETS.I_1_1_5_1)) ;
				this.add(new MyE2_Label(recKosten.get_BETRAG_KOSTEN_cF_NN("-"),recKosten.is_BELEG_VORHANDEN_YES()?MyE2_Label.STYLE_NORMAL_PLAIN():MyE2_Label.STYLE_NORMAL_GREY()), LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_1_1_5_1)) ;
				this.add(new MyE2_Label(E2_ResourceIcon.get_RI(recKosten.is_BELEG_VORHANDEN_YES()?"ok_opaque.png":"ok_opaque__.png")),LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_1_1_1_1));
				
				this.add(new FUK__INFO_Grid_BemerkungenExtender(recKosten, this),LayoutDataFactory.get_GridLayoutGridRight_TOP(E2_INSETS.I_1_1_1_1));
			}
			
		}
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		this.set_cActualMaskValue(cText);
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	@Override
	public boolean get_bIsComplexObject()
	{
		return false;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP,SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP,SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	@Override
	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)
	{
		this.oEXTDB = oEXT_DB;
	}
	

}

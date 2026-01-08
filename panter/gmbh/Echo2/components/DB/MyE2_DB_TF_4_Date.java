/**
 * panter.gmbh.Echo2.components.DB
 * @author manfred
 * @date 29.06.2016
 * 
 */
package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Enum;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 29.06.2016
 *
 */
public class MyE2_DB_TF_4_Date extends E2_TF_4_Date implements MyE2IF__DB_Component{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private boolean 				bIsComplexObject = false;

	private boolean  				bAutoalign = false;
	E2_TF_4_Date_Enum 				m_enumMode = E2_TF_4_Date_Enum.OLD_SELECTION_MODE;
	
	/**
	 * @author manfred
	 * @date 29.06.2016
	 *
	 * @throws myException
	 */
	public MyE2_DB_TF_4_Date(SQLField osqlField,E2_TF_4_Date_Enum mode) throws myException {
		super();
		
		if (osqlField == null)
			throw new myException("MyE2_DB_TextField:Constructor:null-SQLField not allowed !");

		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		if (this.oEXTDB.get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask()>0)
		{
			this.get_oTextField().set_iMaxInputSize(this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_iNumberCharactersInMask());
		}
		
		this.setSelectionMode(mode);
		
	}

	/**
	 * @author manfred
	 * @date 29.06.2016
	 *
	 * @param bErasor
	 * @throws myException
	 */
	public MyE2_DB_TF_4_Date(SQLField osqlField,boolean bErasor,E2_TF_4_Date_Enum mode) throws myException {
		this(osqlField,mode);
		this.__setBasic(false,bErasor);
	}

	/**
	 * @author manfred
	 * @date 29.06.2016
	 *
	 * @param cText
	 * @param iwidthPixel
	 * @param bMiniIcon
	 * @param bErasor
	 * @param mode
	 * @param bSmallPopUp
	 * @throws myException 
	 */
	public MyE2_DB_TF_4_Date(SQLField osqlField, 
			String cText, 
			int iwidthPixel, 
			boolean bMiniIcon, 
			boolean bErasor, 
			E2_TF_4_Date_Enum mode,	
			boolean bSmallPopUp
			) throws myException {
		this(osqlField,mode);
		
		this.set_iWidthPixel(iwidthPixel);
		this.setPopUpInSmallMode(bSmallPopUp);
		this.__setBasic(bMiniIcon, bErasor);
		this.get_oTextField().setText(cText);
		
	}

	/**
	 * @author manfred
	 * @date 29.06.2016
	 *
	 * @param cText
	 * @param iwidthPixel
	 * @param bMiniIcon
	 * @param bErasor
	 * @param mode
	 */
//	public MyE2_DB_TF_4_Date(String cText, int iwidthPixel, boolean bMiniIcon, boolean bErasor,
//			E2_TF_4_Date_Enum mode) {
//		super(cText, iwidthPixel, bMiniIcon, bErasor, mode);
//	}
//
//	/**
//	 * @author manfred
//	 * @date 29.06.2016
//	 *
//	 * @param cText
//	 * @param iwidthPixel
//	 * @param bMiniIcon
//	 * @param bErasor
//	 * @throws myException
//	 */
//	public MyE2_DB_TF_4_Date(String cText, int iwidthPixel, boolean bMiniIcon, boolean bErasor) throws myException {
//		super(cText, iwidthPixel, bMiniIcon, bErasor);
//	}
//
//	/**
//	 * @author manfred
//	 * @date 29.06.2016
//	 *
//	 * @param cText
//	 * @param iwidthPixel
//	 * @param bMiniIcon
//	 * @throws myException
//	 */
//	public MyE2_DB_TF_4_Date(String cText, int iwidthPixel, boolean bMiniIcon) throws myException {
//		super(cText, iwidthPixel, bMiniIcon);
//	}
//
//	/**
//	 * @author manfred
//	 * @date 29.06.2016
//	 *
//	 * @param cText
//	 * @param iwidthPixel
//	 * @throws myException
//	 */
//	public MyE2_DB_TF_4_Date(String cText, int iwidthPixel) throws myException {
//		super(cText, iwidthPixel);
//	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent#set_cActual_Formated_DBContent_To_Mask(java.lang.String, java.lang.String, panter.gmbh.indep.dataTools.SQLResultMAP)
	 */
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)
			throws myException {
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_TextField:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");


		this.set_cActualMaskValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#prepare_ContentForNew(boolean)
	 */
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		String cText = "";
		
		if (!bSetDefault)
		{
			this.get_oTextField().setText(cText);
			this.EXT_DB().set_cLASTActualDBValueFormated(cText);
			this.EXT_DB().set_cLASTActualMaskValue(cText);

			return;
		}

		
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();

		
		this.get_oTextField().setText(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);
		
	}

	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#get_cActualMaskValue()
	 */
	@Override
	public String get_cActualMaskValue() throws myException {
		return this.get_oFormatedDateFromTextField();
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#get_cActualDBValueFormated()
	 */
	@Override
	public String get_cActualDBValueFormated() throws myException {
		return this.get_oFormatedDateFromTextField();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#set_cActualMaskValue(java.lang.String)
	 */
	@Override
	public void set_cActualMaskValue(String cText) throws myException {
		this.get_oTextField().setText(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#set_bIsComplexObject(boolean)
	 */
	@Override
	public void set_bIsComplexObject(boolean bisComplex) {
		this.bIsComplexObject = bisComplex;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#get_bIsComplexObject()
	 */
	@Override
	public boolean get_bIsComplexObject() {
		return bIsComplexObject;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#get_vInsertStack(panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP, panter.gmbh.indep.dataTools.SQLMaskInputMAP)
	 */
	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap)
			throws myException {
		return null;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#get_vUpdateStack(panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP, panter.gmbh.indep.dataTools.SQLMaskInputMAP)
	 */
	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap)
			throws myException {
		return null;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#EXT_DB()
	 */
	@Override
	public MyE2EXT__DB_Component EXT_DB() {
		return this.oEXTDB;	
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component#set_EXT_DB(panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component)
	 */
	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB) {
		this.oEXTDB = oEXT_DB;
	}
	

}

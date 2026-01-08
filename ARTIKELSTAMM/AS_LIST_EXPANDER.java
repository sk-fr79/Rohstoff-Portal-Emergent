package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import java.util.ArrayList;
import java.util.HashMap;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.XX_List_EXPANDER_4_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class AS_LIST_EXPANDER extends XX_List_EXPANDER_4_ComponentMAP 
{
	
	public  E2_ResourceIcon 						oIconLeer = E2_ResourceIcon.get_RI("leer.png");
	
	private AS_BasicModulContainerLIST   			oAS_BasicModulContainerLIST = 	null;
	private RECORD_ARTIKEL 							recArtikel = 		null; 

	
	public AS_LIST_EXPANDER(	AS_BasicModulContainerLIST		KopfModulcontainerList) throws myException
	{
		super(KopfModulcontainerList.get_oNaviList());
		this.set_iLeftColumnOffset(1);
		this.oAS_BasicModulContainerLIST = KopfModulcontainerList;
		this.set_iLeftColumnOffset(4);
	}

	public Component get_ComponentDaughter(String cID_ROW_Unformated) throws myException 
	{
		return null;    //hier wird die variante 2 benutzt
	}

	
	@Override
	public ArrayList<HashMap<String, Component>>  get_ComponentListDaughter(String cID_ARTIKEL) throws myException
	{
		this.recArtikel = new RECORD_ARTIKEL(cID_ARTIKEL);
		
		ArrayList<HashMap<String,Component>> arrayRueck = new ArrayList<HashMap<String,Component>>();
		
		
		//jetzt die sortenbezeichnungen eintragen
		RECLIST_ARTIKEL_BEZ 		recList_ARTBEZ = this.recArtikel.get_DOWN_RECORD_LIST_ARTIKEL_BEZ_id_artikel();
		
		GridLayoutData glLeft = new GridLayoutData();
		glLeft.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		glLeft.setInsets(new Insets(3,1,6,1));
		
		GridLayoutData glRight = new GridLayoutData();
		glRight.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		glRight.setInsets(new Insets(3,1,6,1));
		
		GridLayoutData glCenter = new GridLayoutData();
		glCenter.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		glCenter.setInsets(new Insets(3,1,6,1));
		

		GridLayoutData glLeftLast = new GridLayoutData();
		glLeftLast.setAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
		glLeftLast.setInsets(new Insets(3,1,6,10));

		GridLayoutData glRightLast = new GridLayoutData();
		glRightLast.setAlignment(new Alignment(Alignment.RIGHT,Alignment.TOP));
		glRightLast.setInsets(new Insets(3,1,6,10));

		GridLayoutData glCenterLast = new GridLayoutData();
		glCenterLast.setAlignment(new Alignment(Alignment.CENTER,Alignment.TOP));
		glCenterLast.setInsets(new Insets(3,1,6,10));


		if (recList_ARTBEZ.get_vKeyValues().size()>0)
		{
			for (int i=0;i<recList_ARTBEZ.get_vKeyValues().size();i++)
			{
				HashMap<String, Component> hmZeile = new HashMap<String, Component>();
				arrayRueck.add(hmZeile);
				
				RECORD_ARTIKEL_BEZ recArtBez = recList_ARTBEZ.get(i);
				
				boolean bLastRec = (i==(recList_ARTBEZ.get_vKeyValues().size()-1));
				
				this.put_mit_Layout(hmZeile, "ANR1", new MyE2_Label(recArtBez.get_ANR2_cF_NN("<ANR2>")), 	bLastRec?glRightLast:glRight);
				this.put_mit_Layout(hmZeile, "ARTBEZ1", new MyE2_Label(recArtBez.get_ARTBEZ1_cF_NN(""),true), 											bLastRec?glLeftLast:glLeft);
				this.put_mit_Layout(hmZeile, "ARTBEZ2", new MyE2_Label(recArtBez.get_ARTBEZ2_cF_NN(""),true), 											bLastRec?glLeftLast:glLeft);
				this.put_mit_Layout(hmZeile, "AKTIV",   new MyE2_CheckBox(recArtBez.get_AKTIV_cUF_NN("N").equals("Y"),true),							bLastRec?glCenterLast:glCenter);
				
			}
		}
		else
		{
			HashMap<String, Component> hmZeile = new HashMap<String, Component>();
			arrayRueck.add(hmZeile);
			
			this.put_mit_Layout(hmZeile, "ANR1", new MyE2_Label("--"), 		glRightLast);
			this.put_mit_Layout(hmZeile, "ARTBEZ1", new MyE2_Label("--"), 	glRightLast);
			this.put_mit_Layout(hmZeile, "ARTBEZ2", new MyE2_Label("--"), 	glRightLast);
			this.put_mit_Layout(hmZeile, "AKTIV",   new MyE2_Label("--"), 	glRightLast);
			
		}
		
		return arrayRueck;
	}
	
	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			AS_LIST_EXPANDER oCopy = new AS_LIST_EXPANDER(this.oAS_BasicModulContainerLIST);
			return oCopy;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy(ex.getOriginalMessage());
		}
	}


	public void refreshDaughterComponent() throws myException
	{
		this.get_oNavigationList()._REBUILD_ACTUAL_SITE(null);
	}
	

	
	private void put_mit_Layout(HashMap<String, Component> oHM, String cHashKey,Component oComp, LayoutData oLayout)
	{
		oComp.setLayoutData(oLayout);
		oHM.put(cHashKey, oComp);
	}
	
	
	


	public AS_BasicModulContainerLIST get_oAS_BasicModulContainerLIST()
	{
		return oAS_BasicModulContainerLIST;
	}


}

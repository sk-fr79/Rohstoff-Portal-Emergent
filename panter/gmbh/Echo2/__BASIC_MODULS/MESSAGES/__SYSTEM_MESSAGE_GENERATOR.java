package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Vector;

import panter.gmbh.BasicInterfaces.Service.PdServiceFindArchivMedien;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public abstract class __SYSTEM_MESSAGE_GENERATOR
{
	private String cID_ADRESSE = null;
	
	public abstract Vector<String>  get_VectorSammleMeldungsTypen() throws myException;
	
	public __SYSTEM_MESSAGE_GENERATOR(String ID_ADRESSE) throws myException
	{
		super();
		this.cID_ADRESSE = ID_ADRESSE;
	}

	
	public void ACTIVATE_MESSAGES() throws myException
	{
		Vector<String> vMeldungstypen = this.get_VectorSammleMeldungsTypen();
		
		if (vMeldungstypen==null)
		{
			throw new myException(this,"Messagetype-list MUSAT NOT BE empty");
		}
		
		if (vMeldungstypen.size()==0)
		{
			return;
		}
		
		
		RECLIST_ADRESSE_INFO  reclistInfos = 
			new RECLIST_ADRESSE_INFO(	"ID_ADRESSE="+cID_ADRESSE+" AND NVL(IST_MESSAGE,'N')='Y' AND" +
										" NVL(MESSAGE_TYP,'###') IN ("+bibALL.Concatenate(vMeldungstypen, ",", "'@@@@@'")+")", 
												"");
		
		if (reclistInfos.get_vKeyValues().size()>0)
		{
			for (int i=0;i<reclistInfos.get_vKeyValues().size();i++)
			{
				RECORD_ADRESSE_INFO recInfo = reclistInfos.get(i);
				
				String cInfoTypKlartext = myCONST.HM_ADRESS_INFO_TYP.get(recInfo.get_MESSAGE_TYP_cUF_NN("@@"));
				if (S.isEmpty(cInfoTypKlartext))
				{
					cInfoTypKlartext = "<unbekannt>";
				}
				
				MESSAGE_Entry_Target oTarget = new MESSAGE_Entry_Target(
								cID_ADRESSE,
								E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_MASK,
								null,
								new MyString("Springe zur Adresse").CTrans()
								);
				
				String Nachricht = reclistInfos.get(i).get_TEXT_cUF_NN("<info>");
				
				try {
					RECORD_ADRESSE rec_ADR = recInfo.get_UP_RECORD_ADRESSE_id_adresse();
					if (rec_ADR != null) {
						String Firma = (rec_ADR.get_NAME1_cUF_NN(" ") + rec_ADR.get_NAME2_cUF_NN(" ")).trim();
						Nachricht = new MyString("Firma: ").CTrans() + Firma + "\n\n" + Nachricht;
					}
				} catch (Exception e) {
//					e.printStackTrace();
				}
				
				MESSAGE_Entry msgEntry = new MESSAGE_Entry()
					.set_Titel(new MyE2_String("System-Nachricht: ",true,"(Bereich: ",true,cInfoTypKlartext,false,")",false).CTrans())
					.set_Nachricht(Nachricht)
					.add_idEmpfaenger(bibALL.get_RECORD_USER().get_ID_USER_cUF())
					.set_Sofortanzeige(true)
					.set_DtAnzeigeAb(bibALL.get_cDateNOWInverse("-"))
					.set_Kategorie(MESSAGE_CONST.REMINDER_Kennung.MESSAGE_FIRMENSTAMM_INFO.toString())
					.add_Target(oTarget);
				
				
				//20181219: evtl. archivmedien hier anhaengen
				try {
					PdServiceFindArchivMedien service = new PdServiceFindArchivMedien()._add(_TAB.adresse_info, recInfo.get_ID_ADRESSE_INFO_lValue(-1l));
					VEK<Rec21>  v = service.getArchivMedien(); 
					if (v!=null && v.size()>0) {
						for (Rec21 r: v) {
							msgEntry.add_Archivmedien(new RECORD_ARCHIVMEDIEN(r.getLongDbValue(ARCHIVMEDIEN.id_archivmedien)));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				new MESSAGE_Handler(MESSAGE_CONST.MESSAGE_TYP_SYSTEM).saveMessage(msgEntry);
				
			}
		}

	}
	
}

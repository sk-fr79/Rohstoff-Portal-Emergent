package panter.gmbh.basics4project.SANKTION;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class SANKTION_Hashwert_SHA256_generator {

	private static final String ALGORITHM = "SHA-256";

	public String generate_hashwert(MyE2_MessageVector mv, Rec21 record_adresse) throws myException{
		String sha256_rueck = "";

		if(! (record_adresse == null)) {
			RecList21 vRec_2_encode = new Rec21_adresse(record_adresse).getMainEmployeeAndStoreAdresses();

			VEK<String> vErg = new VEK<String>();

			String[] adresse_field_list = ENUM_SANKTION_ENCODE_MAP.ADRESS.get_fieldList();

			if(vRec_2_encode.size()>0) {
				VEK<String> erg = new VEK<>();
				for(Rec21 record_adresse_2_encode: vRec_2_encode) {
					for(String fi: adresse_field_list) {
						if(fi.startsWith("iso_country_code")) {
							if(S.isFull(fi) && !(LAND.field(fi) == null)) {
								erg._a(record_adresse_2_encode.get_up_Rec20(ADRESSE.id_land, LAND.id_land, false).get_ufs_dbVal(LAND.field(fi),""));
							}
						}else {
							if(S.isFull(fi) && !(ADRESSE.field(fi) == null)) {
								erg._a(record_adresse_2_encode.get_ufs_dbVal(ADRESSE.field(fi),""));
							}
						}
					}
					vErg._a( erg );
				}
			}

			if(vErg.size()>0) {
				StringBuffer originalString = new StringBuffer();
				for(String term:vErg) {
					originalString.append(term);
				}

				sha256_rueck = generate_sha256_hashcode(originalString.toString());
			}
		}

		return sha256_rueck;
	}

	public String generate_hashwert(MyE2_MessageVector mv, String ... info_2_encode ) throws myException{
		String sha256_rueck = "";

		if(! (info_2_encode == null)  & info_2_encode.length>0) {
			VEK<String> vErg = new VEK<String>();

			vErg._a(info_2_encode);

			if(vErg.size()>0) {
				StringBuffer originalString = new StringBuffer();
				for(String term:vErg) {
					originalString.append(term);
				}

				sha256_rueck = generate_sha256_hashcode(originalString.toString());
			}
		}
		return sha256_rueck;
	}


	public String generate_sha256_hashcode(String string2encode) throws myException {
		try {

			MessageDigest digest;
			digest = MessageDigest.getInstance(ALGORITHM);
			byte[] encodedhash = digest.digest(string2encode.getBytes(StandardCharsets.UTF_8));

			return bytesToHex(encodedhash);

		} catch (NoSuchAlgorithmException e) {
			throw new myException(this, "SHA-256 encoding problem: "+ ALGORITHM + " does not exist!");
		}
	}


	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}

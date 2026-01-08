package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.datev;

import java.math.BigDecimal;
import java.util.HashMap;

import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT.ExportBuchung;

/** 
 * Entry hash for {@see ExportBuchung} objects. Because these Buchungen are
 * grouped by the exporter by account and invoice numbers, this is a helper
 * class to easily get these hashes from {@see ExportBuchung} objects.
 * @author nils
 *
 */
public class EntryHash {
	private String invoiceNumber, accountMain, accountCounter;
	private BigDecimal vposId;
	private static HashMap<String, EntryHash> hashes;
	
	
	static {
		hashes = new HashMap<String, EntryHash>();
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public String getAccountMain() {
		return accountMain;
	}

	public String getAccountCounter() {
		return accountCounter;
	}

	public BigDecimal getParentVPosID() {
		return vposId;
	}
	
	/** Private constructor. Use the static getter {@link #get()}. */
	private EntryHash(ExportBuchung b) {
		this.invoiceNumber = b.getBuchungsnummer();
		this.accountMain = b.getKonto();
		this.accountCounter = b.getGegenkonto();
		this.vposId = b.getId_vpos_rg();
	}
	
	public static EntryHash get(ExportBuchung buchung) {
		EntryHash o; 
		String s = buchung.getBuchungsnummer() + ";" + buchung.getKonto() + ";" + buchung.getGegenkonto();
		if (((o = hashes.get(s)) != null)) {
			return o;
		} else {
			o = new EntryHash(buchung);
			hashes.put(s, o);
		}
		return o;
	}
}
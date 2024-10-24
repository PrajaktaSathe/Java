package Espionage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import Espionage.Player.Report;

/** 
 * @author Astrid
 * Utility to check if a Player's list of SecretCards
 * contain a valid Report or not.
 * A valid Report consist of at least 3 cards with
 * the same or adjoining letter(s).  
 **/
class ReportUtil {
	
	//used for testing of the current Report
	static Report obj;
	static Player player;
	static boolean bHasValidReport;
	static List<Report> lReports = new ArrayList<Report>();
	//Valid Reports if there are any.

	public static boolean hasValidReport(Player p ) {	
		player = p;
		obj = p.getEmptyReport();
		bHasValidReport = false;
		lReports.clear();
		check_4_reports(p.getAllDocs());
		return bHasValidReport;
	}
	
	public static boolean isValid(Player p, Report myReport) {
		player = p;
		obj = p.getEmptyReport(); 
		bHasValidReport = false;
		lReports.clear();
		check_4_reports(myReport.getCardList());
		return bHasValidReport;
	}

	//Returns the best Report found
	public static Report getBestReport(Player p) {
		player = p;
		obj = p.getEmptyReport();
		bHasValidReport = false;
		lReports.clear();
		check_4_reports(p.getAllDocs());
		
		if (lReports.isEmpty())
			return null;

		return lReports.stream()
				.collect(Collectors.maxBy(Comparator
				.comparingLong    (c -> ((Report) c).getCardCount()) 
				.thenComparingLong(c -> ((Report) c).getMaxValue())))
				.orElseThrow();
	}

	public static void check_4_reports(List<SecretCard> cards) {

		// Cards grouped by letter (A-F)
		Map<Character, List<SecretCard>> hm = cards.stream()
				.collect(Collectors.groupingBy(SecretCard::letter, Collectors.toList()));

		// =================================================
		// Testing is done for every letter (A-F)
		// =================================================
		IntStream.range('A', 'G').forEach(c -> {
			
			if (!hm.containsKey((char) c)) { 
				// A gap was found. 
				if (obj.getCardCount() >= 3) { 
					//This already is a valid report.
					bHasValidReport = true;
					obj.setMaxPages();
					lReports.add(obj);
					
					//Start testing for another valid Report
					obj = player.getEmptyReport();
				} else { 
					// not a valid report.
					obj.clear();
				}
			} else { 
				// Add to sum until a gap is found.
				List<SecretCard> kort = hm.get((char) c);
				long count = kort.size();
				obj.add(count, kort);
			}
		});

		if (obj.getCardCount() >= 3) { 
			//current report is valid. 
			bHasValidReport = true;
			lReports.add(obj);
			obj = null;
		}
	}
}



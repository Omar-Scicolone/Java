package transactions;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

public class TransactionManager {
	private List<Region> regions = new ArrayList<Region>();
	private List<Carrier> carriers = new ArrayList<Carrier>();
	private List<Request> requests = new ArrayList<Request>();
	private List<Offer> offers = new ArrayList<Offer>();
	private List<Transaction> transactions = new ArrayList<Transaction>();
	private List<String> places = new ArrayList<String>();
	private List<String> products = new ArrayList<String>();
	// R1

	public List<String> addRegion(String regionName, String... placeNames) {

		// List<String> places = Arrays.asList(placeNames);

		List<String> places = Stream.of(placeNames).distinct().sorted().collect(Collectors.toList());
		List<String> tmp = new ArrayList<String>();

		for (String s : this.places) {
			// int i = 0;
			for (String p : places) {
				if (s.equals(p)) {
					// places.remove(p);
					tmp.add(p);//
				}
				// i++;
			}
		}

		places.removeAll(tmp);

		this.places.addAll(places);

		Region r = new Region(regionName, places);
		regions.add(r);

		return places;
	}

	public List<String> addCarrier(String carrierName, String... regionNames) {

		List<String> region = Stream.of(regionNames).distinct().sorted().collect(Collectors.toList());
		List<String> TutteLeRegioni = new ArrayList<>();
		List<String> tmp = new ArrayList<String>();

		for (Region r : regions) {
			TutteLeRegioni.add(r.getName());
		}

		for (String s : region) {
			if (!TutteLeRegioni.contains(s)) {
				tmp.add(s);
			}
		}

		region.removeAll(tmp);

		Carrier r = new Carrier(carrierName, region);
		carriers.add(r);

		for (Region rr : regions) {
			for (String ss : region) {
				if (rr.getName().equals(ss)) {

					rr.addToNameCarrier(r.getName());
				}
			}
		}

		return region;
	}

	public List<String> getCarriersForRegion(String regionName) {

		List<String> list = new ArrayList<String>();

		for (Region r : regions) {
			if (r.getName().equals(regionName)) {
				list = r.getNameCarrier();
			}
		}

		return list.stream().sorted().collect(Collectors.toList());
	}

	// R2
	public void addRequest(String requestId, String placeName, String productId) throws TMException {
		Request req = new Request(requestId, placeName, productId);

		for (Request r : requests) {
			if (r.getRequestId().equals(requestId)) {
				throw new TMException();
			}
		}

		if (!places.contains(placeName)) {
			throw new TMException();
		}

		requests.add(req);
	}

	public void addOffer(String offerId, String placeName, String productId) throws TMException {
		Offer off = new Offer(offerId, placeName, productId);

		for (Offer o : offers) {
			if (o.getOfferId().equals(offerId)) {
				throw new TMException();
			}
		}

		if (!places.contains(placeName)) {
			throw new TMException();// Correct
		}

		offers.add(off);

	}

	// R3
	public void addTransaction(String transactionId, String carrierName, String requestId, String offerId)
			throws TMException {
		Transaction trans = new Transaction(transactionId, carrierName, requestId, offerId);

		for (Transaction t : transactions) {
			if (t.getRequestId().equals(requestId)) {
				throw new TMException();
			}
			if (t.getOfferId().equals(offerId)) {
				throw new TMException();
			}
		}

		String productReq = null;
		String productOff = null;
		for (Request r : requests) {
			if (r.getRequestId().equals(requestId)) {
				productReq = r.getProductId();
			}
		}
		for (Offer r : offers) {
			if (r.getOfferId().equals(offerId)) {
				productOff = r.getProductId();
			}
		}
		if (productReq != productOff) {
			throw new TMException();
		}

		Carrier carr = null;
		for (Carrier c : carriers) {
			if (c.getName().equals(carrierName)) {
				carr = c;
			}
		}
		List<String> regions1 = carr.getRegion(); // regioni servite da carr
													// i posti della regione
		int flag = 0;
		for (Request r : requests) {
			if (r.getRequestId().equals(requestId)) {

				for (String s : regions1) {
					for (Region rr : regions)
						if (s.equals(rr.getName())) {
							if (rr.placeNames().contains(r.getPlaceName())) {
								flag = 1;
							}
						}

				}

			}
		}
		if (flag == 0) {
			throw new TMException();
		}

		int flag1 = 0;
		for (Offer o : offers) {
			if (o.getOfferId().equals(offerId)) {
				for (String s : regions1) {
					for (Region rr : regions)
						if (s.equals(rr.getName())) {
							if (rr.placeNames().contains(o.getPlaceName())) {
								flag1 = 1;
							}
						}

				}

			}
		}
		if (flag1 == 0) {
			throw new TMException();
		}
		
		
		products.add(productReq);
		transactions.add(trans);
	}

	public boolean evaluateTransaction(String transactionId, int score) {
		
		if (score >= 1 && score <= 10) {
			for (Transaction t : transactions) {
				if (t.getTransactionId().equals(transactionId)) {
					t.setScore(score);
					
					for (Carrier c : carriers) {
						if (c.getName().equals(t.getCarrierName())) {
							c.addScore(score);
					
						}
					}
				}
			}
			
			return true;
		}
		return false;

	}

	// R4
	public SortedMap<Long, List<String>> deliveryRegionsPerNT() {
		Map<Long, List<String>> mappa = new TreeMap<Long, List<String>>();
		List<String> pl = new ArrayList<>();
		List<String> reg = new ArrayList<>();

		for (Transaction t : transactions) {
			String rID = t.getRequestId();
			for (Request r : requests) {
				if (r.getRequestId().equals(rID)) {
					pl.add(r.getPlaceName());
				}
			}
		}

		for (String s : pl) {
			for (Region r : regions) {
				if (r.placeNames().contains(s)) {
					reg.add(r.getName());
				}
			}
		}

		SortedMap<String, Long> map = reg.stream().collect(groupingBy(s -> s, TreeMap::new, counting()));
		
		SortedMap<Long, List<String>> map1 = map.entrySet().stream().collect(groupingBy(e -> e.getValue(),
				() -> new TreeMap<Long, List<String>>(reverseOrder()), mapping(e -> e.getKey(), toList())));

		return map1;
	}

	public SortedMap<String, Integer> scorePerCarrier(int minimumScore) {
		
		SortedMap<String, Integer> mapToRet = new TreeMap<String, Integer>();
	
		
		for (Carrier c : carriers) {
			int totScore = 0;
			
			List <Integer> score = c.getScore();
			for (Integer i : score) {
				
				if (i >= minimumScore) {
					totScore += i;
				}
			}
			
			if (totScore >= minimumScore) {
			mapToRet.put(c.getName(), totScore);
			}
		}
		
//		return transactions.stream(
//				).filter(t -> t.getScore() >= minimumScore)
//				.collect(groupingBy(Transaction::getCarrierName, 
//				                    TreeMap::new, 
//				                    summingInt(Transaction::getScore)));
		
		
		
		
		return mapToRet;
	}

	public SortedMap<String, Long> nTPerProduct() {
		
		System.out.println(products.stream().collect(groupingBy(t->t)));
		
		return products.stream().collect(groupingBy(t->t, TreeMap::new, counting()));
		
	}

}

package groups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

public class GroupHandling {
	List<Product> products = new ArrayList<Product>();
	List<Supplier> suppliers = new ArrayList<Supplier>();
	List<Group> groups = new ArrayList<Group>();
	List<Bid> bids = new ArrayList<Bid>();

	// R1
	public void addProduct(String productTypeName, String... supplierNames) throws GroupHandlingException {
		List<String> productsNames = new ArrayList<String>();
		for (Product p : products) {
			productsNames.add(p.getProductTypeName());
		}
		if (productsNames.contains(productTypeName)) {
			throw new GroupHandlingException();
		}
		List<String> listSupplierNames = Arrays.asList(supplierNames);
		Product p = new Product(productTypeName, listSupplierNames);
		products.add(p);

		List<String> suppliersNames = new ArrayList<String>();
		for (Supplier s : suppliers) {
			suppliersNames.add(s.getSupplierName());
		}
		for (String s : listSupplierNames) {
			if (!suppliersNames.contains(s)) {
				Supplier ss = new Supplier(s);
				suppliers.add(ss);
			}
		}

	}

	public List<String> getProductTypes(String supplierName) {
		List<String> prod = new ArrayList<>();

		for (Product p : products) {
			if (p.getSupplierNames().contains(supplierName)) {
				prod.add(p.getProductTypeName());
			}
		}

		return prod.stream().sorted().collect(Collectors.toList());
	}

	// R2
	public void addGroup(String name, String productName, String... customerNames) throws GroupHandlingException {

		List<String> groupsNames = new ArrayList<String>();
		for (Group g : groups) {
			groupsNames.add(g.getName());
		}
		if (groupsNames.contains(name)) {
			throw new GroupHandlingException();
		}

		List<String> listProd = new ArrayList<String>();
		for (Product p : products) {
			listProd.add(p.getProductTypeName());
		}

		if (!listProd.contains(productName)) {
			throw new GroupHandlingException("unknown product");
		}

		List<String> listCustomerNames = Arrays.asList(customerNames);
		Group g = new Group(name, productName, listCustomerNames);
		groups.add(g);

	}

	public List<String> getGroups(String customerName) {
		List<String> groupsNames = new ArrayList<String>();
		for (Group g : groups) {
			if (g.getCustomerNames().contains(customerName)) {
				groupsNames.add(g.getName());
			}
		}

		return groupsNames.stream().sorted().collect(Collectors.toList());
	}

	// R3
	public void setSuppliers(String groupName, String... supplierNames) throws GroupHandlingException {

		List<String> listSupp = new ArrayList<String>();
		for (Supplier s : suppliers) {
			listSupp.add(s.getSupplierName());
		}
		List<String> listSuppToCheck = new ArrayList<String>();
		for (String s : listSuppToCheck) {
			if (listSupp.contains(s)) {
				throw new GroupHandlingException();
			}
		}

		for (Group g : groups) {
			if (g.getName().equals(groupName)) {
				for (Product p : products) {
					if (p.getProductTypeName().equals(g.getProductName())) {

						List<String> listSup = p.getSupplierNames();
						for (String s : supplierNames) {
							if (!listSup.contains(s)) {
								throw new GroupHandlingException(s + " unsuitable");
							}
						}
					}
				}
			}
		}

		List<String> listToPass = Arrays.asList(supplierNames);
		for (Group g : groups) {
			if (g.getName().equals(groupName)) {
				g.setSupplierNames(listToPass);
			}
		}

	}

	public void addBid(String groupName, String supplierName, int price) throws GroupHandlingException {

		for (Group g : groups) {
			if (groupName.equals(g.getName())) {
				if (!g.getSupplierNames().contains(supplierName)) {
					throw new GroupHandlingException(supplierName + " not in " + g.getName());
				}
			}
		}

		Bid b = new Bid(groupName, supplierName, price);
		bids.add(b);
	}

	public String getBids(String groupName) {
		String messBid = new String();
		List<Bid> bids2 = bids.stream()
				.filter(b -> b.getGroupName().equals(groupName))
				.sorted(Comparator
				.comparing(Bid::getPrice, (b1, b2) -> {	return b1.compareTo(b2);})
				.thenComparing(Bid::getSupplierName, (b1, b2) -> {return b1.compareTo(b2);})).collect(Collectors.toList());
		
		int len = 1;
		for (Bid b : bids2) {
			if (len < bids2.size()) {
			messBid = messBid.concat(b.getSupplierName() + ":" + b.getPrice() +  ",");
			}
			else {
				messBid = messBid.concat(b.getSupplierName() + ":" + b.getPrice());
			}
			
			len++;
		}
		

		return messBid;
	}

	// R4
	public void vote(String groupName, String customerName, String supplierName) throws GroupHandlingException {
		
		
	}

	public String getVotes(String groupName) {
		return null;
	}

	public String getWinningBid(String groupName) {
		return null;
	}

	// R5
	public SortedMap<String, Integer> maxPricePerProductType() { // serve toMap
		return null;
	}

	public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
		return null;
	}

	public SortedMap<String, Long> numberOfCustomersPerProductType() {
		return null;
	}

}

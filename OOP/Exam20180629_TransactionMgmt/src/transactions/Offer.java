package transactions;

public class Offer {
	private String offerId;
	private String placeName; 
	private String productId;
	
	public Offer(String offerId, String placeName, String productId) {
	
		this.offerId = offerId;
		this.placeName = placeName;
		this.productId = productId;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
		
}

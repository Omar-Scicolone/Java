package transactions;

import java.util.List;

public class Request {
	private String requestId;
	private String placeName;
	private String productId;
	
	
	public Request(String requestId, String placeName, String productId) {
		this.requestId = requestId;
		this.placeName = placeName;
		this.productId = productId;
	}


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
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
	
	public String placeName() {
		return placeName;
	}

}

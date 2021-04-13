package maikiencuong.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModel {

	private String shipAddress;

	private String paymentMethod;

	private String paymentStatus;

	// ----------------------

	private CustomerModel customer;

	private List<OrderDetailModel> orderDetailss;

}

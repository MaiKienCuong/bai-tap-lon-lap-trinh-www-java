package maikiencuong.controller.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.dto.OrderDetailDTO;
import maikiencuong.entity.OrderDetail;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.OrderDetailServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderDetailApi {

	@Autowired
	private OrderDetailServ orderDetailServ;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/order-detail/order/{id}")
	public ResponseEntity<?> findAllByOrderId(@PathVariable("id") Long id) {
		List<OrderDetail> list = orderDetailServ.findAllByOrder_Id(id);
		if (!list.isEmpty()) {
			List<OrderDetailDTO> result = modelMapper.map(list, new TypeToken<List<OrderDetailDTO>>() {
			}.getType());
			return ResponseEntity.ok(result);
		}

		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
	}

}

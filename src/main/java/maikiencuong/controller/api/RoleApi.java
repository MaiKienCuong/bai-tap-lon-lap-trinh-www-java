package maikiencuong.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maikiencuong.entity.Role;
import maikiencuong.payload.response.MessageResponse;
import maikiencuong.service.RoleServ;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleApi {

	@Autowired
	private RoleServ roleServ;

	@RequestMapping("/roles")
	public ResponseEntity<?> findAll() {
		List<Role> list = roleServ.findAll();
		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		}
		
		return ResponseEntity.badRequest().body(new MessageResponse("Danh sách trống"));
	}

}

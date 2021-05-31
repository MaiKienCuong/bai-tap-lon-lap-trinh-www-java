package quanaotreem.jwt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import quanaotreem.response.MessageResponse;

/**
 * The Class AuthEntryPointJwt.
 * 
 * <p>
 * Class nay de xu ly loi khi truy cap vao cac tai nguyen duoc bao ve ma chua
 * dang nhap (loi 401)
 * </p>
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().println(mapper.writeValueAsString(
				new MessageResponse("Lỗi xác thực hoặc phiên làm việc đã hết hạn. Vui lòng đăng nhập lại")));
	}

}
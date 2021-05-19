package maikiencuong.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import maikiencuong.payload.response.MessageResponse;

/**
 * The Class AccessDeniedHandlerJwt.
 * 
 * <p>
 * Class nay de xu ly loi AccessDenied (loi 403)
 * </p>
 * 
 */
@Component
public class AccessDeniedHandlerJwt implements AccessDeniedHandler {

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().println(
				mapper.writeValueAsString(new MessageResponse("Lỗi: Truy cập bị từ chối. Không có quyền truy cập")));

	}

}

package maikiencuong.handler;

import java.util.List;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import maikiencuong.payload.response.MessageResponse;

@ControllerAdvice
@RestController
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		if (bindingResult.hasFieldErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			return ResponseEntity.badRequest().body(new MessageResponse(errors.get(0).getDefaultMessage()));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Lỗi: Tham số gửi lên không hợp lệ"));
	}

	@ExceptionHandler(JpaSystemException.class)
	public final ResponseEntity<Object> handleAllExceptions(JpaSystemException ex, WebRequest request) {
		ex.printStackTrace();
		return ResponseEntity.badRequest().body(new MessageResponse("Lỗi: Không thể thêm hoặc cập nhật dữ liệu"));
	}

	@ExceptionHandler(PropertyReferenceException.class)
	public final ResponseEntity<Object> handlePropertyReferenceException(PropertyReferenceException ex,
			WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
	}

	@ExceptionHandler(MyExcetion.class)
	public final ResponseEntity<Object> handleMyException(MyExcetion ex, WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse(ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse("Lỗi: Phương thức không được hỗ trợ"));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("handleHttpMediaTypeNotSupported " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("handleHttpMediaTypeNotAcceptable " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse("handleMissingPathVariable " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("handleMissingServletRequestParameter " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("handleServletRequestBindingException " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse("handleConversionNotSupported " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Lỗi: Kiểu dữ liệu không hợp lệ. Không thể chuyển đổi kiểu"));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Lỗi: Dữ liệu không hợp lệ. Không thể đọc được dữ liệu"));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse("handleHttpMessageNotWritable " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("handleMissingServletRequestPart " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse("handleBindException " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("handleNoHandlerFoundException " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("handleAsyncRequestTimeoutException " + ex.getMessage()));
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return ResponseEntity.badRequest().body(new MessageResponse("handleExceptionInternal " + ex.getMessage()));
	}

}

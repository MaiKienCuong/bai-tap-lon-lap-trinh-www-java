package maikiencuong.dto.mapper;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import maikiencuong.handler.MyExcetion;

/**
 * The Class DTOModelMapper.
 * 
 * <p>
 * Class nay la pre-process cho cac phuong thuc co requestBody hoac responsebody
 * </p>
 */
public class DTOModelMapper extends RequestResponseBodyMethodProcessor {

	private ModelMapper modelMapper;

	private EntityManager entityManager;

	public DTOModelMapper(ObjectMapper objectMapper, EntityManager entityManager, ModelMapper modelMapper) {
		super(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper)));
		this.entityManager = entityManager;
		this.modelMapper = modelMapper;
	}

	/**
	 * Supports parameter.
	 * 
	 * <p>
	 * Khai bao nhung annotation nao se duoc class nay xu ly. O day cho co
	 * annotation DTO
	 * </p>
	 *
	 * @param parameter the parameter
	 * @return true, if successful
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(DTO.class);
	}

	/**
	 * Validate if applicable.
	 *
	 * <p>
	 * Kiem tra tinh hop le cuaa du lieu, tuong duong voi annotation Valid
	 * </p>
	 *
	 * @param binder    the binder
	 * @param parameter the parameter
	 */
	@Override
	protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		binder.validate();
	}

	/**
	 * Resolve argument.
	 * 
	 * <p>
	 * Xu ly cac argument. Neu DTO gui len co thuoc tinh id (duoc danh dau bang
	 * annotation @Id) thi entityManager se tim no trong database sau do modelMapper
	 * se map no qua entity. Neu DTO gui len khong co thuoc tinh @ID thi no se duoc
	 * map qua entity luon
	 * </p>
	 *
	 * @param parameter     the parameter
	 * @param mavContainer  the mav container
	 * @param webRequest    the web request
	 * @param binderFactory the binder factory
	 * @return the object
	 * @throws Exception the exception
	 */
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
		if (dto == null)
			throw new MyExcetion("Dữ liệu trống");
		Object id = getEntityId(dto);
		if (id == null) {
			return modelMapper.map(dto, parameter.getParameterType());
		} else {
			Object persistedObject = entityManager.find(parameter.getParameterType(), id);
			if (persistedObject == null)
				throw new MyExcetion("Không tìm thấy dữ liệu");
			modelMapper.map(dto, persistedObject);
			return persistedObject;
		}
	}

	@Override
	protected Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
		for (Annotation ann : parameter.getParameterAnnotations()) {
			DTO dtoType = AnnotationUtils.getAnnotation(ann, DTO.class);
			if (dtoType != null) {
				return super.readWithMessageConverters(inputMessage, parameter, dtoType.value());
			}
		}
		throw new RuntimeException();
	}

	/**
	 * Gets the entity id.
	 * 
	 * <p>
	 * Neu DTO gui len co thuoc tinh nao duoc danh dau bang annotation @Id thi se
	 * lay ra gia tri cua Field do
	 * </p>
	 *
	 * @param dto the dto
	 * @return the entity id
	 */
	private Object getEntityId(Object dto) {
		for (Field field : dto.getClass().getDeclaredFields()) {
			if (field.getAnnotation(Id.class) != null) {
				try {
					field.setAccessible(true);
					return field.get(dto);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}

}

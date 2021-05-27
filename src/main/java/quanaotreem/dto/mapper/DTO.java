package quanaotreem.dto.mapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface DTO.
 * 
 * <p>
 * Dinh nghia annotation de danh dau doi tuong DTO ma client gui len. Nhung
 * argument nao ma duoc danh dau bang annotation nay se duoc modelMapper chuyen
 * sang entity
 * </p>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DTO {

	Class<?> value();

}

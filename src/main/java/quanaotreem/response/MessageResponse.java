package quanaotreem.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class MessageResponse.
 * 
 * <p>
 * Doi tuong cua class nay chua message tu server gui ve cho client
 * </p>
 */
@Getter
@Setter
@AllArgsConstructor
public class MessageResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

}
package maikiencuong.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import maikiencuong.service.impl.AccountDetailsImpl;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${jwtSecret}")
	private String jwtSecret;

	@Value("${jwtExpirationMs}")
	private int jwtExpirationMs;

	/**
	 * Generate jwt token.
	 * 
	 * <p>
	 * tao ra chuoi Authorization Token jwt de tra ve cho client khi dang nhap thanh
	 * cong
	 * </p>
	 *
	 * @param authentication the authentication
	 * @return the string
	 */
	public String generateJwtToken(Authentication authentication) {
		AccountDetailsImpl userPrincipal = (AccountDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * Gets the user name from jwt token.
	 * 
	 * <p>
	 * Lay username tu chuoi Authorization Token
	 * </p>
	 *
	 * @param token the token
	 * @return the user name from jwt token
	 */
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Validate jwt token.
	 * 
	 * <p>
	 * Kiem tra xem Authorization Token client gui len co hop le hay khong
	 * </p>
	 *
	 * @param authToken the auth token
	 * @return true, if successful
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		} catch (Exception e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		}

		return false;
	}
}
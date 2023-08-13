package telran.spring.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	//FIXME moving to env. variable
	String key = "0266028c33bd64c1499f844e80e6be749b4318931c36576c266fa1d5938551ea";
	@Value("${app.security.jwt.expiration.period:3600000}")
	long expPeriod;
public String extractUserName(String token) {
	
	return extractClaim(token, Claims::getSubject);
}
public Date extractExpirationDate(String token) {
	
	return extractClaim(token, Claims::getExpiration);
}
private Claims extractAllClaims(String token) {
	return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
}
public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	return claimResolver.apply(extractAllClaims(token));
}
private Key getSigningKey() {
	byte[] keyBytes = Decoders.BASE64.decode(key);
	return Keys.hmacShaKeyFor(keyBytes);
}
public boolean isNotExpired(String token) {
	Date expDate = extractExpirationDate(token);
	Date currentDate = new Date();
	return currentDate.before(expDate);
	
}
public String createToken(Map<String, Object> extraClaims, UserDetails userDetails) {
	Date current = new Date();
	Date exp = new Date(System.currentTimeMillis() + expPeriod);
	return Jwts.builder().addClaims(extraClaims).setExpiration(exp).setIssuedAt(current)
			.setSubject(userDetails.getUsername()).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
}
}

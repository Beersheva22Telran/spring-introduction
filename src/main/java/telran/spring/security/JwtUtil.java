package telran.spring.security;


import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	String secretKey = "AwI3ai3F2cJ1cig96xNWng7UciFSVRIs";
 public String extractUsername(String token) {
	 
	 return extractClaim(token, Claims::getSubject);
 }
 private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	 Claims claims = extractAllClaims(token);
	 return claimResolver.apply(claims);
 }
 private Claims extractAllClaims(String token) {
	 return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
 }
private Key getSigningKey() {
	byte[] keyBytes = Decoders.BASE64.decode(secretKey);
	return Keys.hmacShaKeyFor(keyBytes);
}
public String generateToken(Map<String, Object> extraClaims, String username) {
	Date currentDate = new Date();
	Date expDate = new Date(currentDate.getTime() + 3600000);
	return Jwts.builder().setClaims(extraClaims).setSubject(username).setIssuedAt(currentDate).setExpiration(expDate)
	.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
}
public boolean isTokenValid(String token, String username) {
	String user = extractUsername(token);
	return username.equals(user) && isNotExpired(token);
}
private boolean isNotExpired(String token) {
	
	return extractClaim(token, Claims::getExpiration).before(new Date());
}
}

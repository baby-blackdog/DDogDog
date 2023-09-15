package com.babyblackdog.ddogdog.global.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.StringJoiner;
import org.springframework.util.Assert;

public class Claims {

  private String username;
  private String email;
  private String role;
  private Date issuedAt;
  private Date expiresAt;

  private Claims() {
  }

  Claims(DecodedJWT decodedJWT) {
    this.username = getUsernameFromClaim(decodedJWT);
    this.email = getEmailFromClaim(decodedJWT);
    this.role = getRoleFromClaim(decodedJWT);
    this.issuedAt = decodedJWT.getIssuedAt();
    this.expiresAt = decodedJWT.getExpiresAt();
  }

  public static Claims from(String username, String email, String role) {
    Claims claims = new Claims();
    claims.username = username;
    claims.email = email;
    claims.role = role;
    return claims;
  }

  private String getUsernameFromClaim(DecodedJWT decodedJWT) {
    Claim username = decodedJWT.getClaim("username");
    Assert.notNull(username, "username is not in claim");
    return username.asString();
  }

  private String getEmailFromClaim(DecodedJWT decodedJWT) {
    Claim email = decodedJWT.getClaim("email");
    Assert.notNull(email, "email is not in claim");
    return email.asString();
  }

  private String getRoleFromClaim(DecodedJWT decodedJWT) {
    Claim role = decodedJWT.getClaim("role");
    Assert.notNull(role, "roles is not in claim");
    return role.asString();
  }

  String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  String getRole() {
    return role;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Claims.class.getSimpleName() + "[", "]")
        .add("username='" + username + "'")
        .add("email='" + email + "'")
        .add("role='" + role + "'")
        .add("issuedAt=" + issuedAt)
        .add("expiresAt=" + expiresAt)
        .toString();
  }
}
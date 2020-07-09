package paris8.wanted.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
	JS, HH, ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}

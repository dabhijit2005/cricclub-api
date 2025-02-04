package com.cricclub.google;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GoogleData {
	private String googleId;
	private String email;
	private boolean emailVerified;
	private String name;
	private String avatarUrl;
	private String locale;
}

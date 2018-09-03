package com.waylau.spring.boot.blog.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.waylau.spring.boot.blog.domain.PersistentLogins;

public interface CustomPersistenTokenRepository extends PersistentTokenRepository,JpaRepository<PersistentLogins,String> {
		@Modifying
		@Query("update User u set u.firstname = ?1 where u.lastname = ?2")
		void createNewToken(PersistentRememberMeToken token);

		void updateToken(String series, String tokenValue, Date lastUsed);

		PersistentRememberMeToken getTokenForSeries(String seriesId);

		void removeUserTokens(String username);
}

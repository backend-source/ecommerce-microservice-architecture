package com.hoangtien2k3.userservice.helper;

import com.hoangtien2k3.userservice.domain.Credential;
import com.hoangtien2k3.userservice.domain.VerificationToken;
import com.hoangtien2k3.userservice.dto.CredentialDto;
import com.hoangtien2k3.userservice.dto.VerificationTokenDto;

public interface VerificationTokenMappingHelper {

    static VerificationTokenDto map(final VerificationToken verificationToken) {
        return VerificationTokenDto.builder()
                .verificationTokenId(verificationToken.getVerificationTokenId())
                .token(verificationToken.getToken())
                .expireDate(verificationToken.getExpireDate())
                .credentialDto(
                        CredentialDto.builder()
                                .credentialId(verificationToken.getCredential().getCredentialId())
                                .username(verificationToken.getCredential().getUsername())
                                .password(verificationToken.getCredential().getPassword())
                                .roleBasedAuthority(verificationToken.getCredential().getRoleBasedAuthority())
                                .isEnabled(verificationToken.getCredential().getIsEnabled())
                                .isAccountNonExpired(verificationToken.getCredential().getIsAccountNonExpired())
                                .isAccountNonLocked(verificationToken.getCredential().getIsAccountNonLocked())
                                .isCredentialsNonExpired(verificationToken.getCredential().getIsCredentialsNonExpired())
                                .build())
                .build();
    }

    static VerificationToken map(final VerificationTokenDto verificationTokenDto) {
        return VerificationToken.builder()
                .verificationTokenId(verificationTokenDto.getVerificationTokenId())
                .token(verificationTokenDto.getToken())
                .expireDate(verificationTokenDto.getExpireDate())
                .credential(
                        Credential.builder()
                                .credentialId(verificationTokenDto.getCredentialDto().getCredentialId())
                                .username(verificationTokenDto.getCredentialDto().getUsername())
                                .password(verificationTokenDto.getCredentialDto().getPassword())
                                .roleBasedAuthority(verificationTokenDto.getCredentialDto().getRoleBasedAuthority())
                                .isEnabled(verificationTokenDto.getCredentialDto().getIsEnabled())
                                .isAccountNonExpired(verificationTokenDto.getCredentialDto().getIsAccountNonExpired())
                                .isAccountNonLocked(verificationTokenDto.getCredentialDto().getIsAccountNonLocked())
                                .isCredentialsNonExpired(verificationTokenDto.getCredentialDto().getIsCredentialsNonExpired())
                                .build())
                .build();
    }

}
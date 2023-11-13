package com.hoangtien2k3.userservice.helper;

import com.hoangtien2k3.userservice.domain.Credential;
import com.hoangtien2k3.userservice.domain.User;
import com.hoangtien2k3.userservice.dto.CredentialDto;
import com.hoangtien2k3.userservice.dto.UserDto;

public interface CredentialMappingHelper {

    static CredentialDto map(final Credential credential) {
        return CredentialDto.builder()
                .credentialId(credential.getCredentialId())
                .username(credential.getUsername())
                .password(credential.getPassword())
                .roleBasedAuthority(credential.getRoleBasedAuthority())
                .isEnabled(credential.getIsEnabled())
                .isAccountNonExpired(credential.getIsAccountNonExpired())
                .isAccountNonLocked(credential.getIsAccountNonLocked())
                .isCredentialsNonExpired(credential.getIsCredentialsNonExpired())
                .userDto(
                        UserDto.builder()
                                .userId(credential.getUser().getUserId())
                                .firstName(credential.getUser().getFirstName())
                                .lastName(credential.getUser().getLastName())
                                .imageUrl(credential.getUser().getImageUrl())
                                .email(credential.getUser().getEmail())
                                .phone(credential.getUser().getPhone())
                                .build())
                .build();
    }

    static Credential map(final CredentialDto credentialDto) {
        return Credential.builder()
                .credentialId(credentialDto.getCredentialId())
                .username(credentialDto.getUsername())
                .password(credentialDto.getPassword())
                .roleBasedAuthority(credentialDto.getRoleBasedAuthority())
                .isEnabled(credentialDto.getIsEnabled())
                .isAccountNonExpired(credentialDto.getIsAccountNonExpired())
                .isAccountNonLocked(credentialDto.getIsAccountNonLocked())
                .isCredentialsNonExpired(credentialDto.getIsCredentialsNonExpired())
                .user(
                        User.builder()
                                .userId(credentialDto.getUserDto().getUserId())
                                .firstName(credentialDto.getUserDto().getFirstName())
                                .lastName(credentialDto.getUserDto().getLastName())
                                .imageUrl(credentialDto.getUserDto().getImageUrl())
                                .email(credentialDto.getUserDto().getEmail())
                                .phone(credentialDto.getUserDto().getPhone())
                                .build())
                .build();
    }

}
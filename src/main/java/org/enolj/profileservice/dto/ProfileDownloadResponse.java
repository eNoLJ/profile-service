package org.enolj.profileservice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PROTECTED)
public class ProfileDownloadResponse {

    private final String profileUrl;

    public static ProfileDownloadResponse from(String profileUrl) {
        return ProfileDownloadResponse.builder()
                .profileUrl(profileUrl)
                .build();
    }
}

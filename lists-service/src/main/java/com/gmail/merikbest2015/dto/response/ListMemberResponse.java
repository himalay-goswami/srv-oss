package com.gmail.merikbest2015.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gmail.merikbest2015.commons.dto.ImageResponse;
import lombok.Data;

@Data
public class ListMemberResponse {
    private Long id;
    private String fullName;
    private String username;
    private String about;
    private ImageResponse avatar;

    @JsonProperty("isPrivateProfile")
    private boolean isPrivateProfile;
}

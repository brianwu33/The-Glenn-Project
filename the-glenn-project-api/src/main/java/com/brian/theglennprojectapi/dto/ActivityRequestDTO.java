package com.brian.theglennprojectapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequestDTO {
    // name should not be null or empty
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String name;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private Long ownerId;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String location;

    @JsonProperty(required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime startAt;

    @JsonProperty(required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime endAt;

    @NotEmpty
    @NotBlank
    private String link;

    @NotEmpty
    @NotBlank
    private String additionalInfo;

    @JsonProperty(required = true)
    @NotEmpty
    private int maximumParticipants;
}

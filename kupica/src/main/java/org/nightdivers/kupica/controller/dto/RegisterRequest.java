package org.nightdivers.kupica.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String nickname) {

}

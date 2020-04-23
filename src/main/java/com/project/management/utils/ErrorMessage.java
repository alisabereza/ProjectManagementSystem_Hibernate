package com.project.management.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@NoArgsConstructor
public @Data class ErrorMessage {
    @NonNull
    private String field;
    @NonNull
    private String error;
}

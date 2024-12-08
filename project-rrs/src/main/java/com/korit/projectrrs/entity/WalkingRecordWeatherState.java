package com.korit.projectrrs.entity;

import lombok.Getter;

@Getter
public enum WalkingRecordWeatherState {
    SUNNY("맑음"),
    CLOUDY("흐림"),
    RAINY("비"),
    SNOWY("눈");

    private final String koreanLabel;

    WalkingRecordWeatherState(String koreanLabel) {
        this.koreanLabel = koreanLabel;
    }
}

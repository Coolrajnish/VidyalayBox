package com.ms.shared.api.tinyurlshortener;

import lombok.Data;

@Data
public class TinyUrlShortenerResponseDTO {

    private TinyUrlData data;
    private int code;
    private String[] errors;
}

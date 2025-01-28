package com.ms.shared.api.tinyurlshortener;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
@AllArgsConstructor
@Data
public class TinyUrlData {
    private String domain;
    private String alias;
    private boolean deleted;
    private boolean archived;
    private TinyUrlAnalytics analytics;
    private String[] tags;
    private OffsetDateTime createdAt;
    private OffsetDateTime expiresAt;
    private String tiny_url;
    private String url;

}

package com.ms.shared.api.WhatsappMessage;

import lombok.Data;

@Data
public class WhatsappMessageDTO {
    private String messagingProduct;
    private String to;
    private String type;
    private TextDTO text;
}

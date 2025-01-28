package com.ms.shared.api.WhatsappMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class WhatsappMessageRequestDTO {

    private String messaging_product;
    private String to;
    private String type;
    private Template template;


    @Data
    public static class Template {
        private String name;
        private List<Component> components;
        private Language language;
    }


    @Data
    public static class Component {
        private String type;
        private List<ComponentParameter> parameters;
    }

    @Data
    @AllArgsConstructor
    public static class ComponentParameter {
        private String type;
        private String text;

        
    }

    @Data
    public static class Language {
        private String code;

    }

}

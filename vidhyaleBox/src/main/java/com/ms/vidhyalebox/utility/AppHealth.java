package com.ms.vidhyalebox.utility;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppHealth {

    String appName;
    String appVersion;
    String healthCheckAt;
}

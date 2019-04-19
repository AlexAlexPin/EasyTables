package com.pinin.alex.style;

import lombok.*;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class EtMargin {
    private float left;
    private float top;
    private float right;
    private float bottom;
}

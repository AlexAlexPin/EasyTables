package com.pinin.alex.style;

import lombok.*;

@Getter
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class EtRectangle {
    private float width;
    private float height;
}

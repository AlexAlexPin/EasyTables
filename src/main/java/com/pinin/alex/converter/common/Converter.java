package com.pinin.alex.converter.common;

import com.pinin.alex.element.EtTable;
import lombok.NonNull;

import java.io.OutputStream;
import java.util.List;

public interface Converter {

    void convert(@NonNull List<EtTable> etTables, @NonNull OutputStream out);
}

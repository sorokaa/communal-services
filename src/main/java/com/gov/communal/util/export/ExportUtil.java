package com.gov.communal.util.export;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExportUtil {

    public Font getFont(String name, int size) {
        return FontFactory.getFont(name, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size);
    }
}

package org.jabref.logic.formatter.bibtexfields;

import java.util.Map;
import java.util.Objects;

import org.jabref.logic.cleanup.Formatter;
import org.jabref.logic.l10n.Localization;
import org.jabref.logic.layout.LayoutFormatter;
import org.jabref.logic.util.strings.HTMLUnicodeConversionMaps;

/**
 * This formatter converts LaTeX character sequences their equivalent unicode characters,
 * and removes other LaTeX commands without handling them.
 *
 * The inverse operation is {@link UnicodeToLatexFormatter}.
 */
public class LatexToUnicodeFormatter extends Formatter implements LayoutFormatter {

    @Override
    public String getName() {
        return Localization.lang("LaTeX to Unicode");
    }

    @Override
    public String getKey() {
        return "latex_to_unicode";
    }

    @Override
    public String format(String text) {
        String result = Objects.requireNonNull(text);
        if (result.isEmpty()) {
            return result;
        }

        // Standard symbols
        for (Map.Entry<Character, String> unicodeLatexPair : HTMLUnicodeConversionMaps.UNICODE_LATEX_CONVERSION_MAP
                .entrySet()) {
            result = result.replace(unicodeLatexPair.getValue(), unicodeLatexPair.getKey().toString());
        }

        return result;
    }

    @Override
    public String getDescription() {
        return Localization.lang("Converts LaTeX encoding to Unicode characters.");
    }

    @Override
    public String getExampleInput() {
        return "M{\\\"{o}}nch";
    }
}

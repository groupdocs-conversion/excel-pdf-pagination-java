// Topic: Compare Excel-to-PDF pagination with GroupDocs.Conversion 26.10.
package com.groupdocs.demo;

import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.licensing.License;
import com.groupdocs.conversion.options.load.SpreadsheetLoadOptions;
import com.groupdocs.conversion.options.convert.PdfConvertOptions;
import java.nio.file.*;

public class ExcelPaginationDemo {
    public static void main(String[] args) throws Exception {
        applyLicense();
        Files.createDirectories(Paths.get("Result"));
        convertWithDefaults();
        convertAllColumnsTogether();
        convertWithoutEmptyRowsAndColumns();
        convertOnePagePerSheet();
        System.out.println("PASS: four pagination variants produced in Result/.");
    }

    private static void applyLicense() {
        String licensePath = "REPLACE_WITH_YOUR_LICENSE_PATH";
        if (Files.isRegularFile(Paths.get(licensePath))) {
            new License().setLicense(licensePath);
            System.out.println("License applied.");
        } else {
            System.out.println("Evaluation mode: output limits apply.");
        }
    }

    /**
     * Converts the sparse Excel report with default pagination.
     *
     * @remarks In 26.10, all-columns and skip-empty flags default to false. The
     * source uses A4 portrait pages and one-inch margins. This baseline preserves
     * empty spacing and allows horizontal pagination. Input: documents/report.xlsx.
     * Output: Result/01-defaults.pdf.
     */
    public static void convertWithDefaults() {
        SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
        try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
            converter.convert("Result/01-defaults.pdf", new PdfConvertOptions());
        }
    }

    /**
     * Keeps all report columns together on each output page.
     *
     * @remarks Uses SpreadsheetLoadOptions.setAllColumnsInOnePagePerSheet(true). This
     * can widen the physical PDF page rather than scale the report to A4. Inspect the
     * page dimensions before printing or attaching the result. Input:
     * documents/report.xlsx. Output: Result/02-all-columns.pdf.
     */
    public static void convertAllColumnsTogether() {
        SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
        options.setAllColumnsInOnePagePerSheet(true);
        try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
            converter.convert("Result/02-all-columns.pdf", new PdfConvertOptions());
        }
    }

    /**
     * Removes empty rows and columns from the converted report.
     *
     * @remarks Uses SpreadsheetLoadOptions.setSkipEmptyRowsAndColumns(true). Empty
     * spacing in the fixture is intentional, so removal changes the layout. Use only
     * when that spacing carries no business meaning. Input: documents/report.xlsx.
     * Output: Result/03-skip-empty.pdf.
     */
    public static void convertWithoutEmptyRowsAndColumns() {
        SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
        options.setSkipEmptyRowsAndColumns(true);
        try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
            converter.convert("Result/03-skip-empty.pdf", new PdfConvertOptions());
        }
    }

    /**
     * Places the entire report sheet on one output page.
     *
     * @remarks Uses SpreadsheetLoadOptions.setOnePagePerSheet(true). Both width and
     * height may become nonstandard. This is useful for screen review when a fixed
     * paper size is unnecessary; it does not promise an A4 print layout. Input:
     * documents/report.xlsx. Output: Result/04-one-page.pdf.
     */
    public static void convertOnePagePerSheet() {
        SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
        options.setOnePagePerSheet(true);
        try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
            converter.convert("Result/04-one-page.pdf", new PdfConvertOptions());
        }
    }
}

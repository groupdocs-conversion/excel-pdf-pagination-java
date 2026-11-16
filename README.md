# Compare Excel-to-PDF Pagination Options in Java

excel-pdf-pagination-java is a runnable Java sample that exports one Excel report with four pagination settings and compares the resulting PDF layouts.

[![Docs](https://img.shields.io/badge/Docs-2865E0?style=for-the-badge)](https://docs.groupdocs.com/conversion/java/)
[![API Reference](https://img.shields.io/badge/API%20Reference-2865E0?style=for-the-badge)](https://reference.groupdocs.com/conversion/java/)
[![Blog](https://img.shields.io/badge/Blog-2865E0?style=for-the-badge)](https://blog.groupdocs.com/categories/groupdocs.conversion-product-family/)
[![Free Support](https://img.shields.io/badge/Free%20Support-2865E0?style=for-the-badge)](https://forum.groupdocs.com/c/conversion/)
[![Temporary License](https://img.shields.io/badge/Temporary%20License-2865E0?style=for-the-badge)](https://purchase.groupdocs.com/temp-license/100050)

## Overview

The four methods use the same synthetic workbook and a new Converter for
each variant. The output files separate horizontal page grouping, removal
of empty rows and columns, and one-page-per-sheet behavior. The comparison
is about layout; no speed or memory benchmark is claimed.

## Technology Stack

JDK 11, Maven and GroupDocs.Conversion for Java 26.10. No spreadsheet editor
is invoked by the Java example. Source and target paths are relative to the
project directory.

## Problem Statement

A wide sheet can split columns across PDF pages. Asking for one page can
change physical page dimensions, while compacting empty cells can remove
intentional spacing. These choices need separate tests on the same input.

## Solution Overview

SpreadsheetLoadOptions configures loading and grouping. PdfConvertOptions
selects PDF output. Each method changes one layout policy and leaves the
other options at their defaults.

## Prerequisites

Use JDK 11 or 17 and Maven. The dependency is pinned to 26.10. A suitable
license is required to reproduce every output without evaluation limits.

## Getting Started

### Installation

Run from the directory containing pom.xml:

```sh
mvn compile exec:java
```

### Configuration

The source contains REPLACE_WITH_YOUR_LICENSE_PATH. Set it only in your
local copy; do not commit your real path or license. The report.xlsx input
is included under documents/. Result/ is created automatically.

## Repository Structure

pom.xml declares the dependency and entry point. RUN.md explains execution.
ExcelPaginationDemo.java is under src/main/java/com/groupdocs/demo/.
documents/report.xlsx contains one sheet with deliberate gaps. Runtime PDFs
are written under Result/ and excluded from version control.

## Which PDF should I choose for printing?

Start by checking the physical page dimensions required by your printer or
downstream system. In this fixture, the default and skip-empty outputs use
A4 pages, while the all-columns and one-page-per-sheet variants produce
custom sizes. Choose a method after inspecting every output page. A smaller
page count alone does not establish that the file meets your printing requirements.

## Code Implementation

### convertWithDefaults

```java
SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
    converter.convert("Result/01-defaults.pdf", new PdfConvertOptions());
}
```

### convertAllColumnsTogether

```java
SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
options.setAllColumnsInOnePagePerSheet(true);
try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
    converter.convert("Result/02-all-columns.pdf", new PdfConvertOptions());
}
```

### convertWithoutEmptyRowsAndColumns

```java
SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
options.setSkipEmptyRowsAndColumns(true);
try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
    converter.convert("Result/03-skip-empty.pdf", new PdfConvertOptions());
}
```

### convertOnePagePerSheet

```java
SpreadsheetLoadOptions options = new SpreadsheetLoadOptions();
options.setOnePagePerSheet(true);
try (Converter converter = new Converter("documents/report.xlsx", () -> options)) {
    converter.convert("Result/04-one-page.pdf", new PdfConvertOptions());
}
```

## Best Practices

| Output | Pages | Physical size in points |
|---|---:|---|
| 01-defaults.pdf | 3 | 595.44 x 841.68 |
| 02-all-columns.pdf | 1 | 1138.32 x 841.68 |
| 03-skip-empty.pdf | 2 | 595.44 x 841.68 |
| 04-one-page.pdf | 1 | 1138.32 x 638.237 |

These measurements apply to the included report.xlsx on Java 11.0.2.
Inspect an actual business workbook before choosing a policy. Empty rows
can carry layout meaning. Explicit PDF margin overrides are outside this
sample's claims because a separate 26.10 check found no visible effect.

## Related Resources

- [Scenario guide](https://docs.groupdocs.com/conversion/java/use-cases/excel-pdf-pagination/)
- [Companion article](https://blog.groupdocs.com/conversion/excel-pdf-pagination-java/)
- [GroupDocs.Conversion Java documentation](https://docs.groupdocs.com/conversion/java/)
- [Java API reference](https://reference.groupdocs.com/conversion/java/)

The scenario guide and article links identify companion drafts. They become
available after publication and the corresponding site deployments.

## Keywords

`Excel to PDF Java`, `XLSX PDF pagination`, `all columns one page Java`, `one page per sheet PDF`, `skip empty rows columns Java`, `spreadsheet PDF page size`, `GroupDocs.Conversion Java`, `Excel report export`, `A4 PDF pages`, `SpreadsheetLoadOptions`, `PdfConvertOptions`, `Java Maven sample`, `PDF horizontal pagination`, `Excel blank rows`, `Java 26.10`

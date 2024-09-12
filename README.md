# PDF Printer Tool

## Project Overview

The PDF Printer Tool is a simple Java application that allows you to print PDF files from the console on various printers. The application supports listing all available printers as well as selecting a specific printer to print one or multiple PDF files.

### Features

- **List all available printers:** Use the `-l` parameter to list all printers available on the system.
- **Print PDF files:** The application allows printing PDF files on a specified printer.
- **Print all PDFs from a directory:** You can print all PDF files in a specified directory.
- **Customize print job name:** The name of the print job can be customized before printing (e.g., based on the file name).

### Libraries Used

- **Apache PDFBox:** For processing and printing PDF files.

### Maven Dependency for PDFBox

If you are using Maven, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox</artifactId>
    <version>2.0.29</version>
</dependency>
```

## Requirements

- Java 8 or higher
- Apache PDFBox (if you want to include it manually)

## Usage

### 1. Display List of All Printers

To display a list of all printers available on your system, use the `-l` parameter:

```bash
java -jar PDFPrinterTool.jar -l
```

### 2. Print PDF Files

To print one or more PDF files from a directory, use the command:

```bash
java -jar PDFPrinterTool.jar -p "path/to/directory" -printer "printer_name"
```

- `-p`: The path to the directory containing the PDF files to be printed.
- `-printer`: The name of the printer to use.

### 3. Customize Print Job Name

The print job name is automatically set based on the PDF file name. You can customize this by modifying the code accordingly.

### Example

```bash
java -jar PDFPrinterTool.jar -p "C:/Documents/PDFs" -printer "HP_LaserJet"
```

This example prints all PDF files from the directory `C:/Documents/PDFs` on the `HP_LaserJet` printer.

## Code Overview

The application consists of the following components:

- **ParameterParserHelper.java:** Contains the logic for parsing and processing command line parameters.
- **PdfPrinterHelper.java:** Provides methods for printing PDF files.
- **PDFPrinterTool.java:** The main class of the application, coordinating the functionality for processing parameters and executing print jobs.

## Possible Extensions

- Support for specific print settings such as duplex printing or color mode.
- Error handling for missing printers or not found PDF files.
- Integration of a GUI frontend for users who prefer a graphical interface.

## License

This project is licensed under the MIT License. For more details, see the `LICENSE` file.
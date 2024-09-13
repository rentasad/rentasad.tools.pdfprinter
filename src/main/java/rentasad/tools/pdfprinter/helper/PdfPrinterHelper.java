package rentasad.tools.pdfprinter.helper;

import lombok.extern.java.Log;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

@Log
public class PdfPrinterHelper
{

	/**
	 * Prints a PDF file to a specified printer.
	 *
	 * @param pdfFilePath  the path of the PDF file to print
	 * @param printerName  the name of the printer to use
	 * @throws IOException        if there is an error reading the PDF file
	 * @throws PrinterException  if there is an error printing the PDF file
	 */
	public static void printPdfFile(final String pdfFilePath, final String printerName) throws IOException, PrinterException
	{
		File pdfFile = new File(pdfFilePath);
		PrintService printService = findPrintService(printerName);
		printPDF(pdfFile, printService);
	}

	/**
	 * Prints all PDF files in the specified directory using the specified printer.
	 *
	 * @param directory    the path of the directory containing PDF files to print
	 * @param printerName  the name of the printer to use for printing
	 */
	public static void printAllPdfFilesInDirectory(final String directory, final String printerName)
	{
		log.info("Hinterlegter Drucker: " + printerName);
		File directoryFile = new File(directory);
		PrintService printService = findPrintService(printerName);
		if (printService == null)
		{
			printService = PrintServiceLookup.lookupDefaultPrintService();
			log.info("Standard-Drucker gefunden: " + printService.getName());
		}
		File[] files = directoryFile.listFiles((dir, name) -> name.toLowerCase()
															  .endsWith(".pdf"));
		if (files == null || files.length == 0)
		{
			log.info("Keine PDF-Dateien im Verzeichnis gefunden.");
		}else
		{
			for (File file : files)
			{
				try
				{
					printPDF(file, printService);
				} catch (IOException | PrinterException e)
				{
					 log.severe("Fehler beim Drucken der Datei: " + file.getName());
					log.log(java.util.logging.Level.SEVERE, e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Finds the {@link PrintService} with the given printer name.
	 *
	 * @param printerName the name of the printer to find
	 * @return the {@link PrintService} with the given printer name, or {@code null} if no matching printer is found
	 */
	private static PrintService findPrintService(String printerName) {
		// Alle verfügbaren Druckdienste abrufen
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

		// Durch die Liste der verfügbaren Drucker iterieren
		for (PrintService printService : printServices) {
			if (printService.getName().equalsIgnoreCase(printerName)) {
				return printService;  // Passender Drucker gefunden
			}
		}

		// Wenn kein Drucker gefunden wurde, gib null zurück
		return null;
	}

	/**
	 * Prints a PDF file to the specified printer.
	 *
	 * @param pdfFile       the PDF file to print
	 * @param printService  the printer to use for printing
	 * @throws IOException        if there is an error reading the PDF file
	 * @throws PrinterException  if there is an error printing the PDF file
	 */
	private static void printPDF(File pdfFile, PrintService printService) throws IOException, PrinterException
	{
		System.out.println("Drucke: " + pdfFile.getName());

		// PDF-Dokument laden
		try (PDDocument document = PDDocument.load(pdfFile))
		{
			// Druckauftrag erstellen
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPageable(new PDFPageable(document));

			// Standarddrucker festlegen
			job.setPrintService(printService);
			// Erstelle ein Attribut-Set, um den Druckauftrag zu benennen
			PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
			attr.add(new JobName(pdfFile.getName(), null));  // JobName auf den Dateinamen setzen
			// Druckauftrag starten
			job.print(attr);
		}
	}

	/**
	 * Retrieves the name of the default printer.
	 *
	 * @return The name of the default printer as a String. If no default printer is found, null is returned.
	 */
	public static String getDefaultPrinter()
	{
		PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		if (defaultPrintService == null)
		{
			System.out.println("Kein Standarddrucker gefunden.");
			return null;
		}
		return defaultPrintService.getName();
	}

	/**
	 * This method lists all available printers in the system.
	 * It retrieves an array of all print services using the PrintServiceLookup.lookupPrintServices method.
	 * If no printers are found, it prints "Keine Drucker verfügbar." to the console and returns.
	 * Otherwise, it prints the names of all available printers to the console.
	 *
	 * @see PrintServiceLookup#lookupPrintServices(javax.print.DocFlavor, javax.print.attribute.AttributeSet)
	 * @see PrintService#getName()
	 */
	public static void listAllPrinters() {
		// Alle verfügbaren PrintServices (Drucker) abrufen
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

		// Überprüfen, ob Drucker verfügbar sind
		if (printServices.length == 0) {
			System.out.println("Keine Drucker verfügbar.");
			return;
		}

		// Alle Druckernamen auflisten
		System.out.println("Verfügbare Drucker:");
		for (PrintService printService : printServices) {
			System.out.println(" - " + printService.getName());
		}
	}

}

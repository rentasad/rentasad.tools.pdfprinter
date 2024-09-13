package rentasad.tools.pdfprinter;
// STARTCLASS: rentasad.tools.pdfprinter.PDFPrinterTool
import rentasad.tools.pdfprinter.helper.ParameterParserHelper;
import rentasad.tools.pdfprinter.helper.PdfPrinterHelper;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.Map;

/**
 * The PDFPrinterTool class is a utility tool for printing PDF files.
 */
public class PDFPrinterTool
{

	/**
	 * The main method of the PDFPrinterTool class.
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{

		Map<String, String> argumentMap = ParameterParserHelper.parseModeStringValueFromArgs(args, "PDFPrinterTool");

		String printerName = argumentMap.get(ParameterParserHelper.PRINTER_OPTION);

		if (argumentMap.containsKey(ParameterParserHelper.DIRECTORY_OPTION))
		{
			String directoryPath = argumentMap.get(ParameterParserHelper.DIRECTORY_OPTION);
			PdfPrinterHelper.printAllPdfFilesInDirectory(directoryPath, printerName);
		}
		if (argumentMap.containsKey(ParameterParserHelper.FILE_OPTION))
		{
			String filePath = argumentMap.get(ParameterParserHelper.FILE_OPTION);
			try
			{
				PdfPrinterHelper.printPdfFile(filePath, printerName);
			} catch (IOException e)
			{
				System.out.println("Fehler beim Drucken der Datei: " + filePath);
				throw new RuntimeException(e);
			} catch (PrinterException e)
			{
				throw new RuntimeException(e);
			}
		}

		if (argumentMap.containsKey(ParameterParserHelper.LIST_OPTION))
		{
			PdfPrinterHelper.listAllPrinters();
		}
	}


}


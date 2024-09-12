package rentasad.tools.pdfprinter.helper;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for parsing command line arguments.
 */
public class ParameterParserHelper
{

	public static final String PRINTER_OPTION = "printer";
	public static final String DIRECTORY_OPTION = "directory";
	public static final String FILE_OPTION = "file";
	public static final String LIST_OPTION = "list";

	/**
	 * Parses the mode parameter value from the command line arguments.
	 *
	 * @param args        the command line arguments
	 * @param utilityName the name of the utility
	 * @return the mode parameter value as a string
	 */
	public static Map<String, String> parseModeStringValueFromArgs(final String[] args, final String utilityName)
	{
		Options options = getOptions();

		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		Map<String, String> runArgumentsValueMap = new HashMap<>();

		CommandLine cmd;

		try
		{
			cmd = parser.parse(options, args);

			if (cmd.hasOption("l"))
			{
				runArgumentsValueMap.put("list", "true");
			}
			if (cmd.hasOption("d"))
			{
				runArgumentsValueMap.put("directory", cmd.getOptionValue("d"));
			}
			if (cmd.hasOption("f"))
			{
				runArgumentsValueMap.put("file", cmd.getOptionValue("f"));
			}
			if (cmd.hasOption("p"))
			{
				runArgumentsValueMap.put("printer", cmd.getOptionValue("p"));
			}

		} catch (ParseException e)
		{
			System.out.println(e.getMessage());
			formatter.printHelp(utilityName, options);
			System.exit(1);
		}
		return runArgumentsValueMap;
	}

	/**
	 * Retrieves the command line options for the application.
	 *
	 * @return the command line options as an Options object
	 */
	private static Options getOptions()
	{
		Options options = new Options();
		Option printerOption = new Option("p", "printer", true, "choose printer");
		Option listOption = new Option("l", "list", false, "list avaible printers");
		Option directoryOption = new Option("d", "dir", true, "directory with pdfs to print");
		Option fileOption = new Option("f", "file", true, "file with pdfs to print");
		printerOption.setRequired(false);
		directoryOption.setRequired(false);
		fileOption.setRequired(false);
		listOption.setRequired(false);
		options.addOption(printerOption);
		options.addOption(directoryOption);
		options.addOption(fileOption);
		options.addOption(listOption);
		return options;
	}

	/**
	 * Private method to get the command line from the parser and options.
	 *
	 * @param parser  the command line parser
	 * @param options the command line options
	 * @param args    the command line arguments
	 * @return the parsed command line
	 * @throws ParseException if there is an error parsing the command line
	 */
	private static CommandLine getCommandLine(CommandLineParser parser, Options options, final String[] args) throws ParseException
	{
		return parser.parse(options, args);
	}

}

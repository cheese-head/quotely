/*
 * This source file was generated by the Gradle 'init' task
 */
package org.quotely;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "quotely", mixinStandardHelpOptions = true, version = "0.0.1",
         description = "")
class App implements Callable<Integer> {

    @Option(names = {"-l", "--language"}, description = "English, Russian (defaults to English)")
    private String language = "english";

    // @Option(names = {"-o", "--output"}, description = "Output to write to (defaults to stdout)")
    // private File output;

    @Override
    public Integer call() throws Exception {
        Quote quote;
        switch (language.toLowerCase()) {
            case "english": 
                quote = Quote.NewQuote("en");
                System.out.println(quote.toString());
                break;
            case "russian": 
                quote = Quote.NewQuote("ru");
                System.out.println(quote.toString());
                break;
            default:
                break;
        }
        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
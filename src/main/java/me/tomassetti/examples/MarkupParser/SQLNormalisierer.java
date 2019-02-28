package me.tomassetti.examples.MarkupParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class SQLNormalisierer {

    private String current_sqlstatement;
    private String original_sqlstatement;
    private MarkupParser markupParser;

    public SQLNormalisierer(String current_sqlstatement, String original_sqlstatement) {
        this.current_sqlstatement = current_sqlstatement;
        this.original_sqlstatement = original_sqlstatement;

        //Baum erstellen
        ANTLRInputStream inputStream = new ANTLRInputStream(current_sqlstatement);
        MarkupLexer markupLexer = new MarkupLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(markupLexer);
        markupParser = new MarkupParser(commonTokenStream);


        /*
        MarkupParser.FileContext fileContext = markupParser.file();     //Baum wird gebildet
        MarkupVisitor visitor = new MarkupVisitor(System.out);
        visitor.visit(fileContext);                                     //Baum wird durch visitor ausgegebe
         */

    }

    public MarkupParser getMarkupParser() {
        return markupParser;
    }

    public void setMarkupParser(MarkupParser markupParser) {
        this.markupParser = markupParser;
    }

    public String getOriginal_sqlstatement() {
        return original_sqlstatement;
    }

    public void setOriginal_sqlstatement(String original_sqlstatement) {
        this.original_sqlstatement = original_sqlstatement;
    }

    public String getCurrent_sqlstatement() {
        return current_sqlstatement;
    }

    public void setCurrent_sqlstatement(String current_sqlstatement) {
        this.current_sqlstatement = current_sqlstatement;
    }

    public SQLNormalisierer normaliseComment(){
        String newStatement = this.getCurrent_sqlstatement();

        /*
        * - Visits through the tree for comments and removes all Comments
        * newStatement = ???
        * */

        return new SQLNormalisierer(newStatement, this.getOriginal_sqlstatement());
    }

    public SQLNormalisierer normaliseSemicolon(){
        String newStatement = this.getCurrent_sqlstatement();

        /*
         * - Examines the statement with the tree and inserts a semicolon, if a semicolon is missing at the end of a statement
         * newStatement = ???
         * */

        return new SQLNormalisierer(newStatement, this.getOriginal_sqlstatement());
    }

    public SQLNormalisierer normaliseAlias(){
        String newStatement = this.getCurrent_sqlstatement();

        /*
         - Visits all tables in the FROM-Clause and replaces all alliases with a self-created name
            -> An AS-Keyword is added, if not already existing
            -> The name is derived from the tablename and is in all lowercase
            -> Additionaly the new name also includes a number, which gets increased for every time the same table is used
                -> Person	=> person1 | person2 | person3 ...
            -> A List saves the new and old aliases
        - Aftwerwards it replaces all usages of the old alias with the new alias
         */

        return new SQLNormalisierer(newStatement, this.getOriginal_sqlstatement());
    }



}

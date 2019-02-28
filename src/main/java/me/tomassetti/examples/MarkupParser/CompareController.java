package me.tomassetti.examples.MarkupParser;

public class CompareController {

    public CompareController() {
    }

    public static void compareTwoStatements(){
        SQLNormalisierer sn1 = null;
        SQLNormalisierer sn2 = null;

        //Liest die Statements ein
        //sn1 = getStatement(1);
        //sn2 = getStatement(2);

        //Normalisiert die Statements
        sn1 = normaliseStatement(sn1);
        sn2 = normaliseStatement(sn2);

        //vergleicht die Statements
        if(compareNormalisedStatements(sn1, sn2)){
            System.out.println("Statements sind semantisch identisch.");
        } else {
            System.out.println("Statements sind nicht semantisch identisch.");
        }

    }

    public static SQLNormalisierer normaliseStatement(SQLNormalisierer sn){


        try {
            sn = sn.normaliseComment();
        } catch (Exception e){
            System.out.println("===== ERROR BEI NormComment =====");
            System.out.println(e);
        }

        try {
            sn = sn.normaliseSemicolon();
        } catch (Exception e){
            System.out.println("===== ERROR BEI NormSemicolon =====");
            System.out.println(e);
        }

        try {
            sn = sn.normaliseAlias();
        } catch (Exception e){
            System.out.println("===== ERROR BEI NormAlias =====");
            System.out.println(e);
        }




        return sn;
    }


    public static boolean compareNormalisedStatements(SQLNormalisierer sn1, SQLNormalisierer sn2){
        //compare
        return true;
    }

}


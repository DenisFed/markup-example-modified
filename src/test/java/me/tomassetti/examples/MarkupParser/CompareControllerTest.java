package me.tomassetti.examples.MarkupParser;

import junit.framework.TestCase;

public class CompareControllerTest extends TestCase {

    public void testStatement1() {
        String st = "SELECT *\t--gibt alles aus\n" +
                "\tFROM Person";

        String n1 = "SELECT *\n" +
                "\tFROM Person";
        String n2 = "SELECT *\n" +
                "\tFROM Person";
        String n3 = "SELECT *\n" +
                "\tFROM Person";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet 1.Norm

    public void testStatement2() {
        String st = "SELECT *\n" +
                "\tFROM PERSON;";

        String n1 = "SELECT *\n" +
                "\tFROM PERSON;";
        String n2 = "SELECT *\n" +
                "\tFROM PERSON";
        String n3 = "SELECT *\n" +
                "\tFROM PERSON";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet 2.Norm

    public void testStatement3() {
        String st = "SELECT *\n" +
                "\tFROM Person AS pers";

        String n1 = "SELECT *\n" +
                "\tFROM Person AS pers";
        String n2 = "SELECT *\n" +
                "\tFROM Person AS pers";
        String n3 = "SELECT *\n" +
                "\tFROM Person  table1";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet 3.Norm

    public void testStatement4() {
        String st = "SELECT *\n" +
                "\tFROM Person";

        String n1 = "SELECT *\n" +
                "\tFROM Person";
        String n2 = "SELECT *\n" +
                "\tFROM Person";
        String n3 = "SELECT *\n" +
                "\tFROM Person";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet wenn schon in Norm

    public void testStatement5() {
        String st = "SELECT *\n" +
                "\tFROM PersonTable AS Person --Kunde\n" +
                "\tWHERE Person.geburt = 20 /*Geburtsjahr*/ AND Person.name = 'Peter';";

        String n1 = "SELECT *\n" +
                "\tFROM PersonTable AS Person \n" +
                "\tWHERE Person.geburt = 20  AND Person.name = 'Peter';";
        String n2 = "SELECT *\n" +
                "\tFROM PersonTable AS Person \n" +
                "\tWHERE Person.geburt = 20  AND Person.name = 'Peter'";
        String n3 = "SELECT *\n" +
                "\tFROM PersonTable  table1 \n" +
                "\tWHERE table1.geburt = 20  AND table1.name = 'Peter'";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet alle Norm gleichzeitig

    public void testStatement6() {
        String st = "--The following SQL statement matches customers that are from the same city:\n" +
                "\tSELECT A.CustomerName AS CustomerName1, B.CustomerName AS CustomerName2, A.City\n" +
                "\tFROM Customers A, Customers B\n" +
                "\tWHERE A.CustomerID <> B.CustomerID\n" +
                "\tAND A.City = B.City\n" +
                "\tORDER BY A.City;";

        String n1 = "SELECT A.CustomerName AS CustomerName1, B.CustomerName AS CustomerName2, A.City\n" +
                "\tFROM Customers A, Customers B\n" +
                "\tWHERE A.CustomerID <> B.CustomerID\n" +
                "\tAND A.City = B.City\n" +
                "\tORDER BY A.City;";
        String n2 = "SELECT A.CustomerName AS CustomerName1, B.CustomerName AS CustomerName2, A.City\n" +
                "\tFROM Customers A, Customers B\n" +
                "\tWHERE A.CustomerID <> B.CustomerID\n" +
                "\tAND A.City = B.City\n" +
                "\tORDER BY A.City";
        String n3 = "SELECT table1.CustomerName AS CustomerName1, table2.CustomerName AS CustomerName2, table1.City\n" +
                "\tFROM Customers table1, Customers table2\n" +
                "\tWHERE table1.CustomerID <> table2.CustomerID\n" +
                "\tAND table1.City = table2.City\n" +
                "\tORDER BY table1.City";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet alle Norm gleichzeitig

    public void testStatement7() {
        String st = "DELETE\n" +
                "\tFROM Customers c, Orders o \n" +
                "\tWHERE c.CustomerID = o.CustomerID";

        String n1 = "DELETE\n" +
                "\tFROM Customers c, Orders o \n" +
                "\tWHERE c.CustomerID = o.CustomerID";
        String n2 = "DELETE\n" +
                "\tFROM Customers c, Orders o \n" +
                "\tWHERE c.CustomerID = o.CustomerID";
        String n3 = "DELETE Customers\n" +
                "\tFROM Customers  table1, Orders  table2\n" +
                "\tWHERE table1.CustomerID = table2.CustomerID";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet DELETE

    public void testStatement8() {
        String st = "UPDATE c\n" +
                "\tFROM Customers c, Orders o, Employees e \n" +
                "\tSET CustomerName = e.FirstName\n" +
                "\tWHERE c.CustomerID = o.CustomerID AND o.EmplyeeID = e.EmplyeeID;";

        String n1 = "UPDATE c\n" +
                "\tFROM Customers c, Orders o, Employees e \n" +
                "\tSET CustomerName = e.FirstName\n" +
                "\tWHERE c.CustomerID = o.CustomerID AND o.EmplyeeID = e.EmplyeeID;";
        String n2 = "UPDATE c\n" +
                "\tFROM Customers c, Orders o, Employees e \n" +
                "\tSET CustomerName = e.FirstName\n" +
                "\tWHERE c.CustomerID = o.CustomerID AND o.EmplyeeID = e.EmplyeeID";
        String n3 = "UPDATE table1\n" +
                "\tFROM Customers  table1, Orders  table2, Employees  table3\n" +
                "\tSET CustomerName = table3.FirstName\n" +
                "\tWHERE table1.CustomerID = table2.CustomerID AND table2.EmplyeeID = table3.EmplyeeID";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet UPDATE

    public void testStatement9() {
        String st = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) /* Alle Parameter */\n" +
                "\tVALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway')";

        String n1 = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)\n" +
                "\tVALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway')";
        String n2 = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)\n" +
                "\tVALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway')";
        String n3 = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)\n" +
                "\tVALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway')";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet INSERT INTO VALUES

    public void testStatement10() {
        String st = "SELECT a.name \n" +
                "\tFROM t1 AS a JOIN(SELECT a.name FROM t1 AS a) AS sub ON sub.name = a.name";

        String n1 = "SELECT a.name \n" +
                "\tFROM t1 AS a JOIN(SELECT a.name FROM t1 AS a) AS sub ON sub.name = a.name";
        String n2 = "SELECT a.name \n" +
                "\tFROM t1 AS a JOIN(SELECT a.name FROM t1 AS a) AS sub ON sub.name = a.name";
        String n3 = "SELECT table1.name \n" +
                "\tFROM t1  table1 JOIN(SELECT table2.name FROM t1  table2)  table3 ON table3.name = table1.name";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet Subselect

    public void testStatement11() {
        String st = "SELECT a.name FROM t1 AS a\n" +
                "\tJOIN(SELECT a.name FROM AS a) AS sub ON sub.name = a.name";

        String n1 = "SELECT a.name FROM t1 AS a\n" +
                "\tJOIN(SELECT a.name FROM AS a) AS sub ON sub.name = a.name";
        String n2 = "SELECT a.name FROM t1 AS a\n" +
                "\tJOIN(SELECT a.name FROM AS a) AS sub ON sub.name = a.name";
        String n3 = "SELECT table1.CustomerName AS CustomerName1, table3.CustomerName AS CustomerName2, table1.City\n" +
                "\tFROM Customers table1 JOIN (SELECT * FROM Customers table2 WHERE table2.Country = 'UK') AS table3 ON table3.City = table1.City\n" +
                "\tWHERE table1.CustomerID <> table3.CustomerID\n" +
                "\tORDER BY table1.City";
        SQLNormalisierer sn = new SQLNormalisierer(st, st);

        sn = sn.normaliseComment();
        assertEquals(sn.getCurrent_sqlstatement(), n1);
        sn = sn.normaliseSemicolon();
        assertEquals(sn.getCurrent_sqlstatement(), n2);
        sn = sn.normaliseAlias();
        assertEquals(sn.getCurrent_sqlstatement(), n3);
    }   //Testet Subselect
}
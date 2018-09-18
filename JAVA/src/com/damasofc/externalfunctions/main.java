package com.damasofc.externalfunctions;
import com.sun.deploy.pings.Pings;
import org.sqlite.Function;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;
import org.sqlite.util.*;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.sql.*;

public class main {

    public static void main(String[] args) throws Exception{
        System.out.println("Hola Mundo");
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c= DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("SQLite DB Connected");
            Statement statement = c.createStatement();
            statement.setQueryTimeout(5000);
            //FUNCION PING
//            Function.create(c, "PING", new PING());
//            statement.execute("select PING('123.198.0.20')");
//            System.out.println(statement.getResultSet().getInt(1));
            //funcion PMT
//            Function.create(c, "PMT", new PMT());
//            statement.execute("select PMT(0.065/12,12*25,275000)");
//            System.out.println(statement.getResultSet().getDouble(1));
//            funcion BIN2DEC
//            Function.create(c, "BIN2DEC", new BIN2DEC());
//            statement.execute("select BIN2DEC('10000')");
//            System.out.println(statement.getResultSet().getInt(1));
            //funcion DEC2BIN
//            Function.create(c, "DEC2BIN", new DEC2BIN());
//            statement.execute("select DEC2BIN('255')");
//            System.out.println(statement.getResultSet().getString(1));
//            funcion C2F
//            Function.create(c, "C2F", new C2F());
//            statement.execute("select C2F(1)");
//            System.out.println(statement.getResultSet().getDouble(1));
//            funcion F2C
//            Function.create(c, "F2C", new F2C());
//            statement.execute("select F2C(100)");
//            System.out.println(statement.getResultSet().getInt(1));
//                        funcion Factorial
//            Function.create(c, "Factorial", new Factorial());
//            statement.execute("select Factorial(6)");
//            System.out.println(statement.getResultSet().getLong(1));
            //funcion HEX2DEC
//            Function.create(c, "HEX2DEC", new HEX2DEC());
//            statement.execute("select HEX2DEC('aabbccdd')");
//            System.out.println(statement.getResultSet().getLong(1));
            //funcion DEC2HEX
//            Function.create(c, "DEC2HEX", new DEC2HEX());
//            statement.execute("select DEC2HEX(15)");
//            System.out.println(statement.getResultSet().getString(1));
            //funcion COMPARESTRING
//            Function.create(c, "COMPARESTRING", new COMPARESTRING());
//            statement.execute("select COMPARESTRING('App','Apaaap')");
//            System.out.println(statement.getResultSet().getInt(1));
            //funcion TRIM
//            Function.create(c, "TRIM", new TRIM());
//            statement.execute("select TRIM('aahola como te llamasaa','aa')");
//            System.out.println(statement.getResultSet().getString(1));
            //funcion REPEAT
            Function.create(c, "REPEAT", new REPEAT());
            statement.execute("select REPEAT('ABC',3)");
            System.out.println(statement.getResultSet().getString(1));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

class PING extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            String ip = value_text(0);
            InetAddress ping = InetAddress.getByName(ip);
            if(ping.isReachable(5000))
                result(1);
            else
                result(0);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class PMT extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            double tazaInteres = value_double(0);
            int numPeriodos = value_int(1);
            double valPrestamo = value_double(2);
            double res = valPrestamo* (Math.pow(1+tazaInteres,numPeriodos)*tazaInteres/(Math.pow(1+tazaInteres,numPeriodos)-1));
            result(res);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class BIN2DEC extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            String bin = value_text(0);
            int binario = Integer.parseInt(bin);
            int m = 0; // to store decimal number

            int l = 0; // to manage power of 2
            int d = 0; // to store intermediate result

            while (binario != 0){ // looping till all digits are over

                d = binario % 10; // taking the last bit of the binary number

                d *= Math.pow(2, l++); // finding 2^l

                binario /= 10; // dividing the binary number by 10 to shift one bit

                m += d; // add the result to decimal
            }
            result(m);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class DEC2BIN extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            int decimal = value_int(0);
            result(Integer.toBinaryString(decimal));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class C2F extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            double celsius = value_double(0);
            double res = (9/5)* celsius + 32;
            result(res) ;
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class F2C extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            double f = value_double(0);
            double res = ((f - 32)*5)/9;
            result(res);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class Factorial extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            int f = value_int(0);
            long res = this.factorialRecursive(f);
            result(res);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    long factorialRecursive( long n )
    {
        return n == 1 ? 1 : n * factorialRecursive( n-1 );
    }
}
class HEX2DEC extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            String hex = value_text(0);
            result(hex2Decimal(hex));
        }catch (Exception e){
            System.out.println(e);
        }
    }
    Long hex2Decimal(String s) {
        String digits = "0123456789ABCDEF";
        s = s.toUpperCase();
        Long sixteen = new Long("16");
        Long bigVal = new Long("0");
        //  int val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            Long bigD = new Long(String.valueOf(d));
            bigVal = (bigVal*16) + bigD;
//            bigVal = (bigVal.multiply(sixteen)).add(bigD);
            //  val = 16 * val + d;
        }
        // return val;
        return bigVal;
    }
}
class DEC2HEX extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            int f = value_int(0);
            result(Integer.toHexString(f));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

class COMPARESTRING extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            String s1 = value_text(0);
            String s2 = value_text(1);
            int res = s1.compareTo(s2);
            if(res < 0)
                res = -1;
            else if(res > 0)
                res = 1;
            else
                res = 0;
            result(res);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class TRIM extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            String s1 = value_text(0);
            String s2 = value_text(1);
            result(s1.replaceAll(s2 + "+$|^" + s2 + "+",""));
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class REPEAT extends Function{
    @Override
    protected void xFunc() throws SQLException {
        try {
            String s1 = value_text(0);
            int s2 = value_int(1);
            String res = "";
            for (int i = 0; i < s2; i += 1) {
                res += s1;
            }
            result(res);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
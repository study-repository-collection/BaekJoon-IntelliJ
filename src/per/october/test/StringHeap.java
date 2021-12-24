package per.october.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class StringHeap {
    public static void main(String[] args) throws IOException {
        StringBuffer a = new StringBuffer("hojong");
        StringBuffer b = new StringBuffer("hojong");
        System.out.println(a.toString().equals(b.toString()));
    }
}

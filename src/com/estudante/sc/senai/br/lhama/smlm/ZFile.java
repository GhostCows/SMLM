package com.estudante.sc.senai.br.lhama.smlm;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Marcelo Vogt on 06/08/2017.
 */
public class ZFile {

	public static String readFile(String path) throws Exception {

		InputStream is = ZFile.class.getClassLoader().getResourceAsStream(path);

		Scanner s = new Scanner(is).useDelimiter("\\A");

		return s.hasNext() ? s.next() : "";

	}

	public static void writeFile(String path, String content) throws Exception {
		PrintWriter writer = new PrintWriter(path, "UTF-8");
		writer.print(content);
		writer.close();

	}

}

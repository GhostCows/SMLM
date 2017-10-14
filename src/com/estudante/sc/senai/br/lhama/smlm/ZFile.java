package com.estudante.sc.senai.br.lhama.smlm;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Stream;

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

	public static Object readJSON(String path) {
		try {

			String json = ZFile.readFile(path);

			return JSONValue.parse(json);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		return null;
	}

	public static void readLineByLine(String path, Consumer<String> c) {

		try (Stream<String> s = Files.lines(Paths.get(path))) {
			s.forEach(c);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

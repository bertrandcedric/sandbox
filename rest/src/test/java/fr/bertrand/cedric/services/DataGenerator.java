package fr.bertrand.cedric.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.IOUtils;

public class DataGenerator {

	public static void createInsert() throws IOException {
		Random r = new Random();

		File file = new File("src/test/resources/insertTmp.txt");
		file.createNewFile();
		FileWriter output = new FileWriter(file);

		for (int i = 0; i < 100000; i++) {
			StringBuffer data = new StringBuffer();
			data.append("INSERT INTO OFFRE (CODEVILLAGE, DATEDEPART, NOMBRENUIT, CODEPAYSCOMMERCIAL, ")//
					.append("OFFRENET, CODEDEVISECOMMERCI, NOMBREPERSONNEADUL, DUREETOTALE, ")//
					.append(" CODELOGEMENTCOMMER, CODEOCCUPATION, CODEVILLEDEPARTDEM, CODEVILLEIATADEPAR, ")//
					.append(" CODEVILLEIATAARRIV, CATEGORIETARIF, CODEINFODISPOOCCUP, TYPEELARGISSEMENT,  ")//
					.append("TRANSPORTRISQUEFIN, PRIXVENTEGDS, CODETERMINALAEROPO, MONTANT,  ")//
					.append("MONTANTPROMOTION, CODEPROMOTION) ");
			data.append("VALUES ('" + i + "', '2010-03-01', 5, 'ZZZ', true, ")//
					.append("'YYY', 1, 7, 'ABCDEF', 1, 'ABCDEF', ")//
					.append("'ABCDEF', 'ABCDEF', 'ABCDEF', 1, 'ABCDEFGHIJ'")//
					.append(", true, 123.1, 'ABCDEF', 124.1, 125.1, 'ABCDEF')");
			data.append("\n");
			IOUtils.write(data, output);
			output.flush();
		}
		output.close();
	}

	public static void main(String[] args) throws IOException {
		createInsert();
	}

}

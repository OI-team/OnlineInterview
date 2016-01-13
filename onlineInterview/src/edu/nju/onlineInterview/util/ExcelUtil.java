package edu.nju.onlineInterview.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {

	public static void createExcel(String savePath, String fileName, String[] headers,
			List<Map<String, String>> contents) {
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String path = savePath + "//" + fileName;
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(new File(path));
			// default only one page
			WritableSheet ws = wwb.createSheet(fileName, 0);

			// generate table headers
			for (int i = 0; i < headers.length; i++) {
				Label label = new Label(i, 0, headers[i]);
				ws.addCell(label);
				for(int j = 0; j < contents.size(); j++){
					Label contentLabel = new Label(i, j + 1, contents.get(j).get(headers[i]).toString());
					ws.addCell(contentLabel);
				}
			}
			wwb.write();
			wwb.close();

		} catch (WriteException e) {
			System.err.println("an error occurs during add cell: " + e);
			e.printStackTrace();
		}catch (IOException e) {
			System.err.println("an error occurs during createWorkbook: " + e);
			e.printStackTrace();
		}
	}

}

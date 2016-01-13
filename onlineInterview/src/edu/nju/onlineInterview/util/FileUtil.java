package edu.nju.onlineInterview.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.Buffer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * @author margine
 * @time 2016年1月10日
 * @description contact ch_margine@163.com
 */
public class FileUtil {
	/**
	 * upload file
	 * 
	 * @param request
	 * @param newfilename
	 *            without suffix
	 * @param fileid
	 *            widget id
	 * @return
	 */
	public static File Upload(HttpServletRequest request, String filedir, String newfilename, String fileid) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile(fileid);
		if (file != null) {
			if (newfilename == null || newfilename.equals("")) {
				newfilename = file.getOriginalFilename();
			} else {
				newfilename = newfilename + "." + getExtensionName(file.getOriginalFilename());
			}
			File tmpfile = new File(filedir, newfilename);
			tmpfile.mkdirs();

			try {
				file.transferTo(tmpfile);
			} catch (Exception e) {
				e.printStackTrace();
				tmpfile.delete();
				return null;
			}

			File save = new File(filedir, newfilename);
			if (save.exists()) {
			}
			tmpfile.renameTo(save);
			return save;

		} else {
			return null;
		}

	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	public static void Upload(MultipartFile file, String url) {
		if (file == null) {
			try {
				throw new Exception("上传失败：文件为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (file.getSize() > 10000000) {
			try {
				throw new Exception("上传失败：文件大小不能超过10M");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String filename = file.getOriginalFilename();
		if (file.getSize() > 0) {
			try {
				SaveFileFromInputStream(file.getInputStream(), url, filename);
			} catch (IOException e) {
				System.out.println(e.getMessage());

			}
		}

		else {
			try {
				throw new Exception("上传失败：上传文件不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * save file
	 * 
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	public static void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, bytesum);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	// 复制文件
	public static File CopyAndPaste(String source, String target) {

		try {
			File sourceFile = new File(source);
			FileInputStream fis = new FileInputStream(sourceFile);
			File targetDir = new File(target);
			targetDir.mkdirs();
			File targetFile = new File(target, sourceFile.getName());
			targetFile.createNewFile();
			SaveFileFromInputStream(fis, target, sourceFile.getName());
			return targetFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * compress files 
	 * @param zipName required suffix
	 * @param fileDirName
	 */
	public static void ZipFile(String zipName, String fileDirName) {
		String zipPath = fileDirName + File.separator + zipName;
		byte[] buffer = new byte[1024];
		File file = new File(fileDirName);
		if (!file.exists()) {
			System.err.println("no such directory : " + fileDirName);
			return;
		}
		File[] files = file.listFiles();
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
			for (int i = 0; i < file.length(); i++) {
				FileInputStream fis = new FileInputStream(files[i]);
				out.putNextEntry(new ZipEntry(files[i].getName()));
				out.setEncoding("GBK");
				int len;
				while ((len = fis.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void Download(HttpServletResponse response, String absolutepath) {
		try {
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + getFileName(absolutepath));
			File pfile = new File(absolutepath);
			os.write(FileUtils.readFileToByteArray(pfile));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static String getFileName(String absolutepath) {
		String fileName = absolutepath;
		if ((absolutepath != null) && (absolutepath.length() > 0)) {
			int dot = absolutepath.lastIndexOf(File.separator);
			if ((dot > -1) && (dot < (absolutepath.length() - 1))) {
				String temp = absolutepath.substring(dot + 1);
				try {
					fileName = URLEncoder.encode(temp, "UTF-8");
					if (fileName.length() > 150) {
						fileName = new String(temp.getBytes("gb2312"), "ISO8859-1");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return fileName;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}
}

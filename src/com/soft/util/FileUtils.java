package com.soft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;

/**
 * 文件上传共通处理
 * 
 * @author Administrator
 */
public class FileUtils {

	/** 缩略图命名标记 */
	public static final String IMAGE_THUMB = "_s";

	/** 水印图片 */
	public static final String WATERMARK_IMAGE = FileUtils.class
			.getClassLoader().getResource("").getPath()
			+ "/conf/main_logo.png";

	/**
	 * 复制文件或文件夹
	 * 
	 * @param srcPath
	 *            源文件或源文件夹的路径
	 * @param destDir
	 *            目标文件所在的目录
	 * @return
	 */
	public static boolean copyGeneralFile(String srcPath, String destDir) {
		boolean flag = false;
		File file = new File(srcPath);
		// 源文件或源文件夹不存在
		if (!file.exists()) {
			return false;
		}
		// 文件复制
		if (file.isFile()) {
			flag = copyFile(srcPath, destDir);
		}
		// 文件夹复制
		else if (file.isDirectory()) {
			flag = copyDirectory(srcPath, destDir);
		}

		return flag;
	}

	/**
	 * 默认的复制文件方法，默认会覆盖目标文件夹下的同名文件
	 * 
	 * @param srcPath
	 *            源文件绝对路径
	 * @param destDir
	 *            目标文件所在目录
	 * @return boolean
	 */
	public static boolean copyFile(String srcPath, String destDir) {
		// 默认覆盖同名文件
		return copyFile(srcPath, destDir, true);
	}

	/**
	 * 默认的复制文件夹方法，默认会覆盖目标文件夹下的同名文件夹
	 * 
	 * @param srcPath
	 *            源文件夹路径
	 * @param destPath
	 *            目标文件夹所在目录
	 * @return boolean
	 */
	public static boolean copyDirectory(String srcPath, String destDir) {
		return copyDirectory(srcPath, destDir, true);
	}

	/**
	 * 复制文件到目标目录
	 * 
	 * @param srcPath
	 *            源文件绝对路径
	 * @param destDir
	 *            目标文件所在目录
	 * @param overwriteExistFile
	 *            是否覆盖目标目录下的同名文件
	 * @return boolean
	 */
	public static boolean copyFile(String srcPath, String destDir,
			boolean overwriteExistFile) {
		boolean flag = false;

		File srcFile = new File(srcPath);
		// 源文件不存在
		if (!srcFile.exists() || !srcFile.isFile()) {
			return false;
		}

		// 获取待复制文件的文件名
		String fileName = srcFile.getName();
		String destPath = destDir + File.separator + fileName;
		File destFile = new File(destPath);
		// 源文件路径和目标文件路径重复
		if (destFile.getAbsolutePath().equals(srcFile.getAbsolutePath())) {
			return false;
		}
		// 目标目录下已有同名文件且不允许覆盖
		if (destFile.exists() && !overwriteExistFile) {
			return false;
		}

		File destFileDir = new File(destDir);
		// 目录不存在并且创建目录失败直接返回
		if (!destFileDir.exists() && !destFileDir.mkdirs()) {
			return false;
		}

		try {
			FileInputStream fis = new FileInputStream(srcPath);
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] buf = new byte[1024];
			int c;
			while ((c = fis.read(buf)) != -1) {
				fos.write(buf, 0, c);
			}
			fos.flush();
			fis.close();
			fos.close();

			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * @param srcPath
	 *            源文件夹路径
	 * @param destPath
	 *            目标文件夹所在目录
	 * @return
	 */
	public static boolean copyDirectory(String srcPath, String destDir,
			boolean overwriteExistDir) {
		if (destDir.contains(srcPath))
			return false;

		boolean flag = false;

		File srcFile = new File(srcPath);
		// 源文件夹不存在
		if (!srcFile.exists() || !srcFile.isDirectory()) {
			return false;
		}

		// 获得待复制的文件夹的名字，比如待复制的文件夹为"E:\\dir\\"则获取的名字为"dir"
		String dirName = srcFile.getName();

		// 目标文件夹的完整路径
		String destDirPath = destDir + File.separator + dirName
				+ File.separator;
		File destDirFile = new File(destDirPath);
		if (destDirFile.getAbsolutePath().equals(srcFile.getAbsolutePath())) {
			return false;
		}
		// 目标位置有一个同名文件夹且不允许覆盖同名文件夹，则直接返回false
		if (destDirFile.exists() && destDirFile.isDirectory()
				&& !overwriteExistDir) {
			return false;
		}
		// 如果目标目录不存在并且创建目录失败
		if (!destDirFile.exists() && !destDirFile.mkdirs()) {
			return false;
		}
		// 获取源文件夹下的子文件和子文件夹
		File[] fileList = srcFile.listFiles();
		// 如果源文件夹为空目录则直接设置flag为true，这一步非常隐蔽，debug了很久
		if (fileList.length == 0) {
			flag = true;
		} else {
			for (File temp : fileList) {
				// 文件
				if (temp.isFile()) {
					// 递归复制时也继承覆盖属性
					flag = copyFile(temp.getAbsolutePath(), destDirPath,
							overwriteExistDir);
				}
				// 文件夹
				else if (temp.isDirectory()) {
					// 递归复制时也继承覆盖属性
					flag = copyDirectory(temp.getAbsolutePath(), destDirPath,
							overwriteExistDir);
				}

				if (!flag) {
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 删除文件或文件夹
	 * 
	 * @param path
	 *            待删除的文件的绝对路径
	 * @return boolean
	 */
	public static boolean deleteFile(String path) {
		boolean flag = false;

		File file = new File(path);
		// 文件不存在直接返回
		if (!file.exists()) {
			return flag;
		}

		flag = file.delete();

		return flag;
	}

	/**
	 * 文件剪切：复制+删除
	 * 
	 * @param srcPath
	 *            源文件或源文件夹的路径
	 * @param destPath
	 *            目标文件所在的目录
	 */
	public static boolean cutGeneralFile(String srcPath, String destDir) {
		boolean flag = false;
		// 复制和删除都成功
		if (copyGeneralFile(srcPath, destDir) && deleteFile(srcPath)) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 获取文件的扩展名
	 * 
	 * @param filename
	 *            文件名称
	 * @return 文件的扩展名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return "";
	}

	/**
	 * 获取不带扩展名的文件名,用于有缩略图生成的情况
	 * 
	 * @param filename
	 *            文件名称
	 * @return 不带扩展名的文件名
	 */
	public static String getFileNameWithoutExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return "";
	}

	/**
	 * 自定义文件上传路径
	 * 
	 * @param uploadPath
	 *            文件上传路径
	 * @return 文件上传路径
	 */
	public static String getUploadPath(String uploadPath) {
		// 文件路径
		String fileUploadPath = "";

		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);

		// 自定义路径+/系统年月+/日
		if (Utils.isNotEmptyString(uploadPath)) {
			fileUploadPath = uploadPath
					+ Utils.getSystemDate(Utils.DATE_FORMAT_2) + File.separator
					+ day;
		}

		return fileUploadPath;
	}

	/**
	 * 自定义文件名称,包含文件扩展名
	 * 
	 * @param originalFilename
	 *            文件原名
	 * @param num
	 *            文件数字
	 * @return 文件名称
	 */
	public static String getFileName(String filename, String originalFilename) {
		// 文件名：ID+_文件数字+原文件后缀
		return filename + "." + getExtensionName(originalFilename);
	}

	/**
	 * 获取图片缩略图名称
	 * 
	 * @param filename
	 *            文件名称
	 * @return 缩略图名称
	 */
	public static String getThumbFileName(String filename) {
		// 文件名：ID+_文件数字+_s+.JPEG
		if (Utils.isNotEmptyString(filename)) {
			return filename + IMAGE_THUMB + ".jpeg";
		}
		return "";
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 *            上传的文件
	 * @param uploadPath
	 *            自定义上传的文件路径
	 * @param fileName
	 *            原文件名称
	 * @param thumbFileName
	 *            缩略图文件名称
	 * @param tflag
	 *            是否生成缩略图(true:生成; false:不生成)
	 * @param sflag
	 *            是否加水印(true:生成; false:不生成)
	 * @return
	 */
	public static boolean fileDeleteAfterCopy(String fromFile,
			String uploadPath, String fileName, String thumbFileName,
			boolean tflag, boolean sflag) {
		// 判断文件是否为空
		if (Utils.isNotEmptyString(fromFile)
				&& Utils.isNotEmptyString(fileName)) {
			try {
				if (Utils.isNotEmptyString(uploadPath)) {
					// 文件保存路径
					String filePath = Config.getUploadBasepath() + uploadPath;
					File fileDir = new File(filePath);
					// 如果文件夹不存在则创建
					if (!fileDir.exists() && !fileDir.isDirectory()) {
						fileDir.mkdirs();
					}

					// 图片加水印
					if (sflag) {
						ImageUtils.pressImage(WATERMARK_IMAGE, fromFile,
								fromFile, 250, 100, 1f);
					}

					File from = new File(fromFile);
					if (from.exists()) {
						// 保存文件
						File toFile = new File(filePath + File.separator
								+ fileName);
						fileChannelCopy(from, toFile);
						from.delete();
					}

					// 生成缩略图
					if (tflag) {
						ImageUtils.scale(filePath + File.separator + fileName,
								filePath + File.separator + thumbFileName, 315,
								315, true);
					}
					return true;
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 使用文件通道的方式复制文件
	 * 
	 * @param s
	 *            源文件
	 * @param t
	 *            复制到的新文件
	 */
	public static void fileChannelCopy(File from, File to) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(from);
			fo = new FileOutputStream(to);
			// 得到对应的文件通道
			in = fi.getChannel();
			// 得到对应的文件通道
			out = fo.getChannel();
			// 连接两个通道，并且从in通道读取，然后写入out通道
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 *            上传的文件
	 * @param uploadPath
	 *            自定义上传的文件路径
	 * @param fileName
	 *            原文件名称
	 * @param thumbFileName
	 *            缩略图文件名称
	 * @param tflag
	 *            是否生成缩略图(true:生成; false:不生成)
	 * @param sflag
	 *            是否加水印(true:生成; false:不生成)
	 * @return
	 */
	public static boolean fileDeleteAfterCompress(String fromFile,
			String uploadPath, String fileName, boolean sflag, int width,
			int height) {
		// 判断文件是否为空
		if (Utils.isNotEmptyString(fromFile)
				&& Utils.isNotEmptyString(fileName)) {
			try {
				if (Utils.isNotEmptyString(uploadPath)) {
					// 文件保存路径
					String filePath = Config.getUploadBasepath() + uploadPath;
					File fileDir = new File(filePath);
					// 如果文件夹不存在则创建
					if (!fileDir.exists() && !fileDir.isDirectory()) {
						fileDir.mkdirs();
					}

					// 图片加水印
					if (sflag) {
						ImageUtils.pressImage(WATERMARK_IMAGE, fromFile,
								fromFile, 250, 100, 1f);
					}

					File from = new File(fromFile);
					if (from.exists()) {
						ImageUtils.scale(fromFile, filePath + File.separator
								+ fileName, height, width, false);
						from.delete();
					}
					return true;
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * convertSeparator(转换路径分割符)
	 * 
	 * @param path
	 *            路径
	 * @return String 转换后的路径
	 * @exception
	 */
	public static String convertSeparator(String path) {
		if (Utils.isNotEmptyString(path)) {
			return path.replaceAll("\\\\", "/");
		}
		return "";
	}
 
	/**
	 * 
	 * toFileSeparator(按系统转换路径分割符)
	 * 
	 * @param path
	 *            路径
	 * @return String 转换后的路径
	 * @exception
	 */
	public static String toFileSeparator(String path) {
		if (Utils.isNotEmptyString(path)) {
			return path.replaceAll("\\\\", "/").replace("/", File.separator);
		}
		return "";
	}
}

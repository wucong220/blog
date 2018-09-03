package com.waylau.spring.boot.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

	@Value("${web.upload-path}")
	private String photoPath;

	/**
	 * 跳转到文件上传页面
	 * 
	 * @return
	 */
	@RequestMapping("test")
	public String toUpload() {
		return "test";
	}

	/**
	 *
	 * @param file
	 *            要上传的文件
	 * @return
	 */
	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "id", required = false) Long id) {

		// Blob b;
		// try {
		// b = new Blob(file.getBytes(), null);
		//
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// 要上传的目标文件存放路径

		// 上传成功或者失败的提示
		String imgName = id != null ? id + "_avatar.png" : file.getOriginalFilename();

		String msg = "";

		boolean success = true;
		try {
			File dest = new File(photoPath + "/" + imgName);  //路径
			if (dest.exists()) {
				if (id != null)
					dest.delete();
				else{
					int last_dot=imgName.lastIndexOf('.');   //如果文件已存在重新生成文件
					String uuid = UUID.randomUUID().toString();
					imgName = uuid+imgName.substring(last_dot);
					dest = new File(photoPath + "/" + imgName);
				}
			}
			String ap = dest.getAbsolutePath();
			// 判断文件父目录是否存在
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdir();
			}
			Files.write(file.getBytes(), dest);  //不管是Glob还是普通文件，都可以直接转换为byte[]，写入文件中，并且可以用做图形。
		} catch (IOException e) {
			// TODO Auto-generated catch block
			success = false;
			msg = "IOException!";
			e.printStackTrace();
		}

		// if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
		// // 上传成功，给出页面提示
		// msg = "上传成功！";
		// }else {
		// msg = "上传失败！";
		//
		// }

		// 显示图片
		return "/photos/" + imgName;
	}

}

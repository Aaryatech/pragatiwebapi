package com.ats.feedback.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ats.feedback.model.master.ErrorMessage;


@RestController
public class ImageUploadController {

	private static String SIGN_URL = "/home/aaryate1/pragati.aaryatechindia.in/tomcat-8.0.18/webapps/signature/";

	@RequestMapping(value = { "/photoUpload" }, method = RequestMethod.POST)
	public @ResponseBody ErrorMessage getFarmerContract(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("imageName") String imageName, @RequestParam("type") String type) {

		ErrorMessage info = new ErrorMessage();

		System.out.println("File Name " + uploadfile.getOriginalFilename());

		try {

			saveUploadedFiles(Arrays.asList(uploadfile), imageName, type);

			info.setError(false);
			info.setMessage("File uploaded successfully");

		} catch (IOException e) {

			e.printStackTrace();
			info.setError(true);
			info.setMessage("File upload failed");
		}

		return info;
	}

	// save file
	private void saveUploadedFiles(List<MultipartFile> files, String imageName, String type) throws IOException {

		try {
			for (MultipartFile file : files) {
				Path path = null;
				if (file.isEmpty()) {
					continue;
				}
				if (type.equalsIgnoreCase("sign")) {
					path = Paths.get(SIGN_URL + imageName);
				} 

				byte[] bytes = file.getBytes();

				Files.write(path, bytes);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

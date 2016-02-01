package br.com.casadocodigo.loja.infra;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	public String write(String string, MultipartFile summary) {
		String homePath = System.getProperty("user.home");
		File uploadPath = new File(homePath, "upload");
		uploadPath.mkdirs();
		File file = new File(uploadPath, summary.getOriginalFilename());
		try {
			summary.transferTo(file);
			String result = file.getAbsolutePath();
			
			System.out.println(result);
			
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}

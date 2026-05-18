// ImageUtil.java
package com.quickbite.utils;

import jakarta.servlet.http.Part;
import jakarta.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageUtil {

    /**
     * Processes and uploads user profile image to server's file system.
     * 
     * This cleans the incoming file name by trimming spaces and illegal characters, and
     * appends a timestamp to avoid file name duplicates. It then creates a directory where
     * to store the image on the server disk, and then commits the byte stream.
     * 
     * @param part - the object representing the multipart form data of the uploaded image
     * @param uploadFolder - the name of the target sub-directory where files are routed (like "uploads")
     * @param context - ServletContext used to dynamically resolve relative web paths to absolute machine storage locations.
     * @return server-relative file path mapping (ex. "uploads/picture.png")
     */
	public String uploadProfileImage(Part part, String uploadFolder, ServletContext context) {
	    
	    if (part == null || part.getSize() == 0) {
	        return uploadFolder + "/default.png";
	    }

	    try {
	        String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
	        
	        // CLEAN THE FILENAME - Remove spaces and special characters
	        String cleanFileName = originalFileName
	                .replaceAll("\\s+", "_")           // Replace spaces with underscore
	                .replaceAll("[^a-zA-Z0-9._-]", ""); // Remove other special characters

	        // Add timestamp to make it unique
	        String fileExtension = "";
	        if (cleanFileName.contains(".")) {
	            fileExtension = cleanFileName.substring(cleanFileName.lastIndexOf("."));
	            cleanFileName = cleanFileName.substring(0, cleanFileName.lastIndexOf("."));
	        }
	        
	        String newFileName = cleanFileName + "_" + System.currentTimeMillis() + fileExtension;

	        // Create upload directory
	        String uploadPath = context.getRealPath("") + File.separator + uploadFolder;
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }

	        // Save file
	        String filePath = uploadPath + File.separator + newFileName;
	        part.write(filePath);

	        System.out.println("Image uploaded: " + filePath);

	        return uploadFolder + "/" + newFileName;

	    } catch (IOException e) {
	        e.printStackTrace();
	        return uploadFolder + "/default.png";
	    }
	}
}
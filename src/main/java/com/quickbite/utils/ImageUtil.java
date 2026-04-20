// ImageUtil.java
package com.quickbite.utils;

import jakarta.servlet.http.Part;
import jakarta.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ImageUtil {

    /**
     * Uploads the profile image and returns the relative path to store in database
     * Example: "uploads/filename.jpg"
     */
    public String uploadProfileImage(Part part, String uploadFolder, ServletContext context) {
        
        // If no file is uploaded, return default image
        if (part == null || part.getSize() == 0) {
            return uploadFolder + "/default.png";
        }

        try {
            // Get clean filename
            String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            
            // Create unique filename to avoid overwriting (optional but recommended)
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + "_" + originalFileName;

            // Get real server path
            String uploadPath = context.getRealPath("") + File.separator + uploadFolder;
            File uploadDir = new File(uploadPath);
            
            // Create directory if it doesn't exist
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Full path to save file
            String filePath = uploadPath + File.separator + newFileName;

            // Save the file
            part.write(filePath);

            System.out.println("Image uploaded successfully: " + filePath);

            // Return relative path to save in database
            return uploadFolder + "/" + newFileName;

        } catch (IOException e) {
            e.printStackTrace();
            return uploadFolder + "/default.png"; // fallback
        }
    }
}
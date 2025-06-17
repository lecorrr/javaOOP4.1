import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class App {
    public static void main(String[] args) throws Exception {
        
         try {
            copyFilesWithExtension("/home/lecor/Pictures", "/home/lecor/Pictures/test", "gif");
            System.out.println("Files copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void copyFilesWithExtension(String sourceDir, String targetDir, String extension) throws IOException {
        File sourceFolder = new File(sourceDir);
        File targetFolder = new File(targetDir);

        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
            throw new IllegalArgumentException("Source directory does not exist or is not a directory.");
        }

        if (!targetFolder.exists()) {
            targetFolder.mkdirs();
        }

        File[] filesToCopy = sourceFolder.listFiles((dir, name) -> name.toLowerCase().endsWith("." + extension.toLowerCase()));
        if (filesToCopy == null) return;

        for (File file : filesToCopy) {
            Path sourcePath = file.toPath();
            Path targetPath = Paths.get(targetFolder.getAbsolutePath(), file.getName());
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}

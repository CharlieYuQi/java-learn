package tk.yuqi.tools.tools.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class FileUtils {

    public void rename(String path, String prefix, String deletedPreName,String deletedPostName){

        File rootDirectory = new File(path);
        File[] files = rootDirectory.listFiles();
        for (File file : files) {
            String originName = file.getName();
            if (StringUtils.isNotBlank(deletedPreName)){
                originName= originName.replace(deletedPreName, "");
            }
            if (StringUtils.isNotBlank(deletedPostName)){
                originName= originName.replace(deletedPostName, "");
            }
            String newName = prefix + originName;
            boolean res = file.renameTo(new File(path+"/"+newName));
            System.out.println("rename "+newName+" result:"+res);
        }

    }

    public static void main(String[] args) {

        String path = "/Volumes/disk2_18867103286/共享文件夹/Media/TV Shows/Test";
        String prefix = "苍兰诀S01E";
        String deletedName = "1080.";
        FileUtils fileUtils = new FileUtils();
        fileUtils.rename(path,prefix,deletedName,null);
    }
}

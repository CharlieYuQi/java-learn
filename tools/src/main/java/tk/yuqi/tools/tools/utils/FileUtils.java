package tk.yuqi.tools.tools.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class FileUtils {

    public void rename(String path, String prefix, String deletedPreName,String deletedPostName, String newPostName){

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
            String newName = prefix + originName + newPostName;
            boolean res = file.renameTo(new File(path+"/"+newName));
            System.out.println("rename "+newName+" result:"+res);
        }

    }

    public static void main(String[] args) {

//        [DBD-Raws][SW笔记][01][1080P][BDRip][HEVC-10bit][FLAC]
        String path = "/Users/yuqi/Movies/进击的巨人 S04";
        String newPrefix = "进击的巨人 S04E";
        String deletedPrefixName = "";
        String deletedPostName = "";
        String newPostName = "";
//        String deletedName = "[zza] Death Note - ";
//        String deletedPostName = " [1080p.x265].ass";
//        String newPostName = "][1080P][BDRip][HEVC-10bit][FLAC].ass";
        FileUtils fileUtils = new FileUtils();
        fileUtils.rename(path,newPrefix,deletedPrefixName,deletedPostName,newPostName);
    }
}

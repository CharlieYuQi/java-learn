package tk.yuqi.tools.tools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    public static void main(String[] args) {
        String input = "exa.mple+bicner.com-2013-6@gmail.com";

        String regex = "^(.+)@gmail\\.com$";
        String extractedEmail=input;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(extractedEmail);

        if (matcher.matches()) {
            extractedEmail = matcher.group(1);
            System.out.println("1提取的邮箱地址: " + extractedEmail);

            //替换掉点号
            extractedEmail=extractedEmail.replaceAll("\\.", "");
            System.out.println("2提取的邮箱地址: " + extractedEmail);

            //替换掉+号及后面部分
            String regex2 = "^([^+]+)\\+.+$";
            Pattern gmailWithAliasPattern = Pattern.compile(regex2);
            Matcher gmailWithAliasPatternMatcher = gmailWithAliasPattern.matcher(extractedEmail);
            if (gmailWithAliasPatternMatcher.matches()) {
                extractedEmail = gmailWithAliasPatternMatcher.group(1);
                System.out.println("3提取的邮箱地址: " + extractedEmail);
            }
            System.out.println("最终地址: " + extractedEmail+"@gmail.com");
        }

    }
}

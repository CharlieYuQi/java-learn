package tk.yuqi.tools.tools.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * 
 * @ClassName: DESUtils 
 * @Description: DES加密算法
 * @author xlmeng
 * @date 2016年11月8日 下午2:50:40
 */
public class DESUtils {
    //字符串常量
    private final static String DES = "DES";

    /**
     * 测试函数
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String key = "123456789024132141234125435145345";
        String val = "2afsdfasdf2241212341234";
        String encrypt = encrypt(val, key);
        System.out.println(val);
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt,key));
//        String mode = args[0];
//        String val = args[1];
//        if ("encrypt".equals(mode)) {
//            System.out.println(encrypt(val, key));
//        } else if ("decrypt".equals(mode)) {
//            System.out.println(decrypt(val, key));
//        }
    }

    /**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) {
        try {
            byte[] bt = encrypt(data.getBytes(), key.getBytes());
            String strs = Base64.encodeBase64String(bt);
            return strs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) {
        try {
            if (data == null) {
                return null;
            }

            byte[] buf = Base64.decodeBase64(data);
            byte[] bt = decrypt(buf, key.getBytes());
            return new String(bt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}

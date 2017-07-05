package tk.yuqi.utils.service.impl;

import org.springframework.stereotype.Service;
import tk.yuqi.utils.service.EncryptService;
import tk.yuqi.utils.util.DESUtils;

/**
 * Created by YuQi on 2017/7/5.
 */
@Service
public class EncryptServiceImpl implements EncryptService {
    final public static String SECURE_KEY = "Kd12xS121asdDASKxljka@#&4ds*DS1m";

    @Override
    public String encrypt(String value) {
        return DESUtils.encrypt(value, SECURE_KEY);
    }

    @Override
    public String decrypt(String value) {
        return DESUtils.decrypt(value, SECURE_KEY);
    }
}

package tk.yuqi.utils.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.yuqi.utils.service.EncryptService;

/**
 * Created by YuQi on 2017/7/5.
 */
@RestController
public class EncryptController {

    @Autowired
    private EncryptService encryptService;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/encrypt/{value}")
    String encrypt(@PathVariable String value){
        return encryptService.encrypt(value);
    }

    @RequestMapping("/decrypt/{value}")
    String decrypt(@PathVariable String value){
        return encryptService.decrypt(value);
    }
}

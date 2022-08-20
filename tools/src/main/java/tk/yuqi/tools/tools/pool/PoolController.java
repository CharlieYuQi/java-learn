package tk.yuqi.tools.tools.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoolController {
    @Autowired
    PoolTest poolTest;

    @GetMapping("pool")
    public String pool(){
        poolTest.runTask();
        return "ok";
    }
}

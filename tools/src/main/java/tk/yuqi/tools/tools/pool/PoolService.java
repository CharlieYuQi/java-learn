package tk.yuqi.tools.tools.pool;

import org.springframework.stereotype.Service;

@Service
public class PoolService {

    public void output(int index){
        System.out.println("running "+index);
    }
}

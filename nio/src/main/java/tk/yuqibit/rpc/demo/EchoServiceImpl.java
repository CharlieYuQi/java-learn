package tk.yuqibit.rpc.demo;

/**
 * Created by YuQi on 2017/9/5.
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping != null ? ping + " ---> I am OK." : "I am OK.";
    }
}

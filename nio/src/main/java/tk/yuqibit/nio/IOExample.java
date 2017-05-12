package tk.yuqibit.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by YuQi on 2017/5/3.
 */
public class IOExample {

    public static void method2() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("README.md"));

            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buf[i]);
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void method1() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("README.md", "rw");
            FileChannel fileChannel = aFile.getChannel();

            //1. 分配空间
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //2. 写入数据到Buffer
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while (bytesRead != -1) {
                //3. 调用filp()方法
                buf.flip();
                while (buf.hasRemaining()) {
                    //4. 从Buffer中读取数据
                    System.out.print((char) buf.get());
                }

                //5. 调用clear()方法或者compact()方法
                /* 调用clear() 方法：position将被设回0，limit设置成capacity，换句话说，Buffer被清空了，其实Buffer中的数据并未被清楚，
                只是这些标记告诉我们可以从哪里开始往Buffer里写数据。如果Buffer中有一些未读的数据，调用clear() 方法，数据将“被遗忘”，
                意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()
                方法。compact() 方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear() 方法一样，
                设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。*/
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        IOExample.method1();
        IOExample.method2();
    }
}

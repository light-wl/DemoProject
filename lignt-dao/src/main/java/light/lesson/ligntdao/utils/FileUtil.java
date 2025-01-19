package light.lesson.ligntdao.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;

/**
 * @Author light
 * @Date 2023/4/24
 * @Desc 对文件的输入输出进行操作
 * 参考装饰器模式：里面介绍了文件的基本输入输出操作
 * 注意：Java.io包的File类，File类用于目录和文件的创建、删除、遍历等操作，但不能用于文件的读写。
 * Reader和Write为字符输入输出流，InputStream和OutputStream为字节输入输出流。这四个类属于抽象流类，
 * 不能在程序中直接实例化使用，可以使用其派生的类。
 * InputStream 是一个抽象类，FileInputStream 是专门用来读取文件流的子类。
 * BufferedInputStream 是一个支持带缓存功能的数据读取类，可以提高数据读取的效率。
 *
 **/
public class FileUtil {
    public static void main(String[] args) {
        String path = "/Users/light/Desktop/int.txt";
        fileExist(path);
        fileOutput(path);

    }

    public static void fileOutput(String path) {
        try {
            File file = new File(path);
            Random random = new Random();
            DataOutputStream output = new DataOutputStream(new BufferedOutputStream(
                    Files.newOutputStream(Paths.get(path))));
            for (int i = 0; i < 800000; i++) {
                int number = random.nextInt(100000);
                output.writeInt(number);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
        }
    }


    public static void fileInput(String path) {
        try {
            File file = new File(path);
            //这里需要进行抛出异常处理
            FileInputStream inputStream = new FileInputStream(file);
            for (int i = 0; i < file.length(); i++) {
                //循环读取字符
                char ch = (char) (inputStream.read());
                System.out.print(ch + " ");
            }
            //换行操作
            System.out.println();
            //关闭文件
            inputStream.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("文件打开失败");
        }
    }

    public static void fileExist(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                System.out.println("文件已经存在");
            } else {
                file.createNewFile();
                System.out.println("文件创建成功");
            }
        } catch (Exception ignored) {
        }
    }

    public static void checkFile(File file) {
        System.out.println("文件是否存在-->" + file.exists());
        System.out.println("文件是否可写-->" + file.canWrite());
        System.out.println("文件是否可读-->" + file.canRead());
        System.out.println("文件是否是文件-->" + file.isFile());
        System.out.println("文件是否是目录-->" + file.isDirectory());
        System.out.println("文件是否是绝对路径-->" + file.isAbsolute());
        System.out.println("文件名是-->" + file.getName());
        System.out.println("文件的路径是-->" + file.getPath());
        System.out.println("文件的绝对路径是-->" + file.getAbsolutePath());
        System.out.println("文件的上级路径是-->" + file.getParent());
        System.out.print("最后修改时间-->");
        System.out.println("文件长度是-->" + file.length());
    }
}


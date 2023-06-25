package com.light.designpattern.behavior;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author light
 * @Date 2023/5/4
 * @Desc 访问者模式
 * 所谓 Single Dispatch，指的是执行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参数的编译时类型来决定。
 * 所谓 Double Dispatch，指的是执行哪个对象的方法，根据对象的运行时类型来决定；执行对象的哪个方法，根据方法参数的运行时类型来决定。
 * Java 是Single Dispatch，即运行哪个对象，根据实际传入的对象进行执行，即运行多态的基础；执行对象的哪个方法时，确定了，
 * 就无法更改了，多态在此处便失效了，即参数是父类，传入子类的参数不会匹配到这个方法。
 **/
public class VisitorDesignPattern {
}


abstract class ResourceFile {
  protected String filePath;
  public ResourceFile(String filePath) {
    this.filePath = filePath;
  }
  abstract public void accept(Visitor vistor);
}

class PdfFile extends ResourceFile {
  public PdfFile(String filePath) {
    super(filePath);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
class PPTFile extends ResourceFile {
  public PPTFile(String filePath) {
    super(filePath);
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
//...PPTFile、WordFile跟PdfFile类似，这里就省略了...

interface Visitor {
  void visit(PdfFile pdfFile);
  void visit(PPTFile pdfFile);
}

class Extractor implements Visitor {
  @Override
  public void visit(PPTFile pptFile) {
    System.out.println("Extract PPT.");
  }

  @Override
  public void visit(PdfFile pdfFile) {
    System.out.println("Extract PDF.");
  }
}

class Compressor implements Visitor {
  @Override
  public void visit(PPTFile pptFile) {
    //...
    System.out.println("Compress PPT.");
  }

  @Override
  public void visit(PdfFile pdfFile) {
    //...
    System.out.println("Compress PDF.");
  }

}

class ToolApplication {
  public static void main(String[] args) {
    Extractor extractor = new Extractor();
    List<ResourceFile> resourceFiles = listAllResourceFiles();
    for (ResourceFile resourceFile : resourceFiles) {
      resourceFile.accept(extractor);
    }

    Compressor compressor = new Compressor();
    for(ResourceFile resourceFile : resourceFiles) {
      resourceFile.accept(compressor);
    }
  }

  private static List<ResourceFile> listAllResourceFiles() {
    List<ResourceFile> resourceFiles = new ArrayList<>();
    //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
    resourceFiles.add(new PdfFile("a.pdf"));
    resourceFiles.add(new PPTFile("c.ppt"));
    return resourceFiles;
  }
}
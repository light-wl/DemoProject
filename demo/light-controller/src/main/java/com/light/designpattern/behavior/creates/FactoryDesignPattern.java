package com.light.designpattern.behavior.creates;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author light
 * @Date 2023/6/25
 * @Desc 工厂模式
 * 1、简单工厂，每次都需要new一个新对象
 * 2、单例 + 工厂
 **/


class RuleConfigParserFactoryOne {
    public static IRuleConfigParser createParser(String configFormat) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(configFormat)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(configFormat)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(configFormat)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(configFormat)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}


class RuleConfigParserFactoryTwo {
  private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

  static {
    cachedParsers.put("json", new JsonRuleConfigParser());
    cachedParsers.put("xml", new XmlRuleConfigParser());
    cachedParsers.put("yaml", new YamlRuleConfigParser());
    cachedParsers.put("properties", new PropertiesRuleConfigParser());
  }

  public static IRuleConfigParser createParser(String configFormat) {
    if (configFormat == null || configFormat.isEmpty()) {
      return null;//返回null还是IllegalArgumentException全凭你自己说了算
    }
    IRuleConfigParser parser = cachedParsers.get(configFormat.toLowerCase());
    return parser;
  }
}

/**配置参数*/
public class FactoryDesignPattern {
}


interface IRuleConfigParser {
}

class JsonRuleConfigParser implements IRuleConfigParser {
}

class XmlRuleConfigParser implements IRuleConfigParser {
}

class YamlRuleConfigParser implements IRuleConfigParser {
}

class PropertiesRuleConfigParser implements IRuleConfigParser {
}

package com.light.designpattern.behavior;

import lombok.Data;
import org.springframework.expression.spel.ast.Selection;

import javax.xml.soap.Text;
import java.awt.*;

/**
 * @Author light
 * @Date 2023/5/11
 * @Desc 中介模式
 * 解释：中介模式定义了一个单独的（中介）对象，来封装一组对象之间的交互。将这组对象之间的交互委派给与中介对象交互，来避免对象之间的直接交互。
 **/
public class MediatorDesignPattern {
}


interface Mediator {
    void handleEvent(String component, String event);
}

@Data
class LandingPageDialog implements Mediator {
    private Button loginButton;
    private Button regButton;
    private Selection selection;
    private Text hintText;

    @Override
    public void handleEvent(String component, String event) {
        if (component.equals(loginButton)) {
            //校验数据...
            //做业务处理...
        } else if (component.equals(regButton)) {
            //获取usernameInput、passwordInput、repeatedPswdInput数据...
            //校验数据...
            //做业务处理...
        } else if (component.equals(selection)) {
            String selectedItem = "";
            if (selectedItem.equals("login")) {
                //...省略其他代码
            } else if (selectedItem.equals("register")) {
                //....
            }
        }
    }
}

class UIControl {
    private static final String LOGIN_BTN_ID = "login_btn";
    private static final String REG_BTN_ID = "reg_btn";
    private static final String USERNAME_INPUT_ID = "username_input";
    private static final String PASSWORD_INPUT_ID = "pswd_input";
    private static final String REPEATED_PASSWORD_INPUT_ID = "repeated_pswd_input";
    private static final String HINT_TEXT_ID = "hint_text";
    private static final String SELECTION_ID = "selection";

    public static void mainProcess() {
        Mediator dialog = new LandingPageDialog();
        dialog.handleEvent("", "click");

    }
}
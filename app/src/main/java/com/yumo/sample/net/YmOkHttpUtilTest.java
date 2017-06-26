package com.yumo.sample.net;


import com.yumo.common.net.YmOkHttpUtil;
import com.yumo.demo.view.YmTestFragment;

import java.io.IOException;

/**
 * Created by yumodev on 17/3/9.
 */

public class YmOkHttpUtilTest extends YmTestFragment {

    private String getLoginName(){
        return "yumodev";
    }


    public void testGetBodyString() throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://api.github.com/users/"+getLoginName();
                String result = "";
                try {
                    result = YmOkHttpUtil.getBodyString(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (result != null){
                    System.out.println(result);
                }
            }
        }).start();

    }
}

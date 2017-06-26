package com.yumo.sample;

import com.yumo.common.android.YmContext;
import com.yumo.demo.entry.YmPackageInfo;
import com.yumo.demo.listener.IGetPackageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumodev on 17/3/9.
 */

public class GetPackageData implements IGetPackageData {
    @Override
    public List<YmPackageInfo> getPackageList() {
        String packageName = YmContext.getInstance().getAppContext().getPackageName()+".";
        List<YmPackageInfo> list = new ArrayList<>();
        list.add(new YmPackageInfo("net", packageName+"net"));
        return list;
    }
}

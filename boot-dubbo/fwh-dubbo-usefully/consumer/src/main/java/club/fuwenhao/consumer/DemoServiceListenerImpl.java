package club.fuwenhao.consumer;

import club.fuwenhao.DemoServiceListener;

public class DemoServiceListenerImpl implements DemoServiceListener {

    @Override
    public void changed(String msg) {
        System.out.println("被回调了："+msg);
    }
}

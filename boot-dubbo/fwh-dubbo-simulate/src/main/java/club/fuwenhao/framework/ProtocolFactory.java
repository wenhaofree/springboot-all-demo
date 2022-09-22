package club.fuwenhao.framework;

import club.fuwenhao.framework.protocol.dubbo.DubboProtocol;
import club.fuwenhao.framework.protocol.http.HttpProtocol;

public class ProtocolFactory {

    public static Protocol getProtocol() {

        String name = System.getProperty("protocolName");
        if (name == null || name.equals("")) name = "http";
        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                break;
        }
        return new HttpProtocol();

//        ExtensionLoader<Protocol> extensionLoader =
//                ExtensionLoader.getExtensionLoader(Protocol.class);

        // http
//        Protocol protocol = extensionLoader.getExtension(用户配置);

//        protocol.start(new URL("localhost", 8080));
    }
}

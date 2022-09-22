package club.fuwenhao.framework.spi;

import club.fuwenhao.framework.Protocol;
import club.fuwenhao.framework.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class DubboSpi {

    public static void main(String[] args) {

        ExtensionLoader<Protocol> extensionLoader =
                ExtensionLoader.getExtensionLoader(Protocol.class);

        // http
        Protocol protocol = extensionLoader.getExtension("http");

        protocol.start(new URL("localhost", 8080));
    }
}

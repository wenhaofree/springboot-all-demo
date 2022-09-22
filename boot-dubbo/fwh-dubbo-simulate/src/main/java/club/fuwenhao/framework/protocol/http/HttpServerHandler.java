package club.fuwenhao.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import club.fuwenhao.framework.Invocation;
import club.fuwenhao.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);

            // JDK11之前用
//            ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
//            Invocation invocation = (Invocation)ois.readObject();

            var interfaceName = invocation.getInterfaceName();
            var implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            var result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

            System.out.println("tomcat:" + result);
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}

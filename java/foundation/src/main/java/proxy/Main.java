package proxy;

public class Main {

    public static void main(String[] args) {
        // 静态代理
        SmsService staticSmsService = new SmsServiceImpl();
        SmsStaticProxy smsStaticProxy = new SmsStaticProxy(staticSmsService);
        smsStaticProxy.send("Static SMS Service");

        // JDK动态代理
        SmsService jdkDynamicSmsService = (SmsService) JDKProxyFactory.getProxy(new SmsServiceImpl());
        jdkDynamicSmsService.send("Jdk Dynamic SMS Service");

        // Cglib动态代理
        MailService mailService = (MailService) CglibProxyFactory.getProxy(MailService.class);
        mailService.send("Cglib Dynamic Mail Service");
    }
}

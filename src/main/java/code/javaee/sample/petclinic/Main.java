package code.javaee.sample.petclinic;

import fish.payara.micro.PayaraMicro;
import fish.payara.micro.PayaraMicroRuntime;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        org.h2.tools.Server.createWebServer().start();
        org.h2.tools.Server.createTcpServer("-tcpAllowOthers").start();

        File war = (args != null && args.length > 0)
                ? new File(args[0])
                : new File("build/libs/petclinic.war");
        PayaraMicro micro = PayaraMicro.getInstance();
        micro.setNoCluster(true);
        PayaraMicroRuntime instance = micro.bootStrap();
        instance.deploy(war);
    }

}

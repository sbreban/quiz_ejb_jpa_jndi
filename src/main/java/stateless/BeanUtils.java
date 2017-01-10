package stateless;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by sbreban on 1/10/17.
 */
public class BeanUtils {
  private static String jndiUserService;
  private static String jndiTestService;
  private static String jndiQuestionService;
  private static Properties jndiProps;

  static {
    jndiUserService = "java:global/quiz/UserServiceBean!stateless.UserService";
    jndiTestService = "java:global/quiz/TestServiceBean!stateless.TestService";
    jndiQuestionService = "java:global/quiz/QuestionServiceBean!stateless.QuestionService";
    jndiProps = new Properties();
//    initWildFly();
    initGlassFish();
  }

  private static void initGlassFish() {
    jndiProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
    jndiProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
    jndiProps.put("org.omg.CORBA.ORBInitialPort", "3700");
    jndiProps.put("java.naming.security.principal", "admin");
    jndiProps.put("java.naming.security.credentials", "adminadmin");
  }

  private static void initWildFly() {
    jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.as.naming.InitialContextFactory");
    jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
    jndiProps.put("jboss.naming.client.ejb.context", true);
  }

  public static UserService getUserService() throws NamingException {
    Context context = new InitialContext(jndiProps);
    return (UserService) context.lookup(jndiUserService);
  }

  public static TestService getTestService() throws NamingException {
    Context context = new InitialContext(jndiProps);
    return (TestService) context.lookup(jndiTestService);
  }

  public static QuestionService getQuestionService() throws NamingException {
    Context context = new InitialContext(jndiProps);
    return (QuestionService) context.lookup(jndiQuestionService);
  }
}

import java.io.File;
import java.io.FileInputStream;
import org.junit.Assert;
import org.junit.Test;
import common.LocalPaths;
import common.MailManager;

public class MailTest {
  @Test
  public void sendTest() throws Throwable {
    LocalPaths.getInstance().setWebRootPath("d:\\property");
    System.out.println(LocalPaths.getClassPath());
    MailManager mail = new MailManager();
    mail.setAddress("nowonbun@gmail.com", "nowonbun@gmail.com,nowonbun@gmail.com", "nowonbun@gmail.com", "nowonbun@gmail.com");
    mail.setSubject("Test Mail");
    File file = new File("d:\\work\\test.png");
    byte[] data = new byte[(int) file.length()];
    try (FileInputStream stream = new FileInputStream(file)) {
      stream.read(data);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.assertTrue(false);
    }
    mail.setImage("testImage.png", "image/png", data, "testImage");
    mail.setContent("<html><head></head><body>Hello Test<br><img src=\"cid:testImage\" ></body></html>");
    mail.sendMail();
  }
}

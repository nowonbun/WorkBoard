package common;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.eclipse.persistence.internal.oxm.ByteArrayDataSource;


public class MailManager {
  private Properties props;
  private Session session;
  private MimeMessage message;
  private Multipart mp;

  public MailManager() throws MessagingException, IOException {
    props = new Properties();
    props.setProperty("mail.transport.protocol", "smtp");
    props.setProperty("mail.host", PropertyMap.getInstance().getProperty("config", "mail_host"));
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", PropertyMap.getInstance().getProperty("config", "mail_smtp_port"));
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.quitwait", "false");
    session = Session.getInstance(props, new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(PropertyMap.getInstance().getProperty("config", "mail_id"), PropertyMap.getInstance().getProperty("config", "mail_pw"));
      }
    });
    String debug = PropertyMap.getInstance().getProperty("config", "mail_debug");
    if (debug != null && "true".equals(debug.toLowerCase())) {
      session.setDebug(true);
    }
    message = new MimeMessage(session);
    message.setContent(new MimeMultipart());
    mp = (Multipart) message.getContent();
  }

  public void setAddress(String from, String to, String cc, String bcc) throws AddressException, MessagingException {
    message.setFrom(getAddress(from));
    Address[] addresses;
    if ((addresses = getAddresses(to)) != null) {
      message.addRecipients(Message.RecipientType.TO, addresses);
    }
    if ((addresses = getAddresses(cc)) != null) {
      message.addRecipients(Message.RecipientType.CC, addresses);
    }
    if ((addresses = getAddresses(bcc)) != null) {
      message.addRecipients(Message.RecipientType.BCC, addresses);
    }
  }

  public void setSubject(String title) throws MessagingException {
    message.setSubject(title);
  }

  public void setContent(String html) throws MessagingException {
    mp.addBodyPart(getContents(html));
  }

  public void setAttachment(String path) throws MessagingException {
    mp.addBodyPart(getFileAttachment(path));
  }

  // it need Test
  public void setAttachment(String fileName, String mimeType, byte[] data) throws MessagingException {
    mp.addBodyPart(geteAttachment(fileName, mimeType, data));
  }

  public void setImage(String path, String cid) throws MessagingException {
    mp.addBodyPart(getImage(path, cid));
  }

  public void setImage(String fileName, String mimeType, byte[] data, String cid) throws MessagingException {
    mp.addBodyPart(getImage(fileName, mimeType, data, cid));
  }

  public void sendMail() throws MessagingException {
    Transport.send(message);
  }

  private Address getAddress(String address) throws AddressException {
    return new InternetAddress(address);
  }

  /**
   * separation word is comma(,)
   * 
   * @param addresses
   * @return
   * @throws AddressException
   */
  private Address[] getAddresses(String addresses) throws AddressException {
    if (addresses == null) {
      return null;
    }
    String[] array = addresses.split(",");
    Address[] ret = new Address[array.length];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = getAddress(array[i]);
    }
    return ret;
  }

  private BodyPart getContents(String html) throws MessagingException {
    BodyPart mbp = new MimeBodyPart();
    mbp.setContent(html, "text/html; charset=utf-8");
    return mbp;
  }

  private BodyPart getFileAttachment(String filename) throws MessagingException {
    BodyPart mbp = new MimeBodyPart();
    File file = new File(filename);
    DataSource source = new FileDataSource(file);
    mbp.setDataHandler(new DataHandler(source));
    mbp.setDisposition(Part.ATTACHMENT);
    mbp.setFileName(file.getName());
    return mbp;
  }

  // It need attach to convert from binary data.
  // mimeType - https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types
  private BodyPart geteAttachment(String fileName, String mimeType, byte[] data) throws MessagingException {
    BodyPart mbp = new MimeBodyPart();
    DataSource source = new ByteArrayDataSource(data, mimeType);
    mbp.setDataHandler(new DataHandler(source));
    mbp.setDisposition(Part.ATTACHMENT);
    mbp.setFileName(fileName);
    return mbp;
  }

  private BodyPart getImage(String filename, String contextId) throws MessagingException {
    BodyPart mbp = getFileAttachment(filename);
    if (contextId != null) {
      mbp.setHeader("Content-ID", "<" + contextId + ">");
    }
    return mbp;
  }

  private BodyPart getImage(String fileName, String mimeType, byte[] data, String contextId) throws MessagingException {
    BodyPart mbp = geteAttachment(fileName, mimeType, data);
    if (contextId != null) {
      mbp.setHeader("Content-ID", "<" + contextId + ">");
    }
    return mbp;
  }
}

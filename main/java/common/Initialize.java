package common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Logger;

@WebServlet("/resetMaster")
public class Initialize extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private Logger logger = null;

  public Initialize() {
    super();
  }

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    String path = getServletContext().getRealPath("/");
    LocalPaths.getInstance().setWebRootPath(path);
    /* DEBUG */
    // LocalPaths.getInstance().setWebRootPath("/home/sy/Desktop");
    LocalPaths.getInstance().setWebRootPath("d:\\property");
    /* DEBUG */
    if (logger == null) {
      logger = LoggerManager.getLogger(Initialize.class);
    }
    logger.info("The program is start.");
    FactoryDao.initializeMaster();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    logger.info("Master reset");
    logger.info("Remote host - " + request.getRemoteHost());
    logger.info("Remote port - " + request.getRemotePort());
    logger.info("Remote addr - " + request.getRemoteAddr());
    logger.info("Remote user - " + request.getRemoteUser());
    FactoryDao.resetMaster();
  }

}

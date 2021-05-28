package common;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;

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
    LocalPaths.getInstance().setWebRootPath("d:\\property");
    /* DEBUG */
    if (logger == null) {
      logger = LoggerManager.getLogger(Initialize.class);
    }
    logger.info("The program is start.");
    FactoryDao.initializeMaster();
  }
}

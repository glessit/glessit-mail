package com.glessit.microservice.mail;

import com.glessit.microservice.mail.service.IGlessitMailBuilder;
import com.glessit.microservice.mail.utils.LogUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

import static com.glessit.microservice.mail.MailType.SIMPLE_MAIL;
import static com.glessit.microservice.mail.utils.LogUtil.loggerConsumer;
import static java.lang.String.format;
import static com.glessit.microservice.mail.utils.LogUtil.LogItem;


/**
 * Main servlet
 * Handle request and send email
 */
public class GlessitMailServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(GlessitMailServlet.class);

    private final IGlessitMailBuilder glessitMailBuilder;

    // should we execute by servlet-container
    public GlessitMailServlet() {
        /*glessitMailBuilder = new GlessitMailBuilder()
                .add()
        ;*/
        glessitMailBuilder = null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loggerConsumer.accept(new LogUtil.LogItem("Servlet is starting ..", Level.DEBUG), LOG);
        loggerConsumer.accept(
                new LogUtil.LogItem(format("Access token is %s", config.getInitParameter("access-token")), Level.DEBUG)
                , LOG);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loggerConsumer.accept(new LogItem("Start reading request ..",  Level.DEBUG), LOG);

        /* extract params. from request */

        // represent mail type for sending, i.e SimpleMail, HTMLMail, ImageMail and so on
//        Optional<String> mailType = Optional
//                .ofNullable(request.getParameter("mail_type"))
//                .orElseThrow((Supplier<Throwable>) () -> new ServletException("Parameter mail_type is null!"));
//        Optional<String> accessToken = Optional.ofNullable(request.getParameter("access_token"));

//        loggerConsumer.accept(
//                new LogItem(
//                        format("MailType is [%s]. AccessToken is [%s]", mailType.get(), accessToken.get()),
//                        Level.DEBUG
//                ),
//                LOG
//        );

        /* Build sender by params. */







       /* switch (MailType.valueOf()) {
            case SIMPLE_MAIL:
//                debugLogConsumer.accept("Start sending simple mail ..");
                break;
            default:
//                debugLogConsumer.accept("");

        }*/

        IOUtils.toString(
                request.getReader()
        );
    }
}

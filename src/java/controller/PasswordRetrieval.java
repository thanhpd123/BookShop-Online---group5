/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.AccountDAO;
import verifyEmail.SendEmail;

public class PasswordRetrieval extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("PasswordRetrieval.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("txtemail");
        AccountDAO accdao = new AccountDAO();
        HttpSession session = request.getSession(true);
        ArrayList<Account> ListEmail = accdao.getEmailList();
        boolean emailFound = false;
        String accemail = null;
        for (Account acc : ListEmail) {
            if (acc.getEmail().equals(email)) {
                emailFound = true;
                accemail = acc.getEmail();

                String serverAddress = request.getServerName() + ":" + request.getServerPort();
                String verifyLink = "";
                // Get the current timestamp
                long createtime = System.currentTimeMillis();
                verifyLink = "http://" + serverAddress + request.getContextPath() + "/verifyemail?key=" + email + "&checktime=" + createtime;
                String sender = "mobiledot1002@gmail.com";
                String passsender = "frjb appb cpim zsbt";
                String recipient = email;
                String subject = "Confirm Your Account";
                String content = "<!DOCTYPE html>\n"
                        + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                        + "\n"
                        + "<head>\n"
                        + "  <title></title>\n"
                        + "  <!--[if !mso]><!-- -->\n"
                        + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                        + "  <!--<![endif]-->\n"
                        + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                        + "  <style type=\"text/css\">\n"
                        + "    #outlook a {\n"
                        + "      padding: 0;\n"
                        + "    }\n"
                        + "    .ReadMsgBody {\n"
                        + "      width: 100%;\n"
                        + "    }\n"
                        + "    .ExternalClass {\n"
                        + "      width: 100%;\n"
                        + "    }\n"
                        + "    .ExternalClass * {\n"
                        + "      line-height: 100%;\n"
                        + "    }\n"
                        + "    body {\n"
                        + "      margin: 0;\n"
                        + "      padding: 0;\n"
                        + "      -webkit-text-size-adjust: 100%;\n"
                        + "      -ms-text-size-adjust: 100%;\n"
                        + "    }\n"
                        + "    table,\n"
                        + "    td {\n"
                        + "      border-collapse: collapse;\n"
                        + "      mso-table-lspace: 0pt;\n"
                        + "      mso-table-rspace: 0pt;\n"
                        + "    }\n"
                        + "  </style>\n"
                        + "  <!--[if !mso]><!-->\n"
                        + "  <style type=\"text/css\">\n"
                        + "    @media only screen and (max-width:480px) {\n"
                        + "      @-ms-viewport {\n"
                        + "        width: 320px;\n"
                        + "      }\n"
                        + "      @viewport {\n"
                        + "        width: 320px;\n"
                        + "      }\n"
                        + "    }\n"
                        + "  </style>\n"
                        + "  <!--<![endif]-->\n"
                        + "  <!--[if mso]><xml>  <o:OfficeDocumentSettings>    <o:AllowPNG/>    <o:PixelsPerInch>96</o:PixelsPerInch>  </o:OfficeDocumentSettings></xml><![endif]-->\n"
                        + "  <!--[if lte mso 11]><style type=\"text/css\">  .outlook-group-fix {    width:100% !important;  }</style><![endif]-->\n"
                        + "  <!--[if !mso]><!-->\n"
                        + "  <link href=\"https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                        + "  <style type=\"text/css\">\n"
                        + "    @import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&display=swap');\n"
                        + "  </style>\n"
                        + "  <!--<![endif]-->\n"
                        + "  <style type=\"text/css\">\n"
                        + "    @media only screen and (max-width:595px) {\n"
                        + "      .container {\n"
                        + "        width: 100% !important;\n"
                        + "      }\n"
                        + "      .button {\n"
                        + "        display: block !important;\n"
                        + "        width: auto !important;\n"
                        + "      }\n"
                        + "    }\n"
                        + "  </style>\n"
                        + "</head>\n"
                        + "<body style=\"font-family: 'Inter', sans-serif; background: #E5E5E5;\">\n"
                        + "  <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" bgcolor=\"#F6FAFB\">\n"
                        + "    <tbody>\n"
                        + "      <tr>\n"
                        + "        <td valign=\"top\" align=\"center\">\n"
                        + "          <table class=\"container\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n"
                        + "            <tbody>\n"
                        + "              <tr>\n"
                        + "                <td style=\"padding:48px 0 30px 0; text-align: center; font-size: 24px; color: #1C1C1C;;font-weight:bold \">\n"
                        + "                  Book Shop\n"
                        + "                </td>\n"
                        + "              </tr>\n"
                        + "              <tr>\n"
                        + "                <td class=\"main-content\" style=\"padding: 48px 30px 40px; color: #000000;\" bgcolor=\"#ffffff\">\n"
                        + "                  <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n"
                        + "                    <tbody style=\"padding: 40px 0\">\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 24px 0; font-size: 20px; line-height: 150%; font-weight: bold; color: #6F6F6F; letter-spacing: 0.01em;text-align:center\">\n"
                        + "                          Hello user!                       </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 10px 0; font-size: 24px; line-height: 150%; font-weight: 400; color: #6F6F6F; letter-spacing: 0.01em; text-align:center\">\n"
                        + "                         Please click the \"Set New Password\" button to replace your forgotten password!\n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 16px 0; font-size: 14px; line-height: 150%; font-weight: 400; color: #6F6F6F; letter-spacing: 0.01em;\">\n"
                        + "                          \n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 24px 0;text-align:center;\">\n"
                        + "                          <a class=\"button\" href=\"" + verifyLink + "\" title=\"Reset Password\" style=\"width: 50%; background: #D19C97;; text-decoration: none; display: inline-block; padding: 10px 0; color: #212529; font-size: 14px; line-height: 21px; text-align: center; font-weight: bold; border-radius: 7px;\">Set New Password</a>\n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 16px 0; font-size: 14px; line-height: 150%; font-weight: 400;letter-spacing: 0.01em;\">\n"
                        + "                         \n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 8px;\">\n"
                        + "                          <a style=\"display: flex; justify-content: space-between; align-items: center; padding: 28px 24px; border-radius: 4px; text-decoration: none;\" href=\"{{get_started_link}}\">\n"
                        + "<span style=\"width: 90%; font-size: 14px; line-height: 150%; font-weight: bold; letter-spacing: 0.01em;\"></span>\n"
                        + "                            <span style=\"width: 10%; float: right;\">\n"
                        + "                            </span>\n"
                        + "                          </a>\n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 60px;\">\n"
                        + "                          <a style=\"display: flex; padding: 28px 24px; border-radius: 4px;  text-decoration: none;\" href=\"{{onboarding_video_link}}\">\n"
                        + "                            <span style=\"width: 90%; font-size: 14px; line-height: 150%; font-weight: bold; letter-spacing: 0.01em;\"></span>\n"
                        + "                            <span style=\"width: 10%; float: right;\">\n"
                        + "                            </span>\n"
                        + "                          </a>\n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"padding: 0 0 16px;\">\n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                      <tr>\n"
                        + "                        <td style=\"font-size: 14px; line-height: 170%; font-weight: 400; letter-spacing: 0.01em;\">\n"
                        + "                        </td>\n"
                        + "                      </tr>\n"
                        + "                    </tbody>\n"
                        + "                  </table>\n"
                        + "                </td>\n"
                        + "              </tr>\n"
                        + "              <tr>\n"
                        + "                <td style=\"padding: 24px 0 48px; font-size: 0px;\">\n"
                        + "                  <!--[if mso | IE]>      <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">        <tr>          <td style=\"vertical-align:top;width:300px;\">      <![endif]-->\n"
                        + "                  <div class=\"outlook-group-fix\" style=\"padding: 0 0 20px 0; vertical-align: top; display: inline-block; text-align: center; width:100%;\">\n"
                        + "                    <span style=\"padding: 0; font-size: 11px; line-height: 15px; font-weight: normal;\">\n"
                        + "                    </div>\n"
                        + "                  </div>\n"
                        + "                  <!--[if mso | IE]>      </td></tr></table>      <![endif]-->\n"
                        + "                </td>\n"
                        + "              </tr>\n"
                        + "            </tbody>\n"
                        + "          </table>\n"
                        + "        </td>\n"
                        + "      </tr>\n"
                        + "    </tbody>\n"
                        + "  </table>\n"
                        + "</body>\n"
                        + "</html>";
                SendEmail.sendEmail(recipient, subject, content, sender, passsender);
            }
        }
        if (emailFound) {
            session.setAttribute("accemail", accemail);
            request.setAttribute("mess", "The password reset link has been sent! Please check your .DOT Moblie email to access the link.");
        } else {
            request.setAttribute("error", "Email does not exist. Please check your email again to reset your password.");
        }
        request.getRequestDispatcher("PasswordRetrieval.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

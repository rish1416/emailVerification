/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allpckage;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class EmailVerificationController extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
 
        int userotp = Integer.parseInt(request.getParameter("otp"));
        HttpSession session = request.getSession();
        int otp = (int) session.getAttribute("otp");
        System.out.println(otp);
        DetailsDTO details = (DetailsDTO)session.getAttribute("details");
        RequestDispatcher rd;
        if(userotp==otp)
        {
            try
            {
            int register = RegistrationDAO.registerUser(details);
            request.setAttribute("register", register);
            rd = request.getRequestDispatcher("welcome.jsp");
            rd.forward(request, response);
            }
            catch(Exception e)
            {
                System.out.println("Exception is :"+e.getMessage());
            }
        }
        else
        {
            rd = request.getRequestDispatcher("invalidotp.html");
            rd.forward(request, response);
        }
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}



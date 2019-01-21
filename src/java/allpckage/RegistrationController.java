/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allpckage;

import java.io.IOException;
import java.io.PrintWriter;
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
public class RegistrationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        
        DetailsDTO details = new DetailsDTO();
        details.setEmail(email);
        details.setMobile(mobile);
        details.setPassword(password);
        
        RequestDispatcher rd;
        
        try
        {
            boolean result = RegistrationDAO.validateUser(email);
            if(result==false)
            {
               Email sendMail = new Email();
               int otp = sendMail.otpGenerator();
               int value =  sendMail.sendMail(email,otp);
                request.setAttribute("value", value);
                request.setAttribute("otp", otp);
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("details",details);
                rd = request.getRequestDispatcher("register.jsp");
                rd.forward(request, response);
                
            }
            else
            {
                rd = request.getRequestDispatcher("alreadyRegistered.html");
                rd.forward(request, response);
            }
         
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

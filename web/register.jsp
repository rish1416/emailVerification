<%@page import="allpckage.DetailsDTO"%>
<%
    int value = (int) request.getAttribute("value");
    int otp = (int) request.getAttribute("otp");

    if(value==0)
    {
         session.setAttribute("otp",otp);
         request.getRequestDispatcher("enterotp.html").forward(request, response);
    }
    else
    {
        out.print("<h2>Oops! Something went wrong");
    }
%>
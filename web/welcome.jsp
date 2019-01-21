<%
    int register = (int) request.getAttribute("register");
    String email = (String) session.getAttribute("email");
    if(register==1)
    {
        out.print("Welcome "+email);
    }
    else
    {
        out.print("Sorry, Cannot register. Try again.");
    }

%>

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Forum.AccountBean;
import Forum.EntityClasses.Users;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nils
 */
public class AccontSettingsServlet extends HttpServlet {

    @EJB
    private AccountBean accountBean;

    @PersistenceContext
    EntityManager em;
    
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
        
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            Users u = accountBean.getUser((String)request.getSession().getAttribute("username"));
            if(username != null && !username.equals("") && !username.equals(u.getUsername())){
                System.out.println("Changin attribute");
                request.getSession().setAttribute("username",username);
            }
            accountBean.modifyAccount(u.getId(), username, password, email);
            response.sendRedirect("modifyAccount.xhtml");
        }
        catch(Exception e){
            System.out.println("EXCEPTION");}
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
                System.out.println("in doPost servlet");
            response.sendRedirect("modifyAccount.xhtml");
   
    }
    
     public Users getUser(String name){
        return (Users)em.createNamedQuery("Users.findByUsername").setParameter("username", name).getSingleResult();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author rizz4
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (request.getParameter("action") != null && request.getParameter("action").equals("logout")) {
            request.getSession().invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }else if(request.getSession().getAttribute("username") != null){
           getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        }else{
           getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> listItems;
        //Make array list if it hasnt existed yet
        if(request.getSession().getAttribute("listItems") == null){
            listItems = new ArrayList<>();
        }else{
            listItems = (ArrayList<String>)request.getSession().getAttribute("listItems");
        }
        //register 
        if (request.getParameter("action").equals("register")) {
            if (request.getParameter("username") != null && !request.getParameter("username").isEmpty()) {
                request.getSession().setAttribute("username", request.getParameter("username"));
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            }
          //add button
        } else if (request.getParameter("action").equals("add"))  {
            if(request.getParameter("item") != null && !request.getParameter("item").isEmpty()){
                listItems.add(request.getParameter("item"));//add item
                request.getSession().setAttribute("listItems", listItems);//set attribute 
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }else{
                request.setAttribute("message", "Enter Name of the item to add on the list");
                getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            }
        }else if(request.getParameter("action").equals("delete")){
            String itemDel = request.getParameter("item");
            ArrayList<String> tmp = new ArrayList<>(listItems);
            for (String i : tmp) {
                if (itemDel.equals(i)) {
                    listItems.remove(listItems.indexOf(i));
                    break;
                }
        }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    

    }
}

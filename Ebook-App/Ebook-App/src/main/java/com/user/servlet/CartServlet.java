 package com.user.servlet;

import java.io.IOException;

import com.DAO.BookDAOImpl;
import com.DAO.CartDAO;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			
			int bid=Integer.parseInt(req.getParameter("bid").trim());
			int uid=Integer.parseInt(req.getParameter("uid").trim());
			
			BookDAOImpl dao=new BookDAOImpl(DBConnect.getConn());
			BookDtls b=dao.getBookById(bid);
			
			Cart c=new Cart();
			c.setBid(bid);
			c.setUserId(uid);
			c.setBookName(b.getBookName());
			c.setAuthor(b.getAuthor());
			c.setPrice(Double.parseDouble(b.getPrice()));
			c.setTotalPrice(Double.parseDouble(b.getPrice()));
			
			CartDAOImpl dao2=new CartDAOImpl(DBConnect.getConn());
			boolean f=dao2.addCart(c);
			
			HttpSession session=req.getSession();
			
			if(f)
			{
				session.setAttribute("addCart", "Book Added to cart");
				resp.sendRedirect("all_new_book.jsp");
				//System.out.println("Add Cart Sucess");
			}else {
				session.setAttribute("failed", "Something Wrong on Server");
				resp.sendRedirect("all_new_book.jsp");
				//System.out.println("Not added to cart");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

 }
	
}
//=================================================##################=====================================


//package com.user.servlet;
//
//import java.io.IOException;
//
//import com.DAO.BookDAOImpl;
//import com.DAO.CartDAOImpl;
//import com.DB.DBConnect;
//import com.entity.BookDtls;
//import com.entity.Cart;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/cart")
//public class CartServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    
//        try {
//            // Debug statements
//            System.out.println("Received request to add to cart");
//
//            // Fetch and print parameters
//            String bidParam = req.getParameter("bid");
//            String uidParam = req.getParameter("uid");
//            System.out.println("Bid parameter: " + bidParam);
//            System.out.println("Uid parameter: " + uidParam);
//
//            int bid = Integer.parseInt(bidParam.trim()); // Use trim() to remove extra spaces
//            int uid = Integer.parseInt(uidParam.trim());
//
//            // Debug statement for parameters
//            System.out.println("Parsed bid: " + bid);
//            System.out.println("Parsed uid: " + uid);
//
//            // Database operations
//            BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
//            BookDtls b = dao.getBookById(bid);
//
//            Cart c = new Cart();
//            c.setBid(bid);
//            c.setUserId(uid);
//            c.setBookName(b.getBookName());
//            c.setAuthor(b.getAuthor());
//            c.setPrice(Double.parseDouble(b.getPrice()));
//            c.setTotalPrice(Double.parseDouble(b.getPrice()));
//
//            CartDAOImpl dao2 = new CartDAOImpl(DBConnect.getConn());
//            boolean f = dao2.addCart(c);
//
//            if (f) {
//                System.out.println("Add Cart Success");
//            } else {
//                System.out.println("Not added to cart");
//            }
//            
//        } catch (Exception e) {
//            // Print stack trace for debugging
//            e.printStackTrace();
//        }
//    }
//}

package com.lawrence.servlet;

import com.lawrence.util.MessageUtil;
import com.lawrence.util.SignUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Lawrence on 9/26/2016.
 */
@SuppressWarnings("serial")
public class CoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
            System.out.println("微信服务验证成功！");
        }
        out.close();
        out = null;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Map<String,String> request_data= MessageUtil.parseXML(request);
            System.out.println(request_data.get("MsgType")+" "+request_data.get("Content"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

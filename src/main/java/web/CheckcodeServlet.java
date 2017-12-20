package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ͼ
		//����һ���ڴ�ӳ�����(����)
		BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
		//��û���
		Graphics g  = image.getGraphics();
		//����������ɫ
		g.setColor(new Color(255,255,255));
		//�ñʸ��������ñ�����ɫ
		g.fillRect(0, 0, 80, 30);
		//���¸�������һ���µ���ɫ
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//��������(�������ͣ���񣬴�С)
		g.setFont(new Font(null,Font.ITALIC,24));
		//��ͼ(number)
		String number =getCode(5);
		//��number�󶨵�session������
		HttpSession session =  req.getSession();
		session.setAttribute("number", number);
		
		g.drawString(number, 1, 25);
		//��һЩ������
		for(int i =0;i<5;i++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		/*
		 * 	��ͼƬѹ��Ȼ�����(���͸������)
		 * */
		//����MIME����(��������������������ص���ͼƬ)
		res.setContentType("image/jpeg");
		//���ͼƬ��Ӧ��ʹ���ֽ������
		OutputStream os = res.getOutputStream();
		//ѹ��ͼƬ�����
		javax.imageio.ImageIO.write(image, "jpeg", os);
		os.close();
	}

	
	private String getCode(int size) {
		String number = "";
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		Random r = new Random();
		for(int i=0;i<size;i++){
			number +=chars.charAt(r.nextInt(chars.length()));
		}
		return number;
	}

}

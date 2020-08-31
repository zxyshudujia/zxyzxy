package com.oracle.gdms.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.oracle.gdms.entity.GoodsEntity;
import com.oracle.gdms.service.GoodsService;
import com.oracle.gdms.util.Factory;

/**
 * Servlet implementation class GoodsUpdate
 */
@WebServlet("/admin/goods/update.php")
public class GoodsUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����ajax�����Ĳ���
		request.setCharacterEncoding("UTF-8");
		
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		String spec = request.getParameter("spec");
		String price = request.getParameter("price");
		String amount = request.getParameter("amount");
		
		//�����ݷ�װ��һ��Goods���󣬴���ҵ���
		GoodsEntity goods = new GoodsEntity();
		goods.setGoodsid(Integer.parseInt(goodsid));//ת����
		goods.setName(name);
		goods.setSpec(spec);
		int i = price.indexOf("��");
		price = price.substring(i+1);
		goods.setPrice(Float.parseFloat(price));
		goods.setAmount(Float.parseFloat(amount));
		
//		System.out.println(goodsid);
//		System.out.println(name);
//		System.out.println(spec);
//		System.out.println(price);
//		System.out.println(amount);
		
		GoodsService service = (GoodsService) Factory.getInstacance().getObject("goods.service.impl");
		int count = service.update(goods); //���ظ��µĽ������Ӱ���������
		response.setContentType("application/json;charset=UTF-8");//֪ͨ����һ��json����
		JSONObject j = new JSONObject();
		if( count > 0 ) {
			j.put("code", 0);
			j.put("msg", "��Ʒ�޸ĳɹ�");			
		} else {
			j.put("code", 10882);
			j.put("msg", "��Ʒ�޸�ʧ��");
		}
		response.getWriter().print(j.toJSONString());		
	}

}

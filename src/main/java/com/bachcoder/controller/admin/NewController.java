package com.bachcoder.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bachcoder.constant.SystemConstant;
import com.bachcoder.model.NewModel;
import com.bachcoder.paging.PageRequest;
import com.bachcoder.paging.Pageble;
import com.bachcoder.service.INewService;
import com.bachcoder.sort.Sorter;
import com.bachcoder.utils.FormUtil;


@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {

	/**
	 * 
	 */
	@Inject 
	private INewService newService;
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewModel model = FormUtil.toMapper(NewModel.class, request);
		
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
		model.setListResult(newService.findAll(pageble));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem())) ;//tong so item / tổng số item trên 1 page
		request.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

	}

}

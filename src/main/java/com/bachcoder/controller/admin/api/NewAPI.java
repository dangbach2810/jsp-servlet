package com.bachcoder.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bachcoder.model.NewModel;
import com.bachcoder.service.INewService;
import com.bachcoder.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet{

	/**
	 * 
	 */
	@Inject 
	private INewService newService;
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();//create json by string and nguoc lai with readValue() method
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.of(request.getReader()).toMapper(NewModel.class);//kĩ thuật hay
		newModel = newService.save(newModel); //save model to db and get model again
		//response to client
		mapper.writeValue(response.getOutputStream(), newModel);
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModelUpdate = HttpUtil.of(request.getReader()).toMapper(NewModel.class);//kĩ thuật hay
		newModelUpdate = newService.update(newModelUpdate); //save model to db and get model again
		//response to client
		mapper.writeValue(response.getOutputStream(), newModelUpdate);
	}
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewModel newModel = HttpUtil.of(request.getReader()).toMapper(NewModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel newModel = HttpUtil.of(req.getReader()).toMapper(NewModel.class);
		newModel = newService.findOne(newModel.getId());
		mapper.writeValue(resp.getOutputStream(), newModel);
		
	}
	

	

}

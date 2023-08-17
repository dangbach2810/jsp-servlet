<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
		<div class="main-content">
			<form action="<c:url value='/admin-new'/>" id="formSubmit"
				method="get">
				<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li><i class="ace-icon fa fa-home home-icon"></i> <a
								href="#">Trang chủ</a></li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">

								<div class="widget-box table-filter">
									<div class="table-btn-controls">
										<div class="pull-right tableTools-container">
											<div class="dt-buttons btn-overlap btn-group">
												<a flag="info"
													class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
													data-toggle="tooltip" title='Thêm bài viết'
													href='<c:url value="/admin-new?type=edit"/>'> <span>
														<i class="fa fa-plus-circle bigger-110 purple"></i>
												</span>
												</a>
												<button id="btnDelete" type="button"
													class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
													data-toggle="tooltip" title='Xóa bài viết'>
													<span> <i class="fa fa-trash-o bigger-110 pink"></i>
													</span>
												</button>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
												<thead>
													<tr>
														<!-- <th><input type="checkbox" id="checkAll"></th> -->
														<th>Tên bài viết</th>
														<th>Mô tả ngắn</th>
														<th>Thao tác</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td>${item.title}</td>
															<td>${item.shortDescription}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<ul class="pagination" id="pagination"></ul>
											<input type="hidden" id ="page" name="page" value=""/>
											<input type="hidden" id ="maxPageItem" name="maxPageItem" value=""/>
											<input type="hidden" id ="sortName" name="sortName" value=""/>
											<input type="hidden" id ="sortBy" name="sortBy" value=""/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		var totalPages  = ${model.totalPage}
		var currenPage  = ${model.page}
		var limit = 2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,//tong so button
				visiblePages : 10,//limit button
				startPage : currenPage,
				onPageClick : function(event, page) {
					if(currenPage != page){
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('title');	
						$('#sortBy').val('desc');	
						$('#formSubmit').submit();	
					}
					
				}
			})
		});
	</script>
</body>
</html>